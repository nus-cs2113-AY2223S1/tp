//@@author FeliciaBeatrice

package seedu.duke.exception;

public class ExtraFlagsException extends DukeParseException {

    private final String message;

    public ExtraFlagsException(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return message;
    }
}
