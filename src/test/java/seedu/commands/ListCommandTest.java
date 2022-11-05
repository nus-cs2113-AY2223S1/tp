package seedu.commands;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import seedu.data.CarparkList;
import seedu.exception.FileWriteException;
import seedu.exception.NoFileFoundException;

public class ListCommandTest {
    private final String testFileDirectory = "./src/test/java/seedu/testfiles";
    private final Path validPathAndFile = Paths.get(testFileDirectory, "ltaResponse.json");
    private final Path validBackupPathAndFile = Paths.get(testFileDirectory, "ltaResponseBackup.json");

    private final Path invalidPathAndFile = Paths.get(testFileDirectory, "invalid.json");
    private final Path invalidBackupPathAndFile = Paths.get(testFileDirectory, "invalidBackup.json");

    @Test
    void testList() throws FileWriteException, NoFileFoundException {
        CarparkList carparkList = new CarparkList(validPathAndFile, validBackupPathAndFile);
        String result = new ListCommand(carparkList).execute().showToUser;
        Assertions.assertEquals("CarparkID @|yellow,bold 1|@ at Suntec City\n"
                + "@|faint -->|@ @|yellow 1882|@ available lots total\n"
                + "CarparkID @|yellow,bold 2|@ at Marina Square\n"
                + "@|faint -->|@ @|yellow 1003|@ available lots total\n"
                + "CarparkID @|yellow,bold 3|@ at Raffles City\n"
                + "@|faint -->|@ @|yellow 522|@ available lots total", result);
    }

    @Test
    void testListBackup() throws FileWriteException, NoFileFoundException {
        CarparkList carparkList = new CarparkList(invalidPathAndFile, validBackupPathAndFile);
        String result = new ListCommand(carparkList).execute().showToUser;
        Assertions.assertEquals("CarparkID @|yellow,bold 4|@ at Suntec City\n"
                + "@|faint -->|@ @|yellow 1882|@ available lots total", result);
    }
}
