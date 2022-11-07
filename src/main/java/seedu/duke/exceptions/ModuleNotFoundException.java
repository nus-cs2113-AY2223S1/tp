package seedu.duke.exceptions;

public class ModuleNotFoundException extends Exception {
    private String message;

    public ModuleNotFoundException(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
