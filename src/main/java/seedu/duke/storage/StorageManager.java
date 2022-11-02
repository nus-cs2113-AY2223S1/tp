package seedu.duke.storage;

import seedu.duke.exception.CommandNotFoundException;
import seedu.duke.exception.StoreFailureException;
import seedu.duke.ui.Ui;

import java.io.File;

import static seedu.duke.exception.message.ExceptionMessages.MESSAGE_EXIT_DUKE;
import static seedu.duke.exception.message.ExceptionMessages.MESSAGE_FILES_ILLEGALLY_DELETED;
import static seedu.duke.exception.message.ExceptionMessages.MESSAGE_RESET_DUKE;
import static seedu.duke.exception.message.ExceptionMessages.MESSAGE_TO_FIX_FILES;
import static seedu.duke.exception.message.ExceptionMessages.MESSAGE_YES_OR_NO;

public class StorageManager {
    private static boolean hasItemFile() {
        return new File(FilePath.ITEM_FILE_PATH).exists();
    }

    private static boolean hasUserFile() {
        return new File(FilePath.USER_FILE_PATH).exists();
    }

    private static boolean hasTransactionFile() {
        return new File(FilePath.TRANSACTION_FILE_PATH).exists();
    }

    /**
     * Checks if any file is illegally deleted or not.
     *
     * @throws StoreFailureException If one or two files are deleted
     */
    public static void checkThreeFilesSimultaneouslyExistOrNotExit() throws StoreFailureException {
        if (!((hasUserFile() == hasItemFile()) && (hasTransactionFile() == hasItemFile()))) {
            throw new StoreFailureException(MESSAGE_FILES_ILLEGALLY_DELETED + MESSAGE_TO_FIX_FILES);
        }
    }

    public static boolean checkForForceReset() throws CommandNotFoundException {
        while (true) {
            String input = Ui.readInput();
            switch (input.toLowerCase()) {
            case "y":
                Ui.printResponse(MESSAGE_RESET_DUKE);
                return true;
            case "n":
                Ui.printResponse(MESSAGE_EXIT_DUKE);
                return false;
            default:
                Ui.printResponse(MESSAGE_YES_OR_NO);
            }
        }
    }
}
