package seedu.duke.parsermanager.pairunpair;

//@@author ngdeqi
import seedu.duke.exception.ExtraParametersException;
import seedu.duke.exception.pairunpair.PairUnpairNotIntegerException;
import seedu.duke.parsermanager.Parser;

import java.util.ArrayList;

import static seedu.duke.CommandStructure.START_INDEX;

public abstract class PairUnpairParser extends Parser {


    protected boolean isFlagPresent(int flagIndexPosition) {
        return (flagIndexPosition != FLAG_ABSENT_RETURN_VALUE);
    }


    protected ArrayList<Integer> convertPairUnpairCommandDetailsToInteger(ArrayList<String> pairDetailsString)
            throws PairUnpairNotIntegerException {

        ArrayList<Integer> integerDetails = new ArrayList<>();
        ArrayList<String> nonIntegerDetails = new ArrayList<>();
        for (String detail : pairDetailsString) {
            int integer;
            try {
                integer = Integer.parseInt(detail);
                // Convert to 0-index
                integerDetails.add(integer - UNIT_VALUE);
            } catch (NumberFormatException e) {
                nonIntegerDetails.add(detail);
            }
        }

        if (!nonIntegerDetails.isEmpty()) {
            throw new PairUnpairNotIntegerException(nonIntegerDetails);
        }
        return integerDetails;
    }

    protected void checkForExtraArguments(String commandDescription, int[] flagIndexPositions)
            throws ExtraParametersException {

        int firstFlagIndex = flagIndexPositions[START_INDEX];
        if (firstFlagIndex != START_INDEX) {
            String extraArgument = commandDescription.substring(START_INDEX, firstFlagIndex);
            throw new ExtraParametersException(extraArgument);
        }
    }
}
