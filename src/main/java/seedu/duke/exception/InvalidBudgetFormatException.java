package seedu.duke.exception;

/**
 * Represents exception when incorrect format for budget is given when adding client.
 */
public class InvalidBudgetFormatException extends DukeParseException {

    private final String message;

    public InvalidBudgetFormatException(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return message;
    }
}
