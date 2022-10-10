package seedu.files;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import seedu.common.CommonFiles;
import seedu.data.Carpark;
import seedu.data.CarparkList;
import seedu.exception.NoFileFoundException;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class FileLoaderTest {
    String testFileDirectory = "./src/test/java/seedu/files";
    Path validPathAndFile = Paths.get(testFileDirectory, "ltaResponse.json");
    Path validBackupPathAndFile = Paths.get(testFileDirectory, "ltaResponseSample.json");
    Path invalidPath = Paths.get(testFileDirectory, "invalidpath");
    Path invalidBackupPath = Paths.get(testFileDirectory, "invalidbackuppath");
    Path faultyJsonPath = Paths.get(testFileDirectory, "ltaInvalidResponse.json");

    @Test
    void validLtaJsonFilepathTest() throws NoFileFoundException {
        List<Carpark> validCarparkList = FileLoader.loadLtaJson(validPathAndFile, validBackupPathAndFile);
        Assertions.assertEquals(validCarparkList.get(0).toString(), "CarparkID 1 at Suntec City");
    }


    @Test
    void validBackupLtaJsonFilepathTest() throws NoFileFoundException {
        List<Carpark> validBackupCarparkList = FileLoader.loadLtaJson(invalidPath, validBackupPathAndFile);
        Assertions.assertEquals(validBackupCarparkList.get(0).toString(), "CarparkID 3 at Suntec City");
    }

    @Test
    void invalidFilepathTest() {
        Assertions.assertThrows(FileNotFoundException.class, ()-> FileLoader.loadLtaJson(invalidPath,
                invalidBackupPath));
    }

    @Test
    void invalidJsonFormatTest() {
        Assertions.assertThrows(IOException.class, () -> FileLoader.loadLtaJson(faultyJsonPath,
                faultyJsonPath));
    }
}
