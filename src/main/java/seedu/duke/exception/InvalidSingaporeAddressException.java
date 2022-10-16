package seedu.duke.exception;

/**
 * Represents exception when invalid Singapore Address is provided when adding property.
 */
public class InvalidSingaporeAddressException extends DukeParseException {

    private final String message;

    public InvalidSingaporeAddressException(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return message;
    }
}
