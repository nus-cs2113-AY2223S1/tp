package seedu.duke.exception;

import static seedu.duke.Messages.MESSAGE_INVALID_PRICE_FORMAT;

//@@author OVReader
/**
 * Represents exception when incorrect format for renting_price/month is given when adding property.
 */
public class InvalidPriceFormatException extends DukeParseException {
    @Override
    public String toString() {
        return MESSAGE_INVALID_PRICE_FORMAT;
    }
}
