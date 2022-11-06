//@@author OVReader

package seedu.duke.exception.parseaddexception.parseaddclientexception;

import seedu.duke.exception.parseaddexception.parseaddclientexception.ParseAddClientException;

import static seedu.duke.Messages.MESSAGE_INVALID_EMAIL;

/**
 * Represents exception when incorrect email format is given when adding client.
 */
public class InvalidEmailException extends ParseAddClientException {
    @Override
    public String toString() {
        return MESSAGE_INVALID_EMAIL;
    }
}
