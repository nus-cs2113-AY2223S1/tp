package seedu.duke.exception.pairunpair.unpair;

//@@author ngdeqi
import static seedu.duke.Messages.MESSAGE_NO_EXISTING_PAIR;

/**
 * Represents the exception for when the user tries to unpair a client and property that has not been paired.
 */
public class NoExistingPairException extends CommandUnpairException {


    @Override
    public String toString() {
        return MESSAGE_NO_EXISTING_PAIR;
    }
}
