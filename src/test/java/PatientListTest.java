import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertEquals;


public class PatientListTest {
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
        PatientList patientList = new PatientList();
        assertTrue(patientList.isEmpty());
    }

    @Test
    void isNotEmpty_nonEmptyList_returnFalse() {
        PatientList patientList = new PatientList();
        patientList.addPatient("Jack", "12-10-2001", "M", "T1234");
        assertFalse(patientList.isEmpty());
    }

    @Test
    void listPatients_emptyList() {
        PatientList patientList = new PatientList();
        patientList.listPatients();
        assertEquals("There are no patients in the system right now!", OUTPUT_STREAM.toString().trim());
    }

    @Test
    void addPatient() {
        PatientList patientList = new PatientList();
        patientList.addPatient("Jack", "12-10-2001", "M", "T1234");
        assertEquals(1, patientList.getTotalNumberofPatients());
        patientList.addPatient("Jill", "10-10-2001", "F", "T1244");
        patientList.addPatient("James", "11-10-2001", "M", "T1334");
        assertEquals(3, patientList.getTotalNumberofPatients());
    }

    @Test
    void findPatient_PatientExists() {
        PatientList patientList = new PatientList();
        patientList.addPatient("Jack", "12-10-2001", "M", "T1234");
        patientList.addPatient("Jill", "10-10-2001", "F", "T1244");
        Patient patient = patientList.findPatient("T1244");
        assertEquals("Jill", patient.getName());
    }

    @Test
    void findPatient_emptyList() {
        PatientList patientList = new PatientList();
        assertNull(patientList.findPatient("Jack"));
    }

    @Test
    void findPatient_PatientDoesNotExist() {
        PatientList patientList = new PatientList();
        patientList.addPatient("Jack", "12-10-2001", "M", "T1234");
        patientList.addPatient("Jill", "10-10-2001", "F", "T1244");
        assertNull(patientList.findPatient("Michael"));
    }
}
