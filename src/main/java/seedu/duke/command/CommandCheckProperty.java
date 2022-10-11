package seedu.duke.command;

import seedu.duke.ClientList;
import seedu.duke.PairingList;
import seedu.duke.Property;
import seedu.duke.PropertyList;
import seedu.duke.Storage;
import seedu.duke.Ui;

import java.util.ArrayList;

public class CommandCheckProperty extends CommandCheck {

    private int propertyIndex;

    public CommandCheckProperty(ArrayList<Integer> commandCheckPropertyDetails) {
        this.propertyIndex = commandCheckPropertyDetails.get(0);
    }

    @Override
    public void execute(Ui ui, Storage storage, PropertyList propertyList, ClientList clientList,
            PairingList pairingList) {
        Property property = propertyList.getPropertyList().get(propertyIndex);
        ArrayList<String> tenants = pairingList.getPropertyTenants(property);
        ui.showCheckProperty(tenants);
    }
}
