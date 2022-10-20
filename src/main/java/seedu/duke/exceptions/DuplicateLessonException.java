package seedu.duke.exceptions;

public class DuplicateLessonException extends Exception {
    private String message;

    public DuplicateLessonException(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
