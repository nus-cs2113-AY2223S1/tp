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
        prescriptionsList.add("S123456G", "Ventolin", "4 mg", "6");

        assertFalse(prescriptionsList.isEmpty());
    }

    @Test
    void viewAll_emptyList_printNoPrescriptionsMessage() {
        PrescriptionList prescriptionsList = new PrescriptionList();
        prescriptionsList.viewAll();

        assertEquals("There are currently no prescriptions in the record.", OUTPUT_STREAM.toString().trim());
    }

    @Test
    void viewAll_onePrescription_printOnePrescription() {
        PrescriptionList prescriptionsList = new PrescriptionList();
        prescriptionsList.add("S123456G", "Ventolin", "4 mg", "6");
        prescriptionsList.viewAll();

        String expectedOutput = "Prescription 1" + System.lineSeparator()
                + "ID: S123456G" + System.lineSeparator()
                + "Medicine: Ventolin" + System.lineSeparator()
                + "Dosage: 4 mg" + System.lineSeparator()
                + "Time Interval: once every 6 hours" + System.lineSeparator()
                + "Status: Active";

        assertEquals(expectedOutput, OUTPUT_STREAM.toString().trim());
    }

    @Test
    void viewAll_threePrescriptions_printThreePrescriptions() {
        PrescriptionList prescriptionsList = new PrescriptionList();
        prescriptionsList.add("S123456G", "Ventolin", "4 mg", "6");
        prescriptionsList.add("S987655G", "Losartan Potassium", "50 mg", "24", false);
        prescriptionsList.add("K323232J", "Hydrocodone-Acetaminophen", "1 capsule", "6");
        prescriptionsList.viewAll();

        String expectedOutput = "Prescription 1" + System.lineSeparator()
                + "ID: S123456G" + System.lineSeparator()
                + "Medicine: Ventolin" + System.lineSeparator()
                + "Dosage: 4 mg" + System.lineSeparator()
                + "Time Interval: once every 6 hours" + System.lineSeparator()
                + "Status: Active" + System.lineSeparator()
                + System.lineSeparator()
                + "Prescription 2" + System.lineSeparator()
                + "ID: S987655G" + System.lineSeparator()
                + "Medicine: Losartan Potassium" + System.lineSeparator()
                + "Dosage: 50 mg" + System.lineSeparator()
                + "Time Interval: once every 24 hours" + System.lineSeparator()
                + "Status: Inactive" + System.lineSeparator()
                + System.lineSeparator()
                + "Prescription 3" + System.lineSeparator()
                + "ID: K323232J" + System.lineSeparator()
                + "Medicine: Hydrocodone-Acetaminophen" + System.lineSeparator()
                + "Dosage: 1 capsule" + System.lineSeparator()
                + "Time Interval: once every 6 hours" + System.lineSeparator()
                + "Status: Active"
                ;

        assertEquals(expectedOutput, OUTPUT_STREAM.toString().trim());
    }

    @Test
    void edit_editAllDetails_allDetailsUpdated() {
        PrescriptionList prescriptionsList = new PrescriptionList();
        prescriptionsList.add("S123456G", "Ventolin", "4 mg", "6");
        prescriptionsList.edit(1, "Losartan Potassium", "50 mg", "4");

        String expectedOutput = "The prescription's details has been modified! Here are the new prescription details!"
                + System.lineSeparator()
                + "ID: S123456G" + System.lineSeparator()
                + "Medicine: Losartan Potassium" + System.lineSeparator()
                + "Dosage: 50 mg" + System.lineSeparator()
                + "Time Interval: once every 4 hours" + System.lineSeparator()
                + "Status: Active";

        assertEquals(expectedOutput, OUTPUT_STREAM.toString().trim());
    }

    @Test
    void edit_editSomeDetails_someDetailsUpdated() {
        PrescriptionList prescriptionsList = new PrescriptionList();
        prescriptionsList.add("S123456G", "Ventolin", "4 mg", "6");
        prescriptionsList.edit(1, "", "50 mg", null);

        String expectedOutput = "The prescription's details has been modified! Here are the new prescription details!"
                + System.lineSeparator()
                + "ID: S123456G" + System.lineSeparator()
                + "Medicine: Ventolin" + System.lineSeparator()
                + "Dosage: 50 mg" + System.lineSeparator()
                + "Time Interval: once every 6 hours" + System.lineSeparator()
                + "Status: Active";

        assertEquals(expectedOutput, OUTPUT_STREAM.toString().trim());
    }

    @Test
    void edit_indexOutOfRange_printErrorMessage() {
        PrescriptionList prescriptionsList = new PrescriptionList();
        prescriptionsList.add("S123456G", "Ventolin", "4 mg", "6");
        prescriptionsList.edit(3, "", "50 mg", null);

        String expectedOutput = "The index number is out of range. Try again.";

        assertEquals(expectedOutput, OUTPUT_STREAM.toString().trim());
    }
}
