package seedu.duke.exception;

/**
 * Represents exception when incorrect flag order is given by user.
 */
public class IncorrectFlagOrderException extends DukeParseException {

    private final String message;

    public IncorrectFlagOrderException(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return message;
    }
}
