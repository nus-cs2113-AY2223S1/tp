package seedu.data;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import seedu.exception.FileWriteException;
import seedu.exception.NoCarparkFoundException;
import seedu.exception.NoFileFoundException;

public class CarparkListTest {
    private final String testFileDirectory = "./src/test/java/seedu/testfiles";
    private final Path validPathAndFile = Paths.get(testFileDirectory, "ltaResponse.json");
    private final Path validBackupPathAndFile = Paths.get(testFileDirectory, "ltaResponseBackup.json");

    @Test
    void findCarparkValidString() throws NoFileFoundException, NoCarparkFoundException, FileWriteException {
        CarparkList carparkList = new CarparkList(validPathAndFile, validBackupPathAndFile);
        Assertions.assertEquals(carparkList.findCarpark("3").toString(),
            "CarparkID 3 at Raffles City: 522 lots available");
    }

    @Test
    void findCarparkNoneFound() throws NoFileFoundException, FileWriteException {
        CarparkList carparkList = new CarparkList(validPathAndFile, validBackupPathAndFile);
        Assertions.assertThrows(NoCarparkFoundException.class, () -> carparkList.findCarpark("50"));
    }

    @Test
    void testToString() throws NoFileFoundException, FileWriteException {
        CarparkList carparkList = new CarparkList(validPathAndFile, validBackupPathAndFile);
        Assertions.assertEquals("CarparkID 1 at Suntec City\n"
            + "   1882 available lots total\n"
            + "CarparkID 2 at Marina Square\n"
            + "   1003 available lots total\n"
            + "CarparkID 3 at Raffles City\n"
            + "   522 available lots total\n", carparkList.toString());
    }
}
