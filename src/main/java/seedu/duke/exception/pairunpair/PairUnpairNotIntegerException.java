package seedu.duke.exception.pairunpair;

//@@author ngdeqi
import java.util.ArrayList;

import static seedu.duke.Messages.MESSAGE_NOT_INTEGER;
import static seedu.duke.Messages.MESSAGE_NOT_INTEGER_INSTANCES;

/**
 * Represents the exception where the user keys in non-positive integers for the client and property indexes for pair
 * and unpair commands.
 */
public class PairUnpairNotIntegerException extends ParsePairUnpairException {

    private ArrayList<String> errorStrings;
    private static final String INVERTED_COMMAS = "\"";
    private static final String WHITE_SPACE = "  ";

    public PairUnpairNotIntegerException(ArrayList<String> errorStrings) {
        this.errorStrings = errorStrings;
    }

    @Override
    public String toString() {
        return MESSAGE_NOT_INTEGER + MESSAGE_NOT_INTEGER_INSTANCES + formatErrorStringsForMessage(errorStrings);
    }

    /**
     * Helper method to convert non-integer section of arguments from ArrayList to a printable format.
     *
     * @param errorStrings Array of non-integer section of the arguments.
     * @return Formatted non-integer section of arguments ina single string.
     */
    private String formatErrorStringsForMessage(ArrayList<String> errorStrings) {
        StringBuilder sb = new StringBuilder();
        for (String errorString: errorStrings) {
            sb.append(INVERTED_COMMAS);
            sb.append(errorString);
            sb.append(INVERTED_COMMAS);
            sb.append(WHITE_SPACE);
        }

        return sb.toString();
    }
}
