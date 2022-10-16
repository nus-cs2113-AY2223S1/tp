package seedu.duke.exception;

public class UserNotFoundException extends DukeException {
    /**
     * Constructor of UserNotFoundException.
     *
     * @param message An exception message
     */
    public UserNotFoundException(String message) {
        super(message);
    }
}
