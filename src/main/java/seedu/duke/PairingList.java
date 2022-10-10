package seedu.duke;

import java.util.HashMap;

/**
 * Stores information on which the property each client is renting.
 */
public class PairingList {
    private static final String SEPARATOR = " | ";

    private static final HashMap<String, String> clientPropertyPairs = new HashMap<>();

    public PairingList() {

    }

    /**
     * Records which property a client is renting.
     * @param client Client renting the property.
     * @param property Property being rented.
     */
    public void addPairing(Client client, Property property) {
        String clientPairingData = convertToPairingData(client);
        String propertyPairingData = convertToPairingData(property);

        clientPropertyPairs.put(clientPairingData, propertyPairingData);
    }

    /**
     * Removes a client-property pair to indicate that the client is no longer renting that property.
     * @param client Client who is no longer renting the property.
     * @param property Property that is no loner being rented.
     */
    public void deletePairing(Client client, Property property) {

        String clientPairingData = convertToPairingData(client);
        String propertyPairingData = convertToPairingData(property);

        clientPropertyPairs.remove(clientPairingData, propertyPairingData);
    }

    public boolean isClientPairedWithProperty(Client client) {
        String clientPairingData = convertToPairingData(client);
        return clientPropertyPairs.containsKey(clientPairingData);
    }

    /**
     * Overloaded method to convert client pairing data to a suitable string format.
     * @param client Client whose data is to be converted.
     * @return Pairing data in a suitable string format.
     */
    private String convertToPairingData(Client client) {
        return client.getClientName() + SEPARATOR + client.getClientContactNumber();
    }

    /**
     * Overloaded method to convert property pairing data to a suitable string format.
     * @param property Property whose data is to be converted.
     * @return Pairing data in a suitable string format.
     */
    private String convertToPairingData(Property property) {
        return property.getLandlordName()
                + SEPARATOR
                + property.getPropertyAddress()
                + SEPARATOR
                + property.getRentingPrice()
                + SEPARATOR
                + property.getUnitType();
    }

}
