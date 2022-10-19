package seedu.duke.exceptions;

public class UnknownCommandException extends Exception {
    private static final String ERROR_MESSAGE = "Sorry, I do not understand your command. Enter \"help\" for the available commands";

    @Override
    public String getMessage() {
        return ERROR_MESSAGE;
    }
}
