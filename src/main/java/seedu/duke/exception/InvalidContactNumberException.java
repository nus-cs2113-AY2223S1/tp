package seedu.duke.exception;

/**
 * Represents exception when incorrect contact number (Singapore) format is given when adding client.
 */
public class InvalidContactNumberException extends DukeParseException {

    private final String message;

    public InvalidContactNumberException(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return message;
    }
}
