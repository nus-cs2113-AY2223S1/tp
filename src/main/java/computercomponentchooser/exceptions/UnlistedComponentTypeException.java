package computercomponentchooser.exceptions;


/**
 * Represents the exception when the user tries to add a component of a type that does not exist.
 */
public class UnlistedComponentTypeException extends Exception {

    protected final String errorMessage = "This component type does not exist";

    /**
     * Returns the error message.
     *
     * @return The error message.
     */
    public String getMessage() {
        return errorMessage;
    }
}
