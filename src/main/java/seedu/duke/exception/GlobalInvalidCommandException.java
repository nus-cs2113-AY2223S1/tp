package seedu.duke.exception;

//@@author paullowse
import static seedu.duke.common.ErrorMessages.ERROR_GLOBAL_INVALID_COMMAND;

public class GlobalInvalidCommandException extends MoolahException {
    /**
     * Returns the error message of the exception to alert user of the exception.
     *
     * @return A string containing the error message
     */
    @Override
    public String getMessage() {
        return ERROR_GLOBAL_INVALID_COMMAND.toString();
    }
}
