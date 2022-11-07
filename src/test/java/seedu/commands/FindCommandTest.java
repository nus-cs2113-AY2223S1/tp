package seedu.commands;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import seedu.data.Carpark;
import seedu.data.CarparkList;
import seedu.exception.FileWriteException;
import seedu.exception.NoCarparkFoundException;
import seedu.exception.NoFileFoundException;

public class FindCommandTest {
    private final String testFileDirectory = "./src/test/java/seedu/testfiles";
    private final Path validPathAndFile = Paths.get(testFileDirectory, "ltaResponse.json");
    private final Path validBackupPathAndFile = Paths.get(testFileDirectory, "ltaResponseBackup.json");

    @Test
    void testFindCommand() throws FileWriteException, NoFileFoundException, NoCarparkFoundException {
        CarparkList carparkList = new CarparkList(validPathAndFile, validBackupPathAndFile);
        Carpark result = new FindCommand("3", carparkList).findThisCarpark("3");
        String resultString = result.toString();
        Assertions.assertEquals("CarparkID 3 at Raffles City: 522 lots available", resultString);
    }

    @Test
    void testNoCarparkFound() throws FileWriteException, NoFileFoundException {
        CarparkList carparkList = new CarparkList(validPathAndFile, validBackupPathAndFile);
        Assertions.assertThrows(NoCarparkFoundException.class, () -> {
            new FindCommand("ZZ", carparkList).execute();
        });
    }
}
