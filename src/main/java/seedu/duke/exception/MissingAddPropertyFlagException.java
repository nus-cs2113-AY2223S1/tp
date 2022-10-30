//@@author OVReader

package seedu.duke.exception;

import static seedu.duke.Messages.MESSAGE_ADD_PROPERTY_WRONG_FORMAT;

/**
 * Represents exception when any essential flags required for add property command is missing.
 */
public class MissingAddPropertyFlagException extends ParseAddPropertyException {
    @Override
    public String toString() {
        return MESSAGE_ADD_PROPERTY_WRONG_FORMAT;
    }
}
