package seedu.duke.timetable;

import org.junit.jupiter.api.Test;
import seedu.duke.exceptions.TimetableNotFoundException;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class TimetableManagerTest {
    @Test
    void deleteTimetable_deleteNonExistentTimetable() {
        TimetableManager manager = new TimetableManager();
        assertThrows(TimetableNotFoundException.class, () -> manager.deleteTimetable("Stanford University"));
    }

    @Test
    void deleteTimetable_printNonExistentTimetable() {
        TimetableManager manager = new TimetableManager();
        assertThrows(TimetableNotFoundException.class, () -> manager.printTimetable("Stanford University"));
    }
}
