package seedu.duke.exception.pairunpair.pair;

//@@author ngdeqi
import java.util.ArrayList;

import static seedu.duke.Messages.MESSAGE_MISSING_FLAG;
import static seedu.duke.Messages.MESSAGE_PAIR_WRONG_FORMAT;

/**
 * Represents exception where the user omits flags from the pair command.
 */
public class PairMissingFlagException extends ParsePairException {

    private ArrayList<String> missingFlags;


    public PairMissingFlagException(ArrayList<String> missingFlags) {
        this.missingFlags = missingFlags;
    }

    @Override
    public String toString() {

        String missingFlagsAsString = super.formatMissingFlagsToString(missingFlags);

        return MESSAGE_MISSING_FLAG
                + missingFlagsAsString + System.lineSeparator()
                + MESSAGE_PAIR_WRONG_FORMAT;
    }
}
