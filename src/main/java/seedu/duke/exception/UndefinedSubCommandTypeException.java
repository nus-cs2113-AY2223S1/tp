package seedu.duke.exception;

/**
 * Represents exception when sub command type required for command add is missing (-property/-client).
 */
public class UndefinedSubCommandTypeException extends DukeParseException {

    private final String message;

    public UndefinedSubCommandTypeException(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return message;
    }
}
