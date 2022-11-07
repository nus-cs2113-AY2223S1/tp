package seedu.duke.command;

import seedu.duke.exception.DukeException;

//@@author winston-lim
interface CommandInterface {
    boolean executeCommand() throws DukeException;
}
