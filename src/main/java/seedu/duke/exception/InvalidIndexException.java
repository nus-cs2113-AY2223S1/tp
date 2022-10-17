package seedu.duke.exception;

public class InvalidIndexException extends DukeParseException {

    private final String message;

    public InvalidIndexException(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return message;
    }
}
