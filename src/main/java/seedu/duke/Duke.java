package seedu.duke;

import seedu.duke.exception.StoreFailureException;
import seedu.duke.item.ItemList;
import seedu.duke.logger.DukeLogger;
import seedu.duke.storage.StorageManager;
import seedu.duke.transaction.TransactionList;
import seedu.duke.command.Command;
import seedu.duke.parser.CommandParser;
import seedu.duke.ui.Ui;
import seedu.duke.user.UserList;

import static seedu.duke.logger.LoggerMessages.LOG_EXECUTE_SUCCESSFULLY;
import static seedu.duke.logger.LoggerMessages.LOG_EXIT_DUKE;
import static seedu.duke.logger.LoggerMessages.LOG_RUN_DUKE;
import static seedu.duke.storage.FilePath.ITEM_FILE_PATH;
import static seedu.duke.storage.FilePath.TRANSACTION_FILE_PATH;
import static seedu.duke.storage.FilePath.USER_FILE_PATH;

// @@author bdthanh

/**
 * A chatbot named Duke.
 */
public class Duke {
    private UserList userList;
    private ItemList itemList;
    private TransactionList transactionList;
    private final DukeLogger dukeLogger;
    private final StorageManager storageManager;
    private boolean isExit = false;

    /**
     * Constructor of Duke.
     *
     * @param userFilePath        The file path that Duke stores its users.
     * @param itemFilePath        The file path that Duke stores its items.
     * @param transactionFilePath The file path that Duke stores its transactions.
     */
    private Duke(String userFilePath, String itemFilePath, String transactionFilePath) {
        dukeLogger = new DukeLogger();
        storageManager = new StorageManager(userFilePath, itemFilePath, transactionFilePath);
        try {
            StorageManager.checkThreeFilesSimultaneouslyExistOrNotExit();
            userList = storageManager.initializeUserList(userFilePath);
            itemList = storageManager.initializeItemList(itemFilePath, userList);
            transactionList = storageManager.initializeTransactionList(itemFilePath, itemList);
        } catch (StoreFailureException e) {
            isExit = storageManager.handleDataCorruption(e.getMessage(), userList, itemList, transactionList);
        }
    }

    /**
     * Runs the program.
     */
    public void run() {
        if (!isExit) {
            Ui.printGreeting();
            dukeLogger.info(LOG_RUN_DUKE);
        }
        while (!isExit) {
            try {
                String input = Ui.readInput();
                Command command =
                        CommandParser.createCommand(input, userList, itemList, transactionList);
                isExit = command.executeCommand();
                storageManager.writeDataToFile(userList, itemList, transactionList);
                dukeLogger.info(LOG_EXECUTE_SUCCESSFULLY + input);
            } catch (Exception e) {
                Ui.printErrorMessage(e.getMessage());
                dukeLogger.logDukeException(e);
            }
        }
        dukeLogger.info(LOG_EXIT_DUKE);
    }

    /**
     * Main entry-point for the java.duke.Duke application.
     */
    public static void main(String[] args) {
        new Duke(USER_FILE_PATH, ITEM_FILE_PATH, TRANSACTION_FILE_PATH).run();
    }
}
