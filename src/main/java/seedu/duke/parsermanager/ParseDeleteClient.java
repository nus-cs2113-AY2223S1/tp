package seedu.duke.parsermanager;

import seedu.duke.ClientList;
import seedu.duke.command.Command;
import seedu.duke.command.CommandDeleteClient;
import seedu.duke.exception.EmptyDetailException;
import seedu.duke.exception.IncorrectFlagOrderException;
import seedu.duke.exception.InvalidIndexException;
import seedu.duke.exception.MissingFlagException;
import seedu.duke.exception.NotIntegerException;

import java.util.ArrayList;

import static seedu.duke.CommandStructure.DELETE_CLIENT_FLAGS;
import static seedu.duke.Messages.EXCEPTION;
import static seedu.duke.Messages.MESSAGE_DELETE_CLIENT_WRONG_FORMAT;
import static seedu.duke.Messages.MESSAGE_INVALID_INDEX;
import static seedu.duke.Messages.MESSAGE_NOT_INTEGER;

public class ParseDeleteClient extends Parser {
    private final String commandDescription;
    private final ClientList clientList;

    public ParseDeleteClient(String deleteCommandDescription, ClientList clientList) {
        this.commandDescription = deleteCommandDescription;
        this.clientList = clientList;
    }

    @Override
    public Command parseCommand() throws InvalidIndexException, MissingFlagException, IncorrectFlagOrderException,
            NotIntegerException, EmptyDetailException {
        try {
            checkForEmptyDetails(commandDescription);

            ArrayList<String> deleteClientDetailsString = processCommandDetails(commandDescription);
            ArrayList<Integer> deleteClientDetailsInt = convertProcessedCommandDetailsToInteger(
                    deleteClientDetailsString);

            int clientIndex = deleteClientDetailsInt.get(0);
            checkForInvalidClientIndexDelete(clientIndex);
            return new CommandDeleteClient(clientIndex);

        } catch (InvalidIndexException e) {
            throw new InvalidIndexException(MESSAGE_INVALID_INDEX);
        } catch (MissingFlagException e) {
            throw new MissingFlagException(MESSAGE_DELETE_CLIENT_WRONG_FORMAT);
        } catch (IncorrectFlagOrderException e) {
            throw new IncorrectFlagOrderException(MESSAGE_DELETE_CLIENT_WRONG_FORMAT);
        } catch (NotIntegerException e) {
            throw new NotIntegerException(MESSAGE_NOT_INTEGER);
        } catch (EmptyDetailException e) {
            throw new EmptyDetailException(MESSAGE_DELETE_CLIENT_WRONG_FORMAT);
        }
    }

    private ArrayList<String> processCommandDetails(String rawCommandDetail)
            throws MissingFlagException, IncorrectFlagOrderException {

        String[] flags = DELETE_CLIENT_FLAGS;
        int[] flagIndexPositions = getFlagIndexPositions(rawCommandDetail, flags);
        checkForMissingFlags(flagIndexPositions);
        checkFlagsOrder(flagIndexPositions);
        return extractCommandDetails(rawCommandDetail, flags, flagIndexPositions);
    }

    private void checkForInvalidClientIndexDelete(int clientIndex) throws InvalidIndexException {
        int currentListSize = clientList.getCurrentListSize();
        if (clientIndex < 0 || clientIndex >= currentListSize) {
            throw new InvalidIndexException(EXCEPTION);
        }
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
