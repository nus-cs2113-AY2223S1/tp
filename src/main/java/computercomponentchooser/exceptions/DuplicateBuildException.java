package computercomponentchooser.exceptions;

public class DuplicateBuildException extends Exception {
    protected final String errorMessage = "Build already exists";

    public String getMessage() {
        return errorMessage;
    }
}
