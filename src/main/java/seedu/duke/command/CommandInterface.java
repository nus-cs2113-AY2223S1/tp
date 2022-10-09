package seedu.duke.command;

import seedu.duke.exception.DukeException;
import seedu.duke.exception.InsufficientArgumentsException;

interface CommandInterface {
    boolean executeCommand() throws InsufficientArgumentsException, DukeException;
}
