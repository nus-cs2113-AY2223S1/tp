package seedu.files;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Paths;

import seedu.exception.FileWriteException;
import seedu.ui.Ui;

/**
 * Class to deal with writing to files.
 */
public class FileStorage {
    private final String directoryPath;
    private final String filePath;
    private final File file;
    private final Ui ui;

    /**
     * TODO: Javadoc comment.
     * @param directory
     * @param file
     */
    public FileStorage(String directory, String file) {
        directoryPath = directory;
        filePath = Paths.get(directory, file).toString();
        this.file = new File(filePath);
        this.ui = new Ui();
        checkFileExist();
    }

    private void checkFileExist() {
        if (file.exists()) {
            return;
        }
        File directory = new File(directoryPath);
        directory.mkdirs();
        try {
            file.createNewFile();
        } catch (IOException e) {
            ui.showCreateFileError();
        }
    }

    /**
     * Writes data to a txt file in plain text.
     * @param data data to be written.
     * @throws FileWriteException If the file cannot be written to.
     */
    public void writeDataToFile(String data) throws FileWriteException {
        try {
            checkFileExist();
            FileWriter fileWriter = new FileWriter(filePath);
            fileWriter.write(data);
            fileWriter.close();
        } catch (IOException e){
            throw new FileWriteException(filePath);
        }

    }
}
