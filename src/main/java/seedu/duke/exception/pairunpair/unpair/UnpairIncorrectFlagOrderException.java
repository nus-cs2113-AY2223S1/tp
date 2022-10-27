package seedu.duke.exception.pairunpair.unpair;

import static seedu.duke.Messages.MESSAGE_UNPAIR_WRONG_FORMAT;
import static seedu.duke.Messages.MESSAGE_WRONG_FLAG_ORDER;

public class UnpairIncorrectFlagOrderException extends ParseUnpairException {

    @Override
    public String toString() {
        return MESSAGE_WRONG_FLAG_ORDER + MESSAGE_UNPAIR_WRONG_FORMAT;
    }

}
