package seedu.duke.exception.pairunpair.pair;

import static seedu.duke.Messages.MESSAGE_CLIENT_ALREADY_PAIRED;

public class ClientAlreadyPairedException extends CommandPairException {

    @Override
    public String toString() {
        return MESSAGE_CLIENT_ALREADY_PAIRED;
    }
}
