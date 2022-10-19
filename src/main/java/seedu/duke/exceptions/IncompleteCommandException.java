package seedu.duke.exceptions;

public class IncompleteCommandException extends Exception {
    private static final String ERROR_MESSAGE = "Your command is incomplete.";

    @Override
    public String getMessage() {
        return ERROR_MESSAGE;
    }
}

