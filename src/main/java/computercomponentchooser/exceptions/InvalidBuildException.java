package computercomponentchooser.exceptions;

public class InvalidBuildException extends Exception {
    protected final String errorMessage = "This build name is invalid. Please try other naming conventions.";

    public String getMessage() {
        return errorMessage;
    }
}
