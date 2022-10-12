package seedu.duke;

import seedu.duke.item.ItemList;
import seedu.duke.transaction.TransactionList;
import seedu.duke.command.Command;
import seedu.duke.parser.CommandParser;
import seedu.duke.ui.Ui;
import seedu.duke.user.UserList;

public class Duke {
    private final UserList userList;
    private final ItemList itemList;
    private final TransactionList transactionList;
    private boolean isLastCommand = false;

    public Duke() {
        userList = new UserList();
        itemList = new ItemList();
        transactionList = new TransactionList();
    }

    public void run() {
        Ui.printGreeting();
        while (!isLastCommand) {
            try {
                String input = Ui.readInput();
                Command command = CommandParser.createCommand(input, userList, itemList, transactionList);
                isLastCommand = command.executeCommand();
            } catch (Exception e) {
                Ui.printErrorMessage(e.getMessage());
            }
        }
    }

    /**
     * Main entry-point for the java.duke.Duke application.
     */
    public static void main(String[] args) {
        new Duke().run();
    }
}
