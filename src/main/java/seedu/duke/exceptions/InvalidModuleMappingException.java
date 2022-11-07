package seedu.duke.exceptions;

public class InvalidModuleMappingException extends Exception {
    private String message;

    public InvalidModuleMappingException(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
