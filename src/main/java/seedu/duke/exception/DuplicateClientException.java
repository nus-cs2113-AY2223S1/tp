//@@author OVReader

package seedu.duke.exception;

import static seedu.duke.Messages.MESSAGE_DUPLICATE_CLIENT;

/**
 * Represents exception when a duplicate client is provided when adding new client.
 */
public class DuplicateClientException extends ParseAddClientException {
    @Override
    public String toString() {
        return MESSAGE_DUPLICATE_CLIENT;
    }
}
