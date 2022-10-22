package computercomponentchooser.exceptions;

public class BlankStringException extends Exception {
    protected final String errorMessage = "String cannot be blank";

    public String getMessage() {
        return errorMessage;
    }
}
