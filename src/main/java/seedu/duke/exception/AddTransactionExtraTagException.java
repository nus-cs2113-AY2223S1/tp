package seedu.duke.exception;

import seedu.duke.common.ErrorMessages;

public class AddTransactionExtraTagException extends MoolahException {
    /**
     * Returns the error message of the exception to alert user of the exception.
     *
     * @return A string containing the error message
     */
    @Override
    public String getMessage() {
        return ErrorMessages.ERROR_ADD_COMMAND_EXTRA_TAG.toString();
    }
}
