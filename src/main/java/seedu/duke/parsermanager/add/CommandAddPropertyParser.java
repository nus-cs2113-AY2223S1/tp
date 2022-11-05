//@@author OVReader

package seedu.duke.parsermanager.add;

import seedu.duke.Property;
import seedu.duke.PropertyList;
import seedu.duke.Ui;

import seedu.duke.command.Command;
import seedu.duke.command.add.CommandAddProperty;

import seedu.duke.exception.parseaddexception.parseaddpropertyexception.DuplicatePropertyException;
import seedu.duke.exception.parseaddexception.parseaddpropertyexception.EmptyAddPropertyDetailException;
import seedu.duke.exception.parseaddexception.parseaddpropertyexception.IncorrectAddPropertyFlagOrderException;
import seedu.duke.exception.parseaddexception.parseaddpropertyexception.AddressFormatUnitTypeMismatchException;
import seedu.duke.exception.parseaddexception.parseaddpropertyexception.InvalidPriceFormatException;
import seedu.duke.exception.parseaddexception.parseaddpropertyexception.InvalidSingaporeAddressException;
import seedu.duke.exception.parseaddexception.parseaddpropertyexception.InvalidUnitTypeLabelException;
import seedu.duke.exception.parseaddexception.parseaddpropertyexception.MissingAddPropertyFlagException;
import seedu.duke.exception.parseaddexception.parseaddpropertyexception.ParseAddPropertyException;

import java.util.ArrayList;
import java.util.HashMap;

import static seedu.duke.CommandStructure.ADD_PROPERTY_FLAGS;
import static seedu.duke.CommandStructure.UNIT_TYPE_2ROOM_SHORT;
import static seedu.duke.CommandStructure.UNIT_TYPE_3ROOM_SHORT;
import static seedu.duke.CommandStructure.UNIT_TYPE_4ROOM_SHORT;
import static seedu.duke.CommandStructure.UNIT_TYPE_5ROOM_SHORT;
import static seedu.duke.CommandStructure.UNIT_TYPE_3GEN;
import static seedu.duke.CommandStructure.UNIT_TYPE_EXECUTIVE_FLAT_SHORT;
import static seedu.duke.CommandStructure.UNIT_TYPE_DBSS_SHORT;
import static seedu.duke.CommandStructure.UNIT_TYPE_MAISONETTE_SHORT;
import static seedu.duke.CommandStructure.UNIT_TYPE_JUMBO_SHORT;
import static seedu.duke.CommandStructure.UNIT_TYPE_HDB_TERRENCE_SHORT;
import static seedu.duke.CommandStructure.UNIT_TYPE_CONDO_SHORT;
import static seedu.duke.CommandStructure.UNIT_TYPE_PENTHOUSE_SHORT;
import static seedu.duke.CommandStructure.UNIT_TYPE_LANDED_TERRENCE_SHORT;
import static seedu.duke.CommandStructure.UNIT_TYPE_SEMI_DETEACHED_SHORT;
import static seedu.duke.CommandStructure.UNIT_TYPE_BUNGALOW_SHORT;
import static seedu.duke.CommandStructure.ACTUAL_UNIT_TYPE_2ROOM;
import static seedu.duke.CommandStructure.ACTUAL_UNIT_TYPE_3ROOM;
import static seedu.duke.CommandStructure.ACTUAL_UNIT_TYPE_4ROOM;
import static seedu.duke.CommandStructure.ACTUAL_UNIT_TYPE_5ROOM;
import static seedu.duke.CommandStructure.ACTUAL_UNIT_TYPE_3GEN;
import static seedu.duke.CommandStructure.ACTUAL_UNIT_TYPE_EXECUTIVE_FLAT;
import static seedu.duke.CommandStructure.ACTUAL_UNIT_TYPE_DBSS;
import static seedu.duke.CommandStructure.ACTUAL_UNIT_TYPE_MAISONETTE;
import static seedu.duke.CommandStructure.ACTUAL_UNIT_TYPE_JUMBO;
import static seedu.duke.CommandStructure.ACTUAL_UNIT_TYPE_TERRENCE;
import static seedu.duke.CommandStructure.ACTUAL_UNIT_TYPE_CONDO;
import static seedu.duke.CommandStructure.ACTUAL_UNIT_TYPE_HDB_PENTHOUSE;
import static seedu.duke.CommandStructure.ACTUAL_UNIT_TYPE_LANDED_TERRENCE;
import static seedu.duke.CommandStructure.ACTUAL_UNIT_TYPE_SEMI_DETACHED;
import static seedu.duke.CommandStructure.ACTUAL_UNIT_TYPE_BUNGALOW;

/**
 * Parser for add property command.
 */
public class CommandAddPropertyParser extends CommandAddParser {
    private final String commandDescription;
    private final PropertyList propertyList;

    private static final int PROPERTY_FLAG_SIZE = 4;
    private static final int PROPERTY_ADDRESS_INDEX = 1;
    private static final int PROPERTY_PRICE_INDEX = 2;
    private static final int PROPERTY_UNIT_TYPE_INDEX = 3;

    private static final int FLAG_JUMPER_VALUE = 2;
    private static final int UNIT_VALUE = 1;

    private static final String EXISTING_PROPERTY = "Existing Property:\n  ";


    // Add Property Address Validation Regex
    // Singapore Address Common Component Regex
    private static final String STREET_NAME_REGEX = "[^.!@#$%^&*()_+=<>\\s\\n?`~0-9,{}|-]([a-zA-Z\\s]+)[^.!@#$%^&*()_+"
            + "=<>\\s\\n?`~0-9,{}|-]";
    private static final String STREET_NUMBER_REGEX = " ([1-9]{1}[0-9]{0,3})";
    private static final String POSTAL_CODE_REGEX = ", (S[0-9]{6})$";

    // Singapore Landed Property Regex
    private static final String LANDED_PROPERTY_UNIT_NUMBER_REGEX = "^([0-9]{1,4})([A-Z]?) ";

    private static final String LANDED_PROPERTY_ADDRESS_REGEX = LANDED_PROPERTY_UNIT_NUMBER_REGEX + STREET_NAME_REGEX
            + POSTAL_CODE_REGEX;
    private static final String LANDED_PROPERTY_ADDRESS_WITH_STREET_NUMBER_REGEX = LANDED_PROPERTY_UNIT_NUMBER_REGEX
            + STREET_NAME_REGEX + STREET_NUMBER_REGEX + POSTAL_CODE_REGEX;

    // Singapore Building Regex
    private static final String BUILDING_BLOCK_NUMBER_REGEX = "^([0-9]{1,4})([A-Z]?) ";
    private static final String BUILDING_UNIT_FLOOR_AND_NUMBER_REGEX = " #(([0]{1}[1-9]{1})|([1-9]{1}[0-9]{1,2}))-(([0]"
            + "{1}[1-9]{1})|([1-9]{1}[0-9]{1,3}))([A-Z]?)";
    private static final String BUILDING_NAME_REGEX = " [^.!@#$%^&*()_+=<>\\s\\n?`~0-9,{}|-]([a-zA-Z\\s]+)[^.!@#$%^&*()"
            + "_+=<>\\s\\n?`~0-9,{}|-]";

    private static final String BUILDING_ADDRESS_REGEX = BUILDING_BLOCK_NUMBER_REGEX + STREET_NAME_REGEX
            + BUILDING_UNIT_FLOOR_AND_NUMBER_REGEX + POSTAL_CODE_REGEX;
    private static final String BUILDING_ADDRESS_WITH_STREET_NUMBER_REGEX = BUILDING_BLOCK_NUMBER_REGEX
            + STREET_NAME_REGEX + STREET_NUMBER_REGEX + BUILDING_UNIT_FLOOR_AND_NUMBER_REGEX + POSTAL_CODE_REGEX;
    private static final String BUILDING_ADDRESS_WITH_BUILDING_NAME_REGEX = BUILDING_BLOCK_NUMBER_REGEX
            + STREET_NAME_REGEX + BUILDING_UNIT_FLOOR_AND_NUMBER_REGEX + BUILDING_NAME_REGEX + POSTAL_CODE_REGEX;
    private static final String BUILDING_ADDRESS_WITH_STREET_NUMBER_AND_BUILDING_NAME_REGEX
            = BUILDING_BLOCK_NUMBER_REGEX + STREET_NAME_REGEX + STREET_NUMBER_REGEX
            + BUILDING_UNIT_FLOOR_AND_NUMBER_REGEX + BUILDING_NAME_REGEX + POSTAL_CODE_REGEX;

    // Accepts only positive integer for price.
    private static final String VALID_PRICE_REGEX = "^[1-9]\\d*$";

    private static HashMap<String, String> unitTypeHashmap;

    // Tags for matching address format and unit type validation
    private static final String HASH_SYMBOL = "#";
    private static final String LANDED_PROPERTY_TAG = "LP";
    private static final String HDB_TAG = "HDB";
    private static final String CONDOMINIUM = "Condominium";
    private static final String PENTHOUSE = "Penthouse";

    public CommandAddPropertyParser(String addCommandDescription, PropertyList propertyList) {
        this.commandDescription = addCommandDescription;
        this.propertyList = propertyList;

        HashMap<String, String> unitTypeHashMap = new HashMap<>();
        unitTypeHashMap.put(UNIT_TYPE_2ROOM_SHORT, ACTUAL_UNIT_TYPE_2ROOM);
        unitTypeHashMap.put(UNIT_TYPE_3ROOM_SHORT, ACTUAL_UNIT_TYPE_3ROOM);
        unitTypeHashMap.put(UNIT_TYPE_4ROOM_SHORT, ACTUAL_UNIT_TYPE_4ROOM);
        unitTypeHashMap.put(UNIT_TYPE_5ROOM_SHORT, ACTUAL_UNIT_TYPE_5ROOM);
        unitTypeHashMap.put(UNIT_TYPE_3GEN, ACTUAL_UNIT_TYPE_3GEN);
        unitTypeHashMap.put(UNIT_TYPE_EXECUTIVE_FLAT_SHORT, ACTUAL_UNIT_TYPE_EXECUTIVE_FLAT);
        unitTypeHashMap.put(UNIT_TYPE_DBSS_SHORT, ACTUAL_UNIT_TYPE_DBSS);
        unitTypeHashMap.put(UNIT_TYPE_MAISONETTE_SHORT, ACTUAL_UNIT_TYPE_MAISONETTE);
        unitTypeHashMap.put(UNIT_TYPE_JUMBO_SHORT, ACTUAL_UNIT_TYPE_JUMBO);
        unitTypeHashMap.put(UNIT_TYPE_HDB_TERRENCE_SHORT, ACTUAL_UNIT_TYPE_TERRENCE);
        unitTypeHashMap.put(UNIT_TYPE_CONDO_SHORT, ACTUAL_UNIT_TYPE_CONDO);
        unitTypeHashMap.put(UNIT_TYPE_PENTHOUSE_SHORT, ACTUAL_UNIT_TYPE_HDB_PENTHOUSE);
        unitTypeHashMap.put(UNIT_TYPE_LANDED_TERRENCE_SHORT, ACTUAL_UNIT_TYPE_LANDED_TERRENCE);
        unitTypeHashMap.put(UNIT_TYPE_SEMI_DETEACHED_SHORT, ACTUAL_UNIT_TYPE_SEMI_DETACHED);
        unitTypeHashMap.put(UNIT_TYPE_BUNGALOW_SHORT, ACTUAL_UNIT_TYPE_BUNGALOW);
        unitTypeHashmap = unitTypeHashMap;
    }

    /**
     * Parses input for add property command.
     *
     * @return CommandAddProperty object that is responsible for the execution of add property command.
     * @exception ParseAddPropertyException Represents any exception during the parsing of add property command
     *                                      description.
     */
    public Command parseCommand() throws ParseAddPropertyException {
        checkForEmptyAddPropertyDetails(commandDescription);
        ArrayList<String> propertyDetails = processCommandAddPropertyDetails(commandDescription);
        validatePropertyDetails(propertyDetails);
        return new CommandAddProperty(propertyDetails);
    }

    private void checkForEmptyAddPropertyDetails(String commandDetail) throws EmptyAddPropertyDetailException {
        boolean isEmptyDetail = isEmptyString(commandDetail);
        if (isEmptyDetail) {
            throw new EmptyAddPropertyDetailException();
        }
    }

    public ArrayList<String> processCommandAddPropertyDetails(String rawCommandDetail)
            throws MissingAddPropertyFlagException, IncorrectAddPropertyFlagOrderException {
        int[] flagIndexPositions = getFlagIndexPositions(rawCommandDetail, ADD_PROPERTY_FLAGS);
        checkForMissingPropertyFlags(flagIndexPositions);
        checkPropertyFlagsOrder(flagIndexPositions);
        return extractPropertyDetails(rawCommandDetail, flagIndexPositions);
    }

    private void checkForMissingPropertyFlags(int[] flagIndexPositions) throws MissingAddPropertyFlagException {
        for (int flagIndex : flagIndexPositions) {
            boolean hasPropertyFlag = checkForFlagPresence(flagIndex);
            if (!hasPropertyFlag) {
                throw new MissingAddPropertyFlagException();
            }
        }
    }

    private void checkPropertyFlagsOrder(int[] flagIndexPositions) throws IncorrectAddPropertyFlagOrderException {
        for (int flagIndex = 0; flagIndex < PROPERTY_FLAG_SIZE - UNIT_VALUE; flagIndex++) {
            checkForCorrectFlagOrder(flagIndexPositions[flagIndex], flagIndexPositions[flagIndex + UNIT_VALUE]);
        }
    }

    private void checkForCorrectFlagOrder(int flagPosition, int nextFlagPosition) throws
            IncorrectAddPropertyFlagOrderException {
        boolean hasCorrectOrder = (flagPosition < nextFlagPosition);
        if (!hasCorrectOrder) {
            throw new IncorrectAddPropertyFlagOrderException();
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

    public void validatePropertyDetails(ArrayList<String> propertyDetails) throws EmptyAddPropertyDetailException,
            InvalidSingaporeAddressException, InvalidPriceFormatException, InvalidUnitTypeLabelException,
            AddressFormatUnitTypeMismatchException, DuplicatePropertyException {
        // Checks for Missing Landlord Name, Property Address, Renting Price (SGD/month) and Unit-Type.
        for (String propertyDetail : propertyDetails) {
            checkForEmptyAddPropertyDetails(propertyDetail);
        }

        checkForValidSingaporeAddress(propertyDetails.get(PROPERTY_ADDRESS_INDEX));
        checkForPriceNumberFormat(propertyDetails.get(PROPERTY_PRICE_INDEX));
        propertyDetails.set(PROPERTY_UNIT_TYPE_INDEX,
                checkForValidUnitType(propertyDetails.get(PROPERTY_UNIT_TYPE_INDEX)));
        checkForValidAddressFormatUnitTypeMatching(propertyDetails.get(PROPERTY_ADDRESS_INDEX),
                propertyDetails.get(PROPERTY_UNIT_TYPE_INDEX));

        // Duplicate Property refers to properties with the same address.
        checkForDuplicateProperty(propertyList, propertyDetails.get(PROPERTY_ADDRESS_INDEX));
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
            throw new InvalidPriceFormatException();
        }
    }

    private String checkForValidUnitType(String unitTypeLabel) throws InvalidUnitTypeLabelException {
        String actualUnitType = unitTypeHashmap.get(unitTypeLabel);
        boolean hasValidUnitType = (actualUnitType != null);
        if (!hasValidUnitType) {
            throw new InvalidUnitTypeLabelException();
        }
        return actualUnitType;
    }

    private void checkForValidAddressFormatUnitTypeMatching(String address, String unitType) throws
            AddressFormatUnitTypeMismatchException {
        boolean isHdbTerrace = unitType.equals(ACTUAL_UNIT_TYPE_TERRENCE);
        // HDB Terrace Houses may or may not have unit level.
        if (isHdbTerrace) {
            return;
        }

        boolean hasUnitLevel = address.contains(HASH_SYMBOL);
        // Landed Properties do not have unit level, hence address does not contain '#' symbol
        if (!hasUnitLevel) {
            checkForLandedProperty(unitType);
        } else {
            checkForNonLandedProperty(unitType);
        }
    }

    private void checkForLandedProperty(String unitType) throws AddressFormatUnitTypeMismatchException {
        boolean hasLandedPropertyTag = unitType.contains(LANDED_PROPERTY_TAG);
        if (!hasLandedPropertyTag) {
            throw new AddressFormatUnitTypeMismatchException();
        }
    }

    private void checkForNonLandedProperty(String unitType) throws AddressFormatUnitTypeMismatchException {
        boolean hasHdbTag = unitType.contains(HDB_TAG);
        boolean isCondominium = unitType.contains(CONDOMINIUM);
        boolean isPenthouse = unitType.contains(PENTHOUSE);
        boolean isNonLandedProperty = hasHdbTag || isCondominium || isPenthouse;
        if (!isNonLandedProperty) {
            throw new AddressFormatUnitTypeMismatchException();
        }
    }

    private void checkForDuplicateProperty(PropertyList propertyList, String propertyAddress)
            throws DuplicatePropertyException {
        boolean isDuplicateAddress = checkForDuplicateAddress(propertyList, propertyAddress);
        if (isDuplicateAddress) {
            throw new DuplicatePropertyException();
        }
    }

    private boolean checkForDuplicateAddress(PropertyList propertyList, String propertyAddress) {
        for (Property property: propertyList.getPropertyList()) {
            boolean isDuplicateAddress = property.getPropertyAddress().equalsIgnoreCase(propertyAddress);
            if (isDuplicateAddress) {
                showExistingDuplicateProperty(property);
                return true;
            }
        }
        return false;
    }

    private static void showExistingDuplicateProperty(Property property) {
        Ui ui = new Ui();
        ui.printNewline();
        ui.showToUser(EXISTING_PROPERTY + property.toString());
    }
}