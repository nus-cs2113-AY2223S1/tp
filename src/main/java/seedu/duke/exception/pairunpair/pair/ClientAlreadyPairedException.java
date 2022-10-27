package seedu.duke.exception.pairunpair.pair;

//@@author ngdeqi
import static seedu.duke.Messages.MESSAGE_CLIENT_ALREADY_PAIRED;

/**
 * Represents an exception where the user tries to pair an unpaired property with a client who is already paired with
 * another property.
 */
public class ClientAlreadyPairedException extends CommandPairException {

    @Override
    public String toString() {
        return MESSAGE_CLIENT_ALREADY_PAIRED;
    }
}
