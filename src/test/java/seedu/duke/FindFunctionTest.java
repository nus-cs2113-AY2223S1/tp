package seedu.duke;

import org.junit.jupiter.api.Test;

import seedu.duke.command.CommandFindClient;
import seedu.duke.command.CommandFindProperty;
import seedu.duke.exception.parsefindexception.FindEmptyDescriptionException;
import seedu.duke.exception.parsefindexception.FindIncorrectNumOfTagException;
import seedu.duke.exception.parsefindexception.NoFindClientTagException;
import seedu.duke.exception.parsefindexception.NoFindPropertyTagException;
import seedu.duke.parsermanager.ParseFindClient;
import seedu.duke.parsermanager.ParseFindProperty;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class FindFunctionTest {

    // Constant used to store clients
    private static final String STORE_CLIENT_NAME = "Bubbles";
    private static final String STORE_CLIENT_CONTACT = "98765432";
    private static final String STORE_CLIENT_EMAIL = "bubbles@example.com";
    private static final String STORE_CLIENT_BUDGET = "1500";

    // Constant used to store property
    private static final String STORE_PROPERTY_NAME = "Albert";
    private static final String STORE_PROPERTY_ADDRESS = "123A Lower Kend Ridge Rd, Singapore 119081";
    private static final String STORE_PROPERTY_RENTAL = "1950";
    private static final String STORE_PROPERTY_UNIT_TYPE = "HDB 4-Room";

    private static final String EMPTY_TAG = "f/";
    private static final String INCORRECT_TAG = "c/f/";
    private static final String NO_TAG = "";

    @Test
    public void queryName_clientStored_expectCorrectEntries() {
        ClientList clientList = new ClientList();

        // Store client intended to search
        storeClient(clientList);

        // This name is not the entire name, but a portion of the name.
        // It's used to test if not looking for entire name will still produce the correct find result
        String clientName = "bbles";

        boolean hasClient = false;

        CommandFindClient findClient = new CommandFindClient(clientName);
        for (int i = 0; i < clientList.getCurrentListSize(); i += 1) {
            Client currentClient = clientList.getClientList().get(i);
            hasClient = findClient.hasQueryText(clientName, currentClient);
            if (hasClient) {
                break;
            }
        }

        assertTrue(hasClient);
    }

    @Test
    public void queryClient_clientNoStored_expectNoClientEntries() {
        ClientList clientList = new ClientList();

        // Querying for the name that is not stored
        String clientName = "Bubbles";

        boolean hasClient = false;

        CommandFindClient findClient = new CommandFindClient(clientName);
        for (int i = 0; i < clientList.getCurrentListSize(); i += 1) {
            Client currentClient = clientList.getClientList().get(i);
            hasClient = findClient.hasQueryText(clientName, currentClient);
            if (hasClient) {
                break;
            }
        }
        assertFalse(hasClient);
    }

    @Test
    public void queryProperty_propertyStored_expectCorrectEntries() {
        PropertyList propertyList = new PropertyList();

        // Store the property
        storeProperty(propertyList);

        // Subtext of Address
        String queryText = "end R";

        boolean hasProperty = false;

        CommandFindProperty findProperty = new CommandFindProperty(queryText);
        for (int i = 0; i < propertyList.getCurrentListSize(); i += 1) {
            Property currentProperty = propertyList.getPropertyList().get(i);

            hasProperty = findProperty.hasQueryText(queryText, currentProperty);
            if (hasProperty) {
                break;
            }
        }

        assertTrue(hasProperty);
    }

    @Test
    public void queryProperty_propertyNotStore_expectNoPropertyEntries() {
        PropertyList propertyList = new PropertyList();

        //Query for the name that is not stored
        String queryText = "end Ri";

        boolean hasProperty = false;

        CommandFindProperty findProperty = new CommandFindProperty(queryText);
        for (int i = 0; i < propertyList.getCurrentListSize(); i += 1) {
            Property currentProperty = propertyList.getPropertyList().get(i);

            hasProperty = findProperty.hasQueryText(queryText, currentProperty);
            if (hasProperty) {
                break;
            }
        }

        assertFalse(hasProperty);
    }

    @Test
    public void queryClient_emptyDescription_expectThrow() {
        ParseFindClient parseFindClient = new ParseFindClient(EMPTY_TAG);
        assertThrows(FindEmptyDescriptionException.class, () -> parseFindClient.checkCommandValidity(EMPTY_TAG));
    }

    @Test
    public void queryClient_incorrectNumberOfTag_expectThrow() {
        ParseFindClient parseFindClient = new ParseFindClient(INCORRECT_TAG);
        assertThrows(FindIncorrectNumOfTagException.class, () -> parseFindClient.checkCommandValidity(INCORRECT_TAG));
    }

    @Test
    public void queryProperty_emptyDescription_expectThrow() {
        ParseFindProperty parseFindProperty = new ParseFindProperty(EMPTY_TAG);
        assertThrows(FindEmptyDescriptionException.class, () -> parseFindProperty.checkCommandValidity(EMPTY_TAG));
    }

    @Test
    public void queryProperty_incorrectNumberOfTag_expectThrow() {
        ParseFindProperty parseFindProperty = new ParseFindProperty(INCORRECT_TAG);
        assertThrows(FindIncorrectNumOfTagException.class,
            () -> parseFindProperty.checkCommandValidity(INCORRECT_TAG));
    }

    @Test
    public void queryClient_noTagIncluded_expectThrow() {
        ParseFindClient parseFindClient = new ParseFindClient(NO_TAG);
        assertThrows(NoFindClientTagException.class, () -> parseFindClient.checkCommandValidity(NO_TAG));
    }

    @Test
    public void queryProperty_noTagIncluded_expectThrow() {
        ParseFindProperty parseFindProperty = new ParseFindProperty(NO_TAG);
        assertThrows(NoFindPropertyTagException.class, () -> parseFindProperty.checkCommandValidity(NO_TAG));
    }

    // This function is stored and assumed the entries are in the right order
    private void storeClient(ClientList clientList) {
        clientList.addClient(STORE_CLIENT_NAME, STORE_CLIENT_CONTACT, STORE_CLIENT_EMAIL, STORE_CLIENT_BUDGET);
    }

    private void storeProperty(PropertyList propertyList) {
        propertyList.addProperty(STORE_PROPERTY_NAME, STORE_PROPERTY_ADDRESS, STORE_PROPERTY_RENTAL,
                STORE_PROPERTY_UNIT_TYPE);
    }
}
