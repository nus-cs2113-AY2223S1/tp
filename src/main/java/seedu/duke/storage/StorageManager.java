package seedu.duke.storage;

import java.io.File;

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

    public static boolean checkThreeFilesSimultaneouslyExistOrNotExit() {
        return (hasUserFile() == hasItemFile()) && (hasTransactionFile() == hasItemFile());
    }
}
