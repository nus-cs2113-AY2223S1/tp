package seedu.duke.exception;

public class IncorrectListFlagsException extends DukeCommandException {
    private final String message;

    public IncorrectListFlagsException(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return message;
    }



}
