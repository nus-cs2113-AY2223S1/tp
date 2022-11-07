package seedu.duke.exception;

public class CommandNotFoundException extends DukeException {
    /**
     * Constructor of CommandNotFoundException.
     *
     * @param message An exception message
     */
    public CommandNotFoundException(String message) {
        super(message);
    }
}

