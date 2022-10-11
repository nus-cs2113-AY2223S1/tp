package seedu.duke;

import java.util.HashMap;

/**
 * Stores information on which the property each client is renting.
 */
public class PairingList {
    private static final String SEPARATOR = " | ";
    private static final String CURRENCY = "SGD";
    private static final String OPEN_BRACKET = "[";
    private static final String CLOSE_BRACKET = "]";

    private static final HashMap<String, String> clientPropertyPairs = new HashMap<>();

    /**
     * Default Constructor.
     */
    public PairingList() {

    }

    /**
     * Records which property a client is renting, with Client and Property objects as parameters.
     * @param client Client renting the property.
     * @param property Property being rented.
     */
    public void addPairing(Client client, Property property) {
        String clientPairingData = convertToPairingData(client);
        String propertyPairingData = convertToPairingData(property);
        addPairing(clientPairingData, propertyPairingData);
    }

    /**
     * Records which property a client is renting, with the client and property in the appropriate pairing formats as
     * parameters. This is used to load the pairings from the pairing data file into the class variable.
     * @param clientPairingData Pairing data of client that is renting the property.
     * @param propertyPairingData Pairing data of property that is being rented.
     */
    public void addPairing(String clientPairingData, String propertyPairingData) {
        assert !clientPropertyPairs.containsKey(clientPairingData) : "CommandPair: client is already renting property";
        clientPropertyPairs.put(clientPairingData, propertyPairingData);
    }

    /**
     * Deletes client-property pair to indicate that the client is no longer renting that property.
     * @param client Client who is no longer renting the property.
     * @param property Property that is no longer being rented.
     */
    public void deletePairing(Client client, Property property) {
        String clientPairingData = convertToPairingData(client);
        String propertyPairingData = convertToPairingData(property);
        assert clientPropertyPairs.containsKey(clientPairingData) : "CommandUnpair: client is not renting property";

        clientPropertyPairs.remove(clientPairingData, propertyPairingData);
    }

    /**
     * Returns true if the client is paired with a property.
     * @param client Client whose pairing status is being checked.
     * @return True if the client is currently paired with a property. False if not paired with a property
     */
    public boolean isClientPairedWithProperty(Client client) {
        String clientPairingData = convertToPairingData(client);
        return clientPropertyPairs.containsKey(clientPairingData);
    }

    /**
     * Converts client pairing data to a suitable string format.
     * @param client Client whose data is to be converted.
     * @return Client pairing data in a suitable string format.
     */
    public String convertToPairingData(Client client) {
        return OPEN_BRACKET + client.getClientName()
                + SEPARATOR + client.getClientContactNumber()
                + CLOSE_BRACKET;
    }

    /**
     * Converts property pairing data to a suitable string format.
     * @param property Property whose data is to be converted.
     * @return Property pairing data in a suitable string format.
     */
    public String convertToPairingData(Property property) {
        return OPEN_BRACKET + property.getLandlordName()
                + SEPARATOR + property.getPropertyAddress()
                + SEPARATOR + CURRENCY + property.getRentingPrice()
                + SEPARATOR + property.getUnitType()
                + CLOSE_BRACKET;
    }

}
