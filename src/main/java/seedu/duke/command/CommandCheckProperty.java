package seedu.duke.command;

import seedu.duke.Client;
import seedu.duke.ClientList;
import seedu.duke.PairingList;
import seedu.duke.Property;
import seedu.duke.PropertyList;
import seedu.duke.Storage;
import seedu.duke.Ui;

import java.util.ArrayList;

/**
 * Represents a check property command.
 */
public class CommandCheckProperty extends CommandCheck {

    private int propertyIndex;

    /**
     * Constructs an instance of CommandCheckProperty.
     * @param commandCheckPropertyDetails Parsed property index from the user's input.
     */
    public CommandCheckProperty(ArrayList<Integer> commandCheckPropertyDetails) {
        this.propertyIndex = commandCheckPropertyDetails.get(0);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void execute(Ui ui, Storage storage, PropertyList propertyList, ClientList clientList,
                        PairingList pairingList) {
        Property property = propertyList.getPropertyList().get(propertyIndex);
        ArrayList<Client> tenants = pairingList.getPropertyTenants(property);
        ui.showCheckProperty(tenants);
    }
}
