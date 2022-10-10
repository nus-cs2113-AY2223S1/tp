package seedu.duke;

import org.junit.jupiter.api.Test;
import seedu.duke.module.lessons.Lesson;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TimetableTest {
    Timetable timetable = new Timetable();
    List<Lesson> lesson = new ArrayList<>();

    @Test
    void listModules() {
        timetable.addNewModule("CS2113", "SE and OOP", "Sample Description", lesson);
        timetable.addNewModule("CS2040", "DSA", "Sample Description", lesson);
        assertEquals("Here are your modules:\n"
                + "1. CS2113: SE and OOP\n2. CS2040: DSA\n", timetable.listModules());
    }
}