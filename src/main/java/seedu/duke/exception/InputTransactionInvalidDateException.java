package seedu.duke.exception;

//@@author wcwy
import seedu.duke.common.ErrorMessages;

/**
 * Represents the exception where the date provided for the transaction is not in the supported format.
 */
public class InputTransactionInvalidDateException extends MoolahException {

    /**
     * Returns the error message of the exception to alert user of the exception.
     *
     * @return A string containing the error message
     */
    @Override
    public String getMessage() {
        return ErrorMessages.ERROR_TRANSACTION_INVALID_DATE.toString();
    }
}
