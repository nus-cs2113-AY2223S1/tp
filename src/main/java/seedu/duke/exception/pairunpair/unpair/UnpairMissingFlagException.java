package seedu.duke.exception.pairunpair.unpair;

//@@author ngdeqi
import java.util.ArrayList;

import static seedu.duke.Messages.MESSAGE_MISSING_FLAG;
import static seedu.duke.Messages.MESSAGE_UNPAIR_WRONG_FORMAT;

/**
 * Represents exception where the user omits flags for the unpair command.
 */
public class UnpairMissingFlagException extends ParseUnpairException {

    private ArrayList<String> missingFlags;

    public UnpairMissingFlagException(ArrayList<String> missingFlags) {
        this.missingFlags = missingFlags;
    }

    @Override
    public String toString() {
        String missingFlagsAsString = super.formatMissingFlagsToString(missingFlags);

        return MESSAGE_MISSING_FLAG
                + missingFlagsAsString + System.lineSeparator()
                + MESSAGE_UNPAIR_WRONG_FORMAT;
    }
}
