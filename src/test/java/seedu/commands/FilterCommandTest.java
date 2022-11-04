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

public class FilterCommandTest {
    private final String testFileDirectory = "./src/test/java/seedu/testfiles";
    private final Path validPathAndFile = Paths.get(testFileDirectory, "ltaResponse.json");
    private final Path validBackupPathAndFile = Paths.get(testFileDirectory, "ltaResponseBackup.json");

    @Test
    void testFilterCommand() throws FileWriteException, NoFileFoundException {
        CarparkList carparkList = new CarparkList(validPathAndFile, validBackupPathAndFile);
        Sentence searchQuery = new Sentence("sun");
        String result = new FilterCommand(carparkList, searchQuery).execute().showToUser;
        Assertions.assertEquals("CarparkID @|yellow,bold 1|@ at @|bold,cyan Suntec|@ City\n"
                + "@|faint -->|@ @|yellow 1882|@ available lots total", result);
    }

    @Test
    void testEmptyArgument() throws FileWriteException, NoFileFoundException, NoCarparkFoundException, InvalidCommandException {
        CarparkList carparkList = new CarparkList(validPathAndFile, validBackupPathAndFile);
        String input = "filter ";
        Command command = new Parser().parseCommand(input, null, carparkList, null);
        String result = command.execute().showToUser;
        Assertions.assertEquals("Empty argument. Valid command(s): \n"
                + "`filter QUERY` or `fil QUERY`\t: Filter carparks based on Carpark information.", result);
    }

    @Test
    void testInvalidDashedArgument() throws FileWriteException, NoFileFoundException, NoCarparkFoundException,
            InvalidCommandException {
        CarparkList carparkList = new CarparkList(validPathAndFile, validBackupPathAndFile);
        String input = "filter -hello";
        Command command = new Parser().parseCommand(input, null, carparkList, null);
        String result = command.execute().showToUser;
        Assertions.assertEquals("Invalid dashed argument.\n"
                + "`filter -address QUERY` or `fil -add QUERY` \t: Filter carparks based on its Carpark address.\n"
                + "`filter -id QUERY` or `fil -id QUERY` \t: Filter carparks based on its Carpark Id.", result);
    }

    @Test
    void testTooManyDashedArgument() throws FileWriteException, NoFileFoundException, NoCarparkFoundException,
            InvalidCommandException {
        CarparkList carparkList = new CarparkList(validPathAndFile, validBackupPathAndFile);
        String input = "filter -hello -byebye";
        Command command = new Parser().parseCommand(input, null, carparkList, null);
        String result = command.execute().showToUser;
        Assertions.assertEquals("This command only takes exactly 1 dashed argument(s). Valid command(s):\n"
                + "`filter QUERY` or `fil QUERY`\t: Filter carparks based on Carpark information.\n"
                + "`filter -address QUERY` or `fil -add QUERY` \t: Filter carparks based on its Carpark address.\n"
                + "`filter -id QUERY` or `fil -id QUERY` \t: Filter carparks based on its Carpark Id.", result);
    }

    @Test
    void testNoCarparkFound() throws FileWriteException, NoFileFoundException {
        CarparkList carparkList = new CarparkList(validPathAndFile, validBackupPathAndFile);
        Sentence searchQuery = new Sentence("malaysia");
        String result = new FilterCommand(carparkList, searchQuery).execute().showToUser;
        Assertions.assertEquals("No carpark was found.", result);
    }
}
