package seedu.duke.university;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import seedu.duke.exceptions.InvalidUniversityException;

public class UniversityTest {
    @Test
    void toString_validUniversity_correctString() throws InvalidUniversityException {
        University university = new University("NUS", "Singapore");
        assertEquals("NUS", university.toString());
    }

    @Test
    void toString_invalidUniversityName_throwsInvalidUniversityException() {
        assertThrows(InvalidUniversityException.class, () -> new University("", "Singapore"));
    }

    @Test
    void toString_invalidUniversityCountry_throwsInvalidUniversityException() {
        assertThrows(InvalidUniversityException.class, () -> new University("NUS", ""));
    }
}
