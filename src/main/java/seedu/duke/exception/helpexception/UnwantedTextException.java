package seedu.duke.exception.helpexception;

import seedu.duke.exception.DukeException;

import static seedu.duke.Messages.INVALID_HELP_COMMAND;

public class UnwantedTextException extends DukeException {
    @Override
    public String toString() {
        return INVALID_HELP_COMMAND;
    }
}
