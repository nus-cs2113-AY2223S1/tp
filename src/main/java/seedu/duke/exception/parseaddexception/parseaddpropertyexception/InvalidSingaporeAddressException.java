//@@author OVReader

package seedu.duke.exception.parseaddexception.parseaddpropertyexception;

import seedu.duke.exception.parseaddexception.parseaddpropertyexception.ParseAddPropertyException;

import static seedu.duke.Messages.MESSAGE_INVALID_SINGAPORE_ADDRESS;

/**
 * Represents exception when invalid Singapore Address is provided when adding property.
 */
public class InvalidSingaporeAddressException extends ParseAddPropertyException {
    @Override
    public String toString() {
        return MESSAGE_INVALID_SINGAPORE_ADDRESS;
    }
}
