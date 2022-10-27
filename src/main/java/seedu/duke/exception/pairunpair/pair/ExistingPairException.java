package seedu.duke.exception.pairunpair.pair;

//@@author ngdeqi
import static seedu.duke.Messages.MESSAGE_EXISTING_PAIR;

/**
 * Represents an exception for when the user tries to pair a property and client which already have been paired.
 */
public class ExistingPairException extends CommandPairException {

    @Override
    public String toString() {
        return MESSAGE_EXISTING_PAIR;
    }
}
