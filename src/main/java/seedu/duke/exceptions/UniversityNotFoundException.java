package seedu.duke.exceptions;

public class UniversityNotFoundException extends Exception {
    private String message;

    public UniversityNotFoundException(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
