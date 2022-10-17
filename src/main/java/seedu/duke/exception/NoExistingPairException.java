package seedu.duke.exception;

public class NoExistingPairException extends DukeCommandException {

    private final String message;

    public NoExistingPairException(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return message;
    }
}
