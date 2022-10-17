package seedu.duke.exception;

/**
 * Represents exception when no client description is given when adding client.
 */
public class EmptyDescriptionException extends DukeParseException {

    private final String message;

    public EmptyDescriptionException(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return message;
    }
}
