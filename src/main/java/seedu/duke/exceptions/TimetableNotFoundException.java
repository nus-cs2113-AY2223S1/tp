package seedu.duke.exceptions;

public class TimetableNotFoundException extends Exception {
    private String message;

    public TimetableNotFoundException(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
