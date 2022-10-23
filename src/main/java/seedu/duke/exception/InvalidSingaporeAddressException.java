package seedu.duke.exception;

import static seedu.duke.Messages.MESSAGE_INVALID_SINGAPORE_ADDRESS;

//@@author OVReader
/**
 * Represents exception when invalid Singapore Address is provided when adding property.
 */
public class InvalidSingaporeAddressException extends DukeParseException {
    @Override
    public String toString() {
        return MESSAGE_INVALID_SINGAPORE_ADDRESS;
    }
}
