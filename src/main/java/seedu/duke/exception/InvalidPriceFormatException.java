package seedu.duke.exception;

/**
 * Represents exception when incorrect format for renting_price/month is given when adding property.
 */
public class InvalidPriceFormatException extends DukeParseException {

    private final String message;

    public InvalidPriceFormatException(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return message;
    }
}
