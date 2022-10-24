package seedu.duke.exception;

//@@author chinhan99
import seedu.duke.common.ErrorMessages;

public class StorageWriteErrorException extends MoolahException {
    /**
     * Returns the error message of the exception to alert user of the exception.
     *
     * @return A string containing the error message
     */
    @Override
    public String getMessage() {
        return ErrorMessages.ERROR_STORAGE_WRITE.toString();
    }
}
