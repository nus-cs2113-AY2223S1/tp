package seedu.duke.command;

import seedu.duke.exception.*;

interface CommandInterface {
    boolean executeCommand()
            throws InsufficientArgumentsException, DukeException, InvalidArgumentException,
            DateFormatInvalidException, InvalidUserException, InvalidItemException, ItemNotFoundException,
            UserNotFoundException, ContactNumberInvalidException, DuplicateException, TransactionNotFoundException;
}
