package seedu.duke;

import seedu.duke.exception.ItemFileNotFoundException;
import seedu.duke.exception.StoreFailureException;
import seedu.duke.exception.TransactionFileNotFoundException;
import seedu.duke.exception.UserFileNotFoundException;
import seedu.duke.item.ItemList;
import seedu.duke.storage.ItemStorage;
import seedu.duke.storage.TransactionStorage;
import seedu.duke.storage.UserStorage;
import seedu.duke.transaction.TransactionList;
import seedu.duke.command.Command;
import seedu.duke.parser.CommandParser;
import seedu.duke.ui.Ui;
import seedu.duke.user.UserList;

import static seedu.duke.exception.message.ExceptionMessages.MESSAGE_FILES_ILLEGALLY_DELETED;
import static seedu.duke.exception.message.ExceptionMessages.MESSAGE_TO_FIX_FILES;
import static seedu.duke.storage.FilePath.ITEM_FILE_PATH;
import static seedu.duke.storage.FilePath.TRANSACTION_FILE_PATH;
import static seedu.duke.storage.FilePath.USER_FILE_PATH;

//@@author bdthanh

/**
 * A chatbot named Duke.
 */
public class Duke {
    private UserList userList;
    private ItemList itemList;
    private TransactionList transactionList;
    private final TransactionStorage transactionStorage;
    private final ItemStorage itemStorage;
    private final UserStorage userStorage;
    private boolean isLastCommand = false;

    /**
     * Constructor of Duke.
     *
     * @param userFilePath        The file path that Duke stores its users.
     * @param itemFilePath        The file path that Duke stores its items.
     * @param transactionFilePath The file path that Duke stores its transactions.
     */
    private Duke(String userFilePath, String itemFilePath, String transactionFilePath) {
        userStorage = new UserStorage(userFilePath);
        itemStorage = new ItemStorage(itemFilePath);
        transactionStorage = new TransactionStorage(transactionFilePath);
        try {
            checkIfThreeFilesSimultaneouslyExistOrNotExit();
            initializeUserList();
            initializeTransactionList();
            initializeItemList();
        } catch (StoreFailureException e) {
            exitDukeDueToDataCorruption(e.getMessage());
        }
    }

    /**
     * Initialize transaction list.
     */
    private void initializeTransactionList() throws StoreFailureException {
        try {
            this.transactionList = new TransactionList(transactionStorage.loadData());
        } catch (TransactionFileNotFoundException e) {
            this.transactionList = new TransactionList();
        }
    }

    private void initializeItemList() throws StoreFailureException {
        try {
            this.itemList = new ItemList(itemStorage.loadData());
        } catch (ItemFileNotFoundException e) {
            this.itemList = new ItemList();
        }
    }

    private void initializeUserList() throws StoreFailureException {
        try {
            this.userList = new UserList(userStorage.loadData());
        } catch (UserFileNotFoundException e) {
            this.userList = new UserList();
        }
    }

    private void exitDukeDueToDataCorruption(String errorMessage) {
        Ui.printErrorMessage(errorMessage);
        isLastCommand = true;
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
        boolean areSimultaneouslyExistOrNotExist = (userStorage.hasUserFile() == itemStorage.hasItemFile())
                && (transactionStorage.hasTransactionFile() == itemStorage.hasItemFile());
        if (!areSimultaneouslyExistOrNotExist) {
            throw new StoreFailureException(MESSAGE_FILES_ILLEGALLY_DELETED + MESSAGE_TO_FIX_FILES);
        }
    }

    /**
     * Runs the program.
     */
    public void run() {
        if (!isLastCommand) {
            Ui.printGreeting();
        }
        while (!isLastCommand) {
            try {
                String input = Ui.readInput();
                Command command =
                        CommandParser.createCommand(input, userList, itemList, transactionList);
                isLastCommand = command.executeCommand();
                writeDataToFile();
            } catch (Exception e) {
                Ui.printErrorMessage(e.getMessage());
            }
        }
    }

    /**
     * Main entry-point for the java.duke.Duke application.
     */
    public static void main(String[] args) {
        new Duke(USER_FILE_PATH, ITEM_FILE_PATH, TRANSACTION_FILE_PATH).run();
    }
}
