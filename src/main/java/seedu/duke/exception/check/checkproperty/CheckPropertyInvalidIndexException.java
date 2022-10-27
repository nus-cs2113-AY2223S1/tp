package seedu.duke.exception.check.checkproperty;

//@@author ngdeqi
import seedu.duke.exception.check.CommandCheckException;

import static seedu.duke.Messages.MESSAGE_INVALID_INDEX;

public class CheckPropertyInvalidIndexException extends CommandCheckException {

    @Override
    public String toString() {
        return MESSAGE_INVALID_INDEX;
    }
}
