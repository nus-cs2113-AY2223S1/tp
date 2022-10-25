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
    private static final String COMMAND_HELP = "help";
    private static final String COMMAND_LIST_USERS = "list-users";
    private static final String COMMAND_LIST_ITEMS = "list-items";
    private static final String COMMAND_LIST_TX = "list-tx";
    private static final String COMMAND_VIEW_USER = "view-user /u <userName>";
    private static final String COMMAND_VIEW_ITEM = "view-item /i <itemId>";
    private static final String COMMAND_VIEW_TX = "view-tx /t <transactionId>";
    private static final String COMMAND_ADD_USER =
            "add-user /n <userName> /a <age> /c <contactNumber>";
    private static final String COMMAND_ADD_ITEM =
            "add-item /n <itemName> /c <categoryIndex> /p <price> /o <userName>";
    private static final String COMMAND_ADD_TX =
            "add-tx /i <itemId> /b <borrowerName> /d <duration> /c <createdAt>";
    private static final String COMMAND_REMOVE_USER = "remove-user /u <userName>";
    private static final String COMMAND_REMOVE_ITEM = "remove-item /i <itemId>";
    private static final String COMMAND_REMOVE_TX = "remove-tx /t <transactionId>";
    private static final String COMMAND_FIND_FINISHED_TX = "find-tx /s finished";
    private static final String COMMAND_FIND_UNFINISHED_TX = "find-tx /s unfinished";
    private static final String COMMAND_SORT_ITEM =
            "sort-items /mode <mode: hl or  lh> /min <min> /max <max> /cat <categoryIndex>";
    private static final String COMMAND_LIST_CATEGORIES = "list-categories";
    private static final String COMMAND_FIND_USER = "find-user /k <keyword>";
    private static final String COMMAND_FIND_ITEM = "find-item /k <keyword>";
    private static final String COMMAND_UPDATE_ITEM = "update-item /i <itemId> /p <price>";
    private static final String COMMAND_UPDATE_TX = "update-tx /t <transactionId> /d <duration>";
    private static final String UNDERLINE = System.lineSeparator() + "--------------------";
    private final LinkedHashMap<String, String> commandToDetailMap;

    public HelpCommand() {
        this.commandToDetailMap = new LinkedHashMap<>();
        this.commandToDetailMap.put(System.lineSeparator() + "ADD-RELATED-COMMANDS", UNDERLINE);
        this.commandToDetailMap.put("Add a user", COMMAND_ADD_USER);
        this.commandToDetailMap.put("Add a item", COMMAND_ADD_ITEM);
        this.commandToDetailMap.put("Add a transaction", COMMAND_ADD_TX);
        this.commandToDetailMap.put(System.lineSeparator() + "REMOVE-RELATED-COMMANDS", UNDERLINE);
        this.commandToDetailMap.put("Remove a user", COMMAND_REMOVE_USER);
        this.commandToDetailMap.put("Remove a item", COMMAND_REMOVE_ITEM);
        this.commandToDetailMap.put("Remove a transaction", COMMAND_REMOVE_TX);
        this.commandToDetailMap.put(System.lineSeparator() + "LIST-RELATED-COMMANDS", UNDERLINE);
        this.commandToDetailMap.put("List all commands", COMMAND_HELP);
        this.commandToDetailMap.put("List all users", COMMAND_LIST_USERS);
        this.commandToDetailMap.put("List all items", COMMAND_LIST_ITEMS);
        this.commandToDetailMap.put("List all categories available", COMMAND_LIST_CATEGORIES);
        this.commandToDetailMap.put("List all transactions", COMMAND_LIST_TX);
        this.commandToDetailMap.put(System.lineSeparator() + "VIEW-RELATED-COMMANDS", UNDERLINE);
        this.commandToDetailMap.put("View a user", COMMAND_VIEW_USER);
        this.commandToDetailMap.put("View a item", COMMAND_VIEW_ITEM);
        this.commandToDetailMap.put("View a transaction", COMMAND_VIEW_TX);
        this.commandToDetailMap.put(System.lineSeparator() + "UPDATE-RELATED-COMMANDS", UNDERLINE);
        this.commandToDetailMap.put("Update price of an item", COMMAND_UPDATE_ITEM);
        this.commandToDetailMap.put("Update duration of a transaction", COMMAND_UPDATE_TX);
        this.commandToDetailMap.put(System.lineSeparator() + "FIND-RELATED-COMMANDS", UNDERLINE);
        this.commandToDetailMap.put("Find all finished transactions", COMMAND_FIND_FINISHED_TX);
        this.commandToDetailMap.put("Find all unfinished transactions", COMMAND_FIND_UNFINISHED_TX);
        this.commandToDetailMap.put("Find user by keywords", COMMAND_FIND_USER);
        this.commandToDetailMap.put("Find item by keywords", COMMAND_FIND_ITEM);
        this.commandToDetailMap.put("Sort all items in a range", COMMAND_SORT_ITEM);
    }

    /**
     * Execute HelpCommand (print all available commands).
     *
     * @return false
     */
    public boolean executeCommand() {
        Ui.showLine();
        for (Map.Entry<String, String> entry : commandToDetailMap.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
        Ui.showLine();
        return false;
    }
}
