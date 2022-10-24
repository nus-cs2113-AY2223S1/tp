package seedu.duke.exception;

import static seedu.duke.Messages.MESSAGE_DUPLICATE_PROPERTY;

//@@author OVReader
/**
 * Represents exception when a duplicate property is provided when adding new property.
 */
public class DuplicatePropertyException extends DukeParseException {
    @Override
    public String toString() {
        return MESSAGE_DUPLICATE_PROPERTY;
    }
}
