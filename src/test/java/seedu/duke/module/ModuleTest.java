package seedu.duke.module;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import seedu.duke.exceptions.InvalidModuleException;
import seedu.duke.exceptions.InvalidUniversityException;
import seedu.duke.university.University;

public class ModuleTest {
    @Test
    void toString_validModule_correctString() throws InvalidUniversityException, InvalidModuleException {
        University university = new University("NUS", "Singapore");
        Module module = new Module("CS2113", "Software Engineering & Object-Oriented Programming", "4", university);
        assertEquals("CS2113 Software Engineering & Object-Oriented Programming 4MCs in NUS", module.toString());
    }

    @Test
    void moduleConstructor_invalidModuleCode_throwsInvalidModuleException()
            throws InvalidUniversityException {
        University university = new University("NUS", "Singapore");

        assertThrows(InvalidModuleException.class,
            () -> new Module("", "Software Engineering & Object-Oriented Programming", "4", university));
    }

    @Test
    void moduleConstructor_invalidModuleTitle_throwsInvalidModuleException()
            throws InvalidUniversityException {
        University university = new University("NUS", "Singapore");

        assertThrows(InvalidModuleException.class,
            () -> new Module("CS2113", "", "4", university));
    }

    @Test
    void moduleConstructor_invalidModuleCredit_throwsInvalidModuleException()
            throws InvalidUniversityException {
        University university = new University("NUS", "Singapore");

        assertThrows(InvalidModuleException.class,
            () -> new Module("CS2113", "Software Engineering & Object-Oriented Programming", "", university));
    }

    @Test
    void moduleConstructor_invalidUniversityName_throwsInvalidUniversityException() {
        assertThrows(InvalidUniversityException.class,
            () -> new University("", "Singapore"));
    }

    @Test
    void moduleConstructor_invalidUniversityCountry_throwsInvalidUniversityException() {
        assertThrows(InvalidUniversityException.class, () -> new University("NUS", ""));
    }
}
