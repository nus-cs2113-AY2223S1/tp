package seedu.duke.exception;

public class InvalidArgumentException extends DukeException {
    /**
     * Constructor of InvalidArgumentException.
     *
     * @param message An exception message
     */
    public InvalidArgumentException(String message) {
        super(message);
    }
}
