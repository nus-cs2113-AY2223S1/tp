package seedu.duke.exception.pairunpair.pair;

import static seedu.duke.Messages.MESSAGE_EXISTING_PAIR;

public class ExistingPairException extends CommandPairException {

    @Override
    public String toString() {
        return MESSAGE_EXISTING_PAIR;
    }
}
