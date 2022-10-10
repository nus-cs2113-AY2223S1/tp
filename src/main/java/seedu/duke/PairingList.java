package seedu.duke;

import java.util.HashMap;

public class PairingList {
    private static final String SEPARATOR = " | ";

    private static final HashMap<String, String> clientPropertyPairs = new HashMap<>();

    public PairingList() {

    }

    public void addPairing(Client client, Property property) {
        String clientPairingData = convertToPairingData(client);
        String propertyPairingData = convertToPairingData(property);

        clientPropertyPairs.put(clientPairingData, propertyPairingData);
    }

    public void deletePairing(Client client, Property property) {

        String clientPairingData = convertToPairingData(client);
        String propertyPairingData = convertToPairingData(property);

       clientPropertyPairs.remove(clientPairingData, propertyPairingData);
    }

    private String convertToPairingData(Client client) {
        return client.getClientName() + SEPARATOR + client.getClientContactNumber();
    }

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
