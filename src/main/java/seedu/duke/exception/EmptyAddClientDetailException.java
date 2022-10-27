//@@author OVReader

package seedu.duke.exception;

import static seedu.duke.Messages.MESSAGE_ADD_CLIENT_WRONG_FORMAT;

/**
 * Represents exception when no client description is provided when adding client.
 */
public class EmptyAddClientDetailException extends ParseAddClientException {
    @Override
    public String toString() {
        return MESSAGE_ADD_CLIENT_WRONG_FORMAT;
    }
}
