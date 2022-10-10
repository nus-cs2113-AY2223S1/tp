package seedu.duke.exception;

public class CommandNotFoundException extends Exception {
    public CommandNotFoundException(String message) {
        super(message);
    }
}

