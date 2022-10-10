package seedu.duke.command;


import seedu.duke.Client;
import seedu.duke.ClientList;
import seedu.duke.PairingList;
import seedu.duke.Property;
import seedu.duke.PropertyList;
import seedu.duke.Storage;
import seedu.duke.Ui;

import java.util.ArrayList;

public class CommandPair extends Command {

    private int clientIndex;
    private int propertyIndex;

    public CommandPair(ArrayList<Integer> commandPairDetails) {
        this.clientIndex = commandPairDetails.get(0);
        this.propertyIndex = commandPairDetails.get(1);
    }

    @Override
    public void execute(Ui ui, Storage storage, PropertyList propertyList, ClientList clientList,
                        PairingList pairingList) {
        Client client = clientList.getClientList().get(clientIndex);
        Property property = propertyList.getPropertyList().get(propertyIndex);

        pairingList.addPairing(client, property);
        ui.showPairedConfirmationMessage(client, property);
    }
}
