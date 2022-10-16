package seedu.duke.exception;

public class TransactionFileNotFoundException extends DukeException {
    /**
     * Constructor of TransactionFileNotFoundException.
     *
     * @param message An exception message
     */
    public TransactionFileNotFoundException(String message) {
        super(message);
    }
}
