//@@author OVReader

package seedu.duke.exception;

import static seedu.duke.Messages.MESSAGE_INVALID_CONTACT_NUMBER;

/**
 * Represents exception when incorrect contact number (Singapore) format is given when adding client.
 */
public class InvalidContactNumberException extends ParseAddClientException {
    @Override
    public String toString() {
        return MESSAGE_INVALID_CONTACT_NUMBER;
    }
}
