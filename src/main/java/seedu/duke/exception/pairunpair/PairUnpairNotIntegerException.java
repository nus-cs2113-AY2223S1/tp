package seedu.duke.exception.pairunpair;

import static seedu.duke.Messages.MESSAGE_NOT_INTEGER;

public class PairUnpairNotIntegerException extends ParsePairUnpairException {

    @Override
    public String toString() {
        return MESSAGE_NOT_INTEGER;
    }
}
