package seedu.duke;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TimetableTest {
    Timetable timetable = new Timetable();

    @Test
    void listModules() {
        timetable.addNewModule("CS2113", "SE and OOP", "Sample Description");
        timetable.addNewModule("CS2040", "DSA", "Sample Description");
        assertEquals("Here are your modules:\n"
                + "1. CS2113: SE and OOP\n2. CS2040: DSA\n", timetable.listModules());
    }
}