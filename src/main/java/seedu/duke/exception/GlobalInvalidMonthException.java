package seedu.duke.exception;

//@@author chydarren
import seedu.duke.common.ErrorMessages;

/**
 * Represents the exception where the user input for month is not within 1 to 12.
 */
public class GlobalInvalidMonthException extends MoolahException {
    /**
     * Returns the error message of the exception to alert user of the exception.
     *
     * @return A string containing the error message
     */
    @Override
    public String getMessage() {
        return ErrorMessages.ERROR_GLOBAL_INVALID_MONTH.toString();
    }
}
