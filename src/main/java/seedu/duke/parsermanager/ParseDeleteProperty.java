//@@author FeliciaBeatrice

package seedu.duke.parsermanager;

import seedu.duke.PropertyList;
import seedu.duke.command.Command;
import seedu.duke.command.CommandDeleteProperty;
import seedu.duke.exception.NotIntegerException;
import seedu.duke.exception.parsedeleteexception.parsedeletepropertyexception.EmptyDeletePropertyDetailException;
import seedu.duke.exception.parsedeleteexception.parsedeletepropertyexception.ExtraDeletePropertyFlagsException;
import seedu.duke.exception.parsedeleteexception.parsedeletepropertyexception.InvalidDeletePropertyIndexException;
import seedu.duke.exception.parsedeleteexception.parsedeletepropertyexception.MissingDeletePropertyFlagException;
import seedu.duke.exception.parsedeleteexception.parsedeletepropertyexception.ParseDeletePropertyException;

import java.util.ArrayList;

import static seedu.duke.CommandStructure.CHECK_PROPERTY_FLAGS;
import static seedu.duke.Messages.MESSAGE_NOT_INTEGER;

public class ParseDeleteProperty extends Parser {
    private final String commandDescription;
    private final PropertyList propertyList;


    private static final int CORRECT_FLAG_POSITION = 0;
    private static final int INDEX_POSITION = 2;


    public ParseDeleteProperty(String deleteCommandDescription, PropertyList propertyList) {
        this.commandDescription = deleteCommandDescription;
        this.propertyList = propertyList;
    }

    @Override
    public Command parseCommand() throws ParseDeletePropertyException, NotIntegerException {
        try {
            checkForEmptyDetails(commandDescription);

            ArrayList<String> deletePropertyDetailsString = processCommandDetails(commandDescription);
            ArrayList<Integer> deletePropertyDetailsInt = convertProcessedCommandDetailsToInteger(
                    deletePropertyDetailsString);

            int propertyIndex = deletePropertyDetailsInt.get(0);
            checkForInvalidPropertyIndexDelete(propertyIndex);
            return new CommandDeleteProperty(propertyIndex);
        } catch (NotIntegerException e) {
            throw new NotIntegerException(MESSAGE_NOT_INTEGER);
        }
    }

    private ArrayList<String> processCommandDetails(String rawCommandDetail)
            throws ParseDeletePropertyException {

        String[] flags = CHECK_PROPERTY_FLAGS;
        int[] flagIndexPositions = getFlagIndexPositions(rawCommandDetail, flags);
        checkForMissingFlags(flagIndexPositions);
        checkForExtraFlags(flagIndexPositions);
        return extractCommandDetails(rawCommandDetail, flags, flagIndexPositions);
    }

    private void checkForExtraFlags(int[] flagIndexPositions) throws ExtraDeletePropertyFlagsException {
        if (flagIndexPositions[0] != CORRECT_FLAG_POSITION) {
            throw new ExtraDeletePropertyFlagsException();
        }
    }

    private void checkForInvalidPropertyIndexDelete(int propertyIndex) throws InvalidDeletePropertyIndexException {
        int currentListSize = propertyList.getCurrentListSize();
        if (propertyIndex < 0 || propertyIndex >= currentListSize) {
            throw new InvalidDeletePropertyIndexException();
        }
    }

    private void checkForMissingFlags(int[] flagIndexPositions) throws MissingDeletePropertyFlagException {
        for (int flagIndex : flagIndexPositions) {
            if (!isFlagPresent(flagIndex)) {
                throw new MissingDeletePropertyFlagException();
            }
        }
    }

    private boolean isFlagPresent(int flagIndexPosition) {
        return (flagIndexPosition != -1);
    }

    private void checkForEmptyDetails(String commandDetail) throws EmptyDeletePropertyDetailException {
        boolean isEmptyDetail = isEmptyString(commandDetail);
        if (isEmptyDetail) {
            throw new EmptyDeletePropertyDetailException();
        }
    }
}
