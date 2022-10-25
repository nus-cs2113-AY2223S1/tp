package seedu.duke;

import seedu.duke.exception.DukeException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import static seedu.duke.Messages.LINE_BREAK;
import static seedu.duke.Messages.MESSAGE_CHECK_CLIENT;
import static seedu.duke.Messages.MESSAGE_CHECK_CLIENT_RESULT;
import static seedu.duke.Messages.MESSAGE_CHECK_PROPERTY;
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

    private static final String FULL_STOP = ".";
    private static final String CLIENT_NAME_LABEL = "Client Name: ";
    private static final String CLIENT_CONTACT_NUMBER_LABEL = "Client Contact Number: ";
    private static final String CLIENT_EMAIL_LABEL = "Client Email: ";
    private static final String CLIENT_BUDGET_LABEL = "Client Budget: ";

    private static final String PROPERTY_LANDLORD_LABEL = "Landlord Name: ";
    private static final String PROPERTY_ADDRESS_LABEL = "Property Address: ";
    private static final String PROPERTY_RENTAL_LABEL = "Property Rental Price: ";
    private static final String PROPERTY_UNIT_TYPE_LABEL = "Unit Type: ";

    private static final String SPACE = "\t";


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

        // Prints out the index and client name
        String index = i + FULL_STOP;
        String clientName = CLIENT_NAME_LABEL + client.getClientName();
        System.out.println(index + SPACE + clientName);

        // Prints out client's contact number
        String clientContact = CLIENT_CONTACT_NUMBER_LABEL + client.getClientContactNumber();
        System.out.println(SPACE + clientContact);

        // Prints out client's email if it's indicated
        String clientEmail = CLIENT_EMAIL_LABEL + client.getClientEmail();
        boolean hasEmptyEmail = client.getClientEmail().isEmpty();
        if (!hasEmptyEmail) {
            System.out.println(SPACE + clientEmail);
        }

        // Prints out client's budget
        String clientBudget = CLIENT_BUDGET_LABEL + client.getClientBudgetPerMonth();
        System.out.println(SPACE + clientBudget);

        System.out.println(LINE_BREAK);
    }

    public void displayOneClientName(Client client, int i) {
        System.out.print(i + FULL_STOP);
        System.out.println(client.getClientName());
        System.out.println(LINE_BREAK);
    }

    public void displayOneClientContact(Client client, int i) {
        System.out.print(i + FULL_STOP);
        System.out.println(SPACE + client.getClientContactNumber());
        System.out.println(LINE_BREAK);
    }

    public void displayOneClientEmail(Client client, int i) {
        System.out.print(i + FULL_STOP);
        System.out.println(SPACE + client.getClientEmailForList());
        System.out.println(LINE_BREAK);
    }

    public void displayOneClientBudget(Client client, int i) {
        System.out.print(i + FULL_STOP);
        System.out.println(SPACE + client.getClientBudgetPerMonth());
        System.out.println(LINE_BREAK);
    }

    public void displayOneClientShort(Client client, int i) {
        System.out.print(i + FULL_STOP);
        System.out.println(SPACE + CLIENT_NAME_LABEL + client.getClientName());
        System.out.println(SPACE + CLIENT_BUDGET_LABEL + client.getClientBudgetPerMonth());
        System.out.println(SPACE + LINE_BREAK);
    }

    public void displayOneProperty(Property property, int i) {
        // Prints out the index and landlord name
        String index = i + FULL_STOP;
        String landlordName = PROPERTY_LANDLORD_LABEL + property.getLandlordName();
        System.out.println(index + SPACE + landlordName);

        // Prints out property's address
        String propertyAddress = PROPERTY_ADDRESS_LABEL + property.getPropertyAddress();
        System.out.println(SPACE + propertyAddress);

        // Prints out the rental rate of the property
        String propertyRentalRate = PROPERTY_RENTAL_LABEL + property.getRentingPrice();
        System.out.println(SPACE + propertyRentalRate);

        // Prints out the property's unit type
        String propertyUnitType = PROPERTY_UNIT_TYPE_LABEL + property.getUnitType();
        System.out.println(SPACE + propertyUnitType);

        System.out.println(LINE_BREAK);
    }

    public void displayOnePropertyAddress(Property property, int i) {
        System.out.print(i + FULL_STOP);
        System.out.println(SPACE + property.getPropertyAddress());
        System.out.println(LINE_BREAK);
    }


    public void displayOnePropertyLandlordName(Property property, int i) {
        System.out.print(i + FULL_STOP);
        System.out.println(SPACE + property.getLandlordName());
        System.out.println(LINE_BREAK);
    }

    public void displayOnePropertyRentingPrice(Property property, int i) {
        System.out.print(i + FULL_STOP);
        System.out.println(SPACE + property.getRentingPrice());
        System.out.println(LINE_BREAK);
    }

    public void displayOnePropertyUnitType(Property property, int i) {
        System.out.print(i + FULL_STOP);
        System.out.println(SPACE + property.getUnitType());
        System.out.println(LINE_BREAK);
    }

    public void displayOnePropertyShort(Property property, int i) {
        System.out.print(i + FULL_STOP);
        System.out.println(SPACE + PROPERTY_ADDRESS_LABEL + property.getPropertyAddress());
        System.out.println(SPACE + PROPERTY_UNIT_TYPE_LABEL + property.getUnitType());
        System.out.println(SPACE + PROPERTY_RENTAL_LABEL + property.getRentingPrice());
        System.out.println(SPACE + LINE_BREAK);
    }

    public void displayNoOfProperties(int i) {
        if (i == 1) {
            System.out.println("There is " + i + " property in the list");
        } else {
            System.out.println("There are " + i + " properties in this list");
        }
    }

    public void displayNoOfClients(int i) {
        if (i == 1) {
            System.out.println("There is " + i + " client in the list");
        } else {
            System.out.println("There are " + i + " clients in this list");
        }
    }

    public void showCheckClient(Client client, Property property) {
        showToUser(MESSAGE_CHECK_CLIENT);
        showToUser("  " + client.toString() + "\n");
        showToUser(MESSAGE_CHECK_CLIENT_RESULT);
        showToUser("  " + property.toString());
    }

    public void showCheckProperty(Property property, ArrayList<Client> tenants) {
        showToUser(MESSAGE_CHECK_PROPERTY);
        showToUser("  " + property.toString() + "\n");
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
