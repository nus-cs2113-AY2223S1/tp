package seedu.duke.parsermanager.pairunpair;

//@@author ngdeqi
import seedu.duke.command.Command;
import seedu.duke.command.pairunpair.CommandPair;
import seedu.duke.exception.ExtraParametersException;
import seedu.duke.exception.pairunpair.ParsePairUnpairException;
import seedu.duke.exception.pairunpair.pair.PairExtraArgumentsException;
import seedu.duke.exception.pairunpair.pair.PairIncorrectFlagOrderException;
import seedu.duke.exception.pairunpair.pair.PairMissingDescriptionException;
import seedu.duke.exception.pairunpair.pair.PairMissingFlagException;

import java.util.ArrayList;

import static seedu.duke.CommandStructure.PAIR_FLAGS;

/**
 * Parser for pair commands.
 */
public class CommandPairParser extends CommandPairUnpairParser {
    private final String commandDescription;

    public CommandPairParser(String pairCommandDescription) {
        commandDescription = pairCommandDescription;

    }

    @Override
    public Command parseCommand() throws ParsePairUnpairException {

        checkForEmptyDescription(commandDescription);
        ArrayList<String> stringDetails = processCommandDetails(commandDescription);
        ArrayList<Integer> integerDetails = convertPairUnpairCommandDetailsToInteger(stringDetails);

        return new CommandPair(integerDetails);
    }


    private void checkForEmptyDescription(String commandDetail) throws PairMissingDescriptionException {
        boolean isEmptyDescription = isEmptyString(commandDetail);
        if (isEmptyDescription) {
            throw new PairMissingDescriptionException();
        }
    }

    private ArrayList<String> processCommandDetails(String rawCommandDetail)
            throws PairMissingFlagException, PairIncorrectFlagOrderException, PairExtraArgumentsException {

        String[] flags = PAIR_FLAGS;
        int[] flagIndexPositions = getFlagIndexPositions(rawCommandDetail, flags);

        checkForMissingFlags(flagIndexPositions, flags);
        checkFlagsOrder(flagIndexPositions);
        try {
            super.checkForExtraArguments(rawCommandDetail, flagIndexPositions);
        } catch (ExtraParametersException e) {
            throw new PairExtraArgumentsException(e.toString());
        }
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
            if (!isCorrectFlagOrder(flagIndexPositions[i], flagIndexPositions[i + 1])) {
                throw new PairIncorrectFlagOrderException();
            }
        }
    }

}
