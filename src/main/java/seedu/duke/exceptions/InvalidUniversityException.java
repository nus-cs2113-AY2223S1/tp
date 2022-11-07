package seedu.duke.exceptions;

public class InvalidUniversityException extends Exception {
    private String message;

    public InvalidUniversityException(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
