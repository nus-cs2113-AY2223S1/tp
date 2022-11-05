//@@author OVReader

package seedu.duke.command;

import seedu.duke.ClientList;
import seedu.duke.PairingList;
import seedu.duke.PropertyList;
import seedu.duke.Storage;
import seedu.duke.Ui;

import java.util.logging.Level;
import java.util.logging.Logger;

import java.util.ArrayList;

/**
 * Adds a property to the property list.
 */
public class CommandAddProperty extends CommandAdd {
    private final String landlordName;
    private final String propertyAddress;
    private final String rentingPrice;
    private final String unitType;

    private static final int LANDLORD_INDEX = 0;
    private static final int PROPERTY_ADDRESS_INDEX = 1;
    private static final int PROPERTY_PRICE_INDEX = 2;
    private static final int PROPERTY_UNIT_TYPE_INDEX = 3;

    private static final String COMMAND_ADD_PROPERTY_LOGGING_LABEL = "CommandAddProperty";
    private static final Logger logger = Logger.getLogger(COMMAND_ADD_PROPERTY_LOGGING_LABEL);
    private static final String ADD_PROPERTY_LOGGING_MESSAGE = "Adding new property now";

    /**
     * Constructs constructor for Command Add Property which stores property's Landlord's Name, Address, RentPrice/month
     * and Unit Type.
     *
     * @param propertyDetails Contains information relevant to property
     */
    public CommandAddProperty(ArrayList<String> propertyDetails) {
        for (String propertyDetail : propertyDetails) {
            assert propertyDetail != null;
        }

        landlordName    = propertyDetails.get(LANDLORD_INDEX);
        propertyAddress = propertyDetails.get(PROPERTY_ADDRESS_INDEX);
        rentingPrice    = propertyDetails.get(PROPERTY_PRICE_INDEX);
        unitType        = propertyDetails.get(PROPERTY_UNIT_TYPE_INDEX);
    }

    @Override
    public void execute(Ui ui, Storage storage, PropertyList propertyList, ClientList clientList,
                        PairingList pairingList) {
        logger.log(Level.FINEST, ADD_PROPERTY_LOGGING_MESSAGE);
        propertyList.addProperty(landlordName, propertyAddress, rentingPrice, unitType);
        ui.showPropertyAddedConfirmationMessage(propertyList);

        // Updates Storage
        storage.addToPropertyFile(landlordName, propertyAddress, rentingPrice, unitType);
    }
}
