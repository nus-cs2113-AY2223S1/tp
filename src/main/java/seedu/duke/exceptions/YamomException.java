package seedu.duke.exceptions;

/**
 * A general exception class for YAMOM.
 * Developers are able to set the error message depending on the context of the exception.
 */
public class YamomException extends Exception {
    private String errorMessage;

    public YamomException(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    @Override
    public String getMessage() {
        return "Error! \t" + errorMessage;
    }
}
