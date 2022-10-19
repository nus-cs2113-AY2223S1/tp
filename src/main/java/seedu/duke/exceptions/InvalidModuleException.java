package seedu.duke.exceptions;

public class InvalidModuleException extends Exception {
    private String message;

    public InvalidModuleException(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
