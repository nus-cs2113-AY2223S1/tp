package computercomponentchooser.exceptions;

/**
 * Represents the exception when the user input is blank.
 */
public class BlankStringException extends Exception {
    protected final String errorMessage = "String cannot be blank";

    /**
     * Returns the error message.
     *
     * @return The error message.
     */
    public String getMessage() {
        return errorMessage;
    }
}
