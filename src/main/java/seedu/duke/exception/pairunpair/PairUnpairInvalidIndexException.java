package seedu.duke.exception.pairunpair;

//@@author ngdeqi
import static seedu.duke.Messages.MESSAGE_INVALID_INDEX;

/**
 * Represents exception where user keys in indexes not found in property and client lists for pair and unpair commands.
 */
public class PairUnpairInvalidIndexException extends CommandPairUnpairException {

    @Override
    public String toString() {
        return MESSAGE_INVALID_INDEX;
    }
}
