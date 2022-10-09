package seedu.duke;

import seedu.duke.item.Item;
import seedu.duke.item.ItemList;
import seedu.duke.transaction.TransactionList;
import seedu.duke.command.Command;
import seedu.duke.parser.CommandParser;
import seedu.duke.user.UserList;

public class Duke {

    /**
     * Main entry-point for the java.duke.Duke application.
     */
    public static void main(String[] args) {
        Ui.printGreeting();
        UserList userList = new UserList();
        ItemList itemList = new ItemList();
        TransactionList txList = new TransactionList();
        String input = Ui.readInput();
        Command command;
        boolean isLastCommand = false;
//         maintain conversation
        while (true) {
            try {
                command = CommandParser.createCommand(input, userList, itemList, txList);
                isLastCommand = command.executeCommand();
            } catch (Exception e) {
                // ExceptionManager.handleException(e);
            } finally {
                if (isLastCommand) {
                    break;
                }
                input = Ui.readInput();
            }
        }

        Ui.exit();
    }
}
