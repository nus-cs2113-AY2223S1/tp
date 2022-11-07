package seedu.duke.command.help;

import seedu.duke.command.Command;
import seedu.duke.ui.Ui;

import java.util.LinkedHashMap;
import java.util.Map;

// @@author winston-lim

/**
 * A representation of a command to print all commands.
 */
public class HelpCommand extends Command {
    private static final int NUMBER_OF_COMMANDS = 26;
    private static final int NUMBER_OF_HEADERS = 7;
    private static final String COMMAND_TO_DETAIL_SEPARATOR_TOKEN = ": ";

    // Header constants
    private static final String ADD_COMMAND_HEADER = "ADD-RELATED-COMMANDS";
    private static final String REMOVE_COMMAND_HEADER = "REMOVE-RELATED-COMMANDS";
    private static final String LIST_COMMAND_HEADER = "LIST-RELATED-COMMANDS";
    private static final String VIEW_COMMAND_HEADER = "VIEW-RELATED-COMMANDS";
    private static final String UPDATE_COMMAND_HEADER = "UPDATE-RELATED-COMMANDS";
    private static final String FIND_COMMAND_HEADER = "FIND-RELATED-COMMANDS";
    private static final String ADDITIONAL_DETAILS_HEADER = "ADDITIONAL-DETAILS";

    // Command constants
    private static final String COMMAND_ADD_USER =
            "add-user /n <userName> /a <age> /c <contactNumber>";
    private static final String COMMAND_ADD_USER_DESCRIPTION = "Add a user";

    private static final String COMMAND_ADD_ITEM =
            "add-item /n <itemName> /c <categoryIndex> /p <price> /o <userName>";
    private static final String COMMAND_ADD_ITEM_DESCRIPTION = "Add a item";

    private static final String COMMAND_ADD_TX =
            "add-tx /i <itemId> /b <borrowerName> /d <duration> /c <createdAt>";
    private static final String COMMAND_ADD_TX_DESCRIPTION = "Add a transaction";

    private static final String COMMAND_REMOVE_USER = "remove-user /u <userName>";
    private static final String COMMAND_REMOVE_USER_DESCRIPTION = "Remove a user";

    private static final String COMMAND_REMOVE_ITEM = "remove-item /i <itemId>";
    private static final String COMMAND_REMOVE_ITEM_DESCRIPTION = "Remove a item";

    private static final String COMMAND_REMOVE_TX = "remove-tx /t <transactionId>";
    private static final String COMMAND_REMOVE_TX_DESCRIPTION = "Remove a transaction";

    private static final String COMMAND_HELP = "help";
    private static final String COMMAND_HELP_DESCRIPTION = "List all commands";

    private static final String COMMAND_LIST_USERS = "list-users";
    private static final String COMMAND_LIST_USERS_DESCRIPTION = "List all users";

    private static final String COMMAND_LIST_ITEMS = "list-items";
    private static final String COMMAND_LIST_ITEMS_DESCRIPTION = "List all items";

    private static final String COMMAND_LIST_TX = "list-tx";
    private static final String COMMAND_LIST_TX_DESCRIPTION = "List all transactions";

    private static final String COMMAND_LIST_CATEGORIES = "list-categories";
    private static final String COMMAND_LIST_CATEGORIES_DESCRIPTION =
            "List all categories available";

    private static final String COMMAND_VIEW_USER = "view-user /u <userName>";
    private static final String COMMAND_VIEW_USER_DESCRIPTION = "View a user";

    private static final String COMMAND_VIEW_USER_ITEMS = "view-user-items /u <userName>";
    private static final String COMMAND_VIEW_USER_ITEMS_DESCRIPTION = "View a user's items";

    private static final String COMMAND_VIEW_ITEM = "view-item /i <itemId>";
    private static final String COMMAND_VIEW_ITEM_DESCRIPTION = "View a item";

    private static final String COMMAND_VIEW_USER_LOSS = "view-user-loss /u <userName>";
    private static final String COMMAND_VIEW_USER_LOSS_DESCRIPTION =
            "View the amount of money loss of a user";

    private static final String COMMAND_VIEW_USER_GAIN = "view-user-gain /u <userName>";
    private static final String COMMAND_VIEW_USER_GAIN_DESCRIPTION =
            "View the amount of money gain of a user";

    private static final String COMMAND_VIEW_BORROW_TX_BY_USER =
            "view-borrow-tx-by-user /u <userName>";
    private static final String COMMAND_VIEW_BORROW_TX_BY_USER_DESCRIPTION =
            "View list of user's borrow transaction";

    private static final String COMMAND_VIEW_LEND_TX_BY_USER = "view-lend-tx-by-user /u <userName>";
    private static final String COMMAND_VIEW_LEND_TX_BY_USER_DESCRIPTION =
            "View list of user's lend transaction";

    private static final String COMMAND_VIEW_TX = "view-tx /t <transactionId>";
    private static final String COMMAND_VIEW_TX_DESCRIPTION = "View a transaction";

    private static final String COMMAND_UPDATE_TX = "update-tx /t <transactionId> /d <duration>";
    private static final String COMMAND_UPDATE_TX_DESCRIPTION = "Update duration of a transaction";

    private static final String COMMAND_UPDATE_ITEM = "update-item /i <itemId> /p <price>";
    private static final String COMMAND_UPDATE_ITEM_DESCRIPTION = "Update price of an item";

    private static final String COMMAND_FIND_FINISHED_TX = "find-tx /s finished";
    private static final String COMMAND_FIND_FINISHED_TX_DESCRIPTION =
            "Find all finished transactions";

    private static final String COMMAND_FIND_UNFINISHED_TX = "find-tx /s unfinished";
    private static final String COMMAND_FIND_UNFINISHED_TX_DESCRIPTION =
            "Find all unfinished transactions";

    private static final String COMMAND_FIND_USER = "find-user /k <keyword>";
    private static final String COMMAND_FIND_USER_DESCRIPTION = "Find user by keywords";

    private static final String COMMAND_FIND_ITEM = "find-item /k <keyword>";
    private static final String COMMAND_FIND_ITEM_DESCRIPTION = "Find item by keywords";

    private static final String COMMAND_SORT_ITEM =
            "sort-items /mode <mode: hl or lh> /min <min> /max <max> /cat <categoryIndex>";
    private static final String COMMAND_SORT_ITEM_DESCRIPTION = "Sort all items in a range";

    // Other constants
    private static final String ADD_SPACE_REMINDER =
            "Please take note to add a space before and after delimiters! e.g ' /c '\n";
    private static final String CASE_SENSITIVE_REMINDER =
            "Please note that except for 'find-item' and 'find-user'"
                    + ", all other commands are case-sensitive!\n";
    private static final String INVALID_SYMBOLS_REMINDER =
            "Refrain from using '|' and '/' as arguments!\n";

    private static final String PRICE_DECIMAL_REMINDER =
            "Please note that price inputs can only have at most 2 decimals!";

    private static final String UNDERLINE = System.lineSeparator() + "--------------------";
    private final LinkedHashMap<String, String> commandToDetailMap;

    /**
     * Constructor for HelpCommand.
     */
    public HelpCommand() {
        this.commandToDetailMap = new LinkedHashMap<>();
        this.commandToDetailMap.put(System.lineSeparator() + ADD_COMMAND_HEADER, UNDERLINE);
        this.commandToDetailMap.put(COMMAND_ADD_USER_DESCRIPTION, COMMAND_ADD_USER);
        this.commandToDetailMap.put(COMMAND_ADD_ITEM_DESCRIPTION, COMMAND_ADD_ITEM);
        this.commandToDetailMap.put(COMMAND_ADD_TX_DESCRIPTION, COMMAND_ADD_TX);
        this.commandToDetailMap.put(System.lineSeparator() + REMOVE_COMMAND_HEADER, UNDERLINE);
        this.commandToDetailMap.put(COMMAND_REMOVE_USER_DESCRIPTION, COMMAND_REMOVE_USER);
        this.commandToDetailMap.put(COMMAND_REMOVE_ITEM_DESCRIPTION, COMMAND_REMOVE_ITEM);
        this.commandToDetailMap.put(COMMAND_REMOVE_TX_DESCRIPTION, COMMAND_REMOVE_TX);
        this.commandToDetailMap.put(System.lineSeparator() + LIST_COMMAND_HEADER, UNDERLINE);
        this.commandToDetailMap.put(COMMAND_HELP_DESCRIPTION, COMMAND_HELP);
        this.commandToDetailMap.put(COMMAND_LIST_USERS_DESCRIPTION, COMMAND_LIST_USERS);
        this.commandToDetailMap.put(COMMAND_LIST_ITEMS_DESCRIPTION, COMMAND_LIST_ITEMS);
        this.commandToDetailMap.put(COMMAND_LIST_CATEGORIES_DESCRIPTION, COMMAND_LIST_CATEGORIES);
        this.commandToDetailMap.put(COMMAND_LIST_TX_DESCRIPTION, COMMAND_LIST_TX);
        this.commandToDetailMap.put(System.lineSeparator() + VIEW_COMMAND_HEADER, UNDERLINE);
        this.commandToDetailMap.put(COMMAND_VIEW_USER_DESCRIPTION, COMMAND_VIEW_USER);
        this.commandToDetailMap.put(COMMAND_VIEW_USER_ITEMS_DESCRIPTION, COMMAND_VIEW_USER_ITEMS);
        this.commandToDetailMap.put(COMMAND_VIEW_ITEM_DESCRIPTION, COMMAND_VIEW_ITEM);
        this.commandToDetailMap.put(COMMAND_VIEW_TX_DESCRIPTION, COMMAND_VIEW_TX);
        this.commandToDetailMap.put(COMMAND_VIEW_BORROW_TX_BY_USER_DESCRIPTION,
                COMMAND_VIEW_BORROW_TX_BY_USER);
        this.commandToDetailMap.put(COMMAND_VIEW_LEND_TX_BY_USER_DESCRIPTION,
                COMMAND_VIEW_LEND_TX_BY_USER);
        this.commandToDetailMap.put(COMMAND_VIEW_USER_LOSS_DESCRIPTION, COMMAND_VIEW_USER_LOSS);
        this.commandToDetailMap.put(COMMAND_VIEW_USER_GAIN_DESCRIPTION, COMMAND_VIEW_USER_GAIN);
        this.commandToDetailMap.put(System.lineSeparator() + UPDATE_COMMAND_HEADER, UNDERLINE);
        this.commandToDetailMap.put(COMMAND_UPDATE_ITEM_DESCRIPTION, COMMAND_UPDATE_ITEM);
        this.commandToDetailMap.put(COMMAND_UPDATE_TX_DESCRIPTION, COMMAND_UPDATE_TX);
        this.commandToDetailMap.put(System.lineSeparator() + FIND_COMMAND_HEADER, UNDERLINE);
        this.commandToDetailMap.put(COMMAND_FIND_FINISHED_TX_DESCRIPTION, COMMAND_FIND_FINISHED_TX);
        this.commandToDetailMap.put(COMMAND_FIND_UNFINISHED_TX_DESCRIPTION,
                COMMAND_FIND_UNFINISHED_TX);
        this.commandToDetailMap.put(COMMAND_FIND_USER_DESCRIPTION, COMMAND_FIND_USER);
        this.commandToDetailMap.put(COMMAND_FIND_ITEM_DESCRIPTION, COMMAND_FIND_ITEM);
        this.commandToDetailMap.put(COMMAND_SORT_ITEM_DESCRIPTION, COMMAND_SORT_ITEM);
        this.commandToDetailMap.put(System.lineSeparator() + ADDITIONAL_DETAILS_HEADER, UNDERLINE);
        assert this.commandToDetailMap.size() == NUMBER_OF_COMMANDS
                + NUMBER_OF_HEADERS : "Missing command";
    }

    /**
     * Execute HelpCommand (print all available commands).
     *
     * @return false
     */
    public boolean executeCommand() {
        Ui.printResponse(this.toString());
        return false;
    }

    @Override
    public String toString() {
        StringBuilder output = new StringBuilder();
        for (Map.Entry<String, String> entry : commandToDetailMap.entrySet()) {
            output.append(entry.getKey()).append(COMMAND_TO_DETAIL_SEPARATOR_TOKEN)
                    .append(entry.getValue()).append(System.lineSeparator());
        }
        output.append(ADD_SPACE_REMINDER + CASE_SENSITIVE_REMINDER + INVALID_SYMBOLS_REMINDER + PRICE_DECIMAL_REMINDER);
        return output.toString();
    }
}
