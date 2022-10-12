package seedu.duke;

import java.util.Scanner;

import static seedu.duke.Messages.MESSAGE_WELCOME;

import static seedu.duke.Messages.MESSAGE_PROPERTY_ADDED;
import static seedu.duke.Messages.MESSAGE_CLIENT_ADDED;
import static seedu.duke.Messages.MESSAGE_CLIENT_DELETED;
import static seedu.duke.Messages.MESSAGE_PAIRED;
import static seedu.duke.Messages.MESSAGE_UNPAIRED;
import static seedu.duke.Messages.MESSAGE_EXISTING_PAIR;
import static seedu.duke.Messages.MESSAGE_NO_EXISTING_PAIR;

import static seedu.duke.Messages.MESSAGE_EMPTY_ADD_DESCRIPTION;
import static seedu.duke.Messages.MESSAGE_MISSING_SUB_COMMAND_TYPE_FOR_ADD;
import static seedu.duke.Messages.MESSAGE_EMPTY_PROPERTY_DESCRIPTION;
import static seedu.duke.Messages.MESSAGE_EMPTY_CLIENT_DESCRIPTION;
import static seedu.duke.Messages.MESSAGE_ADD_PROPERTY_WRONG_FORMAT;
import static seedu.duke.Messages.MESSAGE_PROPERTY_INPUT_EXAMPLE;
import static seedu.duke.Messages.MESSAGE_ADD_CLIENT_WRONG_FORMAT;
import static seedu.duke.Messages.MESSAGE_CLIENT_INPUT_EXAMPLE;
import static seedu.duke.Messages.MESSAGE_INVALID_SINGAPORE_ADDRESS;
import static seedu.duke.Messages.MESSAGE_VALID_SINGAPORE_ADDRESS_EXAMPLE;
import static seedu.duke.Messages.MESSAGE_INVALID_PRICE_FORMAT;
import static seedu.duke.Messages.MESSAGE_INVALID_CONTACT_NUMBER;
import static seedu.duke.Messages.MESSAGE_INVALID_EMAIL;
import static seedu.duke.Messages.MESSAGE_INVALID_BUDGET_FORMAT;

import static seedu.duke.Messages.MESSAGE_MISSING_SUB_COMMAND_TYPE_FOR_DELETE;
import static seedu.duke.Messages.MESSAGE_EMPTY_DELETE_DESCRIPTION;
import static seedu.duke.Messages.MESSAGE_INVALID_CLIENT_INDEX;
import static seedu.duke.Messages.MESSAGE_EMPTY_CLIENT_INDEX;

import static seedu.duke.Messages.MESSAGE_EMPTY_COMMAND_PAIR_UNPAIR;
import static seedu.duke.Messages.MESSAGE_NOT_VALID_INDEX;
import static seedu.duke.Messages.MESSAGE_NOT_INTEGER;
import static seedu.duke.Messages.MESSAGE_PAIR_UNPAIR_WRONG_FORMAT;
import static seedu.duke.Messages.MESSAGE_PAIR_UNPAIR_INPUT_EXAMPLE;

import static seedu.duke.Messages.MESSAGE_TRY_AGAIN;

import static seedu.duke.Messages.MESSAGE_INCORRECT_LIST_DETAILS;

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

    public void showClientDeletedConfirmationMessage(Client deletedClient) {
        showToUser(MESSAGE_CLIENT_DELETED);
        showToUser("  " + deletedClient);
    }

    public void showPairedConfirmationMessage(Client client, Property property) {
        showToUser(MESSAGE_PAIRED);
        showToUser("  " + client.getClientName() + " and " + property.getPropertyAddress());
    }

    public void showUnpairedConfirmationMessage(Client client, Property property) {
        showToUser(MESSAGE_UNPAIRED);
        showToUser("  " + client.getClientName() + " and " + property.getPropertyAddress());
    }

    public void showExistingPairMessage() {
        showToUser(MESSAGE_EXISTING_PAIR);
    }

    public void showNoExistingPairMessage() {
        showToUser(MESSAGE_NO_EXISTING_PAIR);
    }


    /* Add Property/Client Related Exceptions */

    public void showMissingCommandAddDetailMessage() {
        showToUser(MESSAGE_EMPTY_ADD_DESCRIPTION);
    }

    public void showUndefinedSubCommandAddTypeMessage() {
        showToUser(MESSAGE_MISSING_SUB_COMMAND_TYPE_FOR_ADD);
    }

    public void showEmptyPropertyDetailMessage() {
        showToUser(MESSAGE_EMPTY_PROPERTY_DESCRIPTION);
    }

    public void showEmptyClientDetailMessage() {
        showToUser(MESSAGE_EMPTY_CLIENT_DESCRIPTION);
    }

    public void showAddPropertyWrongFormatMessage() {
        showToUser(MESSAGE_ADD_PROPERTY_WRONG_FORMAT);
        showToUser(MESSAGE_PROPERTY_INPUT_EXAMPLE);
        showToUser(MESSAGE_TRY_AGAIN);
    }

    public void showAddClientWrongFormatMessage() {
        showToUser(MESSAGE_ADD_CLIENT_WRONG_FORMAT);
        showToUser(MESSAGE_CLIENT_INPUT_EXAMPLE);
        showToUser(MESSAGE_TRY_AGAIN);
    }

    public void showInvalidSingaporeAddressMessage() {
        showToUser(MESSAGE_INVALID_SINGAPORE_ADDRESS);
        showToUser(MESSAGE_VALID_SINGAPORE_ADDRESS_EXAMPLE);
        showToUser(MESSAGE_TRY_AGAIN);
    }

    public void showInvalidPriceFormatMessage() {
        showToUser(MESSAGE_INVALID_PRICE_FORMAT);
    }

    public void showInvalidContactNumberMessage() {
        showToUser(MESSAGE_INVALID_CONTACT_NUMBER);
    }

    public void showInvalidEmailMessage() {
        showToUser(MESSAGE_INVALID_EMAIL);
    }

    public void showInvalidBudgetFormatMessage() {
        showToUser(MESSAGE_INVALID_BUDGET_FORMAT);
    }


    /* Delete Client Related Exceptions */

    public void showUndefinedSubCommandDeleteTypeMessage() {
        showToUser(MESSAGE_MISSING_SUB_COMMAND_TYPE_FOR_DELETE);
    }

    public void showMissingCommandDeleteDetailMessage() {
        showToUser(MESSAGE_EMPTY_DELETE_DESCRIPTION);
    }

    public void showInvalidClientIndexDeleteMessage() {
        showToUser(MESSAGE_INVALID_CLIENT_INDEX);
    }

    public void showEmptyClientIndexDeleteMessage() {
        showToUser(MESSAGE_EMPTY_CLIENT_INDEX);
    }
    public void showIncorrectListDetailsMessage() {
        showToUser(MESSAGE_INCORRECT_LIST_DETAILS);
    }


    /* Pair/Unpair Related Exceptions */

    public void showEmptyCommandPairUnpairDetailsMessage() {
        showToUser(MESSAGE_EMPTY_COMMAND_PAIR_UNPAIR);
    }

    public void showNotValidIndexMessage() {
        showToUser(MESSAGE_NOT_VALID_INDEX);
    }

    public void showNotIntegerMessage() {
        showToUser(MESSAGE_NOT_INTEGER);
    }

    public void showPairUnpairWrongFormatMessage() {
        showToUser(MESSAGE_PAIR_UNPAIR_WRONG_FORMAT);
        showToUser(MESSAGE_PAIR_UNPAIR_INPUT_EXAMPLE);
        showToUser(MESSAGE_TRY_AGAIN);
    }

    public void displayOneClient(Client client) {
        //client display interface
    }

    public void displayOneProperty(Property property) {
        //property display interface
    }
}