package seedu.duke.storage;

import seedu.duke.exception.CommandNotFoundException;
import seedu.duke.ui.Ui;

import java.io.File;

import static seedu.duke.exception.message.ExceptionMessages.MESSAGE_EXIT_DUKE;
import static seedu.duke.exception.message.ExceptionMessages.MESSAGE_RESET_DUKE;
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
     * @return true If any
     */
    public static boolean checkThreeFilesSimultaneouslyExistOrNotExit() {
        return (hasUserFile() == hasItemFile()) && (hasTransactionFile() == hasItemFile());
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
