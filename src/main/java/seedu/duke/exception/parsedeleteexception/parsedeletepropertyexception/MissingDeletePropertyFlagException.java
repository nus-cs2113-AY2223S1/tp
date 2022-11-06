//@@author FeliciaBeatrice

package seedu.duke.exception.parsedeleteexception.parsedeletepropertyexception;

import static seedu.duke.Messages.MESSAGE_DELETE_PROPERTY_WRONG_FORMAT;

/**
 * Represents exception when delete property flag is missing.
 */
public class MissingDeletePropertyFlagException extends ParseDeletePropertyException {
    @Override
    public String toString() {
        return MESSAGE_DELETE_PROPERTY_WRONG_FORMAT;
    }
}
