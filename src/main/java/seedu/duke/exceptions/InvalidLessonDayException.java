package seedu.duke.exceptions;

public class InvalidLessonDayException extends Exception {
    private String message;

    public InvalidLessonDayException(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
