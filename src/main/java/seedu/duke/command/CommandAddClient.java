package seedu.duke.command;

import seedu.duke.ClientList;
import seedu.duke.PropertyList;
import seedu.duke.Storage;
import seedu.duke.Ui;

import java.util.ArrayList;

public class CommandAddClient extends CommandAdd {
    private final String clientName;
    private final String clientContactNumber;
    private final String clientEmail;
    private final String clientBudgetPerMonth;

    public CommandAddClient(ArrayList<String> clientDetails) {
        this.clientName           = clientDetails.get(0);
        this.clientContactNumber  = clientDetails.get(1);
        this.clientEmail          = clientDetails.get(2);
        this.clientBudgetPerMonth = clientDetails.get(3);
    }

    @Override
    public void execute(Ui ui, Storage storage, PropertyList propertyList, ClientList clientList) {
        clientList.addClient(clientName, clientContactNumber, clientEmail, clientBudgetPerMonth);
        ui.showClientAddedConfirmationMessage(clientList);
    }
}
