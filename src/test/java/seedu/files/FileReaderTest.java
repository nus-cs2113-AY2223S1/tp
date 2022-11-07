package seedu.files;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import seedu.data.Carpark;
import seedu.exception.FileWriteException;
import seedu.exception.NoFileFoundException;

public class FileReaderTest {
    private final String testFileDirectory = "./src/test/java/seedu/testfiles";
    private final Path validPathAndFile = Paths.get(testFileDirectory, "ltaResponse.json");
    private final Path validBackupPathAndFile = Paths.get(testFileDirectory, "ltaResponseBackup.json");
    private final Path invalidPath = Paths.get(testFileDirectory, "invalidpath");
    private final Path invalidBackupPath = Paths.get(testFileDirectory, "invalidbackuppath");
    private final Path faultyJsonPath = Paths.get(testFileDirectory, "ltaInvalidResponse.json");

    @Test
    void validLtaJsonFilepathTest() throws NoFileFoundException, FileWriteException {
        List<Carpark> validCarparkList = FileReader.loadLtaJson(validPathAndFile, validBackupPathAndFile);
        Assertions.assertEquals("CarparkID 1 at Suntec City: 1882 lots available", validCarparkList.get(0).toString());
    }

    @Test
    void validBackupLtaJsonFilepathTest() throws NoFileFoundException, FileWriteException {
        List<Carpark> validBackupCarparkList = FileReader.loadLtaJson(invalidPath, validBackupPathAndFile);
        Assertions.assertEquals("CarparkID 4 at Suntec City: 1882 lots available",
                validBackupCarparkList.get(0).toString());
    }

    @Test
    void invalidFilepathTest() {
        Assertions.assertThrows(NoFileFoundException.class, () -> FileReader.loadLtaJson(invalidPath,
            invalidBackupPath));
    }

    @Test
    void invalidJsonFormatTest() {
        Assertions.assertThrows(NoFileFoundException.class, () -> FileReader.loadLtaJson(faultyJsonPath,
            faultyJsonPath));
    }

    @Test
    void invalidReadStringFromTextTest() {
        Assertions.assertThrows(NoFileFoundException.class, () -> FileReader.readStringFromTxt("invalidPath",
                testFileDirectory, false));
    }
    @Test
    void validReadStringFromTextTest() throws IOException, NoFileFoundException, FileWriteException {
        Assertions.assertEquals("This is a valid read.\n",
                FileReader.readStringFromTxt("verification.txt",
                testFileDirectory, false));
    }

    @Test
    void emptyReadStringFromTextTest() throws IOException, NoFileFoundException, FileWriteException {
        Assertions.assertEquals("", FileReader.readStringFromTxt("empty.txt",
                testFileDirectory, false));
    }
}
