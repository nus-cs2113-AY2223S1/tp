//@@author OVReader

package seedu.duke.exception;

import static seedu.duke.Messages.MESSAGE_DUPLICATE_PROPERTY;

/**
 * Represents exception when a duplicate property is provided when adding new property.
 */
public class DuplicatePropertyException extends ParseAddPropertyException {
    @Override
    public String toString() {
        return MESSAGE_DUPLICATE_PROPERTY;
    }
}
