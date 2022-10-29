package seedu.duke.exception.pairunpair.pair;

//@@author ngdeqi
import static seedu.duke.Messages.MESSAGE_EMPTY_DESCRIPTION;
import static seedu.duke.Messages.MESSAGE_PAIR_WRONG_FORMAT;

/**
 * Represents exception where the user does not add a description sto the pair command.
 */
public class PairMissingDescriptionException extends ParsePairException {


    @Override
    public String toString() {
        return MESSAGE_EMPTY_DESCRIPTION + MESSAGE_PAIR_WRONG_FORMAT;
    }
}
