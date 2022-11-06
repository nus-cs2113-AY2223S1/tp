package seedu.duke.command;

//@@author zoranabc201
import seedu.duke.ClientList;
import seedu.duke.PairingList;
import seedu.duke.PropertyList;
import seedu.duke.Storage;
import seedu.duke.Ui;
import seedu.duke.CommandStructure;
import static seedu.duke.Messages.MESSAGE_LIST_CLIENTS_WITH_TAGS_ASSERT;

public class CommandListClientsWithTags extends Command {
    private final String commandTag;

    public CommandListClientsWithTags(String commandFlag) {
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
        assert commandTag.equals(CommandStructure.CONTACT_NUMBER_TAG)
                || commandTag.equals(CommandStructure.NAME_TAG)
                || commandTag.equals(CommandStructure.EMAIL_TAG)
                || commandTag.equals(CommandStructure.BUDGET_TAG)
                || commandTag.equals(CommandStructure.SHORT_TAG)
                : MESSAGE_LIST_CLIENTS_WITH_TAGS_ASSERT;
        switch (commandTag) {
        case CommandStructure.CONTACT_NUMBER_TAG:
            displayClientContacts(clientList, ui);
            break;
        case CommandStructure.NAME_TAG:
            displayClientNames(clientList, ui);
            break;
        case CommandStructure.EMAIL_TAG:
            displayClientEmails(clientList, ui);
            break;
        case CommandStructure.BUDGET_TAG:
            displayClientBudget(clientList, ui);
            break;
        case CommandStructure.SHORT_TAG:
            displayClientShort(clientList, ui);
            break;
        default:
            //no errors are possible at this stage. Any potential errors are handled earlier
            break;
        }
        //newline at the end of command
        ui.printNewline();
    }

    private void displayClientContacts(ClientList clientList, Ui ui) {
        for (int i = CommandStructure.START_INDEX; i < clientList.getCurrentListSize(); i++) {
            ui.displayOneClientContact(clientList.getClientList().get(i), i + 1);
        }
        ui.displayNoOfClients(clientList.getCurrentListSize(),
                clientList.getCurrentListSize() == CommandStructure.ONE_ITEM_IN_LIST);
    }

    public void displayClientShort(ClientList clientList, Ui ui) {
        for (int i = CommandStructure.START_INDEX; i < clientList.getCurrentListSize(); i++) {
            ui.displayOneClientShort(clientList.getClientList().get(i), i + 1);
        }
        ui.displayNoOfClients(clientList.getCurrentListSize(),
                clientList.getCurrentListSize() == CommandStructure.ONE_ITEM_IN_LIST);
    }

    private void displayClientNames(ClientList clientList, Ui ui) {
        for (int i = CommandStructure.START_INDEX; i < clientList.getCurrentListSize(); i++) {
            ui.displayOneClientName(clientList.getClientList().get(i), i + 1);
        }
        ui.displayNoOfClients(clientList.getCurrentListSize(),
                clientList.getCurrentListSize() == CommandStructure.ONE_ITEM_IN_LIST);
    }

    private void displayClientEmails(ClientList clientList, Ui ui) {
        for (int i = CommandStructure.START_INDEX; i < clientList.getCurrentListSize(); i++) {
            ui.displayOneClientEmail(clientList.getClientList().get(i), i + 1);
        }
        ui.displayNoOfClients(clientList.getCurrentListSize(),
                clientList.getCurrentListSize() == CommandStructure.ONE_ITEM_IN_LIST);
    }

    private void displayClientBudget(ClientList clientList, Ui ui) {
        for (int i = CommandStructure.START_INDEX; i < clientList.getCurrentListSize(); i++) {
            ui.displayOneClientBudget(clientList.getClientList().get(i), i + 1);
        }
        ui.displayNoOfClients(clientList.getCurrentListSize(),
                clientList.getCurrentListSize() == CommandStructure.ONE_ITEM_IN_LIST);
    }
}

