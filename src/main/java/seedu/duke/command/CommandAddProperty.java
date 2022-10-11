package seedu.duke.command;

import seedu.duke.ClientList;
import seedu.duke.PropertyList;
import seedu.duke.Storage;
import seedu.duke.Ui;

import java.util.ArrayList;

/**
 * Adds a property to the property list.
 */
public class CommandAddProperty extends CommandAdd {
    private final String landlordName;
    private final String propertyAddress;
    private final String rentingPrice;
    private final String unitType;

    /**
     * Constructs constructor for Command Add Property which stores property's Landlord's Name, Address, RentPrice/month
     * and Unit Type.
     *
     * @param propertyDetails Contains information relevant to property
     */
    public CommandAddProperty(ArrayList<String> propertyDetails) {
        this.landlordName    = propertyDetails.get(0);
        this.propertyAddress = propertyDetails.get(1);
        this.rentingPrice    = propertyDetails.get(2);
        this.unitType        = propertyDetails.get(3);
    }

    @Override
    public void execute(Ui ui, Storage storage, PropertyList propertyList, ClientList clientList) {
        propertyList.addProperty(landlordName, propertyAddress, rentingPrice, unitType);
        ui.showPropertyAddedConfirmationMessage(propertyList);
        //Update Storage
    }
}
