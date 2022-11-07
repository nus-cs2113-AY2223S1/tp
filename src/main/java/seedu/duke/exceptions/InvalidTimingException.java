package seedu.duke.exceptions;

public class InvalidTimingException extends Exception {
    private String message;

    public InvalidTimingException(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
