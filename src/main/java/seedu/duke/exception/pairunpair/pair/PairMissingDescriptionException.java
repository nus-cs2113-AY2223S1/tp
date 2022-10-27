package seedu.duke.exception.pairunpair.pair;

//@@author ngdeqi
import static seedu.duke.Messages.MESSAGE_EMPTY_DESCRIPTION;
import static seedu.duke.Messages.MESSAGE_PAIR_WRONG_FORMAT;

public class PairMissingDescriptionException extends ParsePairException {


    @Override
    public String toString() {
        return MESSAGE_EMPTY_DESCRIPTION + MESSAGE_PAIR_WRONG_FORMAT;
    }
}
