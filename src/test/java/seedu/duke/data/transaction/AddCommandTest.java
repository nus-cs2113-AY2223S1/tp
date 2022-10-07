package seedu.duke.data.transaction;

import org.junit.jupiter.api.Test;
import seedu.duke.command.AddCommand;

import static org.junit.jupiter.api.Assertions.*;

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
