package seedu.duke;

import seedu.duke.exception.DukeException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import static seedu.duke.Messages.LINE_BREAK;
import static seedu.duke.Messages.MESSAGE_CHECK_PROPERTY_RESULT;
import static seedu.duke.Messages.MESSAGE_CLIENT_ADDED;
import static seedu.duke.Messages.MESSAGE_CLIENT_DELETED;
import static seedu.duke.Messages.MESSAGE_COMMAND_UNDEFINED;
import static seedu.duke.Messages.MESSAGE_NUMBER_OF_LIST_RESULTS;
import static seedu.duke.Messages.MESSAGE_PAIRED;
import static seedu.duke.Messages.MESSAGE_PAIRED_CLIENTS_DELETED;
import static seedu.duke.Messages.MESSAGE_PAIRED_PROPERTIES_DELETED;
import static seedu.duke.Messages.MESSAGE_PROPERTY_ADDED;
import static seedu.duke.Messages.MESSAGE_PROPERTY_DELETED;
import static seedu.duke.Messages.MESSAGE_UNPAIRED;
import static seedu.duke.Messages.MESSAGE_WELCOME;


/**
 * Handler for all interactions between the user and the command line.
 */
public class Ui {

    private static boolean inputIsEmpty(String rawInput) {
        return rawInput.trim().isEmpty();
    }

    public static String readCommand() {
        Scanner input = new Scanner(System.in);

        String rawInput = input.nextLine();
        boolean isEmpty = inputIsEmpty(rawInput);
        while (isEmpty) {
            rawInput = input.nextLine();
            isEmpty = inputIsEmpty(rawInput);
        }

        return rawInput;
    }

    public void showToUser(String message) {
        System.out.println(message);
    }

    public void showWelcomeMessage() {
        showToUser(MESSAGE_WELCOME);
    }

    public void showCommandUndefinedMessage() {
        showToUser(MESSAGE_COMMAND_UNDEFINED);
    }


    /* Add-Command-related showMessage methods. */

    public void showPropertyAddedConfirmationMessage(PropertyList propertyList) {
        int currentListSize = propertyList.getCurrentListSize();
        showToUser(MESSAGE_PROPERTY_ADDED);
        showToUser("  " + propertyList.getPropertyList().get(currentListSize - 1));
    }

    public void showClientAddedConfirmationMessage(ClientList clientList) {
        int currentListSize = clientList.getCurrentListSize();
        showToUser(MESSAGE_CLIENT_ADDED);
        showToUser("  " + clientList.getClientList().get(currentListSize - 1));
    }


    /* Delete-Command-related showMessage methods. */

    public void showPropertyDeletedConfirmationMessage(Property deletedProperty) {
        showToUser(MESSAGE_PROPERTY_DELETED);
        showToUser("  " + deletedProperty);
    }

    public void showClientDeletedConfirmationMessage(Client deletedClient) {
        showToUser(MESSAGE_CLIENT_DELETED);
        showToUser("  " + deletedClient);
    }

    public void showPairedPropertiesDeletedConfirmationMessage(Client deletedClient, PairingList pairingList) {
        HashMap<Client, Property> clientPropertyPairs = pairingList.getClientPropertyPairs();
        if (clientPropertyPairs.containsKey(deletedClient)) {
            Property pairedProperty = clientPropertyPairs.get(deletedClient);
            showToUser(MESSAGE_PAIRED_PROPERTIES_DELETED);
            showToUser("  " + pairedProperty.getPropertyAddress());
        }
    }

    public void showPairedClientsDeletedConfirmationMessage(Property deletedProperty, PairingList pairingList) {
        HashMap<Client, Property> clientPropertyPairs = pairingList.getClientPropertyPairs();
        int currentIndex = 1;

        for (Map.Entry<Client, Property> entry : clientPropertyPairs.entrySet()) {
            if (entry.getValue().equals(deletedProperty)) {
                if (currentIndex == 1) {
                    showToUser(MESSAGE_PAIRED_CLIENTS_DELETED);
                }
                Client pairedClient = entry.getKey();
                showToUser("  " + currentIndex + ". " + pairedClient.getClientName());
                currentIndex++;
            }
        }
    }


    /* Pair/Unpair-Command-related showMessage methods. */

    public void showPairedConfirmationMessage(Client client, Property property) {
        showToUser(MESSAGE_PAIRED);
        showToUser("  " + client.getClientName() + " and " + property.getPropertyAddress());
    }

    public void showUnpairedConfirmationMessage(Client client, Property property) {
        showToUser(MESSAGE_UNPAIRED);
        showToUser("  " + client.getClientName() + " and " + property.getPropertyAddress());
    }


    /* Check-Command-related showMessage methods. */

    public void displayOneClient(Client client, int i) {
        System.out.println(i + ".");
        System.out.println(client.toString());
        System.out.println(LINE_BREAK);
    }

    public void displayOneProperty(Property property, int i) {
        System.out.println(i + ".");
        System.out.println(property.toString());
        System.out.println(LINE_BREAK);
    }

    public void showCheckProperty(ArrayList<Client> tenants) {
        showToUser(MESSAGE_CHECK_PROPERTY_RESULT);
        int count = 0;
        for (Client tenant : tenants) {
            String tenantInfo = tenant.toString();

            showToUser(String.format("  %d. %s", ++count, tenantInfo));
        }
        showToUser(MESSAGE_NUMBER_OF_LIST_RESULTS + count);
    }

    public void showExceptionMessage(DukeException e) {
        showToUser(e.toString());
    }
}
