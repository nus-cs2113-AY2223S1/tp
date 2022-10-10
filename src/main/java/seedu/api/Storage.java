package seedu.api;

import seedu.ui.Ui;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Paths;

/**
 * Basic class for data writing.
 */
public class Storage {
    private final String DIRECTORY_PATH;
    private final String FILE_PATH;
    private final File FILE;
    private final Ui ui;

    /**
     * Load the file with the given directory and file path, checks whether it exists.
     *
     * @param directory file directory path
     * @param file file path
     */
    public Storage(String directory, String file) {
        DIRECTORY_PATH = directory;
        FILE_PATH = Paths.get(directory, file).toString();
        FILE = new File(FILE_PATH);
        checkFileExist();
        this.ui = new Ui();
    }

    /**
     * Checks whether file exists in the given directory. Creates one if does not exist.
     */
    private void checkFileExist() {
        if (FILE.exists()) {
            return;
        }
        File directory = new File(DIRECTORY_PATH);
        directory.mkdir();
        try {
            FILE.createNewFile();
        } catch (IOException e) {
            ui.showCreateFileError();
        }
    }

    /**
     * Write string data into the file.
     *
     * @param data String data to write.
     * @throws IOException if file access fails.
     */
    public void writeDataToFile(String data) throws IOException {
        checkFileExist();
        FileWriter fileWriter = new FileWriter(FILE_PATH);
        fileWriter.write(data);
        fileWriter.close();
    }
}
