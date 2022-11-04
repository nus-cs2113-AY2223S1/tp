package seedu.duke.command.pairunpair;

//@@author ngdeqi
import seedu.duke.Client;
import seedu.duke.ClientList;
import seedu.duke.Property;
import seedu.duke.PropertyList;
import seedu.duke.command.Command;
import seedu.duke.exception.pairunpair.PairUnpairInvalidIndexException;

public abstract class CommandPairUnpair extends Command {

    /**
     * Checks if the provided client index corresponds to an actual client in the clientList.
     *
     * @param clientIndex Index of client.
     * @param clientList List of Clients.
     * @throws PairUnpairInvalidIndexException If the provided index does not correspond the index of any client in the
     *                                         clientList.
     */
    protected void checkForClientListIndexOutOfBounds(int clientIndex, ClientList clientList)
            throws PairUnpairInvalidIndexException {
        try {
            Client dummyClient = clientList.getClientList().get(clientIndex);
        } catch (IndexOutOfBoundsException e) {
            throw new PairUnpairInvalidIndexException();
        }
    }

    /**
     * Checks if the provided property index corresponds to an actual property in the propertyList.
     *
     * @param propertyIndex Index of property.
     * @param propertyList List of properties.
     * @throws PairUnpairInvalidIndexException If the provided property index does not correspond to the index of any
     *                                         property in the propertyList.
     */
    protected void checkForPropertyListIndexOutOfBounds(int propertyIndex, PropertyList propertyList) throws
            PairUnpairInvalidIndexException {

        try {
            Property dummyProperty = propertyList.getPropertyList().get(propertyIndex);
        } catch (IndexOutOfBoundsException e) {
            throw new PairUnpairInvalidIndexException();
        }
    }
}
