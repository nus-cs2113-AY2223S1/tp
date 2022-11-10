package computercomponentchooser.exceptions;

public class NameSlashException extends Exception {
    private final String errorMessage = "Build name cannot contain a slash.";

    public String getMessage() {
        return errorMessage;
    }
}
