package seedu.duke.command;

//@@author zoranabc201
import seedu.duke.ClientList;
import seedu.duke.PairingList;
import seedu.duke.PropertyList;
import seedu.duke.Storage;
import seedu.duke.Ui;

import seedu.duke.CommandStructure;

import static seedu.duke.Messages.MESSAGE_LIST_PROPERTIES_WITH_TAGS_ASSERT;

public class CommandListPropertiesWithTags extends Command {
    private final String commandTag;

    public CommandListPropertiesWithTags(String commandFlag) {
        this.commandTag = commandFlag;
    }

    /**
     * Uses information present in commandTag to determine what information needs to be printed, and calls the
     * relevant method to print it.
     * @param ui User interface object that handles interactions between user and the app.
     * @param storage Storage object that is responsible for storing and loading the app data.
     * @param propertyList PropertyList object that handles all app interactions with the list of properties.
     * @param clientList ClientList object that handles all app interactions with the list of clients.
     * @param pairingList PairingList object that handles all app interactions with the clients' rental of property.
     */

    @Override
    public void execute(Ui ui, Storage storage, PropertyList propertyList, ClientList clientList,
                        PairingList pairingList) {
        assert commandTag.equals(CommandStructure.TYPE_TAG)
                || commandTag.equals(CommandStructure.ADDRESS_TAG)
                || commandTag.equals(CommandStructure.NAME_TAG)
                || commandTag.equals(CommandStructure.PRICE_TAG)
                || commandTag.equals(CommandStructure.SHORT_TAG)
                : MESSAGE_LIST_PROPERTIES_WITH_TAGS_ASSERT;
        switch (commandTag) {
        case CommandStructure.ADDRESS_TAG:
            displayPropertyAddresses(propertyList, ui);
            break;
        case CommandStructure.NAME_TAG:
            displayPropertyLandlordNames(propertyList, ui);
            break;
        case CommandStructure.PRICE_TAG:
            displayPropertyPrices(propertyList, ui);
            break;
        case CommandStructure.TYPE_TAG:
            displayPropertyTypes(propertyList, ui);
            break;
        case CommandStructure.SHORT_TAG:
            displayPropertyShort(propertyList, ui);
            break;
        default:
            break;
        }
        //newline at the end of command
        ui.printNewline();
    }

    private void displayPropertyAddresses(PropertyList propertyList, Ui ui) {
        for (int i = CommandStructure.START_INDEX; i < propertyList.getCurrentListSize(); i++) {
            ui.displayOnePropertyAddress(propertyList.getPropertyList().get(i), i + 1);
        }
        ui.displayNoOfProperties(propertyList.getCurrentListSize(),
                propertyList.getCurrentListSize() == CommandStructure.ONE_ITEM_IN_LIST);
    }

    private void displayPropertyLandlordNames(PropertyList propertyList, Ui ui) {
        for (int i = 0; i < propertyList.getCurrentListSize(); i++) {
            ui.displayOnePropertyLandlordName(propertyList.getPropertyList().get(i), i + 1);
        }
        ui.displayNoOfProperties(propertyList.getCurrentListSize(),
                propertyList.getCurrentListSize() == CommandStructure.ONE_ITEM_IN_LIST);
    }

    private void displayPropertyPrices(PropertyList propertyList, Ui ui) {
        for (int i = 0; i < propertyList.getCurrentListSize(); i++) {
            ui.displayOnePropertyRentingPrice(propertyList.getPropertyList().get(i), i + 1);
        }
        ui.displayNoOfProperties(propertyList.getCurrentListSize(),
                propertyList.getCurrentListSize() == CommandStructure.ONE_ITEM_IN_LIST);
    }

    private void displayPropertyTypes(PropertyList propertyList, Ui ui) {
        for (int i = 0; i < propertyList.getCurrentListSize(); i++) {
            ui.displayOnePropertyUnitType(propertyList.getPropertyList().get(i), i + 1);
        }
        ui.displayNoOfProperties(propertyList.getCurrentListSize(),
                propertyList.getCurrentListSize() == CommandStructure.ONE_ITEM_IN_LIST);
    }

    private void displayPropertyShort(PropertyList propertyList, Ui ui) {
        for (int i = 0; i < propertyList.getCurrentListSize(); i++) {
            ui.displayOnePropertyShort(propertyList.getPropertyList().get(i), i + 1);
        }
        ui.displayNoOfProperties(propertyList.getCurrentListSize(),
                propertyList.getCurrentListSize() == CommandStructure.ONE_ITEM_IN_LIST);
    }
}

