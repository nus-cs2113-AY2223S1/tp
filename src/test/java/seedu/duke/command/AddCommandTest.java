package seedu.duke.command;

import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class AddCommandTest {

    boolean testOutputContainsNumber = AddCommand.containNumeric("Food1");
    boolean testOutputWithoutNumber = AddCommand.containNumeric("Food");

    @Test
    public void testCheckIfInputContainsNumeric() {
        assertTrue(testOutputContainsNumber);
    }

    @Test
    public void testCheckIfInputContainsOnlyAlphabets() {
        assertFalse(testOutputWithoutNumber);
    }
}
