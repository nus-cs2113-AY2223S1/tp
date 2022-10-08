package seedu.duke;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TimetableTest {
    Timetable timetable = new Timetable();

    @Test
    void listModules() {
        timetable.addNewModule("SE and OOP", "CS2113", "Sample Description");
        timetable.addNewModule("DSA", "CS2040", "Sample Description");
        assertEquals("Here are your modules:\n"
                + "1. CS2113: SE and OOP\n2. CS2040: DSA\n", timetable.listModules());
    }
}