package seedu.duke;

import seedu.duke.exception.CommandNotFoundException;
import seedu.duke.exception.ItemFileNotFoundException;
import seedu.duke.exception.StoreFailureException;
import seedu.duke.exception.TransactionFileNotFoundException;
import seedu.duke.exception.UserFileNotFoundException;
import seedu.duke.item.ItemList;
import seedu.duke.logger.DukeLogger;
import seedu.duke.storage.ItemStorage;
import seedu.duke.storage.StorageManager;
import seedu.duke.storage.TransactionStorage;
import seedu.duke.storage.UserStorage;
import seedu.duke.transaction.TransactionList;
import seedu.duke.command.Command;
import seedu.duke.parser.CommandParser;
import seedu.duke.ui.Ui;
import seedu.duke.user.UserList;

import static seedu.duke.exception.message.ExceptionMessages.MESSAGE_FILES_ILLEGALLY_DELETED;
import static seedu.duke.exception.message.ExceptionMessages.MESSAGE_TO_FIX_FILES;
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
    private TransactionStorage transactionStorage;
    private ItemStorage itemStorage;
    private UserStorage userStorage;
    private final DukeLogger dukeLogger;
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
        userStorage = new UserStorage(userFilePath);
        itemStorage = new ItemStorage(itemFilePath, userList);
        transactionStorage = new TransactionStorage(transactionFilePath, itemList);
        try {
            checkIfThreeFilesSimultaneouslyExistOrNotExit();
            initializeUserList(userFilePath);
            initializeItemList(itemFilePath);
            initializeTransactionList(transactionFilePath);
        } catch (StoreFailureException e) {
            handleDataCorruption(e.getMessage());
        }
    }

    private void initializeUserList(String userFilePath) throws StoreFailureException {
        try {
            userStorage = new UserStorage(userFilePath);
            this.userList = userStorage.loadData();
        } catch (UserFileNotFoundException e) {
            this.userList = new UserList();
        }
    }

    private void initializeItemList(String itemFilePath) throws StoreFailureException {
        try {
            itemStorage = new ItemStorage(itemFilePath, userList);
            this.itemList = itemStorage.loadData();
        } catch (ItemFileNotFoundException e) {
            this.itemList = new ItemList();
        }
    }

    /**
     * Initialize transaction list.
     */
    private void initializeTransactionList(String transactionFilePath)
            throws StoreFailureException {
        try {
            transactionStorage = new TransactionStorage(transactionFilePath, itemList);
            this.transactionList = transactionStorage.loadData();
        } catch (TransactionFileNotFoundException e) {
            this.transactionList = new TransactionList();
        }
    }

    private void handleDataCorruption(String errorMessage) {
        Ui.printErrorMessage(errorMessage);
        try {
            if (StorageManager.checkForForceReset()) {
                forceReset();
            }
            isExit = true;
        } catch (CommandNotFoundException | StoreFailureException e) {
            Ui.printResponse(e.getMessage());
        }
    }

    private void forceReset() throws StoreFailureException {
        this.userList = new UserList();
        this.itemList = new ItemList();
        this.transactionList = new TransactionList();
        writeDataToFile();
    }

    /**
     * Writes data in 3 list to files.
     *
     * @throws StoreFailureException If something went wrong when storing the data
     */
    private void writeDataToFile() throws StoreFailureException {
        userStorage.writeData(userList);
        itemStorage.writeData(itemList);
        transactionStorage.writeData(transactionList);
    }

    private void checkIfThreeFilesSimultaneouslyExistOrNotExit() throws StoreFailureException {
        boolean areSimultaneouslyExistOrNotExist
                = StorageManager.checkThreeFilesSimultaneouslyExistOrNotExit();
        if (!areSimultaneouslyExistOrNotExist) {
            throw new StoreFailureException(MESSAGE_FILES_ILLEGALLY_DELETED + MESSAGE_TO_FIX_FILES);
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
                writeDataToFile();
                dukeLogger.info(LOG_EXECUTE_SUCCESSFULLY + input);
            } catch (Exception e) {
                Ui.printErrorMessage(e.getMessage());
                dukeLogger.warning(e.getMessage());
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
