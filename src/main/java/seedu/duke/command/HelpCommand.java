package seedu.duke.command;

import seedu.duke.Ui;

import java.util.Map;

public class HelpCommand extends Command {
    private static final String COMMAND_HELP = "help";
    private static final String COMMAND_LIST_USERS = "list-users";
    private static final String COMMAND_LIST_ITEMS = "list-items";
    private static final String COMMAND_LIST_TX = "list-tx";
    private static final String COMMAND_VIEW_USER = "view-user /u <name>";
    private static final String COMMAND_VIEW_ITEM = "view-item /i <name>";
    private static final String COMMAND_VIEW_TX = "view-tx /t <transactionId>";
    private static final String COMMAND_ADD_USER = "add-user /n <name> /a <age> /c <contactNumber>";
    private static final String COMMAND_ADD_ITEM = "add-item /n <name> /c <categoryNumber> /p <price> /o <ownerName>";
    private static final String COMMAND_ADD_TX = "add-tx /i <itemName> /b <borrowerName> /d <duration> /c <createdAt>";
    private static final String COMMAND_REMOVE_USER = "remove-user /u <name>";
    private static final String COMMAND_REMOVE_ITEM = "remove-item /i <name>";
    private static final String COMMAND_REMOVE_TX = "remove-tx /t <transactionId>";
    private static final Map<String, String> commandToDetailMap = Map.ofEntries(
            Map.entry("List all commands", COMMAND_HELP),
            Map.entry("List all users", COMMAND_LIST_USERS),
            Map.entry("List all items", COMMAND_LIST_ITEMS),
            Map.entry("List all transactions", COMMAND_LIST_TX),
            Map.entry("View a user", COMMAND_VIEW_USER),
            Map.entry("View a item", COMMAND_VIEW_ITEM),
            Map.entry("View a transaction", COMMAND_VIEW_TX),
            Map.entry("Add a user", COMMAND_ADD_USER),
            Map.entry("Add a item", COMMAND_ADD_ITEM),
            Map.entry("Add a transaction", COMMAND_ADD_TX),
            Map.entry("Remove a user", COMMAND_REMOVE_USER),
            Map.entry("Remove a item", COMMAND_REMOVE_ITEM),
            Map.entry("Remove a transaction", COMMAND_REMOVE_TX));

    public boolean executeCommand() {
        Ui.showLine();
        for (Map.Entry<String, String> entry : commandToDetailMap.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
        Ui.showLine();
        return false;
    }
}
