package seedu.data;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import seedu.exception.NoCarparkFoundException;
import seedu.exception.NoFileFoundException;

public class CarparkListTest {
    private final String testFileDirectory = "./src/test/java/seedu/testfiles";
    private final Path validPathAndFile = Paths.get(testFileDirectory, "ltaResponse.json");
    private final Path validBackupPathAndFile = Paths.get(testFileDirectory, "ltaResponseBackup.json");

    @Test
    void findCarparkValidString() throws NoFileFoundException, NoCarparkFoundException {
        CarparkList carparkList = new CarparkList(validPathAndFile, validBackupPathAndFile);
        Assertions.assertEquals(carparkList.findCarpark("2").toString(),
            "CarparkID 2 at Marina Square");
    }

    @Test
    void findCarparkNoneFound() throws NoFileFoundException, NoCarparkFoundException {
        CarparkList carparkList = new CarparkList(validPathAndFile, validBackupPathAndFile);
        Assertions.assertThrows(NoCarparkFoundException.class, () -> carparkList.findCarpark("10"));
    }
}
