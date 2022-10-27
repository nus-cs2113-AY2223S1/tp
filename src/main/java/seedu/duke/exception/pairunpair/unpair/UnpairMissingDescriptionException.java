package seedu.duke.exception.pairunpair.unpair;

//@@author ngdeqi
import static seedu.duke.Messages.MESSAGE_EMPTY_DESCRIPTION;
import static seedu.duke.Messages.MESSAGE_UNPAIR_WRONG_FORMAT;

public class UnpairMissingDescriptionException extends ParseUnpairException {

    @Override
    public String toString() {
        return MESSAGE_EMPTY_DESCRIPTION + MESSAGE_UNPAIR_WRONG_FORMAT;
    }
}
