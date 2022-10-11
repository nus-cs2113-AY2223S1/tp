package seedu.duke;

import java.util.Scanner;

import static seedu.duke.Messages.MESSAGE_PROPERTY_ADDED;
import static seedu.duke.Messages.MESSAGE_CLIENT_ADDED;
import static seedu.duke.Messages.MESSAGE_EMPTY_ADD_DESCRIPTION;
import static seedu.duke.Messages.MESSAGE_MISSING_SUB_COMMAND_TYPE_FOR_ADD;
import static seedu.duke.Messages.MESSAGE_EMPTY_PROPERTY_DESCRIPTION;
import static seedu.duke.Messages.MESSAGE_EMPTY_CLIENT_DESCRIPTION;
import static seedu.duke.Messages.MESSAGE_ADD_PROPERTY_WRONG_FORMAT;
import static seedu.duke.Messages.MESSAGE_PROPERTY_INPUT_EXAMPLE;
import static seedu.duke.Messages.MESSAGE_ADD_CLIENT_WRONG_FORMAT;
import static seedu.duke.Messages.MESSAGE_CLIENT_INPUT_EXAMPLE;
import static seedu.duke.Messages.MESSAGE_TRY_AGAIN;
import static seedu.duke.Messages.MESSAGE_INVALID_SINGAPORE_ADDRESS;
import static seedu.duke.Messages.MESSAGE_VALID_SINGAPORE_ADDRESS_EXAMPLE;
import static seedu.duke.Messages.MESSAGE_INVALID_PRICE_FORMAT;
import static seedu.duke.Messages.MESSAGE_INVALID_CONTACT_NUMBER;
import static seedu.duke.Messages.MESSAGE_INVALID_EMAIL;
import static seedu.duke.Messages.MESSAGE_INVALID_BUDGET_FORMAT;

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
}
