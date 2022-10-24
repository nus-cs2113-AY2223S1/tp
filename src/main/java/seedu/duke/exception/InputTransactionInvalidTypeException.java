package seedu.duke.exception;

//@@author chydarren
import seedu.duke.common.ErrorMessages;

/**
 * Represents the exception where the type given for a transaction is not Income or Expense.
 */
public class InputTransactionInvalidTypeException extends MoolahException {
    /**
     * Returns the error message of the exception to alert user of the exception.
     *
     * @return A string containing the error message
     */
    @Override
    public String getMessage() {
        return ErrorMessages.ERROR_TRANSACTION_INVALID_TYPE.toString();
    }
}