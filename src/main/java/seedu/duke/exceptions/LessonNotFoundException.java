package seedu.duke.exceptions;

public class LessonNotFoundException extends Exception {
    private String message;

    public LessonNotFoundException(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
