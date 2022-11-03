package seedu.duke.exception.pairunpair.pair;

//@@author ngdeqi
import seedu.duke.exception.pairunpair.ParsePairUnpairException;

import static seedu.duke.Messages.MESSAGE_EXTRA_ARGUMENTS;
import static seedu.duke.Messages.MESSAGE_PAIR_WRONG_FORMAT;


/**
 * Representation of the exception where the user enters extra arguments for a pair command.
 */
public class PairExtraArgumentsException extends ParsePairUnpairException {

    private String extraArgument;
    private final String INVERTED_COMMAS = "\"";

    public PairExtraArgumentsException(String extraArgument) {
        this.extraArgument = extraArgument;
    }

    @Override
    public String toString() {
        return MESSAGE_EXTRA_ARGUMENTS
                + INVERTED_COMMAS + extraArgument + INVERTED_COMMAS + System.lineSeparator()
                + MESSAGE_PAIR_WRONG_FORMAT;
    }
}
