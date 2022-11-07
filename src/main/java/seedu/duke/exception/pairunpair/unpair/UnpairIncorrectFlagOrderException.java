package seedu.duke.exception.pairunpair.unpair;

//@@author ngdeqi
import static seedu.duke.Messages.MESSAGE_UNPAIR_WRONG_FORMAT;
import static seedu.duke.Messages.MESSAGE_WRONG_FLAG_ORDER;

/**
 * Represents the exception where the user enters flags in the wrong order for the unpair command.
 */
public class UnpairIncorrectFlagOrderException extends ParseUnpairException {

    @Override
    public String toString() {
        return MESSAGE_WRONG_FLAG_ORDER + MESSAGE_UNPAIR_WRONG_FORMAT;
    }

}
