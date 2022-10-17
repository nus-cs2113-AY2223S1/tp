package seedu.duke.exception;

public class AlreadyPairedException extends DukeCommandException {

    private final String message;

    public AlreadyPairedException(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return message;
    }
}
