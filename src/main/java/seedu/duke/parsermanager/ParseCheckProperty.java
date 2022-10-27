package seedu.duke.parsermanager;

//@@author ngdeqi
import seedu.duke.command.Command;
import seedu.duke.command.check.CommandCheckProperty;
import seedu.duke.exception.check.CheckNotIntegerException;
import seedu.duke.exception.check.ParseCheckException;
import seedu.duke.exception.check.checkproperty.CheckPropertyMissingFlagException;

import java.util.ArrayList;

import static seedu.duke.CommandStructure.CHECK_PROPERTY_FLAGS;

public class ParseCheckProperty extends Parser {
    private final String commandDescription;

    public ParseCheckProperty(String checkCommandDescription) {
        this.commandDescription = checkCommandDescription;
    }

    @Override
    public Command parseCommand() throws ParseCheckException {

        ArrayList<String> checkDetailsString = processCommandDetails(commandDescription);
        ArrayList<Integer> checkDetailsInt = convertCheckCommandDetailsToInteger(checkDetailsString);

        return new CommandCheckProperty(checkDetailsInt);
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
        return (flagIndexPosition != -1);
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
                throw new CheckNotIntegerException();
            }
        }

        return integerDetails;
    }
}
