import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class VisitListTest {
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
    void isEmptyList() {
        VisitList visitList = new VisitList();
        visitList.viewAll(ui);
        assertEquals("There are no visits in the system right now!", OUTPUT_STREAM.toString().trim());
    }

    @Test
    void isEmpty_EmptyVisitList_returnTrue() {
        VisitList visitList = new VisitList();
        assertTrue(visitList.isEmpty());
    }

    @Test
    void isEmpty_EmptyVisitList_returnFalse() {
        VisitList visitList = new VisitList();
        visitList.addVisit(ui, "S123", "23-05-2020","13:00","flu");
        assertFalse(visitList.isEmpty());
    }

    @Test
    void addVisit() {
        VisitList visitList = new VisitList();
        visitList.addVisit(ui, "S123", "23-05-2020","13:00","flu");
        assertEquals(1,visitList.getTotalVisits());
        visitList.addVisit(ui, "S234", "13-05-2021","15:00","fever");
        assertEquals(2,visitList.getTotalVisits());
    }

    @Test
    void addVisit_withoutReason() {
        VisitList visitList = new VisitList();
        visitList.addVisit(ui, "S123", "23-05-2020","13:00");
        assertEquals(1,visitList.getTotalVisits());
    }

    @Test
    void viewVisit_emptyList() {
        VisitList visitList = new VisitList();
        visitList.viewVisit(ui,1);
        assertEquals("There are no visits in the system right now!", OUTPUT_STREAM.toString().trim());
    }

    @Test
    void viewVisit_illegalIndex() {
        VisitList visitList = new VisitList();
        visitList.addVisit(ui, "S123", "23-05-2020","13:00");
        visitList.viewVisit(ui,-1);
        String expectedOutput =
                "You have added a visit!" + System.lineSeparator()
                        + "\t____________________________________________________________"
                        + System.lineSeparator()
                        + "#1)"
                        + System.lineSeparator()
                        + "\tID: S123" + System.lineSeparator()
                        + "\tDate: 23-05-2020" + System.lineSeparator()
                        + "\tTime: 13:00" + System.lineSeparator()
                        + "\tReason: NIL" + System.lineSeparator()
                        + "\t____________________________________________________________"
                        + System.lineSeparator()
                        + "There is no such visit in the system with index -1!" + System.lineSeparator()
                        + "\t____________________________________________________________";
        assertEquals(expectedOutput, OUTPUT_STREAM.toString().trim());
    }

    @Test
    void viewVisit_outOfBoundIndex() {
        VisitList visitList = new VisitList();
        visitList.addVisit(ui, "S123", "23-05-2020","13:00");
        visitList.viewVisit(ui,4);
        String expectedOutput =
                "You have added a visit!" + System.lineSeparator()
                        + "\t____________________________________________________________"
                        + System.lineSeparator()
                        + "#1)"
                        + System.lineSeparator()
                        + "\tID: S123" + System.lineSeparator()
                        + "\tDate: 23-05-2020" + System.lineSeparator()
                        + "\tTime: 13:00" + System.lineSeparator()
                        + "\tReason: NIL" + System.lineSeparator()
                        + "\t____________________________________________________________"
                        + System.lineSeparator()
                        + "There is no such visit in the system with index 4!" + System.lineSeparator()
                        + "\t____________________________________________________________";
        assertEquals(expectedOutput, OUTPUT_STREAM.toString().trim());
    }

    @Test
    void viewPatient_emptyList() {
        VisitList visitList = new VisitList();
        visitList.viewPatient(ui,"S123");
        assertEquals("There are no visits in the system right now!", OUTPUT_STREAM.toString().trim());
    }

    @Test
    void viewPatient_noVisitRecordForPatient() {
        VisitList visitList = new VisitList();
        visitList.addVisit(ui, "S123", "23-05-2020","13:00");
        String expectedOutput =
                "You have added a visit!" + System.lineSeparator()
                + "\t____________________________________________________________"
                + System.lineSeparator()
                + "#1)"
                + "\tID: S123" + System.lineSeparator()
                + "\tDate: 23-05-2020" + System.lineSeparator()
                + "\tTime: 13:00" + System.lineSeparator()
                + "\tReason: NIL" + System.lineSeparator()
                + "\t____________________________________________________________"
                + System.lineSeparator()
                + "Sorry, Patient with ID S124 has no visits recorded yet!"
                + System.lineSeparator()
                + "\t____________________________________________________________";
        visitList.viewPatient(ui,"S124");
        assertEquals(expectedOutput, OUTPUT_STREAM.toString().trim());
    }

    @Test
    void viewPatient_withVisits() {
        VisitList visitList = new VisitList();
        visitList.addVisit(ui, "S123", "23-05-2020","13:00", "flu");
        visitList.addVisit(ui, "S123", "24-05-2020","11:00", "fever");
        visitList.viewPatient(ui, "S123");
        String expectedOutput =
                "You have added a visit!" + System.lineSeparator()
                + "\t____________________________________________________________"
                + System.lineSeparator()
                + "1#)"
                + System.lineSeparator()
                + "\tID: S123" + System.lineSeparator()
                + "\tDate: 23-05-2020" + System.lineSeparator()
                + "\tTime: 13:00" + System.lineSeparator()
                + "\tReason: flu" + System.lineSeparator()
                + "\t____________________________________________________________" + System.lineSeparator()
                + "You have added a visit!" + System.lineSeparator()
                + "\t____________________________________________________________"
                + System.lineSeparator()
                + "#2)"
                + System.lineSeparator()
                + "\tID: S123" + System.lineSeparator()
                + "\tDate: 24-05-2020" + System.lineSeparator()
                + "\tTime: 11:00" + System.lineSeparator()
                + "\tReason: fever" + System.lineSeparator()
                + "\t____________________________________________________________" + System.lineSeparator()
                + "Here are the list of visits for Patient with ID: S123" + System.lineSeparator()
                + "\t____________________________________________________________"
                + System.lineSeparator()
                + "VisitIndex #1)" + System.lineSeparator()
                + "\tID: S123" + System.lineSeparator()
                + "\tDate: 23-05-2020" + System.lineSeparator()
                + "\tTime: 13:00" + System.lineSeparator()
                + "\tReason: flu" + System.lineSeparator()
                + "\t____________________________________________________________" + System.lineSeparator()
                + "VisitIndex #2)" + System.lineSeparator()
                + "\tID: S123" + System.lineSeparator()
                + "\tDate: 24-05-2020" + System.lineSeparator()
                + "\tTime: 11:00" + System.lineSeparator()
                + "\tReason: fever" + System.lineSeparator()
                + "\t____________________________________________________________";
        assertEquals(expectedOutput, OUTPUT_STREAM.toString().trim());
    }

    @Test
    void viewAll_noVisits() {
        VisitList visitList = new VisitList();
        visitList.viewAll(ui);
        assertEquals("There are no visits in the system right now!", OUTPUT_STREAM.toString().trim());
    }

    @Test
    void viewAll_withVisits() {
        VisitList visitList = new VisitList();
        visitList.addVisit(ui, "S123", "23-05-2020","13:00", "flu");
        visitList.addVisit(ui, "S124", "24-05-2020","11:00", "fever");
        visitList.viewAll(ui);
        String expectedOutput =
                "You have added a visit!" + System.lineSeparator()
                        + "\t____________________________________________________________"
                        + System.lineSeparator()
                        + "#1)"
                        + System.lineSeparator()
                        + "\tID: S123" + System.lineSeparator()
                        + "\tDate: 23-05-2020" + System.lineSeparator()
                        + "\tTime: 13:00" + System.lineSeparator()
                        + "\tReason: flu" + System.lineSeparator()
                        + "\t____________________________________________________________" + System.lineSeparator()
                        + "You have added a visit!" + System.lineSeparator()
                        + "\t____________________________________________________________"
                        + System.lineSeparator()
                        + "#2)"
                        + System.lineSeparator()
                        + "\tID: S124" + System.lineSeparator()
                        + "\tDate: 24-05-2020" + System.lineSeparator()
                        + "\tTime: 11:00" + System.lineSeparator()
                        + "\tReason: fever" + System.lineSeparator()
                        + "\t____________________________________________________________" + System.lineSeparator()
                        + "Here are the list of visits in the system:" + System.lineSeparator()
                        + "\t____________________________________________________________"
                        + System.lineSeparator()
                        + "VisitIndex #1)" + System.lineSeparator()
                        + "\tID: S123" + System.lineSeparator()
                        + "\tDate: 23-05-2020" + System.lineSeparator()
                        + "\tTime: 13:00" + System.lineSeparator()
                        + "\tReason: flu" + System.lineSeparator()
                        + "\t____________________________________________________________" + System.lineSeparator()
                        + "VisitIndex #2)" + System.lineSeparator()
                        + "\tID: S124" + System.lineSeparator()
                        + "\tDate: 24-05-2020" + System.lineSeparator()
                        + "\tTime: 11:00" + System.lineSeparator()
                        + "\tReason: fever" + System.lineSeparator()
                        + "\t____________________________________________________________";
        assertEquals(expectedOutput, OUTPUT_STREAM.toString().trim());
    }

    @Test
    void editReason() {
        VisitList visitList = new VisitList();
        visitList.addVisit(ui, "S123", "23-05-2020","13:00");
        visitList.editReason(ui, 1, "fever");
        String expectedOutput =
                "You have added a visit!" + System.lineSeparator()
                        + "\t____________________________________________________________"
                        + System.lineSeparator()
                        + "#1)"
                        + System.lineSeparator()
                        + "\tID: S123" + System.lineSeparator()
                        + "\tDate: 23-05-2020" + System.lineSeparator()
                        + "\tTime: 13:00" + System.lineSeparator()
                        + "\tReason: NIL" + System.lineSeparator()
                        + "\t____________________________________________________________"
                        + System.lineSeparator()
                        + "You have edited reason for the visit. Here's the updated visit!" + System.lineSeparator()
                        + "\t____________________________________________________________"
                        + System.lineSeparator()
                        + "#1)"
                        + System.lineSeparator()
                        + "\tID: S123" + System.lineSeparator()
                        + "\tDate: 23-05-2020" + System.lineSeparator()
                        + "\tTime: 13:00" + System.lineSeparator()
                        + "\tReason: fever" + System.lineSeparator()
                        + "\t____________________________________________________________";
        assertEquals(expectedOutput, OUTPUT_STREAM.toString().trim());
    }

    @Test
    void deleteReason() {
        VisitList visitList = new VisitList();
        visitList.addVisit(ui, "S123", "23-05-2020","13:00", "flu");
        visitList.deleteReason(ui, 1);
        String expectedOutput =
                "You have added a visit!" + System.lineSeparator()
                        + "\t____________________________________________________________"
                        + System.lineSeparator()
                        + "#1)"
                        + System.lineSeparator()
                        + "\tID: S123" + System.lineSeparator()
                        + "\tDate: 23-05-2020" + System.lineSeparator()
                        + "\tTime: 13:00" + System.lineSeparator()
                        + "\tReason: flu" + System.lineSeparator()
                        + "\t____________________________________________________________"
                        + System.lineSeparator()
                        + "You have deleted the reason for the visit. Here's the updated visit!"
                        + System.lineSeparator()
                        + "\t____________________________________________________________"
                        + System.lineSeparator()
                        + "#1)"
                        + System.lineSeparator()
                        + "\tID: S123" + System.lineSeparator()
                        + "\tDate: 23-05-2020" + System.lineSeparator()
                        + "\tTime: 13:00" + System.lineSeparator()
                        + "\tReason: NIL" + System.lineSeparator()
                        + "\t____________________________________________________________";
        assertEquals(expectedOutput, OUTPUT_STREAM.toString().trim());
    }
}
