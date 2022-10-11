package seedu.duke;

import seedu.duke.command.Command;
import seedu.duke.command.CommandAddProperty;
import seedu.duke.command.CommandAddClient;
import seedu.duke.command.CommandUndefined;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Parser {
    public static final int ADD_CLIENT_FLAG_SIZE = 4;
    public static final int ADD_PROPERTY_FLAG_SIZE = 4;


    public Command parseCommand(String input) throws EmptyCommandAddDetailException,
            UndefinedSubCommandAddTypeException, EmptyPropertyDetailException, MissingPropertyFlagException,
            IncorrectAddPropertyFlagOrderException, MissingPropertyDetailException, InvalidSingaporeAddressException,
            InvalidPriceFormatException, EmptyClientDetailException, MissingClientFlagException,
            IncorrectAddClientFlagOrderException, MissingClientDetailException, InvalidContactNumberException,
            InvalidEmailException, InvalidBudgetFormatException {
        ArrayList<String> processedCommandDetails = partitionCommandTypeAndDetails(input);
        String commandType    = processedCommandDetails.get(0);
        String commandDetails = processedCommandDetails.get(1);
        switch (commandType) {
        case ("add"):
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

    private Command prepareForCommandAddProperty(String rawPropertyDescriptions) throws  EmptyPropertyDetailException,
            MissingPropertyFlagException, IncorrectAddPropertyFlagOrderException, MissingPropertyDetailException,
            InvalidSingaporeAddressException, InvalidPriceFormatException {
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
}
