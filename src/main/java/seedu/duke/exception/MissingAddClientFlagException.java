//@@author OVReader

package seedu.duke.exception;

import static seedu.duke.Messages.MESSAGE_ADD_CLIENT_WRONG_FORMAT;

/**
 * Represents exception when any essential flags required for add client command is missing.
 */
public class MissingAddClientFlagException extends ParseAddClientException {
    @Override
    public String toString() {
        return MESSAGE_ADD_CLIENT_WRONG_FORMAT;
    }
}
