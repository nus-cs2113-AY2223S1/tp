package seedu.files;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import seedu.data.Carpark;
import seedu.exception.NoFileFoundException;

public class FileReaderTest {
    private final String testFileDirectory = "./src/test/java/seedu/testfiles";
    private final Path validPathAndFile = Paths.get(testFileDirectory, "ltaResponse.json");
    private final Path validBackupPathAndFile = Paths.get(testFileDirectory, "ltaResponseBackup.json");
    private final Path invalidPath = Paths.get(testFileDirectory, "invalidpath");
    private final Path invalidBackupPath = Paths.get(testFileDirectory, "invalidbackuppath");
    private final Path faultyJsonPath = Paths.get(testFileDirectory, "ltaInvalidResponse.json");

    @Test
    void validLtaJsonFilepathTest() throws NoFileFoundException {
        List<Carpark> validCarparkList = FileReader.loadLtaJson(validPathAndFile, validBackupPathAndFile);
        Assertions.assertEquals(validCarparkList.get(0).toString(), "CarparkID 1 at Suntec City");
    }


    @Test
    void validBackupLtaJsonFilepathTest() throws NoFileFoundException {
        List<Carpark> validBackupCarparkList = FileReader.loadLtaJson(invalidPath, validBackupPathAndFile);
        Assertions.assertEquals(validBackupCarparkList.get(0).toString(), "CarparkID 4 at Suntec City");
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
}
