package seedu.duke.exception;

//@@author wcwy
import seedu.duke.common.ErrorMessages;

/**
 * Represents the exception where a same tag is found more than once in the user input.
 */
public class GlobalDuplicateTagException extends MoolahException {
    /**
     * Returns the error message of the exception to alert user of the exception.
     *
     * @return A string containing the error message
     */
    @Override
    public String getMessage() {
        return ErrorMessages.ERROR_GLOBAL_DUPLICATE_TAG.toString();
    }
}
