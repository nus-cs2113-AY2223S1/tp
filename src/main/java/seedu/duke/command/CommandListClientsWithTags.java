package seedu.duke.command;

import seedu.duke.ClientList;
import seedu.duke.PairingList;
import seedu.duke.PropertyList;
import seedu.duke.Storage;
import seedu.duke.Ui;
import seedu.duke.CommandStructure;
import static seedu.duke.Messages.MESSAGE_LIST_CLIENTS_WITH_TAGS_ASSERT;

public class CommandListClientsWithTags extends Command {
    private final String commandFlag;

    public CommandListClientsWithTags(String commandFlag) {
        this.commandFlag = commandFlag;
    }

    @Override
    public void execute(Ui ui, Storage storage, PropertyList propertyList, ClientList clientList,
                        PairingList pairingList) {
        assert commandFlag.equals(CommandStructure.CONTACT_NUMBER_FLAG)
                || commandFlag.equals(CommandStructure.NAME_FLAG)
                || commandFlag.equals(CommandStructure.EMAIL_FLAG)
                || commandFlag.equals(CommandStructure.BUDGET_FLAG)
                || commandFlag.equals(CommandStructure.SHORT_FLAG)
                : MESSAGE_LIST_CLIENTS_WITH_TAGS_ASSERT;
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
        case CommandStructure.SHORT_FLAG:
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

