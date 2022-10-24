package seedu.duke.exception;

//@@author chydarren
import seedu.duke.common.ErrorMessages;

/**
 * Represents the exception where period and number tags are not given as a pair in the input.
 */
public class GlobalMissingPeriodNumberTagException extends MoolahException {
    /**
     * Returns the error message of the exception to alert user of the exception.
     *
     * @return A string containing the error message
     */
    @Override
    public String getMessage() {
        return ErrorMessages.ERROR_GLOBAL_MISSING_TAG_PERIODNUMBER.toString();
    }
}
