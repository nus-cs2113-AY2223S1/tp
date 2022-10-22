package seedu.duke.exception;

import static seedu.duke.Messages.MESSAGE_INVALID_CONTACT_NUMBER;

//@@author OVReader
/**
 * Represents exception when incorrect contact number (Singapore) format is given when adding client.
 */
public class InvalidContactNumberException extends DukeParseException {
    @Override
    public String toString() {
        return MESSAGE_INVALID_CONTACT_NUMBER;
    }
}
