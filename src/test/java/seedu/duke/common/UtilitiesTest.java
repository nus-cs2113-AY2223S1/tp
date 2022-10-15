package seedu.duke.common;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.duke.parser.ParameterParser.containNumeric;

public class UtilitiesTest {
    //@@author chinhan99
    @Test
    public void containNumeric_IfContainsNumeric_ReturnTrue() {
        boolean testOutputContainsNumber = containNumeric("Food1");
        assertTrue(testOutputContainsNumber);
    }

    @Test
    public void containNumeric_IfDoesNotContainNumeric_ReturnFalse() {
        boolean testOutputWithoutNumber = containNumeric("Food");
        assertFalse(testOutputWithoutNumber);
    }
}