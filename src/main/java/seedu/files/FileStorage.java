package seedu.files;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import seedu.common.CommonFiles;
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
     *
     * @param directory
     * @param file
     */
    public FileStorage(String directory, String file) {
        directoryPath = directory;
        filePath = Paths.get(directory, file).toString();
        this.file = new File(filePath);
        this.ui = new Ui();
        checkFileExists();
    }

    private void checkFileExists() {
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
     *
     * @param data data to be written.
     * @throws FileWriteException If the file cannot be written to.
     */
    public void writeDataToFile(String data) throws FileWriteException {
        try {
            checkFileExists();
            FileWriter fileWriter = new FileWriter(filePath);
            fileWriter.write(data);
            fileWriter.close();
        } catch (IOException e) {
            throw new FileWriteException(filePath);
        }
    }

    /**
     * Copies backup from the internal .jar to an external directory.
     */
    //@@author GOXR3PLUS-reused
    // Reused from
    // https://stackoverflow.com/questions/10308221/how-to-copy-file-inside-jar-to-outside-the-jar/44077426#44077426
    // with minor modifications
    public static void ensureBackup() throws FileWriteException {
        URL url = FileStorage.class.getResource("/ltaResponseSample.json");

        Path backupPath = CommonFiles.LTA_BACKUP_FILE_PATH;
        try {
            InputStream source = url.openStream();
            Files.copy(source, backupPath, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException ex) {
            throw new FileWriteException(backupPath.toString());
        }
    }
    //@@author
}
