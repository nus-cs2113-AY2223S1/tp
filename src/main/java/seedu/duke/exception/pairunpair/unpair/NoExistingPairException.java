package seedu.duke.exception.pairunpair.unpair;

//@@author ngdeqi
import static seedu.duke.Messages.MESSAGE_NO_EXISTING_PAIR;

public class NoExistingPairException extends CommandUnpairException {


    @Override
    public String toString() {
        return MESSAGE_NO_EXISTING_PAIR;
    }
}
