package seedu.duke.parsermanager;

//@@author ngdeqi
import seedu.duke.command.Command;
import seedu.duke.command.pairunpair.CommandUnpair;
import seedu.duke.exception.pairunpair.PairUnpairNotIntegerException;
import seedu.duke.exception.pairunpair.ParsePairUnpairException;
import seedu.duke.exception.pairunpair.unpair.UnpairIncorrectFlagOrderException;
import seedu.duke.exception.pairunpair.unpair.UnpairMissingDescriptionException;
import seedu.duke.exception.pairunpair.unpair.UnpairMissingFlagException;

import java.util.ArrayList;

import static seedu.duke.CommandStructure.UNPAIR_FLAGS;

public class ParseUnpair extends Parser {
    private final String commandDescription;


    public ParseUnpair(String unpairCommandDescription) {
        this.commandDescription = unpairCommandDescription;

    }

    @Override
    public Command parseCommand() throws ParsePairUnpairException {

        checkForEmptyDescription(commandDescription);
        ArrayList<String> unpairDetailsString = processCommandDetails(commandDescription);
        ArrayList<Integer> unpairDetailsInt = convertUnpairCommandDetailsToInteger(unpairDetailsString);

        return new CommandUnpair(unpairDetailsInt);
    }



    private void checkForEmptyDescription(String commandDetail) throws UnpairMissingDescriptionException {
        boolean isEmptyDescription = isEmptyString(commandDetail);
        if (isEmptyDescription) {
            throw new UnpairMissingDescriptionException();
        }
    }

    private boolean isEmptyString(String commandDetail) {
        return commandDetail.trim().isEmpty();
    }

    private ArrayList<String> processCommandDetails(String rawCommandDetail)
            throws UnpairMissingFlagException, UnpairIncorrectFlagOrderException {

        String[] flags = UNPAIR_FLAGS;
        int[] flagIndexPositions = getFlagIndexPositions(rawCommandDetail, flags);
        checkForMissingFlags(flagIndexPositions, flags);
        checkFlagsOrder(flagIndexPositions);
        return extractCommandDetails(rawCommandDetail, flags, flagIndexPositions);
    }

    private void checkForMissingFlags(int[] flagIndexPositions, String[] flags) throws UnpairMissingFlagException {
        ArrayList<String> missingFlags = new ArrayList<>();

        for (int flagCounter = 0; flagCounter < flags.length; flagCounter++) {
            int indexOfCurrentFlag = flagIndexPositions[flagCounter];
            if (!isFlagPresent(indexOfCurrentFlag)) {
                missingFlags.add(flags[flagCounter]);
            }
        }

        if (!missingFlags.isEmpty()) {
            throw new UnpairMissingFlagException(missingFlags);
        }
    }

    private void checkFlagsOrder(int[] flagIndexPositions) throws UnpairIncorrectFlagOrderException {
        for (int i = 0; i < flagIndexPositions.length - 1; i++) {
            checkForCorrectFlagOrder(flagIndexPositions[i], flagIndexPositions[i + 1]);
        }
    }

    private boolean isFlagPresent(int flagIndexPosition) {
        return (flagIndexPosition != -1);
    }

    private void checkForCorrectFlagOrder(int flagPosition, int nextFlagPosition)
            throws UnpairIncorrectFlagOrderException {
        boolean hasCorrectOrder = (flagPosition < nextFlagPosition);
        if (!hasCorrectOrder) {
            throw new UnpairIncorrectFlagOrderException();
        }
    }

    private ArrayList<Integer> convertUnpairCommandDetailsToInteger(ArrayList<String> unpairDetailsString)
            throws PairUnpairNotIntegerException {

        ArrayList<Integer> integerDetails = new ArrayList<>();
        ArrayList<String> nonIntegerDetails = new ArrayList<>();
        for (String detail : unpairDetailsString) {
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
