package seedu.duke.parsermanager;

import seedu.duke.command.Command;
import seedu.duke.command.CommandAddProperty;
import seedu.duke.exception.EmptyDescriptionException;
import seedu.duke.exception.EmptyDetailException;
import seedu.duke.exception.IncorrectFlagOrderException;
import seedu.duke.exception.InvalidPriceFormatException;
import seedu.duke.exception.InvalidSingaporeAddressException;
import seedu.duke.exception.MissingFlagException;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static seedu.duke.CommandStructure.ADD_PROPERTY_FLAGS;
import static seedu.duke.Messages.EXCEPTION;
import static seedu.duke.Messages.MESSAGE_ADD_PROPERTY_WRONG_FORMAT;
import static seedu.duke.Messages.MESSAGE_INVALID_SINGAPORE_ADDRESS;
import static seedu.duke.Messages.MESSAGE_INVALID_PRICE_FORMAT;

public class ParseAddProperty extends Parser {
    private final String commandDescription;

    private static final int PROPERTY_FLAG_SIZE = 4;
    private static final int PROPERTY_ADDRESS_INDEX = 1;
    private static final int PROPERTY_PRICE_INDEX = 2;

    private static final int MISSING_FLAG_VALUE = -1;
    private static final int FLAG_JUMPER_VALUE = 2;
    private static final int UNIT_VALUE = 1;

    public ParseAddProperty(String addCommandDescription) {
        this.commandDescription = addCommandDescription;
    }

    public Command parseCommand() throws EmptyDescriptionException, InvalidSingaporeAddressException,
            MissingFlagException, IncorrectFlagOrderException, InvalidPriceFormatException, EmptyDetailException {
        try {
            checkForEmptyDetails(commandDescription);
            ArrayList<String> propertyDetails = processCommandAddPropertyDetails(commandDescription);
            validatePropertyDetails(propertyDetails);
            return new CommandAddProperty(propertyDetails);
        } catch (EmptyDetailException e) {
            throw new EmptyDetailException(MESSAGE_ADD_PROPERTY_WRONG_FORMAT);
        } catch (MissingFlagException e) {
            throw new MissingFlagException(MESSAGE_ADD_PROPERTY_WRONG_FORMAT);
        } catch (IncorrectFlagOrderException e) {
            throw new IncorrectFlagOrderException(MESSAGE_ADD_PROPERTY_WRONG_FORMAT);
        } catch (InvalidSingaporeAddressException e) {
            throw new InvalidSingaporeAddressException(MESSAGE_INVALID_SINGAPORE_ADDRESS);
        } catch (InvalidPriceFormatException e) {
            throw new InvalidPriceFormatException(MESSAGE_INVALID_PRICE_FORMAT);
        }
    }

    private void checkForEmptyDetails(String commandDetail) throws EmptyDetailException {
        boolean isEmptyDetail = isEmptyString(commandDetail);
        if (isEmptyDetail) {
            throw new EmptyDetailException(EXCEPTION);
        }
    }

    private boolean isEmptyString(String commandDetail) {
        return commandDetail.trim().isEmpty();
    }

    private ArrayList<String> processCommandAddPropertyDetails(String rawCommandDetail)
            throws MissingFlagException, IncorrectFlagOrderException {
        String[] flags = ADD_PROPERTY_FLAGS;
        int[] flagIndexPositions = getFlagIndexPositions(rawCommandDetail, flags);
        checkForMissingPropertyFlags(flagIndexPositions);
        checkPropertyFlagsOrder(flagIndexPositions);
        return extractPropertyDetails(rawCommandDetail, flagIndexPositions);
    }

    private int[] getFlagIndexPositions(String commandDetail, String[] flags) {
        int[] flagIndexPositions = new int[flags.length];

        for (int flagIndex = 0; flagIndex < flags.length; flagIndex++) {
            flagIndexPositions[flagIndex] = commandDetail.indexOf(flags[flagIndex]);
        }
        return flagIndexPositions;
    }

    private void checkForMissingPropertyFlags(int[] flagIndexPositions) throws MissingFlagException {
        for (int flagIndex : flagIndexPositions) {
            if (!isFlagPresent(flagIndex)) {
                throw new MissingFlagException(EXCEPTION);
            }
        }
    }

    private boolean isFlagPresent(int flagIndexPosition) {
        return (flagIndexPosition != MISSING_FLAG_VALUE);
    }

    private void checkPropertyFlagsOrder(int[] flagIndexPositions) throws IncorrectFlagOrderException {
        for (int flagIndex = 0; flagIndex < PROPERTY_FLAG_SIZE - UNIT_VALUE; flagIndex++) {
            checkForCorrectFlagOrder(flagIndexPositions[flagIndex], flagIndexPositions[flagIndex + UNIT_VALUE]);
        }
    }

    private void checkForCorrectFlagOrder(int flagPosition, int nextFlagPosition) throws IncorrectFlagOrderException {
        boolean hasCorrectOrder = (flagPosition < nextFlagPosition);
        if (!hasCorrectOrder) {
            throw new IncorrectFlagOrderException(EXCEPTION);
        }
    }

    private ArrayList<String> extractPropertyDetails(String rawPropertyDetail, int[] addPropertyFlagIndexPositions) {
        ArrayList<String> extractedPropertyDetails = new ArrayList<>();
        for (int flagIndex = 0; flagIndex < PROPERTY_FLAG_SIZE; flagIndex++) {
            boolean isLastFlagIndex = ((flagIndex + UNIT_VALUE) == PROPERTY_FLAG_SIZE);
            if (isLastFlagIndex) {
                extractedPropertyDetails.add(extractDetail(rawPropertyDetail,
                        addPropertyFlagIndexPositions[flagIndex] + FLAG_JUMPER_VALUE).trim());
            } else {
                extractedPropertyDetails.add(extractDetail(rawPropertyDetail,
                        addPropertyFlagIndexPositions[flagIndex] + FLAG_JUMPER_VALUE,
                        addPropertyFlagIndexPositions[flagIndex + UNIT_VALUE]).trim());
            }
        }
        return extractedPropertyDetails;
    }

    private static String extractDetail(String rawDetail, int beginIndex) {
        return rawDetail.substring(beginIndex).trim();
    }

    private static String extractDetail(String rawDetail, int beginIndex, int endIndex) {
        return rawDetail.substring(beginIndex, endIndex).trim();
    }

    private void validatePropertyDetails(ArrayList<String> propertyDetails) throws EmptyDetailException,
            InvalidSingaporeAddressException, InvalidPriceFormatException {
        //Checks for Missing Landlord Name, Property Address, Renting Price (SGD/month) and Unit-Type
        for (String propertyDetail : propertyDetails) {
            checkForEmptyDetails(propertyDetail);
        }

        //Checks Format for Address (Singapore) and Renting Price
        checkForValidSingaporeAddress(propertyDetails.get(PROPERTY_ADDRESS_INDEX));
        checkForPriceNumberFormat(propertyDetails.get(PROPERTY_PRICE_INDEX));
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

    private boolean checkForDetailFormat(String regex, String detail) {
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(detail);
        return matcher.matches();
    }
}
