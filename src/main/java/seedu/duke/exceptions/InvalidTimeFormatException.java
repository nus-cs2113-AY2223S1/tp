package seedu.duke.exceptions;

public class InvalidTimeFormatException extends Exception {
    private String message;

    public InvalidTimeFormatException(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
