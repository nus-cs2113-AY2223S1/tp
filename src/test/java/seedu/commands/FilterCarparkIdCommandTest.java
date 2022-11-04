package seedu.commands;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import seedu.data.CarparkList;
import seedu.exception.FileWriteException;
import seedu.exception.InvalidCommandException;
import seedu.exception.NoCarparkFoundException;
import seedu.exception.NoFileFoundException;
import seedu.parser.Parser;
import seedu.parser.search.Sentence;


public class FilterCarparkIdCommandTest {
    private final String testFileDirectory = "./src/test/java/seedu/testfiles";
    private final Path validPathAndFile = Paths.get(testFileDirectory, "ltaResponse.json");
    private final Path validBackupPathAndFile = Paths.get(testFileDirectory, "ltaResponseBackup.json");

    @Test
    void testFilterAddressCommand() throws FileWriteException, NoFileFoundException {
        CarparkList carparkList = new CarparkList(validPathAndFile, validBackupPathAndFile);
        Sentence searchQuery = new Sentence("1");
        String result = carparkList.filterByCarparkId(searchQuery).toString();
        Assertions.assertEquals("CarparkID @|yellow,bold 1|@ at Suntec City\n"
                + "@|faint -->|@ @|yellow 1882|@ available lots total\n", result);
    }

    @Test
    void testEmptyArgument() throws FileWriteException, NoFileFoundException, NoCarparkFoundException, InvalidCommandException {
        CarparkList carparkList = new CarparkList(validPathAndFile, validBackupPathAndFile);
        String input = "filter -id";
        Command command = new Parser().parseCommand(input, null, carparkList, null);
        String result = command.execute().showToUser;
        Assertions.assertEquals("Empty argument. Valid command(s): \n"
                + "`filter -id QUERY` or `fil -id QUERY` \t: Filter carparks based on its Carpark Id.", result);
    }
    @Test
    void testNoCarparkFound() throws FileWriteException, NoFileFoundException, NoCarparkFoundException {
        CarparkList carparkList = new CarparkList(validPathAndFile, validBackupPathAndFile);
        Sentence searchQuery = new Sentence("999");
        String result = new FilterCarparkIdCommand(carparkList, searchQuery).execute().showToUser;
        Assertions.assertEquals("No carpark was found.", result);
    }

}
