package seedu.duke.exception;

/**
 * Represents exception when flags required for command are missing.
 */
public class MissingFlagException extends DukeParseException {

    private final String message;

    public MissingFlagException(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return message;
    }
}
