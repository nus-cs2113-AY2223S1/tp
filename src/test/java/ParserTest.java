import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


public class ParserTest {

    private static Parser p;

    private static PatientList patientList;

    private static VisitList visitList;

    private static PrescriptionList presList;

    private static UI ui;

    @BeforeEach
    public void setUp() {
        patientList = new PatientList();
        visitList = new VisitList();
        presList = new PrescriptionList();
        ui = new UI();
        p = new Parser(patientList, visitList, presList, ui);
    }

    @Test
    public void checkMainMenu() {
        MainMenuState patient = p.mainMenuParser("1");
        assertEquals(MainMenuState.PATIENT, patient);
        MainMenuState visits = p.mainMenuParser("2");
        assertEquals(MainMenuState.VISIT, visits);
        MainMenuState prescription = p.mainMenuParser("3");
        assertEquals(MainMenuState.PRESCRIPTION, prescription);
        MainMenuState exit = p.mainMenuParser("bye");
        assertEquals(MainMenuState.EXIT, exit);
        MainMenuState invalid = p.mainMenuParser("82");
        assertEquals(MainMenuState.INVALID, invalid);
    }

    @Test
    public void checkPatientParserState() {
        SubMenuState backMain = p.patientParser("main");
        assertEquals(SubMenuState.BACK_TO_MAIN, backMain);
        SubMenuState exit = p.patientParser("bye");
        assertEquals(SubMenuState.EXIT, exit);
    }
    @Test
    public void checkPatientParserCommands() {

        //ADD COMMAND
        SubMenuState add = p.patientParser("add n/Ria g/F d/08-11-2001 i/400TXF");
        assertEquals(add, SubMenuState.IN_SUB_MENU);
        assertEquals(1, patientList.getTotalNumberofPatients());

        //EDIT COMMAND
        p.patientParser("edit i/400TXF g/M");
        Patient patient = patientList.findPatient("400TXF");
        assertEquals("M", patient.getGender());

        p.patientParser("edit i/400TXF n/Vora vora");
        patient = patientList.findPatient("400TXF");
        assertEquals("Vora vora", patient.getName());

        p.patientParser("edit i/400TXF d/09-09-1978");
        patient = patientList.findPatient("400TXF");
        assertEquals("09-09-1978", patient.getBirthDate());

        //RETRIEVE COMMAND - checks that it retrieves without errors
        assertEquals(SubMenuState.IN_SUB_MENU, p.patientParser("retrieve i/400TXF"));
    }

    @Test
    public void checkVisitParserState() {
        SubMenuState backMain = p.visitParser("main");
        assertEquals(SubMenuState.BACK_TO_MAIN, backMain);
        SubMenuState exit = p.visitParser("bye");
        assertEquals(SubMenuState.EXIT, exit);
    }
    @Test
    public void checkVisitParserCommands() {

        p.visitParser("add i/400TXF d/08-11-2001 t/08:00 r/checkup");

        //Assert that a visit isn't added because the patient doesn't exist
        assertEquals(0, visitList.getTotalVisits());

        //ADD COMMAND
        p.patientParser("add n/Ria g/F d/08-11-2001 i/400TXF");
        SubMenuState add = p.visitParser("add i/400TXF d/08-11-2001 t/08:00 r/checkup");
        assertEquals(add, SubMenuState.IN_SUB_MENU);
        assertEquals(1, visitList.getTotalVisits());

        //EDIT COMMAND - check that it works
        p.patientParser("edit i/400TXF r/not a checkup");
        assertEquals(1, visitList.getTotalVisits());
    }

    @Test
    public void checkPresParserState() {
        SubMenuState backMain = p.prescriptionParser("main");
        assertEquals(SubMenuState.BACK_TO_MAIN, backMain);
        SubMenuState exit = p.prescriptionParser("bye");
        assertEquals(SubMenuState.EXIT, exit);
    }

    @Test
    public void checkPrescriptionParserCommands() {

        p.visitParser("add i/400TXF d/08-11-2001 t/08:00 r/checkup");

        //Assert that a visit isn't added because the patient doesn't exist
        assertTrue(presList.isEmpty());

        //ADD COMMAND
        p.patientParser("add n/Ria g/F d/08-11-2001 i/400TXF");
        SubMenuState add = p.prescriptionParser("add n/Drug d/10 mg t/every 3 hours");
        assertEquals(add, SubMenuState.IN_SUB_MENU);
//        assertEquals(1, presList.);

        //EDIT COMMAND - check that it works
        p.prescriptionParser("edit i/400TXF r/not a checkup");
        //  assertEquals(1, presList.);
    }

    @Test
    public void incorrectPatientInput() {

        //Incorrect name
        p.patientParser("add n/Ria Vora goes to the store g/F d/08-11-2001 i/4005");
        assertTrue(patientList.isEmpty());

        //Incorrect gender
        p.patientParser("add n/Ria Vora g/X d/08-11-2001 i/4005");
        assertTrue(patientList.isEmpty());

        p.patientParser("add n/Ria Vora g/X3490sdf d/08-11-2001 i/4005");
        assertTrue(patientList.isEmpty());

        //Incorrect date of birth
        p.patientParser("add n/Ria Vora g/F d/sept 9 i/4005");
        assertTrue(patientList.isEmpty());

        //Incorrect id
        p.patientParser("add n/Ria Vora g/F d/08-11-2001 i/so much to id");
        assertTrue(patientList.isEmpty());

        //Wrong order
        p.patientParser("add n/Ria Vora d/08-11-2001 i/400XTF g/F");
        assertTrue(patientList.isEmpty());

        //Random input after
        p.patientParser("add n/Ria Vora g/F d/08-11-2001 i/400XTF 3495ireowkdflj");
        assertTrue(patientList.isEmpty());

        p.patientParser("add n/Ria g/F d/08-11-2001 i/400TXF");
        assertEquals(1, patientList.getTotalNumberofPatients());

        //Id doesn't exist
        p.patientParser("edit i/4009 d/09-11-2001");
        Patient patient = patientList.findPatient("400TXF");
        assertEquals("08-11-2001", patient.getBirthDate());

        //Can't edit two at once
        p.patientParser("edit i/400TXF d/09-11-2001 g/M");
        patient = patientList.findPatient("400TXF");
        assertEquals("08-11-2001", patient.getBirthDate());
        assertEquals("F", patient.getGender());

        //Random input after
        p.patientParser("edit i/4009 70w9dcnv");
        patient = patientList.findPatient("400TXF");
        assertEquals("08-11-2001", patient.getBirthDate());
    }

    @Test
    public void correctPatientInput() {

        //extra spaces
        p.patientParser("add n/ Ria Vora      g/ F d/ 08-11-2001 i/   4005");
        assertEquals(1, patientList.getTotalNumberofPatients());

        //lowercase input
        p.patientParser("add n/Samantha g/f d/20-11-2001 i/4008");
        assertEquals(2, patientList.getTotalNumberofPatients());

    }

    @Test
    public void correctVisitInput() {

        p.patientParser("add n/Ria Vora g/F d/08-11-2001 i/4005");
        assertEquals(1, patientList.getTotalNumberofPatients());

        //extra spaces
        p.visitParser("add i/ 4005    d/ 08-11-2001 t/   08:00 r/new thing");
//        assertEquals(1, patientList.getTotalNumberofPatients());
        assertFalse(visitList.isEmpty());

        //with or without reason
        p.visitParser("add i/4005 d/09-12-2022 t/09:00");
        assertFalse(visitList.isEmpty());
//        assertEquals(2, patientList.getTotalNumberofPatients());

    }

}
