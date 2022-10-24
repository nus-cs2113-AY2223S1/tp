package seedu.duke.exception;

//@@author OVReader

import static seedu.duke.Messages.MESSAGE_INVALID_UNIT_TYPE_LABEL;

/**
 * Represents exception when invalid Unit Type Label is provided when adding property.
 */
public class InvalidUnitTypeLabelException extends DukeParseException {
    @Override
    public String toString() {
        return MESSAGE_INVALID_UNIT_TYPE_LABEL;
    }
}
