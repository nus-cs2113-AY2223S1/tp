package computercomponentchooser.exceptions;

/**
 * Represents the exception when the user uses an input with negative numbers.
 */
public class NegativeNumberException extends Exception {
    protected final String errorMessage = "Please input positive number(s)";

    /**
     * Returns the error message.
     *
     * @return The error message.
     */
    public String getMessage() {
        return errorMessage;
    }
}
