package seedu.duke.parsermanager.pairunpair;

//@@author ngdeqi
import seedu.duke.command.Command;
import seedu.duke.command.pairunpair.CommandUnpair;
import seedu.duke.exception.ExtraParametersException;
import seedu.duke.exception.pairunpair.ParsePairUnpairException;
import seedu.duke.exception.pairunpair.unpair.UnpairExtraArgumentsException;
import seedu.duke.exception.pairunpair.unpair.UnpairIncorrectFlagOrderException;
import seedu.duke.exception.pairunpair.unpair.UnpairMissingDescriptionException;
import seedu.duke.exception.pairunpair.unpair.UnpairMissingFlagException;

import java.util.ArrayList;

import static seedu.duke.CommandStructure.UNPAIR_FLAGS;

/**
 * Parser for unpair commands.
 */
public class CommandUnpairParser extends CommandPairUnpairParser {
    private final String commandDescription;

    public CommandUnpairParser(String unpairCommandDescription) {
        this.commandDescription = unpairCommandDescription;

    }

    @Override
    public Command parseCommand() throws ParsePairUnpairException {

        checkForEmptyDescription(commandDescription);
        ArrayList<String> stringDetails = processCommandDetails(commandDescription);
        ArrayList<Integer> integerDetails = convertPairUnpairCommandDetailsToInteger(stringDetails);

        return new CommandUnpair(integerDetails);
    }


    private void checkForEmptyDescription(String commandDetail) throws UnpairMissingDescriptionException {
        boolean isEmptyDescription = isEmptyString(commandDetail);
        if (isEmptyDescription) {
            throw new UnpairMissingDescriptionException();
        }
    }

    private ArrayList<String> processCommandDetails(String rawCommandDetail)
            throws UnpairMissingFlagException, UnpairIncorrectFlagOrderException, UnpairExtraArgumentsException {

        String[] flags = UNPAIR_FLAGS;
        int[] flagIndexPositions = getFlagIndexPositions(rawCommandDetail, flags);

        checkForMissingFlags(flagIndexPositions, flags);
        checkFlagsOrder(flagIndexPositions);
        try {
            super.checkForExtraArguments(rawCommandDetail, flagIndexPositions);
        } catch (ExtraParametersException e) {
            throw new UnpairExtraArgumentsException(e.toString());
        }

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
            if (!isCorrectFlagOrder(flagIndexPositions[i], flagIndexPositions[i + 1])) {
                throw new UnpairIncorrectFlagOrderException();
            }
        }
    }

}
