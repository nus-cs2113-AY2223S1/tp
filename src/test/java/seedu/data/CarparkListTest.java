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
        Assertions.assertEquals(carparkList.findCarpark("3").toString(),
            "CarparkID 3 at Raffles City: 522 lots available");
    }

    @Test
    void findCarparkNoneFound() throws NoFileFoundException, NoCarparkFoundException {
        CarparkList carparkList = new CarparkList(validPathAndFile, validBackupPathAndFile);
        Assertions.assertThrows(NoCarparkFoundException.class, () -> carparkList.findCarpark("50"));
    }

    @Test
    void testToString() throws NoFileFoundException, NoCarparkFoundException {
        CarparkList carparkList = new CarparkList(validPathAndFile, validBackupPathAndFile);
        Assertions.assertEquals("CarparkID 1 at Suntec City: 1882 lots available\n"
                + "CarparkID 2 at Marina Square: 1003 lots available\n"
                + "CarparkID 3 at Raffles City: 522 lots available\n", carparkList.toString());
    }
}
