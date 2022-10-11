package computercomponentchooser.exceptions;

public class UnlistedBuildException extends Exception {

    protected final String errorMessage = "This build does not exist";

    public String getMessage() {
        return errorMessage;
    }
}
