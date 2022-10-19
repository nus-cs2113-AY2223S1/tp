package seedu.duke.exceptions;

public class StateException extends Exception {
    private static final String ERROR_MESSAGE = "State dummy exception";

    @Override
    public String getMessage() {
        return ERROR_MESSAGE;
    }
}
