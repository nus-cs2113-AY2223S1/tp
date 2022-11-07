package seedu.duke.exception;

public class ExtraParametersException extends DukeParseException {

    private final String message;

    public ExtraParametersException(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return message;
    }
}
