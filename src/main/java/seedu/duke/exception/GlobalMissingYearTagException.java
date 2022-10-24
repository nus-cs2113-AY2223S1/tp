package seedu.duke.exception;

//@@author chydarren
import seedu.duke.common.ErrorMessages;

/**
 * Represents the exception where a month tag is not accompanied by a year tag in the input.
 */
public class GlobalMissingYearTagException extends MoolahException {
    /**
     * Returns the error message of the exception to alert user of the exception.
     *
     * @return A string containing the error message
     */
    @Override
    public String getMessage() {
        return ErrorMessages.ERROR_GLOBAL_MISSING_TAG_YEAR.toString();
    }
}
