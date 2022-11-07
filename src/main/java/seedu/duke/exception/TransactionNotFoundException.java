package seedu.duke.exception;

public class TransactionNotFoundException extends DukeException {
    /**
     * Constructor of TransactionNotFoundException.
     *
     * @param message An exception message
     */
    public TransactionNotFoundException(String message) {
        super(message);
    }
}