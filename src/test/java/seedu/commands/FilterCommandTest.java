package seedu.commands;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import seedu.data.CarparkList;
import seedu.exception.FileWriteException;
import seedu.exception.NoCarparkFoundException;
import seedu.exception.NoFileFoundException;
import seedu.parser.search.Sentence;

import java.nio.file.Path;
import java.nio.file.Paths;

public class FilterCommandTest {
    private final String testFileDirectory = "./src/test/java/seedu/testfiles";
    private final Path validPathAndFile = Paths.get(testFileDirectory, "ltaResponse.json");
    private final Path validBackupPathAndFile = Paths.get(testFileDirectory, "ltaResponseBackup.json");

    @Test
    void testFilterCommand() throws FileWriteException, NoFileFoundException {
        CarparkList carparkList = new CarparkList(validPathAndFile, validBackupPathAndFile);
        Sentence searchQuery = new Sentence("sun");
        String result = new FilterCommand(carparkList, searchQuery).execute().showToUser;
        Assertions.assertEquals("CarparkID @|yellow,bold 1|@ at @|bold,cyan Suntec|@ City\n" +
                "@|faint -->|@ @|yellow 1882|@ available lots total", result);
    }

    @Test
    void testNoCarparkFound() throws FileWriteException, NoFileFoundException {
        CarparkList carparkList = new CarparkList(validPathAndFile, validBackupPathAndFile);
        Sentence searchQuery = new Sentence("malaysia");
        String result = new FilterCommand(carparkList, searchQuery).execute().showToUser;
        Assertions.assertEquals("No carpark was found.", result);
    }
}
