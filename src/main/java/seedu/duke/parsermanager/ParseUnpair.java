package seedu.duke.parsermanager;

import seedu.duke.Client;
import seedu.duke.ClientList;
import seedu.duke.PairingList;
import seedu.duke.Property;
import seedu.duke.PropertyList;
import seedu.duke.command.Command;
import seedu.duke.command.CommandUnpair;
import seedu.duke.exception.EmptyDescriptionException;
import seedu.duke.exception.IncorrectFlagOrderException;
import seedu.duke.exception.InvalidIndexException;
import seedu.duke.exception.MissingFlagException;
import seedu.duke.exception.NoExistingPairException;
import seedu.duke.exception.NotIntegerException;

import java.util.ArrayList;

import static seedu.duke.CommandStructure.UNPAIR_FLAGS;
import static seedu.duke.Messages.EXCEPTION;
import static seedu.duke.Messages.MESSAGE_EMPTY_DESCRIPTION;
import static seedu.duke.Messages.MESSAGE_INVALID_INDEX;
import static seedu.duke.Messages.MESSAGE_NOT_INTEGER;
import static seedu.duke.Messages.MESSAGE_NO_EXISTING_PAIR;
import static seedu.duke.Messages.MESSAGE_UNPAIR_WRONG_FORMAT;

public class ParseUnpair extends Parser {
    private final String commandDescription;
    private final ClientList clientList;
    private final PropertyList propertyList;
    private final PairingList pairingList;

    public ParseUnpair(String unpairCommandDescription, ClientList clientList,
                       PropertyList propertyList, PairingList pairingList) {
        this.commandDescription = unpairCommandDescription;
        this.clientList = clientList;
        this.propertyList = propertyList;
        this.pairingList = pairingList;
    }

    @Override
    public Command parseCommand() throws InvalidIndexException, NoExistingPairException, MissingFlagException,
            IncorrectFlagOrderException, NotIntegerException, EmptyDescriptionException {
        try {
            checkForEmptyDescription(commandDescription);
            ArrayList<String> unpairDetailsString = processCommandDetails(commandDescription);
            ArrayList<Integer> unpairDetailsInt = convertProcessedCommandDetailsToInteger(unpairDetailsString);

            validateUnpairDetails(unpairDetailsInt);
            return new CommandUnpair(unpairDetailsInt);
        } catch (InvalidIndexException e) {
            throw new InvalidIndexException(MESSAGE_INVALID_INDEX);
        } catch (NoExistingPairException e) {
            throw new NoExistingPairException(MESSAGE_NO_EXISTING_PAIR);
        } catch (MissingFlagException e) {
            throw new MissingFlagException(MESSAGE_UNPAIR_WRONG_FORMAT);
        } catch (IncorrectFlagOrderException e) {
            throw new IncorrectFlagOrderException(MESSAGE_UNPAIR_WRONG_FORMAT);
        } catch (NotIntegerException e) {
            throw new NotIntegerException(MESSAGE_NOT_INTEGER);
        }
    }

    private void checkForEmptyDescription(String commandDetail) throws EmptyDescriptionException {
        boolean isEmptyDescription = isEmptyString(commandDetail);
        if (isEmptyDescription) {
            throw new EmptyDescriptionException(MESSAGE_EMPTY_DESCRIPTION);
        }
    }

    private ArrayList<String> processCommandDetails(String rawCommandDetail)
            throws MissingFlagException, IncorrectFlagOrderException {

        String[] flags = UNPAIR_FLAGS;
        int[] flagIndexPositions = getFlagIndexPositions(rawCommandDetail, flags);
        checkForMissingFlags(flagIndexPositions);
        checkFlagsOrder(flagIndexPositions);
        return extractCommandDetails(rawCommandDetail, flags, flagIndexPositions);
    }

    private void validateUnpairDetails(ArrayList<Integer> unpairDetails) throws InvalidIndexException,
            NoExistingPairException {
        int propertyIndex = unpairDetails.get(0);
        int clientIndex = unpairDetails.get(1);

        checkForClientListIndexOutOfBounds(clientIndex);
        checkForPropertyListIndexOutOfBounds(propertyIndex);

        Client client = clientList.getClientList().get(clientIndex);
        Property property = propertyList.getPropertyList().get(propertyIndex);

        if (!pairingList.isAlreadyPaired(client, property)) {
            throw new NoExistingPairException(EXCEPTION);
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

    private void checkForClientListIndexOutOfBounds(int clientIndex) throws InvalidIndexException {
        if (clientIndex < 0 || clientIndex > clientList.getCurrentListSize() - 1) {
            throw new InvalidIndexException(EXCEPTION);
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
