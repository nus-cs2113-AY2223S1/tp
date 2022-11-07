//@@author FeliciaBeatrice

package seedu.duke.exception.parsedeleteexception.parsedeletepropertyexception;

import static seedu.duke.Messages.MESSAGE_EMPTY_DESCRIPTION;

/**
 * Represents exception when an empty description is provided for delete property.
 */
public class EmptyDeletePropertyDetailException extends ParseDeletePropertyException {
    @Override
    public String toString() {
        return MESSAGE_EMPTY_DESCRIPTION;
    }
}
