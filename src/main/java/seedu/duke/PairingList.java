package seedu.duke;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Stores information on which the property each client is renting.
 */
public class PairingList {
    private static final String SEPARATOR = " | ";
    private static final String OPEN_BRACKET = "[";
    private static final String CLOSE_BRACKET = "]";
    private static final String LOG_ADD_PAIR = "The following pairing has been added to PairingList:";
    private static final String LOG_DELETE_PAIR = "The following pairing(s) has been deleted from PairingList: ";
    private static final String LOG_PAIRS_WITH = "Pairs with ";

    private final HashMap<Client, Property> clientPropertyPairs = new HashMap<>();
    private static final Logger LOGGER = Logger.getLogger("PairingList");

    /**
     * Constructs the PairingList object.
     */
    public PairingList() {

    }

    /**
     * Records which property a client is renting, with Client and Property objects as parameters. Its typical use
     * case is to add pairings from an add-command.
     *
     * @param client Client renting the property.
     * @param property Property being rented.
     */
    public void addPairing(Client client, Property property) {
        assert !clientPropertyPairs.containsKey(client) : "Add Pairing: client already paired with property."
                + " Pairing addition unsuccessful.";
        clientPropertyPairs.put(client, property);
        LOGGER.log(Level.INFO, LOG_ADD_PAIR + System.lineSeparator()
                + client.toString() + System.lineSeparator() + property.toString());
    }


    /**
     * Deletes client-property pair to indicate that the client is no longer renting that property.
     *
     * @param client Client who is no longer renting the property.
     * @param property Property that is no longer being rented.
     */
    public void deletePairing(Client client, Property property) {
        assert clientPropertyPairs.containsKey(client) : "Delete Pairing: client is not paired. "
                + "Pairing does not exist. Pairing deletion unsuccessful.";

        boolean isRemoved = clientPropertyPairs.remove(client, property);
        assert isRemoved : "Delete Pairing: pairing deletion unsuccessful.";

        LOGGER.log(Level.INFO, LOG_DELETE_PAIR + System.lineSeparator()
                + client.toString() + System.lineSeparator() + property.toString());
    }

    /**
     * Deletes client-property pair to indicate that the client is no longer renting that property, given the property.
     *
     * @param property Property that has been deleted.
     */
    public void deletePairing(Property property) {
        assert clientPropertyPairs.containsValue(property) : "Delete Pairing: property is not paired."
                + "Pairing deletion unsuccessful.";

        // Iterate through the hash map to delete all the entries containing the properties
        clientPropertyPairs.entrySet().removeIf(e -> e.getValue().equals(property));

        assert !clientPropertyPairs.containsValue(property) :
                "Delete Pairing: pairing deletion unsuccessful.";
        LOGGER.log(Level.INFO, LOG_DELETE_PAIR + System.lineSeparator() + LOG_PAIRS_WITH + property.toString());
    }

    /**
     * Deletes client-property pair to indicate that the client is no longer renting that property, given the client.
     *
     * @param client Client that has been deleted.
     */
    public void deletePairing(Client client) {

        assert clientPropertyPairs.containsKey(client) : "Delete Pairing: Client is not paired."
                + "Pairing deletion unsuccessful.";

        clientPropertyPairs.remove(client);

        assert !clientPropertyPairs.containsKey(client) : "Delete Pairing: Pairing deletion unsuccessful.";

        LOGGER.log(Level.INFO, LOG_DELETE_PAIR + System.lineSeparator() + LOG_PAIRS_WITH + client.toString());
    }

    /**
     * Returns true if the client is paired with a property.
     *
     * @param client Client whose pairing status is being checked.
     * @return True if the client is currently paired with a property. False if not paired with a property.
     */
    public boolean isClientPairedWithProperty(Client client) {
        return clientPropertyPairs.containsKey(client);
    }

    /**
     * Returns true if a pairing involving the specified property and client exists.
     *
     * @param client Client that is part of the pairing to be queried.
     * @param property Property that is part of the pairing to be queried.
     * @return True if the pairing between the specified property and pairing exists. False if it does not exist.
     */
    public boolean isAlreadyPaired(Client client, Property property) {

        if (clientPropertyPairs.containsKey(client)) {
            assert clientPropertyPairs.containsKey(client) : "isAlreadyPaired() : Client is not paired.";
            return clientPropertyPairs.get(client).equals(property);
        }
        return false;
    }

    /**
     * Returns true if the rental price of the property exceeds the client's budget.
     *
     * @param client Client to be paired with the property.
     * @param property Property to be paired with the client.
     * @return True is rental price exceeds client's budget; false if rental price is equal to or lower than clien's
     *         budget.
     */

    public boolean hasPriceExceededBudget(Client client, Property property) {
        int clientBudget = Integer.parseInt(client.getClientBudgetPerMonth());
        int rentalPrice = Integer.parseInt(property.getRentingPrice());
        return rentalPrice > clientBudget;
    }


    /**
     * Fetches a list of tenants that is renting the property.
     *
     * @param property Property being queried.
     * @return List of tenants occupying the property, along with their data.
     */
    public ArrayList<Client> getPropertyTenants(Property property) {
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
