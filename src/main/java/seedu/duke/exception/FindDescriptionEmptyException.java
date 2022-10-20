package seedu.duke.exception;

public class FindDescriptionEmptyException extends DukeCommandException {
    private final String message;

    public FindDescriptionEmptyException(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return message;
    }
}
