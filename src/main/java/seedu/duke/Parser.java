package seedu.duke;

import seedu.duke.command.Command;
import seedu.duke.command.CommandAddClient;
import seedu.duke.command.CommandAddProperty;
import seedu.duke.command.CommandBye;
import seedu.duke.command.CommandCheckProperty;
import seedu.duke.command.CommandDeleteClient;
import seedu.duke.command.CommandDeleteProperty;
import seedu.duke.command.CommandListClients;
import seedu.duke.command.CommandListProperties;
import seedu.duke.command.CommandPair;
import seedu.duke.command.CommandUndefined;
import seedu.duke.command.CommandUnpair;
import seedu.duke.exception.ClientAlreadyPairedException;
import seedu.duke.exception.DukeParseException;
import seedu.duke.exception.EmptyDescriptionException;
import seedu.duke.exception.EmptyDetailException;
import seedu.duke.exception.ExistingPairException;
import seedu.duke.exception.ExtraParametersException;
import seedu.duke.exception.IncorrectFlagOrderException;
import seedu.duke.exception.InvalidBudgetFormatException;
import seedu.duke.exception.InvalidContactNumberException;
import seedu.duke.exception.InvalidEmailException;
import seedu.duke.exception.InvalidIndexException;
import seedu.duke.exception.InvalidPriceFormatException;
import seedu.duke.exception.InvalidSingaporeAddressException;
import seedu.duke.exception.MissingFlagException;
import seedu.duke.exception.NoExistingPairException;
import seedu.duke.exception.NotIntegerException;
import seedu.duke.exception.UndefinedSubCommandTypeException;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static seedu.duke.CommandStructure.ADD_CLIENT_FLAGS;
import static seedu.duke.CommandStructure.ADD_PROPERTY_FLAGS;
import static seedu.duke.CommandStructure.CHECK_PROPERTY_FLAGS;
import static seedu.duke.CommandStructure.COMMAND_ADD;
import static seedu.duke.CommandStructure.COMMAND_CHECK;
import static seedu.duke.CommandStructure.COMMAND_DELETE;
import static seedu.duke.CommandStructure.COMMAND_EXIT;
import static seedu.duke.CommandStructure.COMMAND_LIST;
import static seedu.duke.CommandStructure.COMMAND_PAIR;
import static seedu.duke.CommandStructure.COMMAND_UNPAIR;
import static seedu.duke.CommandStructure.DELETE_CLIENT_FLAGS;
import static seedu.duke.CommandStructure.DELETE_PROPERTY_FLAGS;
import static seedu.duke.CommandStructure.PAIR_FLAGS;
import static seedu.duke.CommandStructure.UNPAIR_FLAGS;
import static seedu.duke.Messages.EXCEPTION;
import static seedu.duke.Messages.MESSAGE_ADD_CLIENT_WRONG_FORMAT;
import static seedu.duke.Messages.MESSAGE_ADD_PROPERTY_WRONG_FORMAT;
import static seedu.duke.Messages.MESSAGE_BYE_PARAMETERS_PRESENT;
import static seedu.duke.Messages.MESSAGE_CHECK_PROPERTY_WRONG_FORMAT;
import static seedu.duke.Messages.MESSAGE_CLIENT_ALREADY_PAIRED;
import static seedu.duke.Messages.MESSAGE_DELETE_CLIENT_WRONG_FORMAT;
import static seedu.duke.Messages.MESSAGE_DELETE_PROPERTY_WRONG_FORMAT;
import static seedu.duke.Messages.MESSAGE_EMPTY_DESCRIPTION;
import static seedu.duke.Messages.MESSAGE_EXISTING_PAIR;
import static seedu.duke.Messages.MESSAGE_INCORRECT_LIST_DETAILS;
import static seedu.duke.Messages.MESSAGE_INVALID_BUDGET_FORMAT;
import static seedu.duke.Messages.MESSAGE_INVALID_CONTACT_NUMBER;
import static seedu.duke.Messages.MESSAGE_INVALID_EMAIL;
import static seedu.duke.Messages.MESSAGE_INVALID_INDEX;
import static seedu.duke.Messages.MESSAGE_INVALID_PRICE_FORMAT;
import static seedu.duke.Messages.MESSAGE_INVALID_SINGAPORE_ADDRESS;
import static seedu.duke.Messages.MESSAGE_MISSING_SUB_COMMAND_TYPE;
import static seedu.duke.Messages.MESSAGE_NOT_INTEGER;
import static seedu.duke.Messages.MESSAGE_NO_EXISTING_PAIR;
import static seedu.duke.Messages.MESSAGE_PAIR_WRONG_FORMAT;
import static seedu.duke.Messages.MESSAGE_UNPAIR_WRONG_FORMAT;

public class Parser {
    private static ClientList clientList;
    private static PropertyList propertyList;
    private static PairingList pairingList;


    public Parser(ClientList clientL, PropertyList propertyL, PairingList pairingL) {
        clientList = clientL;
        propertyList = propertyL;
        pairingList = pairingL;
    }

    public Command parseCommand(String input) throws DukeParseException, ExistingPairException,
            ClientAlreadyPairedException, NoExistingPairException {
        ArrayList<String> processedCommandDetails = partitionCommandTypeAndDetails(input);
        String commandType = processedCommandDetails.get(0);
        String commandDetails = processedCommandDetails.get(1);

        switch (commandType) {
        case COMMAND_ADD:
            checkForEmptyDescription(commandDetails);
            ArrayList<String> processedAddCommandDetails = partitionCommandTypeAndDetails(commandDetails);
            String subAddCommandType = processedAddCommandDetails.get(0);
            String clientOrPropertyDescriptions = processedAddCommandDetails.get(1);
            if (subAddCommandType.equals("-property")) {
                return prepareForCommandAddProperty(clientOrPropertyDescriptions);
            } else if (subAddCommandType.equals("-client")) {
                return prepareForCommandAddClient(clientOrPropertyDescriptions);
            } else {
                throw new UndefinedSubCommandTypeException(MESSAGE_MISSING_SUB_COMMAND_TYPE);
            }

        case COMMAND_DELETE:
            checkForEmptyDescription(commandDetails);
            ArrayList<String> processedDeleteCommandDetails = partitionCommandTypeAndDetails(commandDetails);
            String subDeleteCommandType = processedDeleteCommandDetails.get(0);
            String deleteDescriptions = processedDeleteCommandDetails.get(1).trim();
            if (subDeleteCommandType.equals("-property")) {
                return prepareForCommandDeleteProperty(deleteDescriptions);
            } else if (subDeleteCommandType.equals("-client")) {
                return prepareForCommandDeleteClient(deleteDescriptions);
            } else {
                throw new UndefinedSubCommandTypeException(MESSAGE_MISSING_SUB_COMMAND_TYPE);
            }

        case COMMAND_PAIR:
            checkForEmptyDescription(commandDetails);
            return prepareForCommandPair(commandDetails);

        case COMMAND_UNPAIR:
            checkForEmptyDescription(commandDetails);
            return prepareForCommandUnpair(commandDetails);

        case COMMAND_CHECK:
            checkForEmptyDescription(commandDetails);
            ArrayList<String> processedCheckCommandDetails = partitionCommandTypeAndDetails(commandDetails);
            String subCommandCheckType = processedCheckCommandDetails.get(0);
            String commandCheckClientOrPropertyDescriptions = processedCheckCommandDetails.get(1);

            if (subCommandCheckType.equals("-property")) {
                return prepareForCommandCheckProperty(commandCheckClientOrPropertyDescriptions);
            } else {
                throw new UndefinedSubCommandTypeException(MESSAGE_MISSING_SUB_COMMAND_TYPE);
            }

        case COMMAND_LIST:
            checkForEmptyDescription(commandDetails);
            return prepareForCommandList(commandDetails);

        case COMMAND_EXIT:
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

    private void checkForEmptyDescription(String commandDetails) throws EmptyDescriptionException {
        boolean isEmptyDescription = isEmptyString(commandDetails);
        if (isEmptyDescription) {
            throw new EmptyDescriptionException(MESSAGE_EMPTY_DESCRIPTION);
        }
    }

    private void checkForEmptyDetails(String commandDetails) throws EmptyDetailException {
        boolean isEmptyDetail = isEmptyString(commandDetails);
        if (isEmptyDetail) {
            throw new EmptyDetailException(EXCEPTION);
        }
    }

    private boolean isEmptyString(String commandDetails) {
        return commandDetails.trim().isEmpty();
    }


    private ArrayList<String> processCommandDetails(String rawCommandDetails, String[] flags)
            throws MissingFlagException, IncorrectFlagOrderException {

        int[] flagIndexPositions = getFlagIndexPositions(rawCommandDetails, flags);
        checkForMissingFlags(flagIndexPositions);
        checkFlagsOrder(flagIndexPositions);
        return extractCommandDetails(rawCommandDetails, flags, flagIndexPositions);
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

    // Command Add Client has a special detail processing method because it has an optional email field
    private ArrayList<String> processCommandAddClientDetails(String rawCommandDetails, String[] flags)
            throws MissingFlagException, IncorrectFlagOrderException {

        int[] flagIndexPositions = getFlagIndexPositions(rawCommandDetails, flags);
        checkForMissingFlags(flagIndexPositions);
        checkFlagsOrder(flagIndexPositions);
        return extractClientDetails(rawCommandDetails, flagIndexPositions);
    }

    private int[] getFlagIndexPositions(String commandDetails, String[] flags) {
        int[] flagIndexPositions = new int[flags.length];

        for (int i = 0; i < flags.length; i++) {
            flagIndexPositions[i] = commandDetails.indexOf(flags[i]);
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

    private boolean isFlagPresent(int flagIndexPosition) {
        return (flagIndexPosition != -1);
    }

    private void checkFlagsOrder(int[] flagIndexPositions) throws IncorrectFlagOrderException {
        for (int i = 0; i < flagIndexPositions.length - 1; i++) {
            checkForCorrectFlagOrder(flagIndexPositions[i], flagIndexPositions[i + 1]);
        }
    }

    private void checkForCorrectFlagOrder(int flagPosition, int nextFlagPosition) throws IncorrectFlagOrderException {
        boolean hasCorrectOrder = (flagPosition < nextFlagPosition);
        if (!hasCorrectOrder) {
            throw new IncorrectFlagOrderException(EXCEPTION);
        }
    }

    private ArrayList<String> extractCommandDetails(String rawCommandDetails, String[] flags,
            int[] flagIndexPositions) {
        ArrayList<String> extractedCommandDetails = new ArrayList<>();
        for (int i = 0; i < flags.length; i++) {
            String extractedDetail;
            if (i == flags.length - 1) {
                /* The extracted detail for the last flag starts from the char after the flag, to the end of
                   rawCommandDetails */
                extractedDetail = extractDetail(rawCommandDetails, flagIndexPositions[i] + flags[i].length());
            } else {
                // The extracted detail for non-last starts from the char after the flag, to index before the next flag
                extractedDetail = extractDetail(
                        rawCommandDetails,
                        flagIndexPositions[i] + flags[i].length(),
                        flagIndexPositions[i + 1]);
            }
            extractedCommandDetails.add(extractedDetail.trim());
        }
        return extractedCommandDetails;
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



    /* Add Property/Client Parse Section */

    private Command prepareForCommandAddProperty(String rawPropertyDescriptions)
            throws InvalidSingaporeAddressException, MissingFlagException, InvalidPriceFormatException,
            IncorrectFlagOrderException, EmptyDetailException {

        try {
            checkForEmptyDetails(rawPropertyDescriptions);

            ArrayList<String> propertyDetails = processCommandDetails(rawPropertyDescriptions, ADD_PROPERTY_FLAGS);
            validatePropertyDetails(propertyDetails);
            return new CommandAddProperty(propertyDetails);

        } catch (InvalidSingaporeAddressException e) {
            throw new InvalidSingaporeAddressException(MESSAGE_INVALID_SINGAPORE_ADDRESS);
        } catch (MissingFlagException e) {
            throw new MissingFlagException(MESSAGE_ADD_PROPERTY_WRONG_FORMAT);
        } catch (IncorrectFlagOrderException e) {
            throw new IncorrectFlagOrderException(MESSAGE_ADD_PROPERTY_WRONG_FORMAT);
        } catch (InvalidPriceFormatException e) {
            throw new InvalidPriceFormatException(MESSAGE_INVALID_PRICE_FORMAT);
        } catch (EmptyDetailException e) {
            throw new EmptyDetailException(MESSAGE_ADD_PROPERTY_WRONG_FORMAT);
        }
    }

    private Command prepareForCommandAddClient(String rawClientDescriptions) throws MissingFlagException,
            IncorrectFlagOrderException, InvalidContactNumberException, EmptyDetailException, InvalidEmailException,
            InvalidBudgetFormatException {

        try {
            checkForEmptyDetails(rawClientDescriptions);

            ArrayList<String> clientDetails = processCommandAddClientDetails(rawClientDescriptions, ADD_CLIENT_FLAGS);
            validateClientDetails(clientDetails);
            return new CommandAddClient(clientDetails);

        } catch (MissingFlagException e) {
            throw new MissingFlagException(MESSAGE_ADD_CLIENT_WRONG_FORMAT);
        } catch (IncorrectFlagOrderException e) {
            throw new IncorrectFlagOrderException(MESSAGE_ADD_CLIENT_WRONG_FORMAT);
        } catch (InvalidContactNumberException e) {
            throw new InvalidContactNumberException(MESSAGE_INVALID_CONTACT_NUMBER);
        } catch (EmptyDetailException e) {
            throw new EmptyDetailException(MESSAGE_ADD_CLIENT_WRONG_FORMAT);
        } catch (InvalidEmailException e) {
            throw new InvalidEmailException(MESSAGE_INVALID_EMAIL);
        } catch (InvalidBudgetFormatException e) {
            throw new InvalidBudgetFormatException(MESSAGE_INVALID_BUDGET_FORMAT);
        }
    }


    private void validatePropertyDetails(ArrayList<String> propertyDetails) throws EmptyDetailException,
            InvalidSingaporeAddressException, InvalidPriceFormatException {
        //Checks for Missing Landlord Name, Property Address, Renting Price (SGD/month) and Unit-Type
        for (String propertyDetail : propertyDetails) {
            checkForEmptyDetails(propertyDetail);
        }

        //Checks Format for Address (Singapore) and Renting Price
        checkForValidSingaporeAddress(propertyDetails.get(1));
        checkForPriceNumberFormat(propertyDetails.get(2));
    }

    private void validateClientDetails(ArrayList<String> clientDetails) throws EmptyDetailException,
            InvalidContactNumberException, InvalidEmailException, InvalidBudgetFormatException {
        //Checks for Missing Client Name, Contact Number, Budget Per Month (SGD)
        checkForEmptyDetails(clientDetails.get(0));
        checkForEmptyDetails(clientDetails.get(1));
        checkForEmptyDetails(clientDetails.get(3));

        //Checks for Contact Number, Email and Budget Format
        checkForValidSingaporeContactNumber(clientDetails.get(1));
        boolean hasEmail = !clientDetails.get(2).isEmpty();
        if (hasEmail) {
            checkForValidEmail(clientDetails.get(2));
        }
        checkForBudgetNumberFormat(clientDetails.get(3));
    }

    private void checkForValidSingaporeAddress(String address) throws InvalidSingaporeAddressException {
        boolean hasValidSingaporeLandedPropertyAddress = checkForValidSingaporeLandedPropertyAddress(address);
        boolean hasValidSingaporeBuildingAddress = checkForValidSingaporeBuildingAddress(address);

        boolean hasValidSingaporeAddress = hasValidSingaporeLandedPropertyAddress || hasValidSingaporeBuildingAddress;
        if (!hasValidSingaporeAddress) {
            throw new InvalidSingaporeAddressException(EXCEPTION);
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
            throw new InvalidPriceFormatException(EXCEPTION);
        }
    }

    private void checkForValidSingaporeContactNumber(String clientContactNumber) throws InvalidContactNumberException {
        boolean hasValidContactNumber = checkForDetailFormat("^[689]\\d{7}$", clientContactNumber);
        if (!hasValidContactNumber) {
            throw new InvalidContactNumberException(EXCEPTION);
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
            throw new InvalidEmailException(EXCEPTION);
        }
    }

    private void checkForBudgetNumberFormat(String budget) throws InvalidBudgetFormatException {
        //Accepts only positive whole number
        String regex = "^[1-9]\\d*$";
        boolean hasValidBudgetNumberFormat = checkForDetailFormat(regex, budget);
        if (!hasValidBudgetNumberFormat) {
            throw new InvalidBudgetFormatException(EXCEPTION);
        }
    }

    private boolean checkForDetailFormat(String regex, String detail) {
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(detail);
        return matcher.matches();
    }


    /* Delete Property/Client Parse Section */

    private Command prepareForCommandDeleteClient(String rawCommandDescription) throws InvalidIndexException,
            MissingFlagException, IncorrectFlagOrderException, NotIntegerException, EmptyDetailException {
        try {
            checkForEmptyDetails(rawCommandDescription);

            ArrayList<String> deleteClientDetailsString = processCommandDetails(rawCommandDescription,
                    DELETE_CLIENT_FLAGS);
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

    private void checkForInvalidClientIndexDelete(int clientIndex) throws InvalidIndexException {
        int currentListSize = clientList.getCurrentListSize();
        if (clientIndex < 0 || clientIndex >= currentListSize) {
            throw new InvalidIndexException(EXCEPTION);
        }
    }


    private Command prepareForCommandDeleteProperty(String rawCommandDescription) throws MissingFlagException,
            InvalidIndexException, IncorrectFlagOrderException, NotIntegerException, EmptyDetailException {

        try {
            checkForEmptyDetails(rawCommandDescription);
            ArrayList<String> deletePropertyDetailsString = processCommandDetails(rawCommandDescription,
                    DELETE_PROPERTY_FLAGS);
            ArrayList<Integer> deletePropertyDetailsInt = convertProcessedCommandDetailsToInteger(
                    deletePropertyDetailsString);

            int propertyIndex = deletePropertyDetailsInt.get(0);
            checkForInvalidPropertyIndexDelete(propertyIndex);
            return new CommandDeleteProperty(propertyIndex);
        } catch (InvalidIndexException e) {
            throw new InvalidIndexException(MESSAGE_INVALID_INDEX);
        } catch (MissingFlagException e) {
            throw new MissingFlagException(MESSAGE_DELETE_PROPERTY_WRONG_FORMAT);
        } catch (IncorrectFlagOrderException e) {
            throw new IncorrectFlagOrderException(MESSAGE_DELETE_PROPERTY_WRONG_FORMAT);
        } catch (NotIntegerException e) {
            throw new NotIntegerException(MESSAGE_NOT_INTEGER);
        } catch (EmptyDetailException e) {
            throw new EmptyDetailException(MESSAGE_DELETE_PROPERTY_WRONG_FORMAT);
        }
    }

    private void checkForInvalidPropertyIndexDelete(int propertyIndex) throws InvalidIndexException {
        int currentListSize = propertyList.getCurrentListSize();
        if (propertyIndex < 0 || propertyIndex >= currentListSize) {
            throw new InvalidIndexException(EXCEPTION);
        }
    }

    /* Pair/Unpair Parse Section */

    private Command prepareForCommandPair(String rawPairDescriptions) throws MissingFlagException,
            InvalidIndexException, ClientAlreadyPairedException, IncorrectFlagOrderException, NotIntegerException,
            ExistingPairException {

        try {
            ArrayList<String> pairDetailsString = processCommandDetails(rawPairDescriptions, PAIR_FLAGS);
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
        }
    }

    private Command prepareForCommandUnpair(String rawUnpairDescriptions) throws MissingFlagException,
            IncorrectFlagOrderException, NotIntegerException, InvalidIndexException, NoExistingPairException {

        try {
            ArrayList<String> unpairDetailsString = processCommandDetails(rawUnpairDescriptions, UNPAIR_FLAGS);
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


    private void validatePairDetails(ArrayList<Integer> pairDetails) throws InvalidIndexException,
            ClientAlreadyPairedException, ExistingPairException {
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


    /* Check client/property parse Section */

    private void checkForPropertyListIndexOutOfBounds(int propertyIndex) throws InvalidIndexException {
        if (propertyIndex < 0 || propertyIndex > propertyList.getCurrentListSize() - 1) {
            throw new InvalidIndexException(EXCEPTION);
        }
    }

    private void checkForClientListIndexOutOfBounds(int clientIndex) throws InvalidIndexException {
        if (clientIndex < 0 || clientIndex > clientList.getCurrentListSize() - 1) {
            throw new InvalidIndexException(EXCEPTION);
        }
    }

    private Command prepareForCommandCheckProperty(String rawCheckDescriptions) throws InvalidIndexException,
            MissingFlagException, IncorrectFlagOrderException, NotIntegerException {

        try {
            ArrayList<String> checkDetailsString = processCommandDetails(rawCheckDescriptions, CHECK_PROPERTY_FLAGS);
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


    private void validateCheckPropertyDetails(ArrayList<Integer> checkPropertyDetails) throws InvalidIndexException {
        int propertyIndex = checkPropertyDetails.get(0);
        checkForPropertyListIndexOutOfBounds(propertyIndex);
    }


    /* List Property/Client Parse Section */


    private Command prepareForCommandList(String commandDetails) throws UndefinedSubCommandTypeException {
        if (commandDetails.trim().equals("-client")) {
            return new CommandListClients();
        } else if (commandDetails.trim().equals("-property")) {
            return new CommandListProperties();
        } else {
            throw new UndefinedSubCommandTypeException(MESSAGE_INCORRECT_LIST_DETAILS);
        }
    }

    private void checkForByeParameters(String commandDetails) throws ExtraParametersException {
        if (!commandDetails.trim().isEmpty()) {
            throw new ExtraParametersException(MESSAGE_BYE_PARAMETERS_PRESENT);
        }
    }

}
