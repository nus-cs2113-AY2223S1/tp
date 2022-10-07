package seedu.duke.command;

import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class AddCommandTest {

    @Test
    public void containNumeric_IfContainsNumeric_ReturnTrue() {
        boolean testOutputContainsNumber = AddCommand.containNumeric("Food1");
        assertTrue(testOutputContainsNumber);
    }

    @Test
    public void containNumeric_IfDoesNotContainNumeric_ReturnFalse(){
        boolean testOutputWithoutNumber = AddCommand.containNumeric("Food");
        assertFalse(testOutputWithoutNumber);
    }
}
