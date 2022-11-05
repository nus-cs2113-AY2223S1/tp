package seedu.duke.exception.pairunpair;

//@@author ngdeqi
import seedu.duke.exception.DukeParseException;

import java.util.ArrayList;

/**
 * Represents exceptions where there are parsing problems for pair and unpair commands.
 */
public abstract class ParsePairUnpairException extends DukeParseException {

    private static final String WHITESPACE = " ";

    /**
     * Helper method for child classes to convert an array of missing flags to a printable string.
     *
     * @param missingFlags Array of missing flags.
     * @return Missing Flags as a single string.
     */

    protected String formatMissingFlagsToString(ArrayList<String> missingFlags) {
        StringBuilder missingFlagStringBuilder = new StringBuilder();

        for (String missingFlag : missingFlags) {
            missingFlagStringBuilder.append(missingFlag).append(WHITESPACE);
        }

        return missingFlagStringBuilder.toString();
    }
}
