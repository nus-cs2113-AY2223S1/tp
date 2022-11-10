package computercomponentchooser.exceptions;

/**
 * Represents the exception when the user tries to add a build with a name that already exists.
 */
public class DuplicateBuildException extends Exception {
    protected final String errorMessage = "Build already exists";

    /**
     * Returns the error message.
     *
     * @return The error message.
     */
    public String getMessage() {
        return errorMessage;
    }
}
