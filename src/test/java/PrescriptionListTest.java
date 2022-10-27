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

        String expectedOutput = "You have added a prescription!" + System.lineSeparator()
                + "\t____________________________________________________________" + System.lineSeparator()
                + "#1)" + System.lineSeparator()
                + "\tID: S123456G" + System.lineSeparator()
                + "\tMedicine: Ventolin" + System.lineSeparator()
                + "\tDosage: 4 mg" + System.lineSeparator()
                + "\tTime Interval: Once every 6 hours" + System.lineSeparator()
                + "\tStatus: Active" + System.lineSeparator()
                + "\t____________________________________________________________" + System.lineSeparator()
                + "Here are all the prescriptions:" + System.lineSeparator()
                + "\t____________________________________________________________" + System.lineSeparator()
                + "Prescription #1" + System.lineSeparator()
                + "\tID: S123456G" + System.lineSeparator()
                + "\tMedicine: Ventolin" + System.lineSeparator()
                + "\tDosage: 4 mg" + System.lineSeparator()
                + "\tTime Interval: Once every 6 hours" + System.lineSeparator()
                + "\tStatus: Active" + System.lineSeparator()
                + "\t____________________________________________________________";

        assertEquals(expectedOutput, OUTPUT_STREAM.toString().trim());
    }

    @Test
    void viewAll_threePrescriptions_printThreePrescriptions() {
        PrescriptionList prescriptionsList = new PrescriptionList();
        prescriptionsList.add(ui, "S123456G", "Ventolin", "4 mg", "Once every 6 hours");
        prescriptionsList.add(ui, "S987655G", "Losartan Potassium", "50 mg", "Once every day", false);
        prescriptionsList.add(ui, "K323232J", "Hydrocodone-Acetaminophen", "1 capsule", "3 times a day");
        prescriptionsList.viewAll(ui);

        String expectedOutput =
                "You have added a prescription!" + System.lineSeparator()
                + "\t____________________________________________________________" + System.lineSeparator()
                + "#1)" + System.lineSeparator()
                + "\tID: S123456G" + System.lineSeparator()
                + "\tMedicine: Ventolin" + System.lineSeparator()
                + "\tDosage: 4 mg" + System.lineSeparator()
                + "\tTime Interval: Once every 6 hours" + System.lineSeparator()
                + "\tStatus: Active" + System.lineSeparator()
                + "\t____________________________________________________________" + System.lineSeparator()
                + "You have added a prescription!" + System.lineSeparator()
                + "\t____________________________________________________________" + System.lineSeparator()
                + "#2)" + System.lineSeparator()
                + "\tID: S987655G" + System.lineSeparator()
                + "\tMedicine: Losartan Potassium" + System.lineSeparator()
                + "\tDosage: 50 mg" + System.lineSeparator()
                + "\tTime Interval: Once every day" + System.lineSeparator()
                + "\tStatus: Inactive" + System.lineSeparator()
                + "\t____________________________________________________________" + System.lineSeparator()
                + "You have added a prescription!" + System.lineSeparator()
                + "\t____________________________________________________________" + System.lineSeparator()
                + "#3)" + System.lineSeparator()
                + "\tID: K323232J" + System.lineSeparator()
                + "\tMedicine: Hydrocodone-Acetaminophen" + System.lineSeparator()
                + "\tDosage: 1 capsule" + System.lineSeparator()
                + "\tTime Interval: 3 times a day" + System.lineSeparator()
                + "\tStatus: Active" + System.lineSeparator()
                + "\t____________________________________________________________" + System.lineSeparator()
                + "Here are all the prescriptions:" + System.lineSeparator()
                + "\t____________________________________________________________" + System.lineSeparator()
                + "Prescription #1" + System.lineSeparator()
                + "\tID: S123456G" + System.lineSeparator()
                + "\tMedicine: Ventolin" + System.lineSeparator()
                + "\tDosage: 4 mg" + System.lineSeparator()
                + "\tTime Interval: Once every 6 hours" + System.lineSeparator()
                + "\tStatus: Active" + System.lineSeparator()
                + "\t____________________________________________________________" + System.lineSeparator()
                + "Prescription #2" + System.lineSeparator()
                + "\tID: S987655G" + System.lineSeparator()
                + "\tMedicine: Losartan Potassium" + System.lineSeparator()
                + "\tDosage: 50 mg" + System.lineSeparator()
                + "\tTime Interval: Once every day" + System.lineSeparator()
                + "\tStatus: Inactive" + System.lineSeparator()
                + "\t____________________________________________________________" + System.lineSeparator()
                + "Prescription #3" + System.lineSeparator()
                + "\tID: K323232J" + System.lineSeparator()
                + "\tMedicine: Hydrocodone-Acetaminophen" + System.lineSeparator()
                + "\tDosage: 1 capsule" + System.lineSeparator()
                + "\tTime Interval: 3 times a day" + System.lineSeparator()
                + "\tStatus: Active" + System.lineSeparator()
                + "\t____________________________________________________________";

        assertEquals(expectedOutput, OUTPUT_STREAM.toString().trim());
    }

    @Test
    void edit_editAllDetails_allDetailsUpdated() {
        PrescriptionList prescriptionsList = new PrescriptionList();
        prescriptionsList.add(ui, "S123456G", "Ventolin", "4 mg", "Once every 6 hours");
        prescriptionsList.edit(ui, 1, "Losartan Potassium", "50 mg",
                "4 times a day");

        String expectedOutput =
                "You have added a prescription!" + System.lineSeparator()
                + "\t____________________________________________________________" + System.lineSeparator()
                + "#1)" + System.lineSeparator()
                + "\tID: S123456G" + System.lineSeparator()
                + "\tMedicine: Ventolin" + System.lineSeparator()
                + "\tDosage: 4 mg" + System.lineSeparator()
                + "\tTime Interval: Once every 6 hours" + System.lineSeparator()
                + "\tStatus: Active" + System.lineSeparator()
                + "\t____________________________________________________________" + System.lineSeparator()
                + "You have edited the prescription!" + System.lineSeparator()
                + "\t____________________________________________________________" + System.lineSeparator()
                + "#1)" + System.lineSeparator()
                + "\tID: S123456G" + System.lineSeparator()
                + "\tMedicine: Losartan Potassium" + System.lineSeparator()
                + "\tDosage: 50 mg" + System.lineSeparator()
                + "\tTime Interval: 4 times a day" + System.lineSeparator()
                + "\tStatus: Active" + System.lineSeparator()
                + "\t____________________________________________________________";

        assertEquals(expectedOutput, OUTPUT_STREAM.toString().trim());
    }

    @Test
    void edit_editSomeDetails_someDetailsUpdated() {
        PrescriptionList prescriptionsList = new PrescriptionList();
        prescriptionsList.add(ui, "S123456G", "Ventolin", "4 mg", "Once every 6 hours");
        prescriptionsList.edit(ui, 1, "", "50 mg", "");

        String expectedOutput =
                "You have added a prescription!" + System.lineSeparator()
                + "\t____________________________________________________________" + System.lineSeparator()
                + "#1)" + System.lineSeparator()
                + "\tID: S123456G" + System.lineSeparator()
                + "\tMedicine: Ventolin" + System.lineSeparator()
                + "\tDosage: 4 mg" + System.lineSeparator()
                + "\tTime Interval: Once every 6 hours" + System.lineSeparator()
                + "\tStatus: Active" + System.lineSeparator()
                + "\t____________________________________________________________" + System.lineSeparator()
                + "You have edited the prescription!" + System.lineSeparator()
                + "#1)" + System.lineSeparator()
                + "\t____________________________________________________________" + System.lineSeparator()
                + "\tID: S123456G" + System.lineSeparator()
                + "\tMedicine: Ventolin" + System.lineSeparator()
                + "\tDosage: 50 mg" + System.lineSeparator()
                + "\tTime Interval: Once every 6 hours" + System.lineSeparator()
                + "\tStatus: Active" + System.lineSeparator()
                + "\t____________________________________________________________";

        assertEquals(expectedOutput, OUTPUT_STREAM.toString().trim());
    }

    @Test
    void edit_indexOutOfRange_printErrorMessage() {
        PrescriptionList prescriptionsList = new PrescriptionList();
        prescriptionsList.add(ui, "S123456G", "Ventolin", "4 mg", "Once every 6 hours");
        prescriptionsList.edit(ui, 3, "", "50 mg", "");

        String expectedOutput =
                "You have added a prescription!" + System.lineSeparator()
                + "\t____________________________________________________________" + System.lineSeparator()
                + "#1)" + System.lineSeparator()
                + "\tID: S123456G" + System.lineSeparator()
                + "\tMedicine: Ventolin" + System.lineSeparator()
                + "\tDosage: 4 mg" + System.lineSeparator()
                + "\tTime Interval: Once every 6 hours" + System.lineSeparator()
                + "\tStatus: Active" + System.lineSeparator()
                + "\t____________________________________________________________" + System.lineSeparator()
                + "The index number is out of range. Try again.";

        assertEquals(expectedOutput, OUTPUT_STREAM.toString().trim());
    }
}
