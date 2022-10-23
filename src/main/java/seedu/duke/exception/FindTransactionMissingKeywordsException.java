package seedu.duke.exception;

//@@author chydarren
import seedu.duke.common.ErrorMessages;

public class FindTransactionMissingKeywordsException extends MoolahException {
    /**
     * Returns the error message of the exception to alert user of the exception.
     *
     * @return A string containing the error message
     */
    @Override
    public String getMessage() {
        return ErrorMessages.ERROR_FIND_COMMAND_MISSING_KEYWORDS.toString();
    }
}