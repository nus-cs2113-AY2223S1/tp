package seedu.duke.parsermanager;

import seedu.duke.Client;
import seedu.duke.ClientList;
import seedu.duke.PairingList;
import seedu.duke.Property;
import seedu.duke.PropertyList;
import seedu.duke.command.Command;
import seedu.duke.command.CommandPair;
import seedu.duke.exception.BudgetExceededException;
import seedu.duke.exception.ClientAlreadyPairedException;
import seedu.duke.exception.DukeException;
import seedu.duke.exception.EmptyDescriptionException;
import seedu.duke.exception.ExistingPairException;
import seedu.duke.exception.IncorrectFlagOrderException;
import seedu.duke.exception.InvalidIndexException;
import seedu.duke.exception.MissingFlagException;
import seedu.duke.exception.NotIntegerException;

import java.util.ArrayList;

import static seedu.duke.CommandStructure.PAIR_FLAGS;
import static seedu.duke.Messages.EXCEPTION;
import static seedu.duke.Messages.MESSAGE_BUDGET_EXCEEDED;
import static seedu.duke.Messages.MESSAGE_CLIENT_ALREADY_PAIRED;
import static seedu.duke.Messages.MESSAGE_EMPTY_DESCRIPTION;
import static seedu.duke.Messages.MESSAGE_EXISTING_PAIR;
import static seedu.duke.Messages.MESSAGE_INVALID_INDEX;
import static seedu.duke.Messages.MESSAGE_NOT_INTEGER;
import static seedu.duke.Messages.MESSAGE_PAIR_WRONG_FORMAT;

public class ParsePair extends Parser {
    private final String commandDescription;
    private static ClientList clientList;
    private static PropertyList propertyList;
    private static PairingList pairingList;

    public ParsePair(String pairCommandDescription, ClientList clientL,
                     PropertyList propertyL, PairingList pairingL) {
        commandDescription = pairCommandDescription;
        clientList = clientL;
        propertyList = propertyL;
        pairingList = pairingL;
    }

    @Override
    public Command parseCommand() throws DukeException {
        try {

            checkForEmptyDescription(commandDescription);
            ArrayList<String> pairDetailsString = processCommandDetails(commandDescription);
            ArrayList<Integer> pairDetailsInt = convertProcessedCommandDetailsToInteger(pairDetailsString);

            validatePairDetails(pairDetailsInt);
            return new CommandPair(pairDetailsInt);
        } catch (InvalidIndexException e) {
            throw new InvalidIndexException(MESSAGE_INVALID_INDEX);
        } catch (ClientAlreadyPairedException e) {
            throw new ClientAlreadyPairedException(MESSAGE_CLIENT_ALREADY_PAIRED);
        } catch (MissingFlagException e) {
            throw new MissingFlagException(MESSAGE_PAIR_WRONG_FORMAT);
        } catch (IncorrectFlagOrderException e) {
            throw new IncorrectFlagOrderException(MESSAGE_PAIR_WRONG_FORMAT);
        } catch (NotIntegerException e) {
            throw new NotIntegerException(MESSAGE_NOT_INTEGER);
        } catch (ExistingPairException e) {
            throw new ExistingPairException(MESSAGE_EXISTING_PAIR);
        } catch (BudgetExceededException e) {
            throw new BudgetExceededException(MESSAGE_BUDGET_EXCEEDED);
        }
    }

    private void checkForEmptyDescription(String commandDetail) throws EmptyDescriptionException {
        boolean isEmptyDescription = isEmptyString(commandDetail);
        if (isEmptyDescription) {
            throw new EmptyDescriptionException(MESSAGE_EMPTY_DESCRIPTION);
        }
    }

    private boolean isEmptyString(String commandDetail) {
        return commandDetail.trim().isEmpty();
    }


    private ArrayList<String> processCommandDetails(String rawCommandDetail)
            throws MissingFlagException, IncorrectFlagOrderException {

        String[] flags = PAIR_FLAGS;
        int[] flagIndexPositions = getFlagIndexPositions(rawCommandDetail, flags);
        checkForMissingFlags(flagIndexPositions);
        checkFlagsOrder(flagIndexPositions);
        return extractCommandDetails(rawCommandDetail, flags, flagIndexPositions);
    }

    private ArrayList<Integer> convertProcessedCommandDetailsToInteger(ArrayList<String> processedCommandDetails)
            throws NotIntegerException {
        ArrayList<Integer> integerDetails = new ArrayList<>();
        for (String detail : processedCommandDetails) {
            int integer;
            try {
                integer = Integer.parseInt(detail);
            } catch (NumberFormatException e) {
                throw new NotIntegerException(EXCEPTION);
            }
            integerDetails.add(integer - 1); // Convert to 0-index
        }
        return integerDetails;
    }

    private void validatePairDetails(ArrayList<Integer> pairDetails) throws InvalidIndexException,
            ClientAlreadyPairedException, ExistingPairException, BudgetExceededException {
        int propertyIndex = pairDetails.get(0);
        int clientIndex = pairDetails.get(1);
        checkForClientListIndexOutOfBounds(clientIndex);
        checkForPropertyListIndexOutOfBounds(propertyIndex);

        Client client = clientList.getClientList().get(clientIndex);
        Property property = propertyList.getPropertyList().get(propertyIndex);

        if (pairingList.isAlreadyPaired(client, property)) {
            throw new ExistingPairException(EXCEPTION);
        }

        if (pairingList.isClientPairedWithProperty(client)) {
            throw new ClientAlreadyPairedException(EXCEPTION);
        }

        if (pairingList.hasPriceExceededBudget(client, property)) {
            throw new BudgetExceededException(EXCEPTION);
        }
    }

    private int[] getFlagIndexPositions(String commandDetail, String[] flags) {
        int[] flagIndexPositions = new int[flags.length];

        for (int i = 0; i < flags.length; i++) {
            flagIndexPositions[i] = commandDetail.indexOf(flags[i]);
        }
        return flagIndexPositions;
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

    private ArrayList<String> extractCommandDetails(String rawCommandDetail, String[] flags,
                                                    int[] flagIndexPositions) {
        ArrayList<String> extractedCommandDetails = new ArrayList<>();
        for (int i = 0; i < flags.length; i++) {
            String extractedDetail;
            if (i == flags.length - 1) {
                /* The extracted detail for the last flag starts from the char after the flag, to the end of
                   rawCommandDetails */
                extractedDetail = extractDetail(rawCommandDetail, flagIndexPositions[i] + flags[i].length());
            } else {
                // The extracted detail for non-last starts from the char after the flag, to index before the next flag
                extractedDetail = extractDetail(
                        rawCommandDetail,
                        flagIndexPositions[i] + flags[i].length(),
                        flagIndexPositions[i + 1]);
            }
            extractedCommandDetails.add(extractedDetail.trim());
        }
        return extractedCommandDetails;
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

    private static String extractDetail(String rawDetail, int beginIndex) {
        return rawDetail.substring(beginIndex).trim();
    }

    private static String extractDetail(String rawDetail, int beginIndex, int endIndex) {
        return rawDetail.substring(beginIndex, endIndex).trim();
    }





}
