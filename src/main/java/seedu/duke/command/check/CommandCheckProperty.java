package seedu.duke.command.check;

//@@author ngdeqi
import seedu.duke.Client;
import seedu.duke.ClientList;
import seedu.duke.PairingList;
import seedu.duke.Property;
import seedu.duke.PropertyList;
import seedu.duke.Storage;
import seedu.duke.Ui;
import seedu.duke.exception.check.CommandCheckException;
import seedu.duke.exception.check.checkproperty.CheckPropertyInvalidIndexException;

import java.util.ArrayList;

import static seedu.duke.CommandStructure.START_INDEX;

/**
 * Represents a check property command.
 */
public class CommandCheckProperty extends CommandCheck {

    private int propertyIndex;

    /**
     * Constructs an instance of CommandCheckProperty.
     *
     * @param commandCheckPropertyDetails Parsed property index from the user's input.
     */
    public CommandCheckProperty(ArrayList<Integer> commandCheckPropertyDetails) {
        this.propertyIndex = commandCheckPropertyDetails.get(START_INDEX);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void execute(Ui ui, Storage storage, PropertyList propertyList, ClientList clientList,
                        PairingList pairingList) throws CommandCheckException {
        checkForPropertyListIndexOutOfBounds(propertyIndex, propertyList);

        Property property = propertyList.getPropertyList().get(propertyIndex);
        ArrayList<Client> tenants = pairingList.getPropertyTenants(property);
        ui.showCheckPropertyMessage(property, tenants);
    }

    /**
     * Checks if the provided property index corresponds to an actual property in the propertyList.
     *
     * @param propertyIndex Index of property.
     * @param propertyList List of properties.
     * @throws CheckPropertyInvalidIndexException If the provided property index does not correspond to the index of any
     *                                            property in the propertyList.
     */
    protected void checkForPropertyListIndexOutOfBounds(int propertyIndex, PropertyList propertyList) throws
            CheckPropertyInvalidIndexException {

        try {
            Property dummyProperty = propertyList.getPropertyList().get(propertyIndex);
        } catch (IndexOutOfBoundsException e) {
            throw new CheckPropertyInvalidIndexException();
        }
    }

}
