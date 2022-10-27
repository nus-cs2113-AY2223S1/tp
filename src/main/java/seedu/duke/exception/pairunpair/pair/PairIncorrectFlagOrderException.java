package seedu.duke.exception.pairunpair.pair;

import static seedu.duke.Messages.MESSAGE_UNPAIR_WRONG_FORMAT;
import static seedu.duke.Messages.MESSAGE_WRONG_FLAG_ORDER;

public class PairIncorrectFlagOrderException extends ParsePairException {

    @Override
    public String toString() {
        return MESSAGE_WRONG_FLAG_ORDER + MESSAGE_UNPAIR_WRONG_FORMAT;
    }

}
