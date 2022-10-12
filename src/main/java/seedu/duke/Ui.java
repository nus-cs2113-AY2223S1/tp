package seedu.duke;

import java.util.ArrayList;
import java.util.Scanner;

import static seedu.duke.Messages.MESSAGE_EMPTY_PROPERTY_INDEX;
import static seedu.duke.Messages.MESSAGE_INVALID_PROPERTY_INDEX;
import static seedu.duke.Messages.MESSAGE_INVALID_PROPERTY_INDEX_FLAG_FORMAT;
import static seedu.duke.Messages.MESSAGE_MISSING_PROPERTY_INDEX_FLAG;
import static seedu.duke.Messages.MESSAGE_PROPERTY_DELETED;
import static seedu.duke.Messages.MESSAGE_WELCOME;
import static seedu.duke.Messages.MESSAGE_PROPERTY_ADDED;
import static seedu.duke.Messages.MESSAGE_ADD_CLIENT_WRONG_FORMAT;
import static seedu.duke.Messages.MESSAGE_CHECK_PROPERTY_RESULT;
import static seedu.duke.Messages.MESSAGE_CHECK_PROPERTY_WRONG_FORMAT;
import static seedu.duke.Messages.MESSAGE_CLIENT_ADDED;
import static seedu.duke.Messages.MESSAGE_CLIENT_ALREADY_PAIRED;
import static seedu.duke.Messages.MESSAGE_CLIENT_DELETED;
import static seedu.duke.Messages.MESSAGE_CLIENT_INPUT_EXAMPLE;
import static seedu.duke.Messages.MESSAGE_COMMAND_UNDEFINED;
import static seedu.duke.Messages.MESSAGE_EMPTY_ADD_DESCRIPTION;
import static seedu.duke.Messages.MESSAGE_EMPTY_CHECK_DESCRIPTION;
import static seedu.duke.Messages.MESSAGE_PROPERTY_ALREADY_PAIRED;
import static seedu.duke.Messages.MESSAGE_WELCOME;
import static seedu.duke.Messages.MESSAGE_PROPERTY_ADDED;
import static seedu.duke.Messages.MESSAGE_PAIRED;
import static seedu.duke.Messages.MESSAGE_UNPAIRED;
import static seedu.duke.Messages.MESSAGE_NO_EXISTING_PAIR;
import static seedu.duke.Messages.MESSAGE_MISSING_SUB_COMMAND_TYPE_FOR_ADD;
import static seedu.duke.Messages.MESSAGE_EMPTY_PROPERTY_DESCRIPTION;
import static seedu.duke.Messages.MESSAGE_EMPTY_CLIENT_DESCRIPTION;
import static seedu.duke.Messages.MESSAGE_EMPTY_COMMAND_PAIR_UNPAIR;
import static seedu.duke.Messages.MESSAGE_EMPTY_CLIENT_INDEX;
import static seedu.duke.Messages.MESSAGE_EMPTY_DELETE_DESCRIPTION;
import static seedu.duke.Messages.MESSAGE_INVALID_BUDGET_FORMAT;
import static seedu.duke.Messages.MESSAGE_INVALID_CLIENT_INDEX;
import static seedu.duke.Messages.MESSAGE_INVALID_CLIENT_INDEX_FLAG_FORMAT;
import static seedu.duke.Messages.MESSAGE_INVALID_CONTACT_NUMBER;
import static seedu.duke.Messages.MESSAGE_INVALID_EMAIL;
import static seedu.duke.Messages.MESSAGE_MISSING_CLIENT_INDEX_FLAG;
import static seedu.duke.Messages.MESSAGE_MISSING_SUB_COMMAND_TYPE_FOR_CHECK;
import static seedu.duke.Messages.MESSAGE_MISSING_SUB_COMMAND_TYPE_FOR_DELETE;
import static seedu.duke.Messages.MESSAGE_NUMBER_OF_LIST_RESULTS;
import static seedu.duke.Messages.MESSAGE_TRY_AGAIN;
import static seedu.duke.Messages.MESSAGE_NOT_INTEGER;
import static seedu.duke.Messages.MESSAGE_ADD_PROPERTY_WRONG_FORMAT;
import static seedu.duke.Messages.MESSAGE_PROPERTY_INPUT_EXAMPLE;
import static seedu.duke.Messages.MESSAGE_INVALID_SINGAPORE_ADDRESS;
import static seedu.duke.Messages.MESSAGE_VALID_SINGAPORE_ADDRESS_EXAMPLE;
import static seedu.duke.Messages.MESSAGE_INVALID_PRICE_FORMAT;
import static seedu.duke.Messages.MESSAGE_NOT_VALID_INDEX;
import static seedu.duke.Messages.MESSAGE_PAIR_UNPAIR_WRONG_FORMAT;
import static seedu.duke.Messages.MESSAGE_PAIR_UNPAIR_INPUT_EXAMPLE;
import static seedu.duke.Messages.MESSAGE_TRY_AGAIN;

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

    public void showMissingCommandAddDetailMessage() {
        showToUser(MESSAGE_EMPTY_ADD_DESCRIPTION);
    }

    public void showUndefinedSubCommandAddTypeMessage() {
        showToUser(MESSAGE_MISSING_SUB_COMMAND_TYPE_FOR_ADD);
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

    public void showEmptyPropertyDetailMessage() {
        showToUser(MESSAGE_EMPTY_PROPERTY_DESCRIPTION);
    }

    public void showEmptyClientDetailMessage() {
        showToUser(MESSAGE_EMPTY_CLIENT_DESCRIPTION);
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

    public void showUndefinedSubCommandDeleteTypeMessage() {
        showToUser(MESSAGE_MISSING_SUB_COMMAND_TYPE_FOR_DELETE);
    }

    public void showMissingCommandDeleteDetailMessage() {
        showToUser(MESSAGE_EMPTY_DELETE_DESCRIPTION);
    }

    public void showInvalidPropertyIndexDeleteMessage() {
        showToUser(MESSAGE_INVALID_PROPERTY_INDEX);
    }

    public void showEmptyPropertyIndexDeleteMessage() {
        showToUser(MESSAGE_EMPTY_PROPERTY_INDEX);
    }

    public void showMissingPropertyIndexFlagMessage() {
        showToUser(MESSAGE_MISSING_PROPERTY_INDEX_FLAG);
    }

    public void showInvalidPropertyIndexFlagFormatMessage() {
        showToUser(MESSAGE_INVALID_PROPERTY_INDEX_FLAG_FORMAT);
    }

    public void showInvalidClientIndexDeleteMessage() {
        showToUser(MESSAGE_INVALID_CLIENT_INDEX);
    }

    public void showEmptyClientIndexDeleteMessage() {
        showToUser(MESSAGE_EMPTY_CLIENT_INDEX);
    }

    public void showMissingClientIndexFlagMessage() {
        showToUser(MESSAGE_MISSING_CLIENT_INDEX_FLAG);
    }

    public void showInvalidClientIndexFlagFormatMessage() {
        showToUser(MESSAGE_INVALID_CLIENT_INDEX_FLAG_FORMAT);
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

    public void showClientAlreadyPairedMessage() {
        showToUser(MESSAGE_CLIENT_ALREADY_PAIRED);
    }

    public void showPropertyAlreadyPairedMessage() {
        showToUser(MESSAGE_PROPERTY_ALREADY_PAIRED);
    }

    public void showNoExistingPairMessage() {
        showToUser(MESSAGE_NO_EXISTING_PAIR);
    }


    /* Check-Command-related showMessage methods. */

    public void showCheckPropertyWrongFormatMessage() {
        showToUser(MESSAGE_CHECK_PROPERTY_WRONG_FORMAT);
    }

    public void showUndefinedSubCommandCheckTypeMessage() {
        showToUser(MESSAGE_MISSING_SUB_COMMAND_TYPE_FOR_CHECK);
    }

    public void showEmptyCommandCheckDetailException() {
        showToUser(MESSAGE_EMPTY_CHECK_DESCRIPTION);
    }

    public void showCheckProperty(ArrayList<String> tenants) {
        showToUser(MESSAGE_CHECK_PROPERTY_RESULT);
        int count = 0;
        for (String tenant : tenants) {
            // Remove brackets at first and last indexes of tenant(client) string
            String tenantInfo = tenant.substring(1, tenant.length() - 1);
            showToUser(String.format("  %d. %s", ++count, tenantInfo));
        }
        showToUser(MESSAGE_NUMBER_OF_LIST_RESULTS + count);
    }
}
