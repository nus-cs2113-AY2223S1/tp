//@@author OVReader

package seedu.duke.parsermanager;

import seedu.duke.Client;
import seedu.duke.ClientList;
import seedu.duke.Ui;

import seedu.duke.command.Command;
import seedu.duke.command.CommandAddClient;

import seedu.duke.exception.DuplicateClientException;
import seedu.duke.exception.EmptyAddClientDetailException;
import seedu.duke.exception.IncorrectAddClientFlagOrderException;
import seedu.duke.exception.InvalidBudgetFormatException;
import seedu.duke.exception.InvalidContactNumberException;
import seedu.duke.exception.InvalidEmailException;
import seedu.duke.exception.MissingAddClientFlagException;
import seedu.duke.exception.ParseAddClientException;

import java.util.ArrayList;

import static seedu.duke.CommandStructure.ADD_CLIENT_FLAGS;

/**
 * Parses input for add client command.
 */
public class ParseAddClient extends ParseAdd {
    private final String commandDescription;
    private final ClientList clientList;

    private static final int CLIENT_NAME_INDEX = 0;
    private static final int CLIENT_CONTACT_NUMBER_INDEX = 1;
    private static final int CLIENT_EMAIL_INDEX = 2;
    private static final int CLIENT_BUDGET_INDEX = 3;

    private static final int FLAG_JUMPER_VALUE = 2;

    private static final String EXISTING_CLIENT = "Existing Client:\n  ";

    // Adds Client Regex for Validation.
    private static final String VALID_SINGAPORE_CONTACT_NUMBER_REGEX = "^[689]\\d{7}$";
    // General Email Regex (RFC 5322 Official Standard)
    private static final String VALID_EMAIL_REGEX = "(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)"
            + "*|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x"
            + "7f])*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:25[0-5]|2"
            + "[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|[a-z0-9-]*[a-z0-9]:(?:[\\x01"
            + "-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])";
    // Accepts only positive whole number for budget.
    private static final String VALID_BUDGET_REGEX = "^[1-9]\\d*$";

    public ParseAddClient(String addCommandDescription, ClientList clientList) {
        this.commandDescription = addCommandDescription;
        this.clientList = clientList;
    }

    public Command parseCommand() throws ParseAddClientException {
        checkForEmptyAddClientDetails(commandDescription);
        ArrayList<String> clientDetails = processCommandAddClientDetails(commandDescription);
        validateClientDetails(clientDetails);
        return new CommandAddClient(clientDetails);
    }

    private void checkForEmptyAddClientDetails(String commandDetail) throws EmptyAddClientDetailException {
        boolean isEmptyDetail = isEmptyString(commandDetail);
        if (isEmptyDetail) {
            throw new EmptyAddClientDetailException();
        }
    }

    private ArrayList<String> processCommandAddClientDetails(String rawCommandDetail)
            throws MissingAddClientFlagException, IncorrectAddClientFlagOrderException {
        int[] flagIndexPositions = getFlagIndexPositions(rawCommandDetail, ADD_CLIENT_FLAGS);
        checkForMissingClientFlags(flagIndexPositions);
        checkClientFlagsOrder(flagIndexPositions);
        return extractClientDetails(rawCommandDetail, flagIndexPositions);
    }

    private void checkForMissingClientFlags(int[] flagIndexPositions) throws MissingAddClientFlagException {
        for (int flagIndex = 0; flagIndex < flagIndexPositions.length; flagIndex++) {
            boolean isEmailIndex = (flagIndex == CLIENT_EMAIL_INDEX);
            // Skips empty check for email as email is optional
            if (!isEmailIndex && !checkForFlagPresence(flagIndex)) {
                throw new MissingAddClientFlagException();
            }
        }
    }

    private void checkClientFlagsOrder(int[] flagIndexPositions) throws IncorrectAddClientFlagOrderException {
        checkForCorrectFlagOrder(flagIndexPositions[CLIENT_NAME_INDEX],
                flagIndexPositions[CLIENT_CONTACT_NUMBER_INDEX]);
        boolean hasEmail = (flagIndexPositions[CLIENT_EMAIL_INDEX] != MISSING_FLAG_VALUE);
        if (hasEmail) {
            checkForCorrectFlagOrder(flagIndexPositions[CLIENT_CONTACT_NUMBER_INDEX],
                    flagIndexPositions[CLIENT_EMAIL_INDEX]);
            checkForCorrectFlagOrder(flagIndexPositions[CLIENT_EMAIL_INDEX], flagIndexPositions[CLIENT_BUDGET_INDEX]);
        }  else {
            checkForCorrectFlagOrder(flagIndexPositions[CLIENT_CONTACT_NUMBER_INDEX],
                    flagIndexPositions[CLIENT_BUDGET_INDEX]);
        }
    }

    private void checkForCorrectFlagOrder(int flagPosition, int nextFlagPosition) throws
            IncorrectAddClientFlagOrderException {
        boolean hasCorrectOrder = (flagPosition < nextFlagPosition);
        if (!hasCorrectOrder) {
            throw new IncorrectAddClientFlagOrderException();
        }
    }

    private ArrayList<String> extractClientDetails(String rawClientDetail, int[] addClientFlagIndexPositions) {
        boolean hasEmail = (addClientFlagIndexPositions[CLIENT_EMAIL_INDEX] != MISSING_FLAG_VALUE);
        String clientContactNumber;
        String clientEmail = "";
        if (hasEmail) {
            clientContactNumber = extractDetail(rawClientDetail,
                    addClientFlagIndexPositions[CLIENT_CONTACT_NUMBER_INDEX] + FLAG_JUMPER_VALUE,
                    addClientFlagIndexPositions[CLIENT_EMAIL_INDEX]);
            clientEmail = extractDetail(rawClientDetail, addClientFlagIndexPositions[CLIENT_EMAIL_INDEX]
                    + FLAG_JUMPER_VALUE, addClientFlagIndexPositions[CLIENT_BUDGET_INDEX]);
        } else {
            clientContactNumber = extractDetail(rawClientDetail,
                    addClientFlagIndexPositions[CLIENT_CONTACT_NUMBER_INDEX] + FLAG_JUMPER_VALUE,
                    addClientFlagIndexPositions[CLIENT_BUDGET_INDEX]);
        }
        String clientName = extractDetail(rawClientDetail, addClientFlagIndexPositions[CLIENT_NAME_INDEX]
                + FLAG_JUMPER_VALUE, addClientFlagIndexPositions[CLIENT_CONTACT_NUMBER_INDEX]);
        String clientBudgetPerMonth = extractDetail(rawClientDetail,
                addClientFlagIndexPositions[CLIENT_BUDGET_INDEX] + FLAG_JUMPER_VALUE);

        ArrayList<String> extractedClientDetails = new ArrayList<>();
        extractedClientDetails.add(clientName.trim());
        extractedClientDetails.add(clientContactNumber.trim());
        extractedClientDetails.add(clientEmail.trim());
        extractedClientDetails.add(clientBudgetPerMonth.trim());
        return extractedClientDetails;
    }

    private void validateClientDetails(ArrayList<String> clientDetails) throws EmptyAddClientDetailException,
            InvalidContactNumberException, InvalidEmailException, InvalidBudgetFormatException,
            DuplicateClientException {
        // Checks for Missing Client Name, Contact Number, Budget Per Month (SGD)
        checkForEmptyAddClientDetails(clientDetails.get(CLIENT_NAME_INDEX));
        checkForEmptyAddClientDetails(clientDetails.get(CLIENT_CONTACT_NUMBER_INDEX));
        checkForEmptyAddClientDetails(clientDetails.get(CLIENT_BUDGET_INDEX));

        // Checks for Contact Number, Email and Budget Format
        checkForValidSingaporeContactNumber(clientDetails.get(CLIENT_CONTACT_NUMBER_INDEX));
        boolean hasEmail = !clientDetails.get(CLIENT_EMAIL_INDEX).isEmpty();
        if (hasEmail) {
            checkForValidEmail(clientDetails.get(CLIENT_EMAIL_INDEX));
        }
        checkForBudgetNumberFormat(clientDetails.get(CLIENT_BUDGET_INDEX));

        // Duplicate Client refers to clients with the same Name/Contact Number/Email.
        checkForDuplicateClient(clientList, clientDetails);
    }

    private void checkForValidSingaporeContactNumber(String clientContactNumber) throws InvalidContactNumberException {
        boolean hasValidContactNumber = checkForDetailFormat(VALID_SINGAPORE_CONTACT_NUMBER_REGEX, clientContactNumber);
        if (!hasValidContactNumber) {
            throw new InvalidContactNumberException();
        }
    }

    private void checkForValidEmail(String clientEmail) throws InvalidEmailException {
        boolean hasValidEmail = checkForDetailFormat(VALID_EMAIL_REGEX, clientEmail);
        if (!hasValidEmail) {
            throw new InvalidEmailException();
        }
    }

    private void checkForBudgetNumberFormat(String budget) throws InvalidBudgetFormatException {
        boolean hasValidBudgetNumberFormat = checkForDetailFormat(VALID_BUDGET_REGEX, budget);
        if (!hasValidBudgetNumberFormat) {
            throw new InvalidBudgetFormatException();
        }
    }

    private void checkForDuplicateClient(ClientList clientList, ArrayList<String> clientDetails) throws
            DuplicateClientException {
        boolean hasEmail = !clientDetails.get(CLIENT_EMAIL_INDEX).isEmpty();
        boolean isDuplicateEmail = false;

        for (Client client : clientList.getClientList()) {
            boolean isDuplicateName = clientDetails.get(CLIENT_NAME_INDEX).equals(client.getClientName());
            boolean isDuplicateContactNumber = clientDetails.get(CLIENT_CONTACT_NUMBER_INDEX)
                    .equals(client.getClientContactNumber());
            if (hasEmail) {
                isDuplicateEmail = clientDetails.get(CLIENT_EMAIL_INDEX).equals(client.getClientEmail());
            }
            boolean isDuplicateClient = isDuplicateName || isDuplicateContactNumber || isDuplicateEmail;
            if (isDuplicateClient) {
                showExistingDuplicateClient(client);
                throw new DuplicateClientException();
            }
        }
    }

    private static void showExistingDuplicateClient(Client client) {
        Ui ui = new Ui();
        ui.showToUser(EXISTING_CLIENT + client.toString());
    }
}
