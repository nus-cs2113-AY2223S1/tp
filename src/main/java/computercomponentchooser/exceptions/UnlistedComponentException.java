package computercomponentchooser.exceptions;

/**
 * Represents the exception when the user tries to access/use a component that does not exist.
 */
public class UnlistedComponentException extends Exception {
    protected final String errorMessage = "This component does not exist.";

    /**
     * Returns the error message.
     *
     * @return The error message.
     */
    public String getMessage() {
        return errorMessage;
    }
}
