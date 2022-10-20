package seedu.duke.exceptions;

public class TimetableClashException extends Exception {
    private String message;

    public TimetableClashException(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
