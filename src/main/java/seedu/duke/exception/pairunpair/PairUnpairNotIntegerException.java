package seedu.duke.exception.pairunpair;

//@@author ngdeqi
import java.util.ArrayList;

import static seedu.duke.Messages.MESSAGE_NOT_INTEGER;
import static seedu.duke.Messages.MESSAGE_NOT_INTEGER_INSTANCES;

public class PairUnpairNotIntegerException extends ParsePairUnpairException {

    private ArrayList<String> errorStrings;
    private final String INVERTED_COMMAS = "\"";
    private final String WHITE_SPACE = "  ";

    public PairUnpairNotIntegerException(ArrayList<String> errorStrings) {
        this.errorStrings = errorStrings;
    }

    @Override
    public String toString() {
        return MESSAGE_NOT_INTEGER + MESSAGE_NOT_INTEGER_INSTANCES + formatErrorStringsForMessage(errorStrings);
    }

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
