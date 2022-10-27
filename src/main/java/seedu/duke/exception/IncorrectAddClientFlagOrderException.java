//@@author OVReader

package seedu.duke.exception;

import static seedu.duke.Messages.MESSAGE_ADD_CLIENT_WRONG_FORMAT;

/**
 * Represents exception when incorrect flag order is provided by user when adding client.
 */
public class IncorrectAddClientFlagOrderException extends ParseAddClientException {
    @Override
    public String toString() {
        return MESSAGE_ADD_CLIENT_WRONG_FORMAT;
    }
}
