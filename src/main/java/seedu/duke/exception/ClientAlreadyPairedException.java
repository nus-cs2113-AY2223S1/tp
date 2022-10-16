package seedu.duke.exception;

public class ClientAlreadyPairedException extends DukeCommandException {

    private final String message;

    public ClientAlreadyPairedException(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return message;
    }
}
