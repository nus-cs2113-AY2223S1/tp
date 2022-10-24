package seedu.duke.exception;

//@@author wcwy
import seedu.duke.common.ErrorMessages;

/**
 * Represents the exception where the number of transactions will exceed the maximum value set if a new transaction
 * is added to the list.
 */
public class MaximumTransactionCountException extends MoolahException {
    /**
     * Returns the error message of the exception to alert user of the exception.
     *
     * @return A string containing the error message
     */
    @Override
    public String getMessage() {
        return ErrorMessages.ERROR_MAXIMUM_TRANSACTION_COUNT_REACHED.toString();
    }
}
