package seedu.duke;

import seedu.duke.exception.DukeException;
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
            StorageManager.checkThreeFilesSimultaneouslyExistOrNotExist();
            userList = storageManager.initializeUserList(userFilePath);
            itemList = storageManager.initializeItemList(itemFilePath, userList);
            transactionList = storageManager.initializeTransactionList(transactionFilePath, userList, itemList);
        } catch (StoreFailureException e) {
            isExit = storageManager.handleDataCorruption(e.getMessage());
        }
    }

    /**
     * Main activity of Duke: reads input from user and parses command,
     * and execute it.
     *
     * @throws DukeException If there is any error
     */
    private void readInputAndParseCommand() throws DukeException {
        String input = Ui.readInput();
        Command command =
                CommandParser.createCommand(input, userList, itemList, transactionList);
        isExit = command.executeCommand();
        storageManager.writeDataToFiles(userList, itemList, transactionList);
        dukeLogger.info(LOG_EXECUTE_SUCCESSFULLY + input);
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
                readInputAndParseCommand();
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
