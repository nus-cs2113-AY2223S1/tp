package seedu.parser;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import seedu.commands.Command;
import seedu.data.CarparkList;
import seedu.exception.FileWriteException;
import seedu.exception.InvalidCommandException;
import seedu.exception.NoCarparkFoundException;
import seedu.exception.NoFileFoundException;

public class ParserFilterTest {
    private final String testFileDirectory = "./src/test/java/seedu/testfiles";
    private final Path validPathAndFile = Paths.get(testFileDirectory, "ltaResponse.json");
    private final Path validBackupPathAndFile = Paths.get(testFileDirectory, "ltaResponseBackup.json");

    @Test
    void testFilter() throws FileWriteException, NoCarparkFoundException, InvalidCommandException,
            NoFileFoundException {
        CarparkList carparkList = new CarparkList(validPathAndFile, validBackupPathAndFile);
        String input = "filter Sun";
        Command command = new Parser().parseCommand(input, null, carparkList, null);
        String result = command.execute().showToUser;
        Assertions.assertEquals("CarparkID @|yellow,bold 1|@ at @|bold,cyan Suntec|@ City\n"
                + "@|faint -->|@ @|yellow 1882|@ available lots total", result);
    }

    @Test
    void testFilterCaps() throws FileWriteException, NoCarparkFoundException, InvalidCommandException,
            NoFileFoundException {
        String input = "FILTER SUNT";
        CarparkList carparkList = new CarparkList(validPathAndFile, validBackupPathAndFile);
        Command command = new Parser().parseCommand(input, null, carparkList, null);
        String result = command.execute().showToUser;
        Assertions.assertEquals("CarparkID @|yellow,bold 1|@ at @|bold,cyan Suntec|@ City\n"
                + "@|faint -->|@ @|yellow 1882|@ available lots total", result);
    }

    @Test
    void testFilterInvalidDashedArgument() throws FileWriteException, NoCarparkFoundException, InvalidCommandException,
            NoFileFoundException {
        String input = "filter -hello";
        CarparkList carparkList = new CarparkList(validPathAndFile, validBackupPathAndFile);
        Command command = new Parser().parseCommand(input, null, carparkList, null);
        String result = command.execute().showToUser;
        Assertions.assertEquals("Invalid dashed argument.\n"
                + "`filter -address QUERY` or `fil -add QUERY` \t: Filter carparks based on its Carpark address.\n"
                + "`filter -id QUERY` or `fil -id QUERY` \t: Filter carparks based on its Carpark Id.", result);
    }

    @Test
    void testFilterTooManyDashedArgument() throws FileWriteException, NoCarparkFoundException, InvalidCommandException,
            NoFileFoundException {
        String input = "filter -hello -byebye";
        CarparkList carparkList = new CarparkList(validPathAndFile, validBackupPathAndFile);
        Command command = new Parser().parseCommand(input, null, carparkList, null);
        String result = command.execute().showToUser;
        Assertions.assertEquals("This command only takes exactly 1 dashed argument(s). Valid command(s):\n"
                + "`filter QUERY` or `fil QUERY`\t: Filter carparks based on Carpark information.\n"
                + "`filter -address QUERY` or `fil -add QUERY` \t: Filter carparks based on its Carpark address.\n"
                + "`filter -id QUERY` or `fil -id QUERY` \t: Filter carparks based on its Carpark Id.", result);
    }
}
