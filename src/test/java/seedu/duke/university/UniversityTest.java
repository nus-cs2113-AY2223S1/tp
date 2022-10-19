package seedu.duke.university;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import seedu.duke.exceptions.InvalidUniversityException;

public class UniversityTest {
    @Test
    void toString_validUniversity_correctString() throws InvalidUniversityException{
        University university = new University("NUS", "Singapore");
        assertEquals("NUS", university.toString());
    }
}
