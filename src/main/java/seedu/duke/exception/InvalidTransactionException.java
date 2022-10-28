package seedu.duke.exception;

public class InvalidTransactionException extends DukeException {
    /**
     * Constructor of InvalidTransactionException.
     *
     * @param message An exception message
     */
    public InvalidTransactionException(String message) {
        super(message);
    }
}