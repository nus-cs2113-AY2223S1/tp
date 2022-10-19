package seedu.duke.parsermanager;

import seedu.duke.command.Command;
import seedu.duke.command.CommandAddProperty;
import seedu.duke.exception.EmptyDetailException;
import seedu.duke.exception.IncorrectFlagOrderException;
import seedu.duke.exception.InvalidPriceFormatException;
import seedu.duke.exception.InvalidSingaporeAddressException;
import seedu.duke.exception.MissingFlagException;

import java.util.ArrayList;

import static seedu.duke.CommandStructure.ADD_PROPERTY_FLAGS;
import static seedu.duke.Messages.EXCEPTION;
import static seedu.duke.Messages.MESSAGE_ADD_PROPERTY_WRONG_FORMAT;
import static seedu.duke.Messages.MESSAGE_INVALID_SINGAPORE_ADDRESS;
import static seedu.duke.Messages.MESSAGE_INVALID_PRICE_FORMAT;

public class ParseAddProperty extends ParseAdd {
    private final String commandDescription;

    private static final int PROPERTY_FLAG_SIZE = 4;
    private static final int PROPERTY_ADDRESS_INDEX = 1;
    private static final int PROPERTY_PRICE_INDEX = 2;

    private static final int FLAG_JUMPER_VALUE = 2;
    private static final int UNIT_VALUE = 1;

    /* Add Property Regex for Validation */
    //Singapore Address Related Regex
    private static final String LANDED_PROPERTY_UNIT_NUMBER_REGEX = "^([0-9]{1,4})([A-Z]?) ";
    private static final String BUILDING_BLOCK_NUMBER_REGEX = "^([0-9]{1,4})([A-Z]?) ";
    private static final String STREET_NAME_REGEX = "[^.!@#$%^&*()_+=<>\\s\\n?`~0-9,{}|-]([a-zA-Z\\s]+)[^.!@#$%^&*()_+"
            + "=<>\\s\\n?`~0-9,{}|-]";
    private static final String STREET_NUMBER_REGEX = " ([1-9]{1}[0-9]{0,3})";
    private static final String BUILDING_UNIT_FLOOR_AND_NUMBER_REGEX = " #(([0]{1}[1-9]{1})|([1-9]{1}[0-9]{1,2}))-(([0]"
            + "{1}[1-9]{1})|([1-9]{1}[0-9]{1,3}))([A-Z]?)";
    private static final String BUILDING_NAME_REGEX = " [^.!@#$%^&*()_+=<>\\s\\n?`~0-9,{}|-]([a-zA-Z\\s]+)[^.!@#$%^&*()"
            + "_+=<>\\s\\n?`~0-9,{}|-]";
    private static final String POSTAL_CODE_REGEX = ", (Singapore [0-9]{6})$";

    //Singapore Landed Property Regex
    private static final String LANDED_PROPERTY_ADDRESS_REGEX = LANDED_PROPERTY_UNIT_NUMBER_REGEX + STREET_NAME_REGEX
            + POSTAL_CODE_REGEX;
    private static final String LANDED_PROPERTY_ADDRESS_WITH_STREET_NUMBER_REGEX = LANDED_PROPERTY_UNIT_NUMBER_REGEX
            + STREET_NAME_REGEX + STREET_NUMBER_REGEX + POSTAL_CODE_REGEX;

    //Singapore Building Regex
    private static final String BUILDING_ADDRESS_REGEX = BUILDING_BLOCK_NUMBER_REGEX + STREET_NAME_REGEX
            + BUILDING_UNIT_FLOOR_AND_NUMBER_REGEX + POSTAL_CODE_REGEX;
    private static final String BUILDING_ADDRESS_WITH_STREET_NUMBER_REGEX = BUILDING_BLOCK_NUMBER_REGEX
            + STREET_NAME_REGEX + STREET_NUMBER_REGEX + BUILDING_UNIT_FLOOR_AND_NUMBER_REGEX + POSTAL_CODE_REGEX;
    private static final String BUILDING_ADDRESS_WITH_BUILDING_NAME_REGEX = BUILDING_BLOCK_NUMBER_REGEX
            + STREET_NAME_REGEX + BUILDING_UNIT_FLOOR_AND_NUMBER_REGEX + BUILDING_NAME_REGEX + POSTAL_CODE_REGEX;
    private static final String BUILDING_ADDRESS_WITH_STREET_NUMBER_AND_BUILDING_NAME_REGEX
            = BUILDING_BLOCK_NUMBER_REGEX + STREET_NAME_REGEX + STREET_NUMBER_REGEX
            + BUILDING_UNIT_FLOOR_AND_NUMBER_REGEX + BUILDING_NAME_REGEX + POSTAL_CODE_REGEX;

    //Accepts only positive whole number for price
    private static final String VALID_PRICE_REGEX = "^[1-9]\\d*$";

    public ParseAddProperty(String addCommandDescription) {
        this.commandDescription = addCommandDescription;
    }

    public Command parseCommand() throws  EmptyDetailException, MissingFlagException, IncorrectFlagOrderException,
            InvalidSingaporeAddressException, InvalidPriceFormatException {
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

    private ArrayList<String> processCommandAddPropertyDetails(String rawCommandDetail)
            throws MissingFlagException, IncorrectFlagOrderException {
        int[] flagIndexPositions = getFlagIndexPositions(rawCommandDetail, ADD_PROPERTY_FLAGS);
        checkForMissingPropertyFlags(flagIndexPositions);
        checkPropertyFlagsOrder(flagIndexPositions);
        return extractPropertyDetails(rawCommandDetail, flagIndexPositions);
    }

    private void checkForMissingPropertyFlags(int[] flagIndexPositions) throws MissingFlagException {
        for (int flagIndex : flagIndexPositions) {
            if (!checkForFlagPresence(flagIndex)) {
                throw new MissingFlagException(EXCEPTION);
            }
        }
    }

    private void checkPropertyFlagsOrder(int[] flagIndexPositions) throws IncorrectFlagOrderException {
        for (int flagIndex = 0; flagIndex < PROPERTY_FLAG_SIZE - UNIT_VALUE; flagIndex++) {
            checkForCorrectFlagOrder(flagIndexPositions[flagIndex], flagIndexPositions[flagIndex + UNIT_VALUE]);
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
        boolean hasValidLandedPropertyAddress = checkForDetailFormat(LANDED_PROPERTY_ADDRESS_REGEX, address);
        boolean hasValidLandedPropertyAddressWithStreetNumber
                = checkForDetailFormat(LANDED_PROPERTY_ADDRESS_WITH_STREET_NUMBER_REGEX, address);
        return hasValidLandedPropertyAddress || hasValidLandedPropertyAddressWithStreetNumber;
    }

    private boolean checkForValidSingaporeBuildingAddress(String address) {
        boolean hasValidBuildingAddress = checkForDetailFormat(BUILDING_ADDRESS_REGEX, address);
        boolean hasValidBuildingAddressWithStreetNumber
                = checkForDetailFormat(BUILDING_ADDRESS_WITH_STREET_NUMBER_REGEX, address);
        boolean hasValidBuildingAddressWithBuildingName
                = checkForDetailFormat(BUILDING_ADDRESS_WITH_BUILDING_NAME_REGEX, address);
        boolean hasValidBuildingAddressWithStreetNumberAndBuildingName
                = checkForDetailFormat(BUILDING_ADDRESS_WITH_STREET_NUMBER_AND_BUILDING_NAME_REGEX, address);
        return hasValidBuildingAddress || hasValidBuildingAddressWithStreetNumber
                || hasValidBuildingAddressWithBuildingName || hasValidBuildingAddressWithStreetNumberAndBuildingName;
    }

    private void checkForPriceNumberFormat(String budget) throws InvalidPriceFormatException {
        boolean hasValidPriceNumberFormat = checkForDetailFormat(VALID_PRICE_REGEX, budget);
        if (!hasValidPriceNumberFormat) {
            throw new InvalidPriceFormatException(EXCEPTION);
        }
    }
}
