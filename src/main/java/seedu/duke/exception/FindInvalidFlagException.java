package seedu.duke.exception;

public class FindInvalidFlagException extends DukeCommandException {
    private final String message;

    public FindInvalidFlagException(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return message;
    }
}
