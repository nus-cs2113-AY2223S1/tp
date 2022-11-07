package seedu.duke.exception.pairunpair.pair;

//@@author ngdeqi
import static seedu.duke.Messages.MESSAGE_PAIR_WRONG_FORMAT;
import static seedu.duke.Messages.MESSAGE_WRONG_FLAG_ORDER;

/**
 * Represents exception where the user enters flags in the wrong order for pair commands.
 */
public class PairIncorrectFlagOrderException extends ParsePairException {

    @Override
    public String toString() {
        return MESSAGE_WRONG_FLAG_ORDER + MESSAGE_PAIR_WRONG_FORMAT;
    }

}
