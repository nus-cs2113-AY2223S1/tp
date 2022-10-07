package seedu.duke.exception;

import static seedu.duke.common.ErrorMessages.ERROR_INVALID_COMMAND;

public class InvalidCommandException extends MoolahException {
    /**
     * Returns the error message of the exception to alert user of the exception.
     *
     * @return A string containing the error message
     */
    @Override
    public String getMessage() {
        return ERROR_INVALID_COMMAND.toString();
    }

}
