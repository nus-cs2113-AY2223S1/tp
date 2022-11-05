package seedu.commands;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import commands.FilterAddressCommand;
import seedu.data.CarparkList;
import seedu.exception.FileWriteException;
import seedu.exception.NoCarparkFoundException;
import seedu.exception.NoFileFoundException;
import seedu.parser.search.Sentence;

public class FilterAddressCommandTest {
    private final String testFileDirectory = "./src/test/java/seedu/testfiles";
    private final Path validPathAndFile = Paths.get(testFileDirectory, "ltaResponse.json");
    private final Path validBackupPathAndFile = Paths.get(testFileDirectory, "ltaResponseBackup.json");

    @Test
    void testFilterAddressCommand() throws FileWriteException, NoFileFoundException {
        CarparkList carparkList = new CarparkList(validPathAndFile, validBackupPathAndFile);
        Sentence searchQuery = new Sentence("raffles");
        String result = carparkList.filterByAddress(searchQuery).toString();
        Assertions.assertEquals("CarparkID @|yellow,bold 3|@ at @|bold,cyan Raffles|@ City\n"
                + "@|faint -->|@ @|yellow 522|@ available lots total\n", result);
    }

    @Test
    void testNoCarparkFound() throws FileWriteException, NoFileFoundException, NoCarparkFoundException {
        CarparkList carparkList = new CarparkList(validPathAndFile, validBackupPathAndFile);
        Sentence searchQuery = new Sentence("malaysia");
        String result = new FilterAddressCommand(carparkList, searchQuery).execute().showToUser;
        Assertions.assertEquals("No carpark was found.", result);
    }
}
