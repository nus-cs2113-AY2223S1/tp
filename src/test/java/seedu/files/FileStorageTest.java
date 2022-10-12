package seedu.files;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.Scanner;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import seedu.exception.FileWriteException;

public class FileStorageTest {
    private final String testFileDirectoryCreate = "./src/test/java/seedu/testfiles_create";
    private final String testFilePathCreate = "new.json";

    private final String testFileDirectory = "./src/test/java/seedu/testfiles";
    private final String testFileWriteDest = "empty.txt";

    @Test
    public void validFileCreation() {
        FileStorage storage = new FileStorage(testFileDirectoryCreate, testFilePathCreate);
        File fileCheck = new File(Paths.get(testFileDirectoryCreate, testFilePathCreate).toString());
        Assertions.assertTrue(fileCheck.exists());
        fileCheck.delete();
        File fileDirectory = new File(testFileDirectoryCreate);
        fileDirectory.delete();
    }

    @Test
    public void validDataWrite() throws IOException, FileWriteException {
        FileStorage storage = new FileStorage(testFileDirectory, testFileWriteDest);
        storage.writeDataToFile("I am currently writing this data to the file.");
        File file = new File(Paths.get(testFileDirectory, testFileWriteDest).toString());
        Scanner scanner = new Scanner(file);
        if (!scanner.hasNext()) {
            Assertions.fail();
        } else {
            Assertions.assertEquals(scanner.nextLine(), "I am currently writing this data to the file.");
        }
        FileWriter fw = new FileWriter(file);
        fw.write("");
        fw.close();
    }
}
