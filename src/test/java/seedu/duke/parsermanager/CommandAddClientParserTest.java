//@@author OVReader

package seedu.duke.parsermanager;

import org.junit.jupiter.api.Test;
import seedu.duke.ClientList;
import seedu.duke.PairingList;
import seedu.duke.PropertyList;
import seedu.duke.Storage;
import seedu.duke.Ui;
import seedu.duke.command.Command;
import seedu.duke.exception.DukeException;
import seedu.duke.exception.DuplicateClientException;
import seedu.duke.exception.EmptyAddClientDetailException;
import seedu.duke.exception.IncorrectAddClientFlagOrderException;
import seedu.duke.exception.InvalidBudgetFormatException;
import seedu.duke.exception.InvalidContactNumberException;
import seedu.duke.exception.InvalidEmailException;
import seedu.duke.exception.MissingAddClientFlagException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class CommandAddClientParserTest {
    private static final String VALID_TEST_CASE = "n/Joe c/93437878 e/john123@example.com b/2000";
    private static final String EMPTY_CLIENT_INPUT = "";
    private static final String MISSING_CLIENT_FLAG = "John c/93437878 e/john123@example.com b/2000";
    private static final String INCORRECT_CLIENT_FLAG_ORDER = "c/John n/93437878 e/john123@example.com b/2000";
    private static final String MISSING_CLIENT_NAME_INPUT = "n/ c/93437878 e/john123@example.com b/2000";

    // Invalid Singapore Contact Number Test Cases
    private static final String NON_NUMERICAL_CONTACT_NUMBER = "n/Joe c/hello:) e/john123@example.com b/2000";
    private static final String LESS_THAN_EIGHT_DIGIT_CONTACT_NUMBER = "n/Joe c/123456 e/john123@example.com b/2000";
    private static final String MORE_THAN_EIGHT_DIGIT_CONTACT_NUMBER = "n/Joe c/123456789 e/john123@example.com b/2000";
    private static final String NOT_SINGAPORE_CONTACT_NUMBER = "n/Joe c/43657271 e/john123@example.com b/2000";

    // Invalid Email Format Test Cases
    private static final String EMAIL_NO_AT_SYMBOL = "n/Joe c/93657271 e/john123example.com b/2000";
    private static final String EMAIL_EMPTY_BETWEEN_AT_SYMBOL_AND_DOT_COM = "n/Joe c/93657271 e/john123@.com b/2000";

    // Invalid Budget Format Test Cases
    private static final String ZERO_BUDGET = "n/Joe c/93657271 e/john123@example.com b/0";
    private static final String NEGATIVE_BUDGET = "n/Joe c/93657271 e/john123@example.com b/-1";
    private static final String FLOATING_POINT_BUDGET = "n/Joe c/93657271 e/john123@example.com b/12.345";

    // Client Details for Duplicate Client Testing
    private static final String CLIENT_NAME = "Joe";
    private static final String CLIENT_CONTACT_NUMBER = "93437878";
    private static final String CLIENT_EMAIL = "john123@example.com";
    private static final String CLIENT_BUDGET_PER_MONTH = "2000";

    private static final int OFFSET_UNIT_VALUE = 1;


    @Test
    public void parseCommand_validAddClientInput_matchingClientDetails() throws DukeException {
        ClientList clientList = new ClientList();
        CommandAddClientParser commandAddClientParser = new CommandAddClientParser(VALID_TEST_CASE, clientList);

        Command command = commandAddClientParser.parseCommand();
        PropertyList propertyList = new PropertyList();
        PairingList pairingList = new PairingList();
        Storage storage = new Storage(clientList, propertyList, pairingList);
        command.execute(new Ui(), storage, propertyList, clientList, pairingList);

        int addedClientIndex = clientList.getCurrentListSize() - OFFSET_UNIT_VALUE;
        String storedClientName = clientList.getClientList().get(addedClientIndex).getClientName();
        assertEquals(storedClientName, CLIENT_NAME);
        String storedClientContactNumber = clientList.getClientList().get(addedClientIndex).getClientContactNumber();
        assertEquals(storedClientContactNumber, CLIENT_CONTACT_NUMBER);
        String storedClientEmail = clientList.getClientList().get(addedClientIndex).getClientEmail();
        assertEquals(storedClientEmail, CLIENT_EMAIL);
        String storedClientBudget = clientList.getClientList().get(addedClientIndex).getClientBudgetPerMonth();
        assertEquals(storedClientBudget, CLIENT_BUDGET_PER_MONTH);
    }

    @Test
    public void checkForEmptyAddClientDetails_emptyClientDetail_exceptionThrown() {
        ClientList clientList = new ClientList();
        CommandAddClientParser commandAddClientParser = new CommandAddClientParser(EMPTY_CLIENT_INPUT, clientList);
        assertThrows(EmptyAddClientDetailException.class, commandAddClientParser::parseCommand);
    }

    @Test
    public void checkForMissingClientFlags_missingClientFlag_exceptionThrown() {
        ClientList clientList = new ClientList();
        CommandAddClientParser commandAddClientParser = new CommandAddClientParser(MISSING_CLIENT_FLAG, clientList);
        assertThrows(MissingAddClientFlagException.class, commandAddClientParser::parseCommand);
    }

    @Test
    public void checkClientFlagsOrder_incorrectClientFlagOrder_exceptionThrown() {
        ClientList clientList = new ClientList();
        CommandAddClientParser commandAddClientParser = new CommandAddClientParser(INCORRECT_CLIENT_FLAG_ORDER,
                clientList);
        assertThrows(IncorrectAddClientFlagOrderException.class, commandAddClientParser::parseCommand);
    }

    @Test
    public void checkForMissingClientDetails_missingClientName_exceptionThrown() {
        ClientList clientList = new ClientList();
        CommandAddClientParser commandAddClientParser = new CommandAddClientParser(MISSING_CLIENT_NAME_INPUT,
                clientList);
        assertThrows(EmptyAddClientDetailException.class, commandAddClientParser::parseCommand);
    }

    @Test
    public void checkForValidSingaporeContactNumber_invalidSingaporeContactNumber_exceptionThrown() {
        ClientList clientList = new ClientList();
        CommandAddClientParser commandAddClientParser;

        commandAddClientParser = new CommandAddClientParser(NON_NUMERICAL_CONTACT_NUMBER, clientList);
        assertThrows(InvalidContactNumberException.class, commandAddClientParser::parseCommand);
        commandAddClientParser = new CommandAddClientParser(LESS_THAN_EIGHT_DIGIT_CONTACT_NUMBER, clientList);
        assertThrows(InvalidContactNumberException.class, commandAddClientParser::parseCommand);
        commandAddClientParser = new CommandAddClientParser(MORE_THAN_EIGHT_DIGIT_CONTACT_NUMBER, clientList);
        assertThrows(InvalidContactNumberException.class, commandAddClientParser::parseCommand);
        commandAddClientParser = new CommandAddClientParser(NOT_SINGAPORE_CONTACT_NUMBER, clientList);
        assertThrows(InvalidContactNumberException.class, commandAddClientParser::parseCommand);
    }

    @Test
    public void checkForValidEmail_invalidEmailFormat_exceptionThrown() {
        ClientList clientList = new ClientList();
        CommandAddClientParser commandAddClientParser;

        commandAddClientParser = new CommandAddClientParser(EMAIL_NO_AT_SYMBOL, clientList);
        assertThrows(InvalidEmailException.class, commandAddClientParser::parseCommand);
        commandAddClientParser = new CommandAddClientParser(EMAIL_EMPTY_BETWEEN_AT_SYMBOL_AND_DOT_COM, clientList);
        assertThrows(InvalidEmailException.class, commandAddClientParser::parseCommand);
    }

    @Test
    public void checkForBudgetNumberFormat_invalidBudget_exceptionThrown() {
        ClientList clientList = new ClientList();
        CommandAddClientParser commandAddClientParser;
        // Budget must be positive integer
        commandAddClientParser = new CommandAddClientParser(ZERO_BUDGET, clientList);
        assertThrows(InvalidBudgetFormatException.class, commandAddClientParser::parseCommand);
        commandAddClientParser = new CommandAddClientParser(NEGATIVE_BUDGET, clientList);
        assertThrows(InvalidBudgetFormatException.class, commandAddClientParser::parseCommand);
        commandAddClientParser = new CommandAddClientParser(FLOATING_POINT_BUDGET, clientList);
        assertThrows(InvalidBudgetFormatException.class, commandAddClientParser::parseCommand);
    }

    @Test
    public void checkForDuplicateClient_identicalClientsAdded_exceptionThrown() {
        ClientList clientList = new ClientList();
        clientList.addClient(CLIENT_NAME, CLIENT_CONTACT_NUMBER, CLIENT_EMAIL, CLIENT_BUDGET_PER_MONTH);
        CommandAddClientParser commandAddClientParser = new CommandAddClientParser(VALID_TEST_CASE, clientList);
        assertThrows(DuplicateClientException.class, commandAddClientParser::parseCommand);
    }
}