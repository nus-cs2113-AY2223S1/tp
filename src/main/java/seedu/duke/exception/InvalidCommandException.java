package seedu.duke.exception;

public class InvalidCommandException extends DukeException {

    private final String message;

    public InvalidCommandException(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return message;
    }
}
