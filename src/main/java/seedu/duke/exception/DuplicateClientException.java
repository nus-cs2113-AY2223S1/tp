package seedu.duke.exception;

import static seedu.duke.Messages.MESSAGE_DUPLICATE_CLIENT;

//@@author OVReader
/**
 * Represents exception when a duplicate client is provided when adding new client.
 */
public class DuplicateClientException extends DukeParseException {
    @Override
    public String toString() {
        return MESSAGE_DUPLICATE_CLIENT;
    }
}
