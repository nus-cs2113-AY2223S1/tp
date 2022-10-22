package computercomponentchooser.exceptions;

public class NegativeNumberException extends Exception {
    protected final String errorMessage = "Please input positive number(s)";

    public String getMessage() {
        return errorMessage;
    }
}
