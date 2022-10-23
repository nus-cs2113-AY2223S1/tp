package seedu.duke.exception;

//@@author wcwy
import seedu.duke.common.ErrorMessages;

/**
 * Represents the exception where the budget value provided by the user is not a valid acceptable value.
 */
public class InputBudgetInvalidAmountException extends MoolahException {
    /**
     * Returns the error message of the exception to alert user of the exception.
     *
     * @return A string containing the error message
     */
    @Override
    public String getMessage() {
        return ErrorMessages.ERROR_INVALID_BUDGET.toString();
    }

}