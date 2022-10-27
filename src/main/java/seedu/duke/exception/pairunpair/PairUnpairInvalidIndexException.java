package seedu.duke.exception.pairunpair;

import static seedu.duke.Messages.MESSAGE_INVALID_INDEX;

public class PairUnpairInvalidIndexException extends CommandPairUnpairException {

    @Override
    public String toString() {
        return MESSAGE_INVALID_INDEX;
    }
}
