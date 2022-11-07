package seedu.duke.timetable;

import org.junit.jupiter.api.Test;
import seedu.duke.exceptions.InvalidModuleException;
import seedu.duke.exceptions.InvalidUniversityException;
import seedu.duke.ui.Ui;
import seedu.duke.university.University;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class LessonTest {
    @Test
    void testCreateLesson() throws InvalidUniversityException {
        University university = new University("Stanford University", "USA");
        try {
            Lesson newLesson = new Lesson("CS229", "Machine Learning", "5", university,"Tuesday", "09:00", "11:00");
            assertEquals("Stanford University", newLesson.getUniversity().getName());
            assertEquals("USA", newLesson.getUniversity().getCountry());
            assertEquals("5", newLesson.getCredit());
            assertEquals("CS229", newLesson.getCode());
            assertEquals("Machine Learning", newLesson.getTitle());
            assertEquals("tuesday", newLesson.getDay());
            assertEquals("09:00", newLesson.getStartTime());
            assertEquals("11:00", newLesson.getEndTime());
        } catch (InvalidModuleException e) {
            Ui.printExceptionMessage(e);
        }
    }
}
