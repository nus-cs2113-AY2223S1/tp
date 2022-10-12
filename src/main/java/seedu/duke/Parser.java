package seedu.duke;

import seedu.duke.command.Command;
import seedu.duke.command.CommandAddProperty;
import seedu.duke.command.CommandAddClient;
import seedu.duke.command.CommandCheckProperty;
import seedu.duke.command.CommandDeleteClient;
import seedu.duke.command.CommandDeleteProperty;
import seedu.duke.command.CommandPair;
import seedu.duke.command.CommandUnpair;
import seedu.duke.command.CommandUndefined;
import seedu.duke.command.CommandListClients;
import seedu.duke.command.CommandListProperties;
import seedu.duke.command.CommandBye;

import seedu.duke.exception.ClientAlreadyPairedException;
import seedu.duke.exception.EmptyClientDetailException;
import seedu.duke.exception.EmptyClientIndexDeleteException;
import seedu.duke.exception.EmptyCommandAddDetailException;
import seedu.duke.exception.EmptyCommandCheckDetailException;
import seedu.duke.exception.EmptyCommandDeleteDetailException;
import seedu.duke.exception.EmptyCommandPairUnpairDetailsException;
import seedu.duke.exception.EmptyPropertyDetailException;
import seedu.duke.exception.EmptyPropertyIndexDeleteException;
import seedu.duke.exception.ExistingPairException;
import seedu.duke.exception.IncorrectAddClientFlagOrderException;
import seedu.duke.exception.IncorrectAddPropertyFlagOrderException;
import seedu.duke.exception.IncorrectFlagOrderException;
import seedu.duke.exception.IncorrectPairUnpairFlagOrderException;
import seedu.duke.exception.InvalidBudgetFormatException;
import seedu.duke.exception.InvalidClientIndexDeleteException;
import seedu.duke.exception.InvalidClientIndexFlagFormatException;
import seedu.duke.exception.InvalidContactNumberException;
import seedu.duke.exception.InvalidEmailException;
import seedu.duke.exception.MissingCheckPropertyFlagException;
import seedu.duke.exception.InvalidPriceFormatException;
import seedu.duke.exception.InvalidPropertyIndexDeleteException;
import seedu.duke.exception.InvalidPropertyIndexFlagFormatException;
import seedu.duke.exception.InvalidSingaporeAddressException;
import seedu.duke.exception.MissingClientDetailException;
import seedu.duke.exception.MissingClientFlagException;
import seedu.duke.exception.MissingClientIndexFlagException;
import seedu.duke.exception.MissingFlagException;
import seedu.duke.exception.MissingPairUnpairFlagException;
import seedu.duke.exception.MissingPropertyDetailException;
import seedu.duke.exception.MissingPropertyFlagException;
import seedu.duke.exception.MissingPropertyIndexFlagException;
import seedu.duke.exception.NoExistingPairException;
import seedu.duke.exception.NotIntegerException;
import seedu.duke.exception.NotValidIndexException;
import seedu.duke.exception.PropertyAlreadyPairedException;
import seedu.duke.exception.UndefinedSubCommandAddTypeException;
import seedu.duke.exception.UndefinedSubCommandCheckTypeException;
import seedu.duke.exception.UndefinedSubCommandDeleteTypeException;
import seedu.duke.exception.IncorrectListDetailsException;
import seedu.duke.exception.MissingListDetailException;
import seedu.duke.exception.ByeParametersPresentException;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Parser {
    private static ClientList clientList;
    private static PropertyList propertyList;
    private static PairingList pairingList;

    public static final int ADD_PROPERTY_FLAG_SIZE = 4;
    public static final int ADD_CLIENT_FLAG_SIZE = 4;
    public static final int PAIR_UNPAIR_FLAG_SIZE = 2;
    public static final int CHECK_PROPERTY_FLAG_SIZE = 1;


    public Parser(ClientList clientL, PropertyList propertyL, PairingList pairingL) {
        clientList = clientL;
        propertyList = propertyL;
        pairingList = pairingL;
    }

    public Command parseCommand(String input) throws EmptyCommandAddDetailException,
            UndefinedSubCommandAddTypeException, EmptyPropertyDetailException, MissingPropertyFlagException,
            IncorrectAddPropertyFlagOrderException, MissingPropertyDetailException, InvalidSingaporeAddressException,
            InvalidPriceFormatException, EmptyClientDetailException, MissingClientFlagException,
            IncorrectAddClientFlagOrderException, MissingClientDetailException, InvalidContactNumberException,
            InvalidEmailException, InvalidBudgetFormatException, UndefinedSubCommandDeleteTypeException,
            EmptyCommandDeleteDetailException, InvalidPropertyIndexFlagFormatException,
            MissingPropertyIndexFlagException, EmptyPropertyIndexDeleteException, InvalidPropertyIndexDeleteException,
            InvalidClientIndexDeleteException, EmptyClientIndexDeleteException, MissingClientIndexFlagException,
            InvalidClientIndexFlagFormatException, EmptyCommandPairUnpairDetailsException,
            MissingPairUnpairFlagException, IncorrectPairUnpairFlagOrderException, NotValidIndexException,
            NotIntegerException, NoExistingPairException, ClientAlreadyPairedException, PropertyAlreadyPairedException,
            UndefinedSubCommandCheckTypeException, EmptyCommandCheckDetailException, MissingCheckPropertyFlagException,
            IncorrectListDetailsException, MissingListDetailException, ByeParametersPresentException {
        ArrayList<String> processedCommandDetails = partitionCommandTypeAndDetails(input);
        String commandType = processedCommandDetails.get(0);
        String commandDetails = processedCommandDetails.get(1);
        switch (commandType) {
        case "add":
            checkForEmptyCommandAddDetails(commandDetails);
            ArrayList<String> processedAddCommandDetails = partitionCommandTypeAndDetails(commandDetails);
            String subCommandType = processedAddCommandDetails.get(0);
            String clientOrPropertyDescriptions = processedAddCommandDetails.get(1);
            if (subCommandType.equals("-property")) {
                return prepareForCommandAddProperty(clientOrPropertyDescriptions);
            } else if (subCommandType.equals("-client")) {
                return prepareForCommandAddClient(clientOrPropertyDescriptions);
            } else {
                throw new UndefinedSubCommandAddTypeException();
            }

        case "delete":
            checkForEmptyCommandDeleteDetails(commandDetails);
            ArrayList<String> processedDeleteCommandDetails = partitionCommandTypeAndDetails(commandDetails);
            String subDeleteCommandType = processedDeleteCommandDetails.get(0);
            String indexDescription = processedDeleteCommandDetails.get(1).trim();
            if (subDeleteCommandType.equals("-property")) {
                checkForPropertyIndexFlag(indexDescription);
                int propertyIndexToDelete = getPropertyIndexToDelete(indexDescription.substring(3));
                return prepareForCommandDeleteProperty(propertyIndexToDelete, propertyList);
            } else if (subDeleteCommandType.equals("-client")) {
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

        case "check":
            checkForEmptyCommandCheckDetails(commandDetails);
            ArrayList<String> processedCheckCommandDetails = partitionCommandTypeAndDetails(commandDetails);
            String subCommandCheckType = processedCheckCommandDetails.get(0);
            String commandCheckClientOrPropertyDescriptions = processedCheckCommandDetails.get(1);

            if (subCommandCheckType.equals("-property")) {
                return prepareForCommandCheckProperty(commandCheckClientOrPropertyDescriptions);
            } else {
                throw new UndefinedSubCommandCheckTypeException();
            }

        case "list":
            checkForEmptyListParameters(commandDetails);
            return prepareForCommandList(commandDetails);

        case "bye":
            checkForByeParameters(commandDetails);
            return new CommandBye();
        default:
            return new CommandUndefined();
        }
    }

    private ArrayList<String> partitionCommandTypeAndDetails(String fullCommandDetails) {
        String[] inputDetails = fullCommandDetails.trim().split(" ", 2);
        // This is the type of command/sub-command that will be executed
        String commandType = inputDetails[0];
        String commandDetails = fullCommandDetails.replaceFirst(commandType, "").trim();
        ArrayList<String> processedCommandDetails = new ArrayList<>();
        processedCommandDetails.add(commandType);
        processedCommandDetails.add(commandDetails);
        return processedCommandDetails;
    }

    void checkForEmptyListParameters(String commandListDetails) throws MissingListDetailException {
        boolean isEmptyListDetail = checkForEmptyDetail(commandListDetails);
        if (isEmptyListDetail) {
            throw new MissingListDetailException();
        }
    }


    /* Add Property/Client Parse Section */

    private void checkForEmptyCommandAddDetails(String commandAddDetails) throws EmptyCommandAddDetailException {
        boolean isEmptyCommandAddDetail = checkForEmptyDetail(commandAddDetails);
        if (isEmptyCommandAddDetail) {
            throw new EmptyCommandAddDetailException();
        }
    }

    private Command prepareForCommandAddProperty(String rawPropertyDescriptions) throws
            EmptyPropertyDetailException, MissingPropertyFlagException, IncorrectAddPropertyFlagOrderException,
            MissingPropertyDetailException, InvalidSingaporeAddressException, InvalidPriceFormatException {
        checkForEmptyAddPropertyDetails(rawPropertyDescriptions);
        try {
            ArrayList<String> propertyDetails = processPropertyDetails(rawPropertyDescriptions);
            validatePropertyDetails(propertyDetails);
            return new CommandAddProperty(propertyDetails);
        } catch (MissingFlagException e) {
            throw new MissingPropertyFlagException();
        } catch (IncorrectFlagOrderException e) {
            throw new IncorrectAddPropertyFlagOrderException();
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

    private void checkForEmptyAddPropertyDetails(String commandAddDescriptions) throws EmptyPropertyDetailException {
        boolean isEmptyCommandAddDetail = checkForEmptyDetail(commandAddDescriptions);
        if (isEmptyCommandAddDetail) {
            throw new EmptyPropertyDetailException();
        }
    }

    private void checkForEmptyAddClientDetails(String commandAddDescriptions) throws EmptyClientDetailException {
        boolean isEmptyCommandAddDetail = checkForEmptyDetail(commandAddDescriptions);
        if (isEmptyCommandAddDetail) {
            throw new EmptyClientDetailException();
        }
    }

    private ArrayList<String> processPropertyDetails(String rawPropertyDetails) throws MissingFlagException,
            IncorrectFlagOrderException {
        String[] addPropertyFlags = {"n/", "a/", "p/", "t/"};
        int[] addPropertyFlagIndexPositions = new int[ADD_PROPERTY_FLAG_SIZE];

        for (int i = 0; i < addPropertyFlags.length; i++) {
            addPropertyFlagIndexPositions[i] = rawPropertyDetails.indexOf(addPropertyFlags[i]);
        }

        checkForMissingPropertyFlags(addPropertyFlagIndexPositions);
        checkForPropertyFlagsOrder(addPropertyFlagIndexPositions);
        return extractPropertyDetails(rawPropertyDetails, addPropertyFlagIndexPositions);
    }

    private ArrayList<String> processClientDetails(String rawClientDetails) throws MissingFlagException,
            IncorrectFlagOrderException {
        String[] addClientFlags = {"n/", "c/", "e/", "b/"};
        int[] addClientFlagIndexPositions = new int[ADD_CLIENT_FLAG_SIZE];

        for (int i = 0; i < addClientFlags.length; i++) {
            addClientFlagIndexPositions[i] = rawClientDetails.indexOf(addClientFlags[i]);
        }

        checkForMissingClientFlags(addClientFlagIndexPositions);
        checkForClientFlagsOrder(addClientFlagIndexPositions);
        return extractClientDetails(rawClientDetails, addClientFlagIndexPositions);
    }

    private void checkForMissingPropertyFlags(int[] addPropertyFlagIndexPositions) throws MissingFlagException {
        for (int propertyFlagIndex : addPropertyFlagIndexPositions) {
            checkForEssentialAddFlag(propertyFlagIndex);
        }
    }

    private void checkForMissingClientFlags(int[] addClientFlagIndexPositions) throws MissingFlagException {
        checkForEssentialAddFlag(addClientFlagIndexPositions[0]);
        checkForEssentialAddFlag(addClientFlagIndexPositions[1]);
        checkForEssentialAddFlag(addClientFlagIndexPositions[3]);
    }

    private void checkForEssentialAddFlag(int addClientFlagIndexes) throws MissingFlagException {
        boolean hasFlag = (addClientFlagIndexes != -1);
        if (!hasFlag) {
            throw new MissingFlagException();
        }
    }

    private void checkForPropertyFlagsOrder(int[] addPropertyFlagIndexPositions) throws IncorrectFlagOrderException {
        for (int propertyFlagIndex = 0; propertyFlagIndex < ADD_PROPERTY_FLAG_SIZE - 1; propertyFlagIndex++) {
            checkForCorrectFlagOrder(addPropertyFlagIndexPositions[propertyFlagIndex],
                    addPropertyFlagIndexPositions[propertyFlagIndex + 1]);
        }
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

    private void checkForCorrectFlagOrder(int flagPosition, int nextFlagPosition) throws IncorrectFlagOrderException {
        boolean hasCorrectOrder = (flagPosition < nextFlagPosition);
        if (!hasCorrectOrder) {
            throw new IncorrectFlagOrderException();
        }
    }

    private ArrayList<String> extractPropertyDetails(String rawPropertyDetails, int[] addPropertyFlagIndexPositions) {
        String landlordName = extractDetail(rawPropertyDetails, addPropertyFlagIndexPositions[0] + 2,
                addPropertyFlagIndexPositions[1]);
        String propertyAddress = extractDetail(rawPropertyDetails, addPropertyFlagIndexPositions[1] + 2,
                addPropertyFlagIndexPositions[2]);
        String rentingPrice = extractDetail(rawPropertyDetails, addPropertyFlagIndexPositions[2] + 2,
                addPropertyFlagIndexPositions[3]);
        String unitType = extractDetail(rawPropertyDetails, addPropertyFlagIndexPositions[3] + 2);

        ArrayList<String> processedPropertyDetails = new ArrayList<>();
        processedPropertyDetails.add(landlordName.trim());
        processedPropertyDetails.add(propertyAddress.trim());
        processedPropertyDetails.add(rentingPrice.trim());
        processedPropertyDetails.add(unitType.trim());
        return processedPropertyDetails;
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

    private static String extractDetail(String rawDetails, int beginIndex) {
        return rawDetails.substring(beginIndex).trim();
    }

    private static String extractDetail(String rawDetails, int beginIndex, int endIndex) {
        return rawDetails.substring(beginIndex, endIndex).trim();
    }

    private void validatePropertyDetails(ArrayList<String> propertyDetails) throws MissingPropertyDetailException,
            InvalidSingaporeAddressException, InvalidPriceFormatException {
        //Checks for Missing Landlord Name, Property Address, Renting Price (SGD/month) and Unit-Type
        for (String propertyDetail : propertyDetails) {
            checkForMissingPropertyDetails(propertyDetail);
        }

        //Checks Format for Address (Singapore) and Renting Price
        checkForValidSingaporeAddress(propertyDetails.get(1));
        checkForPriceNumberFormat(propertyDetails.get(2));
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

    private void checkForMissingPropertyDetails(String propertyDetail) throws MissingPropertyDetailException {
        boolean isEmptyDetail = checkForEmptyDetail(propertyDetail);
        if (isEmptyDetail) {
            throw new MissingPropertyDetailException();
        }
    }

    private void checkForMissingClientDetails(String clientDetail) throws MissingClientDetailException {
        boolean isEmptyDetail = checkForEmptyDetail(clientDetail);
        if (isEmptyDetail) {
            throw new MissingClientDetailException();
        }
    }

    private boolean checkForEmptyDetail(String commandDetails) {
        return commandDetails.trim().isEmpty();
    }

    private void checkForValidSingaporeAddress(String address) throws InvalidSingaporeAddressException {
        boolean hasValidSingaporeLandedPropertyAddress = checkForValidSingaporeLandedPropertyAddress(address);
        boolean hasValidSingaporeBuildingAddress = checkForValidSingaporeBuildingAddress(address);

        boolean hasValidSingaporeAddress = hasValidSingaporeLandedPropertyAddress || hasValidSingaporeBuildingAddress;
        if (!hasValidSingaporeAddress) {
            throw new InvalidSingaporeAddressException();
        }
    }

    private boolean checkForValidSingaporeLandedPropertyAddress(String address) {
        String landedPropertyUnitNumberRegex = "^([0-9]{1,4})([A-Z]?) ";
        String streetNameRegex = "[^.!@#$%^&*()_+=<>\\s\\n?`~0-9,{}|-]([a-zA-Z\\s]+)[^.!@#$%^&*()_+=<>\\s\\n?`~0-9,"
                + "{}|-]";
        String streetNumberRegex = " ([1-9]{1}[0-9]{0,3})";
        String postalCodeRegex = ", (Singapore [0-9]{6})$";

        String landedPropertyAddressRegex = landedPropertyUnitNumberRegex + streetNameRegex + postalCodeRegex;
        String landedPropertyAddressWithStreetNumberRegex = landedPropertyUnitNumberRegex + streetNameRegex
                + streetNumberRegex + postalCodeRegex;

        boolean hasValidLandedPropertyAddress = checkForDetailFormat(landedPropertyAddressRegex, address);
        boolean hasValidLandedPropertyAddressWithStreetNumber
                = checkForDetailFormat(landedPropertyAddressWithStreetNumberRegex, address);
        return hasValidLandedPropertyAddress || hasValidLandedPropertyAddressWithStreetNumber;
    }

    private boolean checkForValidSingaporeBuildingAddress(String address) {
        String buildingBlockNumberRegex = "^([0-9]{1,4})([A-Z]?) ";
        String streetNameRegex = "[^.!@#$%^&*()_+=<>\\s\\n?`~0-9,{}|-]([a-zA-Z\\s]+)[^.!@#$%^&*()_+=<>\\s\\n?`~0-9,"
                + "{}|-]";
        String streetNumberRegex = " ([1-9]{1}[0-9]{0,3})";
        String buildingUnitFloorAndNumberRegex = " #(([0]{1}[1-9]{1})|([1-9]{1}[0-9]{1,2}))-(([0]{1}[1-9]{1})|([1-9]"
                + "{1}[0-9]{1,3}))([A-Z]?)";
        String buildingNameRegex = " [^.!@#$%^&*()_+=<>\\s\\n?`~0-9,{}|-]([a-zA-Z\\s]+)[^.!@#$%^&*()_+=<>\\s\\n?`~0-9"
                + ",{}|-]";
        String postalCodeRegex = ", (Singapore [0-9]{6})$";

        String buildingAddressRegex = buildingBlockNumberRegex + streetNameRegex + buildingUnitFloorAndNumberRegex
                + postalCodeRegex;
        String buildingAddressWithStreetNumberRegex = buildingBlockNumberRegex + streetNameRegex + streetNumberRegex
                + buildingUnitFloorAndNumberRegex + postalCodeRegex;
        String buildingAddressWithBuildingNameRegex = buildingBlockNumberRegex + streetNameRegex
                + buildingUnitFloorAndNumberRegex + buildingNameRegex + postalCodeRegex;
        String buildingAddressWithStreetNumberAndBuildingNameRegex = buildingBlockNumberRegex + streetNameRegex
                + streetNumberRegex + buildingUnitFloorAndNumberRegex + buildingNameRegex + postalCodeRegex;

        boolean hasValidBuildingAddress = checkForDetailFormat(buildingAddressRegex, address);
        boolean hasValidBuildingAddressWithStreetNumber
                = checkForDetailFormat(buildingAddressWithStreetNumberRegex, address);
        boolean hasValidBuildingAddressWithBuildingName
                = checkForDetailFormat(buildingAddressWithBuildingNameRegex, address);
        boolean hasValidBuildingAddressWithStreetNumberAndBuildingName
                = checkForDetailFormat(buildingAddressWithStreetNumberAndBuildingNameRegex, address);
        return hasValidBuildingAddress || hasValidBuildingAddressWithStreetNumber
                || hasValidBuildingAddressWithBuildingName || hasValidBuildingAddressWithStreetNumberAndBuildingName;
    }

    private void checkForPriceNumberFormat(String budget) throws InvalidPriceFormatException {
        //Accepts only positive whole number
        String regex = "^[1-9]\\d*$";
        boolean hasValidPriceNumberFormat = checkForDetailFormat(regex, budget);
        if (!hasValidPriceNumberFormat) {
            throw new InvalidPriceFormatException();
        }
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


    /* Delete Property/Client Parse Section */

    private void checkForEmptyCommandDeleteDetails(String commandAddDetails) throws EmptyCommandDeleteDetailException {
        boolean isEmptyCommandAddDetail = checkForEmptyDetail(commandAddDetails);
        if (isEmptyCommandAddDetail) {
            throw new EmptyCommandDeleteDetailException();
        }
    }

    private int getClientIndexToDelete(String commandDetails) throws EmptyClientIndexDeleteException {
        if (commandDetails.isEmpty()) {
            throw new EmptyClientIndexDeleteException();
        }
        return Integer.parseInt(commandDetails.trim()) - 1;
    }

    private Command prepareForCommandList(String commandDetails) throws IncorrectListDetailsException {
        if (commandDetails.trim().equals("-client")) {
            return new CommandListClients();
        } else if (commandDetails.trim().equals("-property")) {
            return new CommandListProperties();
        } else {
            throw new IncorrectListDetailsException();
        }
    }

    private void checkForClientIndexFlag(String commandDetails)
            throws MissingClientIndexFlagException, InvalidClientIndexFlagFormatException {
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

    private int getPropertyIndexToDelete(String commandDetails) throws EmptyPropertyIndexDeleteException {
        if (commandDetails.isEmpty()) {
            throw new EmptyPropertyIndexDeleteException();
        }
        return Integer.parseInt(commandDetails.trim()) - 1;
    }

    private void checkForPropertyIndexFlag(String commandDetails)
            throws MissingPropertyIndexFlagException, InvalidPropertyIndexFlagFormatException {
        if (!commandDetails.contains("ip/")) {
            throw new MissingPropertyIndexFlagException();
        } else {
            String clientIndexFlag = commandDetails.substring(0, 3);
            if (!clientIndexFlag.equals("ip/")) {
                throw new InvalidPropertyIndexFlagFormatException();
            }
        }
    }

    private Command prepareForCommandDeleteProperty(int propertyIndex, PropertyList propertyList)
            throws InvalidPropertyIndexDeleteException {
        checkForInvalidPropertyIndexDelete(propertyIndex, propertyList);
        return new CommandDeleteProperty(propertyIndex);
    }

    private void checkForInvalidPropertyIndexDelete(int propertyIndex, PropertyList propertyList)
            throws InvalidPropertyIndexDeleteException {
        int currentListSize = propertyList.getCurrentListSize();
        if (propertyIndex < 0 || propertyIndex >= currentListSize) {
            throw new InvalidPropertyIndexDeleteException();
        }
    }

    /* Pair/Unpair Parse Section */

    private void checkForEmptyCommandPairUnpairDetails(String commandPairUnpairDetails)
            throws EmptyCommandPairUnpairDetailsException {
        boolean isEmptyCommandPairUnpairDetail = checkForEmptyDetail(commandPairUnpairDetails);
        if (isEmptyCommandPairUnpairDetail) {
            throw new EmptyCommandPairUnpairDetailsException();
        }
    }

    private Command prepareForCommandPair(String rawPairDescriptions) throws NotIntegerException,
            NotValidIndexException, MissingPairUnpairFlagException, IncorrectPairUnpairFlagOrderException,
            ClientAlreadyPairedException, PropertyAlreadyPairedException {
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
            ClientAlreadyPairedException, PropertyAlreadyPairedException {
        int clientIndex = pairUnpairDetails.get(0);
        int propertyIndex = pairUnpairDetails.get(1);
        checkForPairingListIndexOutOfBounds(clientIndex, propertyIndex);

        Client client = clientList.getClientList().get(clientIndex);
        Property property = propertyList.getPropertyList().get(propertyIndex);

        if (pairingList.isClientPairedWithProperty(client)) {
            throw new ClientAlreadyPairedException();
        }

        if (pairingList.isPropertyPairedWithClient(property)) {
            throw new PropertyAlreadyPairedException();
        }
    }

    private void validateUnpairDetails(ArrayList<Integer> pairUnpairDetails) throws NotValidIndexException,
            NoExistingPairException {
        int clientIndex = pairUnpairDetails.get(0);
        int propertyIndex = pairUnpairDetails.get(1);
        checkForPairingListIndexOutOfBounds(clientIndex, propertyIndex);
        Client client = clientList.getClientList().get(clientIndex);
        if (!pairingList.isClientPairedWithProperty(client)) {
            throw new NoExistingPairException();
        }
    }

    private void checkForPairingListIndexOutOfBounds(int clientIndex, int propertyIndex) throws NotValidIndexException {
        checkForClientListIndexOutOfBounds(clientIndex);
        checkForPropertyListIndexOutOfBounds(propertyIndex);
    }

    private void checkForPropertyListIndexOutOfBounds(int propertyIndex) throws NotValidIndexException {
        if (propertyIndex < 0 || propertyIndex > propertyList.getCurrentListSize() - 1) {
            throw new NotValidIndexException();
        }
    }

    private void checkForClientListIndexOutOfBounds(int clientIndex) throws NotValidIndexException {
        if (clientIndex < 0 || clientIndex > clientList.getCurrentListSize() - 1) {
            throw new NotValidIndexException();
        }
    }

    private Command prepareForCommandCheckProperty(String rawPropertyDescriptions) throws NotIntegerException,
            NotValidIndexException, MissingCheckPropertyFlagException {
        try {
            ArrayList<Integer> checkPropertyDetails = processCheckPropertyDetails(rawPropertyDescriptions);
            validateCheckPropertyDetails(checkPropertyDetails);
            return new CommandCheckProperty(checkPropertyDetails);
        } catch (MissingFlagException e) {
            throw new MissingCheckPropertyFlagException();
        }
    }

    private void checkForEmptyCommandCheckDetails(String commandCheckDetails) throws EmptyCommandCheckDetailException {
        boolean isEmptyCommandCheckDetail = checkForEmptyDetail(commandCheckDetails);
        if (isEmptyCommandCheckDetail) {
            throw new EmptyCommandCheckDetailException();
        }
    }

    private ArrayList<Integer> processCheckPropertyDetails(String rawPropertyDetails) throws MissingFlagException,
            NotIntegerException {
        String[] checkPropertyFlags = {"ip/"};
        int[] checkPropertyFlagIndexPositions = new int[CHECK_PROPERTY_FLAG_SIZE];

        for (int i = 0; i < checkPropertyFlags.length; i++) {
            checkPropertyFlagIndexPositions[i] = rawPropertyDetails.indexOf(checkPropertyFlags[i]);
        }

        checkForMissingCheckPropertyFlags(checkPropertyFlagIndexPositions);
        return extractCheckPropertyDetails(rawPropertyDetails, checkPropertyFlagIndexPositions, checkPropertyFlags);
    }

    private void checkForMissingCheckPropertyFlags(int[] checkPropertyFlagIndexPositions) throws MissingFlagException {
        checkForEssentialCheckPropertyFlag(checkPropertyFlagIndexPositions[0]);
    }

    private void checkForEssentialCheckPropertyFlag(int checkPropertyFlagIndexPosition) throws MissingFlagException {
        boolean hasFlag = (checkPropertyFlagIndexPosition != -1);
        if (!hasFlag) {
            throw new MissingFlagException();
        }
    }

    private ArrayList<Integer> extractCheckPropertyDetails(String rawPropertyDetails,
            int[] checkPropertyFlagIndexPositions, String[] checkFlags) throws NotIntegerException {
        String propertyIndexString = extractDetail(rawPropertyDetails,
                checkPropertyFlagIndexPositions[0] + checkFlags[0].length());

        int propertyIndex;
        try {
            propertyIndex = Integer.parseInt(propertyIndexString);
        } catch (NumberFormatException e) {
            throw new NotIntegerException();
        }
        ArrayList<Integer> processedCheckPropertyDetails = new ArrayList<>();
        processedCheckPropertyDetails.add(propertyIndex - 1);
        return processedCheckPropertyDetails;
    }

    private void validateCheckPropertyDetails(ArrayList<Integer> checkPropertyDetails) throws NotValidIndexException {
        int propertyIndex = checkPropertyDetails.get(0);
        checkForPropertyListIndexOutOfBounds(propertyIndex);
    }

    private void checkForByeParameters(String commandDetails) throws ByeParametersPresentException {
        if (!commandDetails.trim().isEmpty()) {
            throw new ByeParametersPresentException();
        }
    }

}
