package seedu.duke.exception;

//@@author wcwy
import seedu.duke.common.ErrorMessages;

/**
 * Represents the exception where the option chosen in help command is not "o/detailed".
 */
public class HelpUnknownCommandWordException extends MoolahException {
    /**
     * Returns the error message of the exception to alert user of the exception.
     *
     * @return A string containing the error message
     */
    @Override
    public String getMessage() {
        return ErrorMessages.ERROR_UNKNOWN_HELP_COMMAND_WORD.toString();
    }

}
