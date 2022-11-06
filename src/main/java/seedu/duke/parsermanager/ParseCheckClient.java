//@@author FeliciaBeatrice

package seedu.duke.parsermanager;

import seedu.duke.ClientList;
import seedu.duke.command.Command;
import seedu.duke.command.check.CommandCheckClient;
import seedu.duke.exception.ExtraFlagsException;
import seedu.duke.exception.IncorrectFlagOrderException;
import seedu.duke.exception.InvalidIndexException;
import seedu.duke.exception.MissingFlagException;
import seedu.duke.exception.NotIntegerException;

import java.util.ArrayList;

import static seedu.duke.CommandStructure.CHECK_CLIENT_FLAGS;
import static seedu.duke.Messages.EXCEPTION;
import static seedu.duke.Messages.MESSAGE_CHECK_CLIENT_WRONG_FORMAT;
import static seedu.duke.Messages.MESSAGE_INVALID_INDEX;
import static seedu.duke.Messages.MESSAGE_NOT_INTEGER;

public class ParseCheckClient extends Parser {
    private final String commandDescription;

    private final ClientList clientList;


    private static final int CORRECT_FLAG_POSITION = 8;
    private static final int NO_FLAG_FOUND = -1;


    public ParseCheckClient(String checkCommandDescription, ClientList clientList) {
        this.commandDescription = checkCommandDescription;
        this.clientList = clientList;
    }

    @Override
    public Command parseCommand() throws InvalidIndexException, MissingFlagException,
            IncorrectFlagOrderException, NotIntegerException, ExtraFlagsException {
        try {
            ArrayList<String> stringCheckDetails = processCommandDetails(commandDescription);
            ArrayList<Integer> integerCheckDetails = convertProcessedCommandDetailsToInteger(stringCheckDetails);

            validateCheckClientDetails(integerCheckDetails);
            return new CommandCheckClient(integerCheckDetails);
        } catch (InvalidIndexException e) {
            throw new InvalidIndexException(MESSAGE_INVALID_INDEX);
        } catch (MissingFlagException e) {
            throw new MissingFlagException(MESSAGE_CHECK_CLIENT_WRONG_FORMAT);
        } catch (IncorrectFlagOrderException e) {
            throw new IncorrectFlagOrderException(MESSAGE_CHECK_CLIENT_WRONG_FORMAT);
        } catch (NotIntegerException e) {
            throw new NotIntegerException(MESSAGE_NOT_INTEGER);
        } catch (ExtraFlagsException e) {
            throw new ExtraFlagsException(MESSAGE_CHECK_CLIENT_WRONG_FORMAT);
        }
    }

    private ArrayList<String> processCommandDetails(String rawCommandDetail)
            throws MissingFlagException, IncorrectFlagOrderException, ExtraFlagsException {

        String[] flags = CHECK_CLIENT_FLAGS;
        int[] flagIndexPositions = getFlagIndexPositions(rawCommandDetail, flags);
        checkForExtraFlags(flagIndexPositions);
        checkForMissingFlags(flagIndexPositions);
        checkFlagsOrder(flagIndexPositions);
        return extractCommandDetails(rawCommandDetail, flags, flagIndexPositions);
    }

    private void checkForExtraFlags(int[] flagIndexPositions) throws ExtraFlagsException {
        if (flagIndexPositions[0] != CORRECT_FLAG_POSITION) {
            throw new ExtraFlagsException(EXCEPTION);
        }
    }

    private void validateCheckClientDetails(ArrayList<Integer> clientDetails) throws InvalidIndexException {
        int clientIndex = clientDetails.get(0);
        checkForClientListIndexOutOfBounds(clientIndex);
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

    private void checkForClientListIndexOutOfBounds(int clientIndex) throws InvalidIndexException {
        if (clientIndex < 0 || clientIndex > clientList.getCurrentListSize() - 1) {
            throw new InvalidIndexException(EXCEPTION);
        }
    }

    private boolean isFlagPresent(int flagIndexPosition) {
        return (flagIndexPosition != NO_FLAG_FOUND);
    }

    private void checkForCorrectFlagOrder(int flagPosition, int nextFlagPosition) throws IncorrectFlagOrderException {
        boolean hasCorrectOrder = (flagPosition < nextFlagPosition);
        if (!hasCorrectOrder) {
            throw new IncorrectFlagOrderException(EXCEPTION);
        }
    }
}
