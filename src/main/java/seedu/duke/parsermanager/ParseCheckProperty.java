package seedu.duke.parsermanager;

import seedu.duke.PropertyList;
import seedu.duke.command.Command;
import seedu.duke.command.CommandCheckProperty;
import seedu.duke.exception.IncorrectFlagOrderException;
import seedu.duke.exception.InvalidIndexException;
import seedu.duke.exception.MissingFlagException;
import seedu.duke.exception.NotIntegerException;

import java.util.ArrayList;

import static seedu.duke.CommandStructure.CHECK_PROPERTY_FLAGS;
import static seedu.duke.Messages.EXCEPTION;
import static seedu.duke.Messages.MESSAGE_CHECK_PROPERTY_WRONG_FORMAT;
import static seedu.duke.Messages.MESSAGE_INVALID_INDEX;
import static seedu.duke.Messages.MESSAGE_NOT_INTEGER;

public class ParseCheckProperty extends Parser {
    private final String commandDescription;
    private final PropertyList propertyList;

    public ParseCheckProperty(String checkCommandDescription, PropertyList propertyList) {
        this.commandDescription = checkCommandDescription;
        this.propertyList = propertyList;
    }

    @Override
    public Command parseCommand() throws InvalidIndexException, MissingFlagException,
            IncorrectFlagOrderException, NotIntegerException {
        try {
            ArrayList<String> checkDetailsString = processCommandDetails(commandDescription);
            ArrayList<Integer> checkDetailsInt = convertProcessedCommandDetailsToInteger(checkDetailsString);

            validateCheckPropertyDetails(checkDetailsInt);
            return new CommandCheckProperty(checkDetailsInt);
        } catch (InvalidIndexException e) {
            throw new InvalidIndexException(MESSAGE_INVALID_INDEX);
        } catch (MissingFlagException e) {
            throw new MissingFlagException(MESSAGE_CHECK_PROPERTY_WRONG_FORMAT);
        } catch (IncorrectFlagOrderException e) {
            throw new IncorrectFlagOrderException(MESSAGE_CHECK_PROPERTY_WRONG_FORMAT);
        } catch (NotIntegerException e) {
            throw new NotIntegerException(MESSAGE_NOT_INTEGER);
        }
    }

    private ArrayList<String> processCommandDetails(String rawCommandDetail)
            throws MissingFlagException, IncorrectFlagOrderException {

        String[] flags = CHECK_PROPERTY_FLAGS;
        int[] flagIndexPositions = getFlagIndexPositions(rawCommandDetail, flags);
        checkForMissingFlags(flagIndexPositions);
        checkFlagsOrder(flagIndexPositions);
        return extractCommandDetails(rawCommandDetail, flags, flagIndexPositions);
    }

    private void validateCheckPropertyDetails(ArrayList<Integer> checkPropertyDetails) throws InvalidIndexException {
        int propertyIndex = checkPropertyDetails.get(0);
        checkForPropertyListIndexOutOfBounds(propertyIndex);
    }

    private void checkForMissingFlags(int[] flagIndexPositions) throws MissingFlagException {
        for (int flagIndex : flagIndexPositions) {
            if (!isFlagPresent(flagIndex)) {
                throw  new MissingFlagException(EXCEPTION);
            }
        }
    }

    private void checkFlagsOrder(int[] flagIndexPositions) throws IncorrectFlagOrderException {
        for (int i = 0; i < flagIndexPositions.length - 1; i++) {
            checkForCorrectFlagOrder(flagIndexPositions[i], flagIndexPositions[i + 1]);
        }
    }

    private void checkForPropertyListIndexOutOfBounds(int propertyIndex) throws InvalidIndexException {
        if (propertyIndex < 0 || propertyIndex > propertyList.getCurrentListSize() - 1) {
            throw new InvalidIndexException(EXCEPTION);
        }
    }

    private boolean isFlagPresent(int flagIndexPosition) {
        return (flagIndexPosition != -1);
    }

    private void checkForCorrectFlagOrder(int flagPosition, int nextFlagPosition) throws IncorrectFlagOrderException {
        boolean hasCorrectOrder = (flagPosition < nextFlagPosition);
        if (!hasCorrectOrder) {
            throw new IncorrectFlagOrderException(EXCEPTION);
        }
    }
}
