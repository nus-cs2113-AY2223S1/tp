package seedu.duke.command;

import seedu.duke.ClientList;
import seedu.duke.PairingList;
import seedu.duke.PropertyList;
import seedu.duke.Storage;
import seedu.duke.Ui;
import seedu.duke.CommandStructure;

public class CommandListClientsWithTags extends Command {
    private final String commandFlag;

    public CommandListClientsWithTags(String commandFlag) {
        this.commandFlag = commandFlag;
    }

    @Override
    public void execute(Ui ui, Storage storage, PropertyList propertyList, ClientList clientList,
                        PairingList pairingList) {
        switch (commandFlag) {
        case CommandStructure.CONTACT_NUMBER_FLAG:
            displayClientContacts(clientList, ui);
            break;
        case CommandStructure.NAME_FLAG:
            displayClientNames(clientList, ui);
            break;
        case CommandStructure.EMAIL_FLAG:
            displayClientEmails(clientList, ui);
            break;
        case CommandStructure.BUDGET_FLAG:
            displayClientBudget(clientList, ui);
            break;
        default:
            break;
        }
    }

    private void displayClientContacts(ClientList clientList, Ui ui) {
        for (int i = 0; i < clientList.getCurrentListSize(); i++) {
            ui.displayOneClientContact(clientList.getClientList().get(i), i + 1);
        }
        ui.displayNoOfClients(clientList.getCurrentListSize());
    }

    private void displayClientNames(ClientList clientList, Ui ui) {
        for (int i = 0; i < clientList.getCurrentListSize(); i++) {
            ui.displayOneClientName(clientList.getClientList().get(i), i + 1);
        }
        ui.displayNoOfClients(clientList.getCurrentListSize());
    }

    private void displayClientEmails(ClientList clientList, Ui ui) {
        for (int i = 0; i < clientList.getCurrentListSize(); i++) {
            ui.displayOneClientEmail(clientList.getClientList().get(i), i + 1);
        }
        ui.displayNoOfClients(clientList.getCurrentListSize());
    }

    private void displayClientBudget(ClientList clientList, Ui ui) {
        for (int i = 0; i < clientList.getCurrentListSize(); i++) {
            ui.displayOneClientBudget(clientList.getClientList().get(i), i + 1);
        }
        ui.displayNoOfClients(clientList.getCurrentListSize());
    }
}

