package seedu.duke.parsermanager;

//@@author ngdeqi
import seedu.duke.command.Command;
import seedu.duke.command.check.CommandCheckProperty;
import seedu.duke.exception.check.CheckNotIntegerException;
import seedu.duke.exception.check.ParseCheckException;
import seedu.duke.exception.check.checkproperty.CheckPropertyMissingFlagException;

import java.util.ArrayList;

import static seedu.duke.CommandStructure.CHECK_PROPERTY_FLAGS;

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
        ArrayList<Integer> integerCheckDetails = convertCheckCommandDetailsToInteger(stringCheckDetails);

        return new CommandCheckProperty(integerCheckDetails);
    }


    private ArrayList<String> processCommandDetails(String rawCommandDetail) throws CheckPropertyMissingFlagException {
        String[] flags = CHECK_PROPERTY_FLAGS;
        int[] flagIndexPositions = getFlagIndexPositions(rawCommandDetail, flags);
        checkForMissingFlags(flagIndexPositions);
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

    private ArrayList<Integer> convertCheckCommandDetailsToInteger(ArrayList<String> checkDetailsString)
            throws CheckNotIntegerException {
        ArrayList<Integer> integerDetails = new ArrayList<>();
        for (String detail : checkDetailsString) {
            int integer;
            try {
                integer = Integer.parseInt(detail);
                // Convert to 0-index
                integerDetails.add(integer - UNIT_VALUE);
            } catch (NumberFormatException e) {
                throw new CheckNotIntegerException(detail);
            }
        }

        return integerDetails;
    }
}
