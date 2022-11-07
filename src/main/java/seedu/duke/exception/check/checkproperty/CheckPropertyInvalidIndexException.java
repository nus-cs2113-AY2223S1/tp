package seedu.duke.exception.check.checkproperty;

//@@author ngdeqi
import seedu.duke.exception.check.CommandCheckException;

import static seedu.duke.Messages.MESSAGE_INVALID_INDEX;

/**
 * Representation of an exception where the user provides an index that is not within the property list in a check
 * property command.
 */
public class CheckPropertyInvalidIndexException extends CommandCheckException {

    @Override
    public String toString() {
        return MESSAGE_INVALID_INDEX;
    }
}
