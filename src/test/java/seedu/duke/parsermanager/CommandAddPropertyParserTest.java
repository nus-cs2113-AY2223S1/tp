//@@author OVReader

package seedu.duke.parsermanager;

import org.junit.jupiter.api.Test;
import seedu.duke.ClientList;
import seedu.duke.PairingList;
import seedu.duke.PropertyList;
import seedu.duke.Storage;
import seedu.duke.Ui;
import seedu.duke.command.Command;
import seedu.duke.exception.AddressFormatUnitTypeMismatchException;
import seedu.duke.exception.DukeException;
import seedu.duke.exception.DuplicatePropertyException;
import seedu.duke.exception.EmptyAddPropertyDetailException;
import seedu.duke.exception.IncorrectAddPropertyFlagOrderException;
import seedu.duke.exception.InvalidPriceFormatException;
import seedu.duke.exception.InvalidSingaporeAddressException;
import seedu.duke.exception.InvalidUnitTypeLabelException;
import seedu.duke.exception.MissingAddPropertyFlagException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class CommandAddPropertyParserTest {
    private static final String VALID_LANDED_PROPERTY_INPUT = "n/Bob a/60 Aria Street, S602580 p/1000 t/LP BGL";
    private static final String VALID_LANDED_PROPERTY_WITH_STREET_NUMBER_INPUT = "n/Bob a/60 Aria Street 321, "
            + "S602580 p/1000 t/LP BGL";
    private static final String VALID_BUILDING_PROPERTY_WITH_STREET_NUMBER_INPUT = "n/Bob a/101C Marlow Street 121 "
            + "#12-05, S059020 p/1000 t/HDB 3";
    private static final String VALID_BUILDING_PROPERTY_WITH_STREET_NAME_AND_BUILDING_NAME_INPUT = "n/Bob a/101C "
            + "Marlow Street 121 #12-05 Clife Parkview, S059020 p/1000 t/HDB 3";

    private static final String LANDED_ADDRESS = "60 Aria Street, S602580";
    private static final String LANDED_ADDRESS_WITH_STREET_NUMBER = "60 Aria Street 321, S602580";
    private static final String BUILDING_ADDRESS_WITH_STREET_NUMBER = "101C Marlow Street 121 #12-05, S059020";
    private static final String BUILDING_ADDRESS_WITH_STREET_NUMBER_AND_BUILDING_NAME = "101C Marlow Street 121 #12-05 "
            + "Clife Parkview, S059020";

    private static final String LP_UNIT_TYPE = "LP Bungalow";
    private static final String BUILDING_UNIT_TYPE = "HDB 3-Room";


    private static final String EMPTY_PROPERTY_INPUT = "";
    private static final String MISSING_PROPERTY_FLAG = "n/Bob a/60 Aria Street, S602580 p/1000 LP BGL";
    private static final String INCORRECT_PROPERTY_FLAG_ORDER = "n/Bob p/60 Aria Street, S602580 t/1000 a/LP BGL";
    private static final String MISSING_PROPERTY_ADDRESS = "n/Bob a/ p/1000 t/LP BGL";
    private static final String INVALID_PROPERTY_ADDRESS = "n/Bob a/60 Aria Street :), S602580 p/1000 t/LP BGL";

    // Invalid Price Format Test Cases
    private static final String ZERO_PRICE = "n/Bob a/60 Aria Street, S602580 p/0 t/LP BGL";
    private static final String NEGATIVE_PRICE = "n/Bob a/60 Aria Street, S602580 p/-1 t/LP BGL";
    private static final String FLOATING_POINT_PRICE = "n/Bob a/60 Aria Street, S602580 p/12.345 t/LP BGL";

    private static final String INVALID_UNIT_TYPE_LABEL = "n/Bob a/60 Aria Street, S602580 p/1000 t/idk what label";

    // Invalid Address Format and Unit Type Test Cases
    private static final String LANDED_ADDRESS_NON_LANDED_UNIT_TYPE = "n/Bob a/60 Aria Street, S602580 p/1000 t/HDB 3";
    private static final String NON_LANDED_ADDRESS_LANDED_UNIT_TYPE = "n/Bob a/60 Aria Street #01-15, S602580 p/1000 "
            + "t/LP TH";

    // Property Details for Duplicate Properties Testing
    private static final String LANDLORD_NAME = "Bob";
    private static final String RENTING_PRICE = "1000";

    private static final int OFFSET_UNIT_VALUE = 1;


    @Test
    public void parseCommand_varyingAddressValidAddPropertyInput_matchingPropertyDetails() throws DukeException {
        parseCommand_validAddPropertyInput_matchingPropertyDetails(VALID_LANDED_PROPERTY_INPUT, LANDED_ADDRESS,
                LP_UNIT_TYPE);
        parseCommand_validAddPropertyInput_matchingPropertyDetails(VALID_LANDED_PROPERTY_WITH_STREET_NUMBER_INPUT,
                LANDED_ADDRESS_WITH_STREET_NUMBER, LP_UNIT_TYPE);
        parseCommand_validAddPropertyInput_matchingPropertyDetails(VALID_BUILDING_PROPERTY_WITH_STREET_NUMBER_INPUT,
                BUILDING_ADDRESS_WITH_STREET_NUMBER, BUILDING_UNIT_TYPE);
        parseCommand_validAddPropertyInput_matchingPropertyDetails(
                VALID_BUILDING_PROPERTY_WITH_STREET_NAME_AND_BUILDING_NAME_INPUT,
                BUILDING_ADDRESS_WITH_STREET_NUMBER_AND_BUILDING_NAME,
                BUILDING_UNIT_TYPE);
    }

    private static void parseCommand_validAddPropertyInput_matchingPropertyDetails(String validInput,
            String address, String unitType) throws DukeException {
        PropertyList propertyList = new PropertyList();
        CommandAddPropertyParser commandAddPropertyParser = new CommandAddPropertyParser(validInput, propertyList);

        Command command = commandAddPropertyParser.parseCommand();
        ClientList clientList = new ClientList();
        PairingList pairingList = new PairingList();
        Storage storage = new Storage(clientList, propertyList, pairingList);
        command.execute(new Ui(), storage, propertyList, clientList, pairingList);

        int addedPropertyIndex = propertyList.getCurrentListSize() - OFFSET_UNIT_VALUE;
        String storedLandlordName = propertyList.getPropertyList().get(addedPropertyIndex).getLandlordName();
        assertEquals(storedLandlordName, LANDLORD_NAME);
        String storedPropertyAddress = propertyList.getPropertyList().get(addedPropertyIndex).getPropertyAddress();
        assertEquals(storedPropertyAddress, address);
        String storedRentingPrice = propertyList.getPropertyList().get(addedPropertyIndex).getRentingPrice();
        assertEquals(storedRentingPrice, RENTING_PRICE);
        String storedUnitType = propertyList.getPropertyList().get(addedPropertyIndex).getUnitType();
        assertEquals(storedUnitType, unitType);
    }

    @Test
    public void checkForEmptyAddPropertyDetails_emptyPropertyDetail_exceptionThrown() {
        PropertyList propertyList = new PropertyList();
        CommandAddPropertyParser commandAddPropertyParser = new CommandAddPropertyParser(EMPTY_PROPERTY_INPUT,
                propertyList);
        assertThrows(EmptyAddPropertyDetailException.class, commandAddPropertyParser::parseCommand);
    }

    @Test
    public void checkForMissingPropertyFlag_missingPropertyFlag_exceptionThrown() {
        PropertyList propertyList = new PropertyList();
        CommandAddPropertyParser commandAddPropertyParser = new CommandAddPropertyParser(MISSING_PROPERTY_FLAG,
                propertyList);
        assertThrows(MissingAddPropertyFlagException.class, commandAddPropertyParser::parseCommand);
    }

    @Test
    public void checkPropertyFlagsOrder_incorrectPropertyFlagOrder_exceptionThrown() {
        PropertyList propertyList = new PropertyList();
        CommandAddPropertyParser commandAddPropertyParser = new CommandAddPropertyParser(INCORRECT_PROPERTY_FLAG_ORDER,
                propertyList);
        assertThrows(IncorrectAddPropertyFlagOrderException.class, commandAddPropertyParser::parseCommand);
    }

    @Test
    public void checkForMissingPropertyDetails_missingPropertyAddress_exceptionThrown() {
        PropertyList propertyList = new PropertyList();
        CommandAddPropertyParser commandAddPropertyParser = new CommandAddPropertyParser(MISSING_PROPERTY_ADDRESS,
                propertyList);
        assertThrows(EmptyAddPropertyDetailException.class, commandAddPropertyParser::parseCommand);
    }

    @Test
    public void checkForValidSingaporeAddress_invalidPropertyAddress_exceptionThrown() {
        PropertyList propertyList = new PropertyList();
        CommandAddPropertyParser commandAddPropertyParser = new CommandAddPropertyParser(INVALID_PROPERTY_ADDRESS,
                propertyList);
        assertThrows(InvalidSingaporeAddressException.class, commandAddPropertyParser::parseCommand);
    }

    @Test
    public void checkForPriceNumberFormat_invalidPrice_exceptionThrown() {
        PropertyList propertyList = new PropertyList();
        CommandAddPropertyParser commandAddPropertyParser;

        // Price must be positive integer
        commandAddPropertyParser = new CommandAddPropertyParser(ZERO_PRICE, propertyList);
        assertThrows(InvalidPriceFormatException.class, commandAddPropertyParser::parseCommand);
        commandAddPropertyParser = new CommandAddPropertyParser(NEGATIVE_PRICE, propertyList);
        assertThrows(InvalidPriceFormatException.class, commandAddPropertyParser::parseCommand);
        commandAddPropertyParser = new CommandAddPropertyParser(FLOATING_POINT_PRICE, propertyList);
        assertThrows(InvalidPriceFormatException.class, commandAddPropertyParser::parseCommand);
    }

    @Test
    public void checkForValidUnitType_invalidUnitTypeLabel_exceptionThrown() {
        PropertyList propertyList = new PropertyList();
        CommandAddPropertyParser commandAddPropertyParser = new CommandAddPropertyParser(INVALID_UNIT_TYPE_LABEL,
                propertyList);
        assertThrows(InvalidUnitTypeLabelException.class, commandAddPropertyParser::parseCommand);
    }

    @Test
    public void checkForValidAddressFormatUnitTypeMatching_invalidAddressFormatUnitTypeMatching_exceptionThrown() {
        PropertyList propertyList = new PropertyList();
        CommandAddPropertyParser commandAddPropertyParser;

        commandAddPropertyParser = new CommandAddPropertyParser(LANDED_ADDRESS_NON_LANDED_UNIT_TYPE, propertyList);
        assertThrows(AddressFormatUnitTypeMismatchException.class, commandAddPropertyParser::parseCommand);
        commandAddPropertyParser = new CommandAddPropertyParser(NON_LANDED_ADDRESS_LANDED_UNIT_TYPE, propertyList);
        assertThrows(AddressFormatUnitTypeMismatchException.class, commandAddPropertyParser::parseCommand);
    }

    @Test
    public void checkForDuplicateAddress_identicalPropertiesAdded_exceptionThrown() {
        PropertyList propertyList = new PropertyList();
        propertyList.addProperty(LANDLORD_NAME, LANDED_ADDRESS, RENTING_PRICE, LP_UNIT_TYPE);
        CommandAddPropertyParser commandAddPropertyParser = new CommandAddPropertyParser(VALID_LANDED_PROPERTY_INPUT,
                propertyList);
        assertThrows(DuplicatePropertyException.class, commandAddPropertyParser::parseCommand);
    }
}