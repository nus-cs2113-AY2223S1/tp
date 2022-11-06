//@@author FeliciaBeatrice

package seedu.duke.exception.parsedeleteexception.parsedeletepropertyexception;

import static seedu.duke.Messages.MESSAGE_INVALID_INDEX;

/**
 * Represents exception when an invalid index is provided when deleting a property.
 */
public class InvalidDeletePropertyIndexException extends ParseDeletePropertyException {
    @Override
    public String toString() {
        return MESSAGE_INVALID_INDEX;
    }
}
