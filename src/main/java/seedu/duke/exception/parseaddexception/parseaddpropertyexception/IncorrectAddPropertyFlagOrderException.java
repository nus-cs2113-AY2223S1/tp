//@@author OVReader

package seedu.duke.exception.parseaddexception.parseaddpropertyexception;

import seedu.duke.exception.parseaddexception.parseaddpropertyexception.ParseAddPropertyException;

import static seedu.duke.Messages.MESSAGE_ADD_PROPERTY_WRONG_FORMAT;

/**
 * Represents exception when incorrect flag order is provided by user when adding property.
 */
public class IncorrectAddPropertyFlagOrderException extends ParseAddPropertyException {
    @Override
    public String toString() {
        return MESSAGE_ADD_PROPERTY_WRONG_FORMAT;
    }
}
