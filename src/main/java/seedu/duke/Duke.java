package seedu.duke;

import seedu.duke.exception.StoreFailureException;
import seedu.duke.exception.TransactionFileNotFoundException;
import seedu.duke.item.ItemList;
import seedu.duke.storage.TransactionStorage;
import seedu.duke.transaction.TransactionList;
import seedu.duke.command.Command;
import seedu.duke.parser.CommandParser;
import seedu.duke.ui.Ui;
import seedu.duke.user.UserList;

import static seedu.duke.storage.FilePath.ITEM_FILE_PATH;
import static seedu.duke.storage.FilePath.TRANSACTION_FILE_PATH;
import static seedu.duke.storage.FilePath.USER_FILE_PATH;

/**
 * A chatbot named Duke.
 */
public class Duke {
    private final UserList userList;
    private final ItemList itemList;
    private TransactionList transactionList;
    private final TransactionStorage transactionStorage;
    private boolean isLastCommand = false;

    /**
     * Constructor of Duke.
     *
     * @param userFilePath The file path that Duke stores its users.
     * @param itemFilePath The file path that Duke stores its items.
     * @param transactionFilePath The file path that Duke stores its transactions.
     */
    public Duke(String userFilePath, String itemFilePath, String transactionFilePath) {
        userList = new UserList();
        itemList = new ItemList();
        // userStorage = new UserStorage(userFilePath);
        // itemStorage = new ItemStorage(itemFilePath);
        transactionStorage = new TransactionStorage(transactionFilePath);
        // initializeItemList();
        // initializeUserList();
        initializeTransactionList();
    }

    /**
     * Initialize transaction list.
     */
    private void initializeTransactionList() {
        try {
            this.transactionList = new TransactionList(transactionStorage.loadData());
        } catch (TransactionFileNotFoundException e) {
            this.transactionList = new TransactionList();
        }
    }

    /**
     * Writes data in 3 list to files.
     *
     * @param isLastCommand A boolean true if the exit command is input
     * @throws StoreFailureException If something went wrong when storing the data
     */
    private void writeDataToFile(boolean isLastCommand) throws StoreFailureException {
        if (isLastCommand) {
            // userStorage.writeData(userList)
            // itemStorage.writeData(itemList)
            transactionStorage.writeData(transactionList);
        }
    }

    /**
     * Runs the program.
     */
    public void run() {
        Ui.printGreeting();
        while (!isLastCommand) {
            try {
                String input = Ui.readInput();
                Command command =
                        CommandParser.createCommand(input, userList, itemList, transactionList);
                isLastCommand = command.executeCommand();
                writeDataToFile(isLastCommand);
            } catch (Exception e) {
                Ui.printErrorMessage(e.getMessage());
            }
        }
    }

    /**
     * Main entry-point for the java.duke.Duke application.
     */
    public static void main(String[] args) {
<<<<<<< HEAD
        new Duke(USER_FILE_PATH, ITEM_FILE_PATH, TRANSACTION_FILE_PATH).run();
=======
        assert false : "dummy assertion set to fail";
        new Duke().run();
>>>>>>> 2e20a09f44da64052d9d9c86615dc38bba01250a
    }
}
