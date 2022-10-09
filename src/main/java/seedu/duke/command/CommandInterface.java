package seedu.duke.command;

import seedu.duke.exception.*;

interface CommandInterface {
    boolean executeCommand()
            throws InsufficientArgumentsException, DukeException, InvalidArgumentException,
            DateFormatInvalidException, InvalidBorrowerException, InvalidItemException, ItemNotFoundException,
            UserNotFoundException, ContactNumberInvalidException, DuplicateException;
}
