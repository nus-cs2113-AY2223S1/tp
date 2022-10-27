package seedu.duke.parsermanager;

//@@author ngdeqi
import seedu.duke.command.Command;
import seedu.duke.command.pairunpair.CommandPair;
import seedu.duke.exception.pairunpair.PairUnpairNotIntegerException;
import seedu.duke.exception.pairunpair.ParsePairUnpairException;
import seedu.duke.exception.pairunpair.pair.PairIncorrectFlagOrderException;
import seedu.duke.exception.pairunpair.pair.PairMissingDescriptionException;
import seedu.duke.exception.pairunpair.pair.PairMissingFlagException;

import java.util.ArrayList;

import static seedu.duke.CommandStructure.PAIR_FLAGS;

public class ParsePair extends Parser {
    private final String commandDescription;

    public ParsePair(String pairCommandDescription) {
        commandDescription = pairCommandDescription;

    }

    @Override
    public Command parseCommand() throws ParsePairUnpairException {

        checkForEmptyDescription(commandDescription);
        ArrayList<String> pairDetailsString = processCommandDetails(commandDescription);
        ArrayList<Integer> pairDetailsInt = convertPairCommandDetailsToInteger(pairDetailsString);

        return new CommandPair(pairDetailsInt);
    }


    private void checkForEmptyDescription(String commandDetail) throws PairMissingDescriptionException {
        boolean isEmptyDescription = isEmptyString(commandDetail);
        if (isEmptyDescription) {
            throw new PairMissingDescriptionException();
        }
    }

    private boolean isEmptyString(String commandDetail) {
        return commandDetail.trim().isEmpty();
    }


    private ArrayList<String> processCommandDetails(String rawCommandDetail)
            throws PairMissingFlagException, PairIncorrectFlagOrderException {

        String[] flags = PAIR_FLAGS;
        int[] flagIndexPositions = getFlagIndexPositions(rawCommandDetail, flags);
        checkForMissingFlags(flagIndexPositions, flags);
        checkFlagsOrder(flagIndexPositions);
        return extractCommandDetails(rawCommandDetail, flags, flagIndexPositions);
    }

    private void checkForMissingFlags(int[] flagIndexPositions, String[] flags) throws PairMissingFlagException {
        ArrayList<String> missingFlags = new ArrayList<>();

        for (int flagCounter = 0; flagCounter < flags.length; flagCounter++) {
            int indexOfCurrentFlag = flagIndexPositions[flagCounter];
            if (!isFlagPresent(indexOfCurrentFlag)) {
                missingFlags.add(flags[flagCounter]);
            }
        }

        if (!missingFlags.isEmpty()) {
            throw new PairMissingFlagException(missingFlags);
        }
    }

    private void checkFlagsOrder(int[] flagIndexPositions) throws PairIncorrectFlagOrderException {
        for (int i = 0; i < flagIndexPositions.length - 1; i++) {
            checkForCorrectFlagOrder(flagIndexPositions[i], flagIndexPositions[i + 1]);
        }
    }

    private boolean isFlagPresent(int flagIndexPosition) {
        return (flagIndexPosition != -1);
    }

    private void checkForCorrectFlagOrder(int flagPosition, int nextFlagPosition)
            throws PairIncorrectFlagOrderException {
        boolean hasCorrectOrder = (flagPosition < nextFlagPosition);
        if (!hasCorrectOrder) {
            throw new PairIncorrectFlagOrderException();
        }
    }

    private ArrayList<Integer> convertPairCommandDetailsToInteger(ArrayList<String> pairDetailsString)
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

}
