import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

class PrescriptionListTest {
    private static final PrintStream SYSTEM_OUT = System.out;
    private static final ByteArrayOutputStream OUTPUT_STREAM = new ByteArrayOutputStream();
    private static final UI ui = new UI();

    private static final String LINE = "\t____________________________________________________________";
    private static final String LINE_NEWLINE = LINE + System.lineSeparator();
    private static final String PRESCRIPTION_STRING_1 = "\tID: S123456G" + System.lineSeparator()
            + "\tMedicine: Ventolin" + System.lineSeparator()
            + "\tDosage: 4 mg" + System.lineSeparator()
            + "\tTime Interval: Once every 6 hours" + System.lineSeparator()
            + "\tStatus: Active" + System.lineSeparator();
    private static final String PRESCRIPTION_STRING_2 = "\tID: S987655G" + System.lineSeparator()
            + "\tMedicine: Losartan Potassium" + System.lineSeparator()
            + "\tDosage: 50 mg" + System.lineSeparator()
            + "\tTime Interval: Once every day" + System.lineSeparator()
            + "\tStatus: Active" + System.lineSeparator();
    private static final String PRESCRIPTION_STRING_3 = "\tID: K323232J" + System.lineSeparator()
            + "\tMedicine: Hydrocodone-Acetaminophen" + System.lineSeparator()
            + "\tDosage: 1 capsule" + System.lineSeparator()
            + "\tTime Interval: 3 times a day" + System.lineSeparator()
            + "\tStatus: Active" + System.lineSeparator();
    private static final String ADD_MESSAGE = "You have added a prescription!" + System.lineSeparator();
    private static final String EDIT_MESSAGE = "You have edited the prescription!" + System.lineSeparator();

    private static final String PRESCRIPTION_STRING_1_EDIT_SOME_DETAILS = "\tID: S123456G" + System.lineSeparator()
            + "\tMedicine: Ventolin" + System.lineSeparator()
            + "\tDosage: 50 mg" + System.lineSeparator()
            + "\tTime Interval: Once every 6 hours" + System.lineSeparator()
            + "\tStatus: Active" + System.lineSeparator();
    private static final String PRESCRIPTION_STRING_1_EDIT_ALL_DETAILS = "\tID: S123456G" + System.lineSeparator()
            + "\tMedicine: Losartan Potassium" + System.lineSeparator()
            + "\tDosage: 50 mg" + System.lineSeparator()
            + "\tTime Interval: 4 times a day" + System.lineSeparator()
            + "\tStatus: Active" + System.lineSeparator();

    @BeforeEach
    void setUp() {
        // Switch the system output to byte array output stream
        System.setOut(new PrintStream(OUTPUT_STREAM));
    }

    @AfterEach
    void tearDown() {
        // Switch the byte array output stream back to system output
        System.setOut(SYSTEM_OUT);
        OUTPUT_STREAM.reset();
    }

    @Test
    void isEmpty_emptyList_returnTrue() {
        PrescriptionList prescriptionsList = new PrescriptionList();

        assertTrue(prescriptionsList.isEmpty());
    }

    @Test
    void isEmpty_nonEmptyList_returnFalse() {
        PrescriptionList prescriptionsList = new PrescriptionList();
        prescriptionsList.add(ui, "S123456G", "Ventolin", "4 mg", "Once every 6 hours");

        assertFalse(prescriptionsList.isEmpty());
    }

    @Test
    void add_duplicatePrescription_reject2ndAdd() {
        PrescriptionList prescriptionsList = new PrescriptionList();
        prescriptionsList.add(ui, "S123456G", "Ventolin", "4 mg", "Once every 6 hours");
        prescriptionsList.add(ui, "S123456G", "Ventolin", "4 mg", "Once every 6 hours");

        String expectedOutput = ADD_MESSAGE
                + LINE_NEWLINE
                + "Prescription #1" + System.lineSeparator()
                + PRESCRIPTION_STRING_1
                + LINE_NEWLINE
                + "The prescription is already existing." + System.lineSeparator()
                + LINE_NEWLINE
                + "Prescription #1" + System.lineSeparator()
                + PRESCRIPTION_STRING_1
                + LINE;

        assertEquals(expectedOutput, OUTPUT_STREAM.toString().trim());
    }

    @Test
    void viewAll_emptyList_printNoPrescriptionsMessage() {
        PrescriptionList prescriptionsList = new PrescriptionList();
        prescriptionsList.viewAll(ui);

        assertEquals("There are currently no prescriptions in the record.", OUTPUT_STREAM.toString().trim());
    }

    @Test
    void viewAll_onePrescription_printOnePrescription() {
        PrescriptionList prescriptionsList = new PrescriptionList();
        prescriptionsList.add(ui, "S123456G", "Ventolin", "4 mg", "Once every 6 hours");
        prescriptionsList.viewAll(ui);

        String expectedOutput = ADD_MESSAGE
                + LINE_NEWLINE
                + "Prescription #1" + System.lineSeparator()
                + PRESCRIPTION_STRING_1
                + LINE_NEWLINE
                + "Here are all the prescriptions:" + System.lineSeparator()
                + LINE_NEWLINE
                + "Prescription #1" + System.lineSeparator()
                + PRESCRIPTION_STRING_1
                + LINE;

        assertEquals(expectedOutput, OUTPUT_STREAM.toString().trim());
    }

    @Test
    void viewAll_threePrescriptions_printThreePrescriptions() {
        PrescriptionList prescriptionsList = new PrescriptionList();
        prescriptionsList.add(ui, "S123456G", "Ventolin", "4 mg", "Once every 6 hours");
        prescriptionsList.add(ui, "S987655G", "Losartan Potassium", "50 mg", "Once every day");
        prescriptionsList.add(ui, "K323232J", "Hydrocodone-Acetaminophen", "1 capsule", "3 times a day");
        prescriptionsList.viewAll(ui);

        String expectedOutput = ADD_MESSAGE
                + LINE_NEWLINE
                + "Prescription #1" + System.lineSeparator()
                + PRESCRIPTION_STRING_1
                + LINE_NEWLINE
                + ADD_MESSAGE
                + LINE_NEWLINE
                + "Prescription #2" + System.lineSeparator()
                + PRESCRIPTION_STRING_2
                + LINE_NEWLINE
                + ADD_MESSAGE
                + LINE_NEWLINE
                + "Prescription #3" + System.lineSeparator()
                + PRESCRIPTION_STRING_3
                + LINE_NEWLINE
                + "Here are all the prescriptions:" + System.lineSeparator()
                + LINE_NEWLINE
                + "Prescription #1" + System.lineSeparator()
                + PRESCRIPTION_STRING_1
                + LINE_NEWLINE
                + "Prescription #2" + System.lineSeparator()
                + PRESCRIPTION_STRING_2
                + LINE_NEWLINE
                + "Prescription #3" + System.lineSeparator()
                + PRESCRIPTION_STRING_3
                + LINE;

        assertEquals(expectedOutput, OUTPUT_STREAM.toString().trim());
    }

    @Test
    void edit_editAllDetails_allDetailsUpdated() {
        PrescriptionList prescriptionsList = new PrescriptionList();
        prescriptionsList.add(ui, "S123456G", "Ventolin", "4 mg", "Once every 6 hours");
        prescriptionsList.edit(ui, 1, "Losartan Potassium", "50 mg",
                "4 times a day");

        String expectedOutput = ADD_MESSAGE
                + LINE_NEWLINE
                + "Prescription #1" + System.lineSeparator()
                + PRESCRIPTION_STRING_1
                + LINE_NEWLINE
                + EDIT_MESSAGE
                + LINE_NEWLINE
                + "Prescription #1" + System.lineSeparator()
                + PRESCRIPTION_STRING_1_EDIT_ALL_DETAILS
                + LINE;

        assertEquals(expectedOutput, OUTPUT_STREAM.toString().trim());
    }

    @Test
    void edit_editSomeDetails_someDetailsUpdated() {
        PrescriptionList prescriptionsList = new PrescriptionList();
        prescriptionsList.add(ui, "S123456G", "Ventolin", "4 mg", "Once every 6 hours");
        prescriptionsList.edit(ui, 1, "", "50 mg", "");

        String expectedOutput = ADD_MESSAGE
                + LINE_NEWLINE
                + "Prescription #1" + System.lineSeparator()
                + PRESCRIPTION_STRING_1
                + LINE_NEWLINE
                + EDIT_MESSAGE
                + LINE_NEWLINE
                + "Prescription #1" + System.lineSeparator()
                + PRESCRIPTION_STRING_1_EDIT_SOME_DETAILS
                + LINE;

        assertEquals(expectedOutput, OUTPUT_STREAM.toString().trim());
    }

    @Test
    void edit_indexOutOfRange_printErrorMessage() {
        PrescriptionList prescriptionsList = new PrescriptionList();
        prescriptionsList.add(ui, "S123456G", "Ventolin", "4 mg", "Once every 6 hours");
        prescriptionsList.edit(ui, 3, "", "50 mg", "");

        String expectedOutput = ADD_MESSAGE
                + LINE_NEWLINE
                + "Prescription #1" + System.lineSeparator()
                + PRESCRIPTION_STRING_1
                + LINE_NEWLINE
                + "The index number is out of range. Try again.";

        assertEquals(expectedOutput, OUTPUT_STREAM.toString().trim());
    }

    @Test
    void edit_duplicatePrescription_rejectEdit() {
        PrescriptionList prescriptionsList = new PrescriptionList();
        prescriptionsList.add(ui, "S123456G", "Ventolin", "4 mg", "Once every 6 hours");
        prescriptionsList.add(ui, "S123456G", "Ventolin", "50 mg", "Once every 6 hours");
        prescriptionsList.edit(ui, 2, "", "4 mg", "");

        String expectedOutput = ADD_MESSAGE
                + LINE_NEWLINE
                + "Prescription #1" + System.lineSeparator()
                + PRESCRIPTION_STRING_1
                + LINE_NEWLINE
                + ADD_MESSAGE
                + LINE_NEWLINE
                + "Prescription #2" + System.lineSeparator()
                + PRESCRIPTION_STRING_1_EDIT_SOME_DETAILS
                + LINE_NEWLINE
                + "The prescription is already existing." + System.lineSeparator()
                + LINE_NEWLINE
                + "Prescription #1" + System.lineSeparator()
                + PRESCRIPTION_STRING_1
                + LINE;

        assertEquals(expectedOutput, OUTPUT_STREAM.toString().trim());
    }
}
