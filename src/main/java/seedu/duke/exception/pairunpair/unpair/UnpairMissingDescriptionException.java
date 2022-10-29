package seedu.duke.exception.pairunpair.unpair;

//@@author ngdeqi
import static seedu.duke.Messages.MESSAGE_EMPTY_DESCRIPTION;
import static seedu.duke.Messages.MESSAGE_UNPAIR_WRONG_FORMAT;

/**
 * Represents the exception where the user fails to provide any descriptions for an unpair command.
 */
public class UnpairMissingDescriptionException extends ParseUnpairException {

    @Override
    public String toString() {
        return MESSAGE_EMPTY_DESCRIPTION + MESSAGE_UNPAIR_WRONG_FORMAT;
    }
}
