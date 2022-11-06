package seedu.duke;

import org.junit.jupiter.api.Test;
import seedu.duke.exception.parsedeleteexception.parsedeleteclientexception.InvalidDeleteClientIndexException;
import seedu.duke.exception.parsedeleteexception.parsedeletepropertyexception.InvalidDeletePropertyIndexException;
import seedu.duke.parsermanager.delete.CommandDeleteClientParser;
import seedu.duke.parsermanager.delete.CommandDeletePropertyParser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;

class DeleteTest {

    public static final String CLIENT_NAME = "Kaedehara Kazuha";
    public static final String CLIENT_CONTACT_NUMBER = "12345678";
    public static final String CLIENT_EMAIL = "kazuha@example.com";
    public static final String CLIENT_BUDGET = "2000";

    public static final String LANDLORD_NAME = "Eren Jaeger";
    public static final String PROPERTY_ADDRESS = "25A Pallet Town, S121111";
    public static final String RENTING_PRICE = "1500";
    public static final String UNIT_TYPE = "LP Bungalow";

    public static final int CLIENT_INDEX_TO_DELETE = 1;
    public static final int PROPERTY_INDEX_TO_DELETE = 1;

    public static final String DELETE_CLIENT_COMMAND_DETAILS = "i/2";
    public static final String DELETE_PROPERTY_COMMAND_DETAILS = "i/2";


    private ClientList initializeClientList() {
        ClientList clientList = new ClientList();
        clientList.addClient(CLIENT_NAME, CLIENT_CONTACT_NUMBER, CLIENT_EMAIL, CLIENT_BUDGET);
        return clientList;
    }

    private PropertyList initializePropertyList() {
        PropertyList propertyList = new PropertyList();
        propertyList.addProperty(LANDLORD_NAME, PROPERTY_ADDRESS, RENTING_PRICE, UNIT_TYPE);
        return propertyList;
    }

    @Test
    void deleteClient_validClientIndex_success() {
        ClientList clientList = initializeClientList();
        Client deletedClient = clientList.deleteClient(CLIENT_INDEX_TO_DELETE);
        Client expectedClient = new Client(CLIENT_NAME, CLIENT_CONTACT_NUMBER, CLIENT_EMAIL, CLIENT_BUDGET);
        assertEquals(expectedClient, deletedClient);
        assertFalse(clientList.getClientList().contains(deletedClient));
    }

    @Test
    void deleteProperty_validPropertyIndex_success() {
        PropertyList propertyList = initializePropertyList();
        Property deletedProperty = propertyList.deleteProperty(PROPERTY_INDEX_TO_DELETE);
        Property expectedProperty = new Property(LANDLORD_NAME, PROPERTY_ADDRESS, RENTING_PRICE, UNIT_TYPE);
        assertEquals(expectedProperty, deletedProperty);
        assertFalse(propertyList.getPropertyList().contains(deletedProperty));
    }

    @Test
    void checkForInvalidClientIndexDelete_invalidClientIndex_exceptionThrown() {
        ClientList clientList = initializeClientList();
        CommandDeleteClientParser commandDeleteClientParser =
                new CommandDeleteClientParser(DELETE_CLIENT_COMMAND_DETAILS, clientList);
        assertThrows(InvalidDeleteClientIndexException.class, commandDeleteClientParser::parseCommand);
    }

    @Test
    void checkForInvalidPropertyIndexDelete_invalidPropertyIndex_exceptionThrown() {
        PropertyList propertyList = initializePropertyList();
        CommandDeletePropertyParser commandDeletePropertyParser =
                new CommandDeletePropertyParser(DELETE_PROPERTY_COMMAND_DETAILS, propertyList);
        assertThrows(InvalidDeletePropertyIndexException.class, commandDeletePropertyParser::parseCommand);
    }

}
