package seedu.duke;

import seedu.duke.command.Command;
import seedu.duke.command.CommandAddClient;
import seedu.duke.command.CommandDeleteClient;
import seedu.duke.command.CommandPair;
import seedu.duke.command.CommandUnpair;
import seedu.duke.command.CommandUndefined;
import seedu.duke.exception.EmptyClientDetailException;
import seedu.duke.exception.EmptyClientIndexDeleteException;
import seedu.duke.exception.EmptyCommandAddDetailException;
import seedu.duke.exception.EmptyCommandDeleteDetailException;
import seedu.duke.exception.EmptyCommandPairUnpairDetailsException;
import seedu.duke.exception.ExistingPairException;
import seedu.duke.exception.IncorrectAddClientFlagOrderException;
import seedu.duke.exception.IncorrectFlagOrderException;
import seedu.duke.exception.IncorrectPairUnpairFlagOrderException;
import seedu.duke.exception.InvalidBudgetFormatException;
import seedu.duke.exception.InvalidClientIndexDeleteException;
import seedu.duke.exception.InvalidClientIndexFlagFormatException;
import seedu.duke.exception.InvalidContactNumberException;
import seedu.duke.exception.InvalidEmailException;
import seedu.duke.exception.MissingClientDetailException;
import seedu.duke.exception.MissingClientFlagException;
import seedu.duke.exception.MissingClientIndexFlagException;
import seedu.duke.exception.MissingFlagException;
import seedu.duke.exception.MissingPairUnpairFlagException;
import seedu.duke.exception.NoExistingPairException;
import seedu.duke.exception.NotIntegerException;
import seedu.duke.exception.NotValidIndexException;
import seedu.duke.exception.UndefinedSubCommandAddTypeException;
import seedu.duke.exception.UndefinedSubCommandDeleteTypeException;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class Parser {
    private static ClientList clientList;
    private static PropertyList propertyList;
    private static PairingList pairingList;

    public static final int ADD_CLIENT_FLAG_SIZE = 4;
    public static final int PAIR_UNPAIR_FLAG_SIZE = 2;

    public Parser(ClientList clientL, PropertyList propertyL, PairingList pairingL) {
        clientList = clientL;
        propertyList = propertyL;
        pairingList = pairingL;
    }

    public Command parseCommand(String input) throws EmptyCommandAddDetailException,
            UndefinedSubCommandAddTypeException, EmptyClientDetailException, MissingClientFlagException,
            IncorrectAddClientFlagOrderException, MissingClientDetailException, InvalidContactNumberException,
            InvalidEmailException, InvalidBudgetFormatException, UndefinedSubCommandDeleteTypeException,
            EmptyCommandDeleteDetailException, InvalidClientIndexDeleteException, EmptyClientIndexDeleteException,
            MissingClientIndexFlagException, InvalidClientIndexFlagFormatException,
            EmptyCommandPairUnpairDetailsException, MissingPairUnpairFlagException,
            IncorrectPairUnpairFlagOrderException, NotValidIndexException, NotIntegerException, ExistingPairException,
            NoExistingPairException {
        ArrayList<String> processedCommandDetails = partitionCommandTypeAndDetails(input);
        String commandType    = processedCommandDetails.get(0);
        String commandDetails = processedCommandDetails.get(1);
        switch (commandType) {
        case "add":
            checkForEmptyCommandAddDetails(commandDetails);
            ArrayList<String> processedAddCommandDetails = partitionCommandTypeAndDetails(commandDetails);
            String subCommandType = processedAddCommandDetails.get(0);
            String clientOrPropertyDescriptions = processedAddCommandDetails.get(1);

            if (subCommandType.equals("-client")) {
                return prepareForCommandAddClient(clientOrPropertyDescriptions);
            } else {
                throw new UndefinedSubCommandAddTypeException();
            }
        case "delete":
            checkForEmptyCommandDeleteDetails(commandDetails);
            ArrayList<String> processedDeleteCommandDetails = partitionCommandTypeAndDetails(commandDetails);
            String subDeleteCommandType = processedDeleteCommandDetails.get(0);
            String indexDescription = processedDeleteCommandDetails.get(1).trim();

            if (subDeleteCommandType.equals("-client")) {
                checkForClientIndexFlag(indexDescription);
                int clientIndexToDelete = getClientIndexToDelete(indexDescription.substring(3));
                return prepareForCommandDeleteClient(clientIndexToDelete, clientList);
            } else {
                throw new UndefinedSubCommandDeleteTypeException();
            }
        case "pair":
            checkForEmptyCommandPairUnpairDetails(commandDetails);
            return prepareForCommandPair(commandDetails);
        case "unpair":
            checkForEmptyCommandPairUnpairDetails(commandDetails);
            return prepareForCommandUnpair(commandDetails);
        default:
            return new CommandUndefined();
        }
    }

    private ArrayList<String> partitionCommandTypeAndDetails(String fullCommandDetails) {
        String[] inputDetails = fullCommandDetails.trim().split(" ", 2);
        // This is the type of command/sub-command that will be executed
        String commandType    = inputDetails[0];
        String commandDetails = fullCommandDetails.replaceFirst(commandType, "").trim();
        ArrayList<String> processedCommandDetails = new ArrayList<>();
        processedCommandDetails.add(commandType);
        processedCommandDetails.add(commandDetails);
        return processedCommandDetails;
    }

    private void checkForEmptyCommandAddDetails(String commandAddDetails) throws EmptyCommandAddDetailException {
        boolean isEmptyCommandAddDetail = checkForEmptyDetail(commandAddDetails);
        if (isEmptyCommandAddDetail) {
            throw new EmptyCommandAddDetailException();
        }
    }

    private void checkForEmptyCommandDeleteDetails(String commandAddDetails) throws EmptyCommandDeleteDetailException {
        boolean isEmptyCommandAddDetail = checkForEmptyDetail(commandAddDetails);
        if (isEmptyCommandAddDetail) {
            throw new EmptyCommandDeleteDetailException();
        }
    }

    private Command prepareForCommandAddClient(String rawClientDescriptions) throws EmptyClientDetailException,
            MissingClientFlagException, IncorrectAddClientFlagOrderException, MissingClientDetailException,
            InvalidContactNumberException, InvalidEmailException, InvalidBudgetFormatException {
        checkForEmptyAddClientDetails(rawClientDescriptions);
        try {
            ArrayList<String> clientDetails = processClientDetails(rawClientDescriptions);
            validateClientDetails(clientDetails);
            return new CommandAddClient(clientDetails);
        } catch (MissingFlagException e) {
            throw new MissingClientFlagException();
        } catch (IncorrectFlagOrderException e) {
            throw new IncorrectAddClientFlagOrderException();
        }
    }

    private void validateClientDetails(ArrayList<String> clientDetails) throws MissingClientDetailException,
            InvalidContactNumberException, InvalidEmailException, InvalidBudgetFormatException {
        //Checks for Missing Client Name, Contact Number, Budget Per Month (SGD)
        checkForMissingClientDetails(clientDetails.get(0));
        checkForMissingClientDetails(clientDetails.get(1));
        checkForMissingClientDetails(clientDetails.get(3));

        //Checks for Contact Number, Email and Budget Format
        checkForValidSingaporeContactNumber(clientDetails.get(1));
        boolean hasEmail = !clientDetails.get(2).isEmpty();
        if (hasEmail) {
            checkForValidEmail(clientDetails.get(2));
        }
        checkForBudgetNumberFormat(clientDetails.get(3));
    }

    private void checkForValidSingaporeContactNumber(String clientContactNumber) throws InvalidContactNumberException {
        boolean hasValidContactNumber = checkForDetailFormat("^[689]\\d{7}$", clientContactNumber);
        if (!hasValidContactNumber) {
            throw new InvalidContactNumberException();
        }
    }

    private void checkForValidEmail(String clientEmail) throws InvalidEmailException {
        //General Email Regex (RFC 5322 Official Standard)
        String regex = "(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|\"(?:[\\x01-\\x08\\x0b\\"
                + "x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-z0-"
                + "9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-"
                + "9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\x0b\\x0"
                + "c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])";
        boolean hasValidContactNumber = checkForDetailFormat(regex, clientEmail);
        if (!hasValidContactNumber) {
            throw new InvalidEmailException();
        }
    }

    private void checkForBudgetNumberFormat(String budget) throws InvalidBudgetFormatException {
        //Accepts only positive whole number
        String regex = "^[1-9]\\d*$";
        boolean hasValidBudgetNumberFormat = checkForDetailFormat(regex, budget);
        if (!hasValidBudgetNumberFormat) {
            throw new InvalidBudgetFormatException();
        }
    }

    private boolean checkForDetailFormat(String regex, String detail) {
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(detail);
        return matcher.matches();
    }

    private void checkForMissingClientDetails(String clientDetail) throws MissingClientDetailException {
        boolean isEmptyDetail = checkForEmptyDetail(clientDetail);
        if (isEmptyDetail) {
            throw new MissingClientDetailException();
        }
    }

    private void checkForEmptyAddClientDetails(String commandAddDescriptions) throws EmptyClientDetailException {
        boolean isEmptyCommandAddDetail = checkForEmptyDetail(commandAddDescriptions);
        if (isEmptyCommandAddDetail) {
            throw new EmptyClientDetailException();
        }
    }

    private ArrayList<String> processClientDetails(String rawClientDetails) throws MissingFlagException,
            IncorrectFlagOrderException {
        String[] addClientFlags    = {"n/", "c/", "e/", "b/"};
        int[] addClientFlagIndexPositions = new int[ADD_CLIENT_FLAG_SIZE];

        for (int i = 0; i < addClientFlags.length; i++) {
            addClientFlagIndexPositions[i] = rawClientDetails.indexOf(addClientFlags[i]);
        }

        checkForMissingClientFlags(addClientFlagIndexPositions);
        checkForClientFlagsOrder(addClientFlagIndexPositions);
        return extractClientDetails(rawClientDetails, addClientFlagIndexPositions);
    }

    private void checkForMissingClientFlags(int[] addClientFlagIndexPositions) throws MissingFlagException {
        checkForEssentialAddFlag(addClientFlagIndexPositions[0]);
        checkForEssentialAddFlag(addClientFlagIndexPositions[1]);
        checkForEssentialAddFlag(addClientFlagIndexPositions[3]);
    }

    private void checkForClientFlagsOrder(int[] addClientFlagIndexPositions) throws IncorrectFlagOrderException {
        boolean hasEmail = (addClientFlagIndexPositions[2] != -1);
        checkForCorrectFlagOrder(addClientFlagIndexPositions[0], addClientFlagIndexPositions[1]);
        checkForCorrectFlagOrder(addClientFlagIndexPositions[1], addClientFlagIndexPositions[3]);
        if (hasEmail) {
            checkForCorrectFlagOrder(addClientFlagIndexPositions[1], addClientFlagIndexPositions[2]);
            checkForCorrectFlagOrder(addClientFlagIndexPositions[2], addClientFlagIndexPositions[3]);
        }
    }

    private ArrayList<String> extractClientDetails(String rawClientDetails, int[] addClientFlagIndexPositions) {
        boolean hasEmail = (addClientFlagIndexPositions[2] != -1);
        String clientContactNumber;
        String clientEmail = "";
        if (hasEmail) {
            clientContactNumber = extractDetail(rawClientDetails, addClientFlagIndexPositions[1] + 2,
                    addClientFlagIndexPositions[2]);
            clientEmail = extractDetail(rawClientDetails, addClientFlagIndexPositions[2] + 2,
                    addClientFlagIndexPositions[3]);
        } else {
            clientContactNumber = extractDetail(rawClientDetails, addClientFlagIndexPositions[1] + 2,
                    addClientFlagIndexPositions[3]);
        }
        String clientName = extractDetail(rawClientDetails, addClientFlagIndexPositions[0] + 2,
                addClientFlagIndexPositions[1]);
        String clientBudgetPerMonth = extractDetail(rawClientDetails, addClientFlagIndexPositions[3] + 2);

        ArrayList<String> processedClientDetails = new ArrayList<>();
        processedClientDetails.add(clientName.trim());
        processedClientDetails.add(clientContactNumber.trim());
        processedClientDetails.add(clientEmail.trim());
        processedClientDetails.add(clientBudgetPerMonth.trim());
        return processedClientDetails;
    }

    private void checkForEssentialAddFlag(int addClientFlagIndexes) throws MissingFlagException {
        boolean hasFlag = (addClientFlagIndexes != -1);
        if (!hasFlag) {
            throw new MissingFlagException();
        }
    }

    private void checkForCorrectFlagOrder(int flagPosition, int nextFlagPosition) throws IncorrectFlagOrderException {
        boolean hasCorrectOrder = (flagPosition < nextFlagPosition);
        if (!hasCorrectOrder) {
            throw new IncorrectFlagOrderException();
        }
    }

    private static String extractDetail(String rawClientDetails, int beginIndex) {
        return rawClientDetails.substring(beginIndex).trim();
    }

    private static String extractDetail(String rawClientDetails, int beginIndex, int endIndex) {
        return rawClientDetails.substring(beginIndex, endIndex).trim();
    }

    private boolean checkForEmptyDetail(String commandDetails) {
        return commandDetails.trim().isEmpty();
    }

    private int getClientIndexToDelete(String commandDetails) throws EmptyClientIndexDeleteException {
        if (commandDetails.isEmpty()) {
            throw new EmptyClientIndexDeleteException();
        }
        return Integer.parseInt(commandDetails.trim()) - 1;
    }

    private void checkForClientIndexFlag(String commandDetails) throws MissingClientIndexFlagException, InvalidClientIndexFlagFormatException {
        if (!commandDetails.contains("ic/")) {
            throw new MissingClientIndexFlagException();
        } else {
            String clientIndexFlag = commandDetails.substring(0, 3);
            if (!clientIndexFlag.equals("ic/")) {
                throw new InvalidClientIndexFlagFormatException();
            }
        }
    }

    private Command prepareForCommandDeleteClient(int clientIndex, ClientList clientList)
            throws InvalidClientIndexDeleteException {
        checkForInvalidClientIndexDelete(clientIndex, clientList);
        return new CommandDeleteClient(clientIndex);
    }

    private void checkForInvalidClientIndexDelete(int clientIndex, ClientList clientList)
            throws InvalidClientIndexDeleteException {
        int currentListSize = clientList.getCurrentListSize();
        if (clientIndex < 0 || clientIndex >= currentListSize) {
            throw new InvalidClientIndexDeleteException();
        }
    }

    private void checkForEmptyCommandPairUnpairDetails(String commandPairUnpairDetails)
            throws EmptyCommandPairUnpairDetailsException {
        boolean isEmptyCommandPairUnpairDetail = checkForEmptyDetail(commandPairUnpairDetails);
        if (isEmptyCommandPairUnpairDetail) {
            throw new EmptyCommandPairUnpairDetailsException();
        }
    }

    private Command prepareForCommandPair(String rawPairDescriptions) throws NotIntegerException,
            NotValidIndexException, MissingPairUnpairFlagException, IncorrectPairUnpairFlagOrderException,
            ExistingPairException {
        ArrayList<Integer> pairDetails = processPairUnpairDetails(rawPairDescriptions);
        validatePairDetails(pairDetails);
        return new CommandPair(pairDetails);
    }

    private Command prepareForCommandUnpair(String rawUnpairDescriptions) throws NotIntegerException,
            NotValidIndexException, MissingPairUnpairFlagException, IncorrectPairUnpairFlagOrderException,
            NoExistingPairException {
        ArrayList<Integer> unpairDetails = processPairUnpairDetails(rawUnpairDescriptions);
        validateUnpairDetails(unpairDetails);
        return new CommandUnpair(unpairDetails);
    }

    private ArrayList<Integer> processPairUnpairDetails(String rawPairUnpairDetails)
            throws MissingPairUnpairFlagException, IncorrectPairUnpairFlagOrderException, NotIntegerException {
        String[] pairUnpairFlags = {"ip/", "ic/"};
        int[] pairUnpairFlagIndexPositions = new int[PAIR_UNPAIR_FLAG_SIZE];

        for (int i = 0; i < pairUnpairFlags.length; i++) {
            pairUnpairFlagIndexPositions[i] = rawPairUnpairDetails.indexOf(pairUnpairFlags[i]);
        }

        checkForMissingPairUnpairFlags(pairUnpairFlagIndexPositions);
        checkForPairUnpairFlagsOrder(pairUnpairFlagIndexPositions);
        return extractPairUnpairDetails(rawPairUnpairDetails, pairUnpairFlagIndexPositions, pairUnpairFlags);
    }

    private void checkForMissingPairUnpairFlags(int[] pairUnpairFlagIndexPosition)
            throws MissingPairUnpairFlagException {
        try {
            for (int flagIndex : pairUnpairFlagIndexPosition) {
                checkForEssentialAddFlag(flagIndex);
            }
        } catch (MissingFlagException e) {
            throw new MissingPairUnpairFlagException();
        }
    }

    private void checkForPairUnpairFlagsOrder(int[] pairUnpairFlagIndexPosition)
            throws IncorrectPairUnpairFlagOrderException {
        try {
            checkForCorrectFlagOrder(pairUnpairFlagIndexPosition[0], pairUnpairFlagIndexPosition[1]);
        } catch (IncorrectFlagOrderException e) {
            throw new IncorrectPairUnpairFlagOrderException();
        }
    }

    private ArrayList<Integer> extractPairUnpairDetails(String rawPairUnpairDetails, int[] pairFlagIndexPositions,
            String[] pairUnpairFlags) throws NotIntegerException {
        String propertyIndexString = extractDetail(rawPairUnpairDetails,
                pairFlagIndexPositions[0] + pairUnpairFlags[0].length(),
                pairFlagIndexPositions[1]);

        String clientIndexString = extractDetail(rawPairUnpairDetails,
                pairFlagIndexPositions[1] + pairUnpairFlags[1].length());

        int propertyIndex;
        int clientIndex;

        try {
            propertyIndex = Integer.parseInt(propertyIndexString);
            clientIndex = Integer.parseInt(clientIndexString);
        } catch (NumberFormatException e) {
            throw new NotIntegerException();
        }

        ArrayList<Integer> processedPairUnpairDetails = new ArrayList<>();
        processedPairUnpairDetails.add(clientIndex - 1); // convert to 0-index
        processedPairUnpairDetails.add(propertyIndex - 1);
        return processedPairUnpairDetails;
    }

    private void validatePairDetails(ArrayList<Integer> pairUnpairDetails) throws NotValidIndexException,
            ExistingPairException {
        int clientIndex = pairUnpairDetails.get(0);
        int propertyIndex = pairUnpairDetails.get(1);
        checkForListIndexOutOfBounds(clientIndex, propertyIndex);
        Client client = clientList.getClientList().get(clientIndex);
        if (pairingList.isClientPairedWithProperty(client)) {
            throw new ExistingPairException();
        }
    }

    private void validateUnpairDetails(ArrayList<Integer> pairUnpairDetails) throws NotValidIndexException,
            NoExistingPairException {
        int clientIndex = pairUnpairDetails.get(0);
        int propertyIndex = pairUnpairDetails.get(1);
        checkForListIndexOutOfBounds(clientIndex, propertyIndex);
        Client client = clientList.getClientList().get(clientIndex);
        if (!pairingList.isClientPairedWithProperty(client)) {
            throw new NoExistingPairException();
        }
    }

    private void checkForListIndexOutOfBounds(int clientIndex, int propertyIndex) throws NotValidIndexException {
        if (clientIndex < 0 || clientIndex > clientList.getCurrentListSize() - 1
                || propertyIndex < 0 || propertyIndex > propertyList.getCurrentListSize() - 1) {
            throw new NotValidIndexException();
        }
    }

}
