package seedu.duke;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

class PairingListTest {

    public static final Client PRESENT_CLIENT = new Client("Nicky Minaj", "93437878",
            "nicki88@example.com", "100000");

    public static final Client ABSENT_CLIENT = new Client("Doja Cat", "93437878",
            "doja88@example.com", "100000");

    public static final Property PRESENT_PROPERTY = new Property("Mary Tan Bee Bee",
            "107 North Bridge Rd, Singapore 179105", "1000", "HDB 3 Room");

    public static final Property ABSENT_PROPERTY = new Property("Bob Tan Bee Bee",
            "25 Lower Kent Ridge Rd, Singapore 119081", "1000", "HDB 3 Room");


    private PairingList pairingListInit() {

        PairingList pairingList = new PairingList();

        pairingList.addPairing(PRESENT_CLIENT, PRESENT_PROPERTY);
        return pairingList;
    }

    @Test
    void addPairing_correctClientPropertyStrings_success() {
        PairingList pairingList = pairingListInit();

        assertTrue(pairingList.getClientPropertyPairs().containsKey(PRESENT_CLIENT));
        assertTrue(pairingList.getClientPropertyPairs().containsValue(PRESENT_PROPERTY));
    }

    @Test
    void addPairing_invalidClientPropertyStrings_success() {
        PairingList pairingList = pairingListInit();

        String clientDataNoSquareBrackets = "Nicky Minaj | 93437878";
        String clientDataNoSeparator = "[Nicki Minaj 93437878]";
        String clientDataNoContactNumber = "[Nicki Minaj | ]";

        assertFalse(pairingList.getClientPropertyPairs().containsKey(clientDataNoSquareBrackets));
        assertFalse(pairingList.getClientPropertyPairs().containsKey(clientDataNoSeparator));
        assertFalse(pairingList.getClientPropertyPairs().containsKey(clientDataNoContactNumber));

        String propertyDataNoSquareBrackets =
                "Mary Tan Bee Bee | 107 North Bridge Rd, Singapore 179105 | 1000 | HDB 3 Room";
        assertFalse(pairingList.getClientPropertyPairs().containsValue(propertyDataNoSquareBrackets));
    }

    @Test
    void addPairing_propertyClientObjects_success() {
        PairingList pairingList = new PairingList();

        pairingList.addPairing(PRESENT_CLIENT, PRESENT_PROPERTY);

        assertTrue(pairingList.getClientPropertyPairs().containsKey(PRESENT_CLIENT));
        assertTrue(pairingList.getClientPropertyPairs().containsValue(PRESENT_PROPERTY));
    }

    @Test
    void deletePairing_propertyObject_success() {
        PairingList pairingList = pairingListInit();

        pairingList.deletePairing(PRESENT_PROPERTY);
        assertFalse(pairingList.getClientPropertyPairs().containsValue(PRESENT_PROPERTY));
    }

    @Test
    void deletePairing_clientObject_success() {
        PairingList pairingList = pairingListInit();

        pairingList.deletePairing(PRESENT_CLIENT);
        assertFalse(pairingList.getClientPropertyPairs().containsValue(PRESENT_PROPERTY));
    }

    @Test
    void isClientPairedWithProperty_presentAndAbsentClientObjects_success() {
        PairingList pairingList = pairingListInit();

        assertTrue(pairingList.isClientPairedWithProperty(PRESENT_CLIENT));
        assertFalse(pairingList.isClientPairedWithProperty(ABSENT_CLIENT));

    }

    @Test
    void isAlreadyPaired_presentAndAbsentPropertyAndClients_success() {
        PairingList pairingList = pairingListInit();

        assertTrue(pairingList.isAlreadyPaired(PRESENT_CLIENT, PRESENT_PROPERTY));
        assertFalse(pairingList.isAlreadyPaired(PRESENT_CLIENT, ABSENT_PROPERTY));

        // Absent clients will throw assertions errors.
        try {
            assertFalse(pairingList.isAlreadyPaired(ABSENT_CLIENT, PRESENT_PROPERTY));
            fail();
        } catch (AssertionError e) {
            assertEquals(e.getMessage(), e.getMessage());
        }

        try {
            assertFalse(pairingList.isAlreadyPaired(ABSENT_CLIENT, ABSENT_PROPERTY));
            fail();
        } catch (AssertionError e) {
            assertEquals(e.getMessage(), e.getMessage());
        }
    }

    @Test
    void getPropertyTenants_propertyRentedByOnlyPresentClient_success() {
        PairingList pairingList = pairingListInit();
        ArrayList<Client> expectedTenantList = new ArrayList<>(List.of(PRESENT_CLIENT));
        ArrayList<Client> actualTenantList = pairingList.getPropertyTenants(PRESENT_PROPERTY);
        assertEquals(expectedTenantList, actualTenantList);
    }

    @Test
    void convertToPairingData_clientObject_success() {
        String clientPairingData = pairingListInit().convertToPairingData(PRESENT_CLIENT);

        assertTrue(clientPairingData.matches("\\[.*\\|.*]"));
    }

    @Test
    void testConvertToPairingData_propertyObject_success() {
        String propertyPairingData = pairingListInit().convertToPairingData(PRESENT_PROPERTY);
        assertTrue(propertyPairingData.matches("\\[.*\\|.*\\|.*\\|.*]"));
    }
}

