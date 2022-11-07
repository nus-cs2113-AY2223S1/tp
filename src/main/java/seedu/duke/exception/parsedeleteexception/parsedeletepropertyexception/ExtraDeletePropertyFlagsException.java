//@@author FeliciaBeatrice

package seedu.duke.exception.parsedeleteexception.parsedeletepropertyexception;

import static seedu.duke.Messages.MESSAGE_DELETE_PROPERTY_WRONG_FORMAT;

/**
 * Represents exception when extra flags are provided for delete client.
 */
public class ExtraDeletePropertyFlagsException extends ParseDeletePropertyException {
    @Override
    public String toString() {
        return MESSAGE_DELETE_PROPERTY_WRONG_FORMAT;
    }
}
