package seedu.duke.parsermanager;

//@@author ngdeqi
import seedu.duke.command.Command;
import seedu.duke.command.check.CommandCheckProperty;
import seedu.duke.exception.NotIntegerException;
import seedu.duke.exception.check.CheckExtraParametersException;
import seedu.duke.exception.check.CheckNotIntegerException;
import seedu.duke.exception.check.ParseCheckException;
import seedu.duke.exception.check.checkproperty.CheckPropertyMissingFlagException;

import java.util.ArrayList;

import static seedu.duke.CommandStructure.INDEX_FLAGS;
import static seedu.duke.CommandStructure.START_INDEX;

/**
 * Parser for check property commands.
 */
public class ParseCheckProperty extends Parser {
    private final String commandDescription;

    public ParseCheckProperty(String checkCommandDescription) {
        this.commandDescription = checkCommandDescription;
    }

    @Override
    public Command parseCommand() throws ParseCheckException {

        ArrayList<String> stringCheckDetails = processCommandDetails(commandDescription);
        ArrayList<Integer> integerCheckDetails;

        try {
            integerCheckDetails = convertProcessedCommandDetailsToInteger(stringCheckDetails);
        } catch (NotIntegerException e) {
            throw new CheckNotIntegerException();
        }

        return new CommandCheckProperty(integerCheckDetails);
    }


    private ArrayList<String> processCommandDetails(String rawCommandDetail) throws CheckPropertyMissingFlagException,
            CheckExtraParametersException {

        String[] flags = INDEX_FLAGS;
        int[] flagIndexPositions = getFlagIndexPositions(rawCommandDetail, flags);
        checkForMissingFlags(flagIndexPositions);
        checkExtraArguments(commandDescription, flagIndexPositions);
        return extractCommandDetails(rawCommandDetail, flags, flagIndexPositions);
    }


    private void checkForMissingFlags(int[] flagIndexPositions) throws CheckPropertyMissingFlagException {
        for (int flagIndex : flagIndexPositions) {
            if (!isFlagPresent(flagIndex)) {
                throw new CheckPropertyMissingFlagException();
            }
        }
    }

    private boolean isFlagPresent(int flagIndexPosition) {
        return (flagIndexPosition != FLAG_ABSENT_RETURN_VALUE);
    }

    private void checkExtraArguments(String commandDescription, int[] flagIndexPositions)
            throws CheckExtraParametersException {

        int firstFlagIndex = flagIndexPositions[START_INDEX];
        if (firstFlagIndex != START_INDEX) {
            String extraArgument = commandDescription.substring(START_INDEX, firstFlagIndex);
            throw new CheckExtraParametersException(extraArgument);
        }

    }

}
