package seedu.api;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Paths;

public class Storage {
    private final String DIRECTORY_PATH;
    private final String FILE_PATH;
    private final File FILE;

    public Storage(String directory, String file) {
        DIRECTORY_PATH = directory;
        FILE_PATH = Paths.get(directory, file).toString();
        FILE = new File(FILE_PATH);
        checkFileExist();
    }

    private void checkFileExist() {
        if (FILE.exists()) {
            return;
        }
        File directory = new File(DIRECTORY_PATH);
        directory.mkdir();
        try {
            FILE.createNewFile();
        } catch (IOException e) {
            System.out.println("Something wrong happened in file creation.");
        }
    }

    public void writeDataToFile(String data) throws IOException {
        checkFileExist();
        FileWriter fileWriter = new FileWriter(FILE_PATH);
        fileWriter.write(data);
        fileWriter.close();
    }
}
