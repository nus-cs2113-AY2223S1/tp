package seedu.duke.exception;

public class NotIntegerException extends DukeParseException {

    private final String message;

    public NotIntegerException(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return message;
    }
}
