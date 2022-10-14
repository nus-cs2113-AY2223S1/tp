package seedu.duke;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Stores information on which the property each client is renting.
 */
public class PairingList2 {
    private static final String SEPARATOR = " | ";
    private static final String OPEN_BRACKET = "[";
    private static final String CLOSE_BRACKET = "]";
    private static final String LOG_ADD_PAIR = "The following pairing has been added to PairingList: ";
    private static final String LOG_DELETE_PAIR = "The following pairing(s) has been deleted from PairingList: ";
    private static final String LOG_PAIRS_WITH = "Pairs with ";
    private static final String LOG_COLON = " : ";

    private final HashMap<Client, Property> clientPropertyPairs = new HashMap<>();
    private static final Logger LOGGER = Logger.getLogger("PairingList");

    /**
     * Constructs the PairingList object.
     */
    public PairingList2() {

    }

    /**
     * Records which property a client is renting, with Client and Property objects as parameters. Its typical use
     * case is to add pairings from an add-command.
     *
     * @param client Client renting the property.
     * @param property Property being rented.
     */
    public void addPairing(Client client, Property property) {
        clientPropertyPairs.put(client, property);
    }


    /**
     * Deletes client-property pair to indicate that the client is no longer renting that property.
     *
     * @param client Client who is no longer renting the property.
     * @param property Property that is no longer being rented.
     */
    public void deletePairing(Client client, Property property) {
        String clientPairingData = convertToPairingData(client);
        String propertyPairingData = convertToPairingData(property);
        assert clientPropertyPairs.containsKey(clientPairingData) : "Delete Pairing: client is not paired. "
                + "Pairing does not exist. Pairing deletion unsuccessful.";

        boolean isRemoved = clientPropertyPairs.remove(clientPairingData, propertyPairingData);
        assert isRemoved : "Delete Pairing: pairing deletion unsuccessful.";

        LOGGER.log(Level.INFO, LOG_DELETE_PAIR + System.lineSeparator()
                + clientPairingData + LOG_COLON + propertyPairingData);
    }

    /**
     * Deletes client-property pair to indicate that the client is no longer renting that property, given the property.
     *
     * @param property Property that has been deleted.
     */
    public void deletePairing(Property property) {
        String propertyPairingData = convertToPairingData(property);
        assert clientPropertyPairs.containsValue(propertyPairingData) : "Delete Pairing: property is not paired."
                + "Pairing deletion unsuccessful.";

        // Iterate through the hash map to delete all the entries containing the properties
        clientPropertyPairs.entrySet().removeIf(e -> e.getValue().equals(propertyPairingData));

        assert !clientPropertyPairs.containsValue(propertyPairingData) :
                "Delete Pairing: pairing deletion unsuccessful.";
        LOGGER.log(Level.INFO, LOG_DELETE_PAIR + System.lineSeparator() + LOG_PAIRS_WITH + propertyPairingData);
    }

    /**
     * Deletes client-property pair to indicate that the client is no longer renting that property, given the client.
     *
     * @param client Client that has been deleted.
     */
    public void deletePairing(Client client) {
        String clientPairingData = convertToPairingData(client);

        assert clientPropertyPairs.containsKey(clientPairingData) : "Delete Pairing: Client is not paired."
                + "Pairing deletion unsuccessful.";

        clientPropertyPairs.remove(clientPairingData);

        assert !clientPropertyPairs.containsKey(clientPairingData) : "Delete Pairing: Pairing deletion unsuccessful.";

        LOGGER.log(Level.INFO, LOG_DELETE_PAIR + System.lineSeparator() + LOG_PAIRS_WITH + clientPairingData);
    }

    /**
     * Returns true if the client is paired with a property.
     *
     * @param client Client whose pairing status is being checked.
     * @return True if the client is currently paired with a property. False if not paired with a property.
     */
    public boolean isClientPairedWithProperty(Client client) {
        String clientPairingData = convertToPairingData(client);
        return clientPropertyPairs.containsKey(clientPairingData);
    }

    /**
     * Returns true if a pairing involving the specified property and client exists.
     *
     * @param client Client that is part of the pairing to be queried.
     * @param property Property that is part of the pairing to be queried.
     * @return True if the pairing between the specified property and pairing exists. False if it does not exist.
     */
    public boolean isAlreadyPaired(Client client, Property property) {
        String propertyPairingData = convertToPairingData(property);
        String clientPairingData = convertToPairingData(client);

        if (clientPropertyPairs.containsKey(clientPairingData)) {
            assert clientPropertyPairs.containsKey(clientPairingData) : "isAlreadyPaired() : Client is not paired.";
            return clientPropertyPairs.get(clientPairingData).equals(propertyPairingData);
        }
        return false;
    }


    /**
     * Fetches a list of tenants that is renting the property.
     *
     * @param property Property being queried.
     * @return List of tenants occupying the property, along with their data.
     */
    public ArrayList<Client> getPropertyTenants(Property property) {
        String propertyPairingData = convertToPairingData(property);
        ArrayList<Client> tenants = new ArrayList<>();

        for (Client clientPairingData : clientPropertyPairs.keySet()) {
            if (clientPropertyPairs.get(clientPairingData).equals(property)) {
                tenants.add(clientPairingData);
            }
        }
        return tenants;
    }


    /**
     * Converts client pairing data to a suitable string format.
     *
     * @param client Client whose data is to be converted.
     * @return Client pairing data in a suitable string format.
     */
    public String convertToPairingData(Client client) {
        return OPEN_BRACKET + client.getClientName()
                + SEPARATOR + client.getClientContactNumber()
                + SEPARATOR + client.getClientEmail()
                + SEPARATOR + client.getClientBudgetPerMonth()
                + CLOSE_BRACKET;
    }


    /**
     * Converts property pairing data to a suitable string format.
     *
     * @param property Property whose data is to be converted.
     * @return Property pairing data in a suitable string format.
     */
    public String convertToPairingData(Property property) {
        return OPEN_BRACKET + property.getLandlordName()
                + SEPARATOR + property.getPropertyAddress()
                + SEPARATOR + property.getRentingPrice()
                + SEPARATOR + property.getUnitType()
                + CLOSE_BRACKET;
    }

    /**
     * Fetches the hashmap containing the pair between client and property.
     *
     * @return a hashmap with client data as key and property data as value.
     */
    public HashMap<Client, Property> getClientPropertyPairs() {
        return clientPropertyPairs;
    }
}
