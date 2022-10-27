package seedu.duke.exception.pairunpair.pair;

import static seedu.duke.Messages.MESSAGE_MISSING_FLAG;
import static seedu.duke.Messages.MESSAGE_PAIR_WRONG_FORMAT;

public class PairMissingFlagException extends ParsePairException {

    private final String WHITESPACE = " ";
    private String[] missingFlags;


    public PairMissingFlagException(String[] missingFlags) {
        this.missingFlags = missingFlags;
    }

    @Override
    public String toString() {

        StringBuilder missingFlagStringBuilder = new StringBuilder();
        for (String missingFlag : missingFlags) {
            missingFlagStringBuilder.append(missingFlag).append(WHITESPACE);
        }

        String missingFlagsAsString = missingFlagStringBuilder.toString();

        return MESSAGE_MISSING_FLAG
                + missingFlagsAsString + System.lineSeparator()
                + MESSAGE_PAIR_WRONG_FORMAT;
    }
}
