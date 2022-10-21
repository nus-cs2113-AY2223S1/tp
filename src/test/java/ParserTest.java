import static org.junit.jupiter.api.Assertions.*;

import org.junit.Before;
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
        assertEquals(patient, MainMenuState.PATIENT);
        MainMenuState visits = p.mainMenuParser("2");
        assertEquals(visits, MainMenuState.VISIT);
        MainMenuState prescription = p.mainMenuParser("3");
        assertEquals(prescription, MainMenuState.PRESCRIPTION);
        MainMenuState exit = p.mainMenuParser("bye");
        assertEquals(exit, MainMenuState.EXIT);
        MainMenuState invalid = p.mainMenuParser("82");
        assertEquals(invalid, MainMenuState.INVALID);
    }

    @Test
    public void checkPatientParserState() {
        SubMenuState backMain = p.patientParser("main");
        assertEquals(backMain, SubMenuState.BACK_TO_MAIN);
        SubMenuState exit = p.patientParser("bye");
        assertEquals(exit, SubMenuState.EXIT);

    }
    @Test
    public void checkPatientParserCommands() {

        //ADD COMMAND
        SubMenuState add = p.patientParser("add n/Ria g/F d/08-11-2001 i/400TXF");
        assertEquals(add, SubMenuState.IN_SUB_MENU);
        assertEquals(patientList.getTotalNumberofPatients(), 1);

        //EDIT COMMAND
        p.patientParser("edit i/400TXF g/M");
        Patient patient = patientList.findPatient("400TXF");
        assertEquals(patient.getGender(), "M");

        p.patientParser("edit i/400TXF n/Vora vora");
        patient = patientList.findPatient("400TXF");
        assertEquals(patient.getName(), "Vora vora");

        p.patientParser("edit i/400TXF d/09-09-1978");
        patient = patientList.findPatient("400TXF");
        assertEquals(patient.getBirthDate(), "09-09-1978");

        //RETRIEVE COMMAND - checks that it retrieves without errors
        assertEquals(p.patientParser("retrieve i/400TXF"), SubMenuState.IN_SUB_MENU);
    }


}
