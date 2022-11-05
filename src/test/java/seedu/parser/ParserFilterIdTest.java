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

public class ParserFilterIdTest {
    private final String testFileDirectory = "./src/test/java/seedu/testfiles";
    private final Path validPathAndFile = Paths.get(testFileDirectory, "ltaResponse.json");
    private final Path validBackupPathAndFile = Paths.get(testFileDirectory, "ltaResponseBackup.json");

    @Test
    void testFilterId() throws FileWriteException, NoCarparkFoundException, InvalidCommandException,
            NoFileFoundException {
        CarparkList carparkList = new CarparkList(validPathAndFile, validBackupPathAndFile);
        String input = "filter -id 1";
        Command command = new Parser().parseCommand(input, null, carparkList, null);
        String result = command.execute().showToUser;
        Assertions.assertEquals("CarparkID @|yellow,bold 1|@ at Suntec City\n"
                + "@|faint -->|@ @|yellow 1882|@ available lots total\n", result);
    }

    @Test
    void testFilterIdCaps() throws FileWriteException, NoCarparkFoundException, InvalidCommandException,
            NoFileFoundException {
        String input = "FILTER -ID 1";
        CarparkList carparkList = new CarparkList(validPathAndFile, validBackupPathAndFile);
        Command command = new Parser().parseCommand(input, null, carparkList, null);
        String result = command.execute().showToUser;
        Assertions.assertEquals("CarparkID @|yellow,bold 1|@ at Suntec City\n"
                + "@|faint -->|@ @|yellow 1882|@ available lots total\n", result);
    }

    @Test
    void testFilterIdEmptyArgument() throws FileWriteException, NoCarparkFoundException, InvalidCommandException,
            NoFileFoundException {
        String input = "filter -id";
        CarparkList carparkList = new CarparkList(validPathAndFile, validBackupPathAndFile);
        Command command = new Parser().parseCommand(input, null, carparkList, null);
        String result = command.execute().showToUser;
        Assertions.assertEquals("Empty argument. Valid command(s): \n"
                + "`filter -id QUERY` or `fil -id QUERY` \t: Filter carparks based on its Carpark Id.", result);
    }
}
