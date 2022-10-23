package computercomponentchooser.exceptions;

/**
 * Represents the exception when the user tries access/use a build that does not exist.
 */
public class UnlistedBuildException extends Exception {

    protected final String errorMessage = "This build does not exist";

    /**
     * Returns the error message.
     *
     * @return The error message.
     */
    public String getMessage() {
        return errorMessage;
    }
}
