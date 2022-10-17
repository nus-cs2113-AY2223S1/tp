package seedu.duke.exception;

/**
 * Represents exception when incorrect email format is given when adding client.
 */
public class InvalidEmailException extends DukeParseException {

    private final String message;

    public InvalidEmailException(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return message;
    }
}
