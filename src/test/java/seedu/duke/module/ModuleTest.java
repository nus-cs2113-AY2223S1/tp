package seedu.duke.module;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import seedu.duke.university.University;

public class ModuleTest {
    @Test
    void toString_validModule_correctString() {
        University university = new University("NUS", "Singapore");
        Module module = new Module("CS2113", "Software Engineering & Object-Oriented Programming", "4", university);
        assertEquals("CS2113 Software Engineering & Object-Oriented Programming 4MCs in NUS", module.toString());
    }
}
