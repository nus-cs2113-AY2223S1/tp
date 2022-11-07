package seedu.duke.exceptions;

public class InvalidDatabaseLineException extends Exception {
    private String message;

    public InvalidDatabaseLineException(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
