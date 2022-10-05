package seedu.duke;

import java.io.File;

public class Storage {
    private static final String DATA_FILE_PATH = "data/data.txt";

    public static void createDataFile() {
        File file = new File(DATA_FILE_PATH);
        if (!file.getParentFile().mkdirs()) {
            Ui.printCreateParentFolderErrorText();
        }
        Ui.printFilePath(file, DATA_FILE_PATH);
    }
}
