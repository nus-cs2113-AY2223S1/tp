package seedu.commands;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import seedu.data.CarparkList;
import seedu.exception.FileWriteException;
import seedu.exception.InvalidCommandException;
import seedu.exception.NoCarparkFoundException;
import seedu.exception.NoFileFoundException;
import seedu.parser.Parser;

import java.nio.file.Path;
import java.nio.file.Paths;

public class ListCommandTest {
    private final String testFileDirectory = "./src/test/java/seedu/testfiles";
    private final Path validPathAndFile = Paths.get(testFileDirectory, "ltaResponse.json");
    private final Path validBackupPathAndFile = Paths.get(testFileDirectory, "ltaResponseBackup.json");

    @Test
    void testHelp() throws FileWriteException, NoCarparkFoundException, InvalidCommandException, NoFileFoundException {
        CarparkList carparkList = new CarparkList(validPathAndFile, validBackupPathAndFile);
        String input = "list";
        Command command = new Parser().parseCommand(input, null, carparkList, null);
        String result = command.execute().showToUser;
        Assertions.assertEquals("CarparkID @|yellow,bold 1|@ at Suntec City\n"
                + "@|faint -->|@ @|yellow 1882|@ available lots total\n"
                + "CarparkID @|yellow,bold 2|@ at Marina Square\n"
                + "@|faint -->|@ @|yellow 1003|@ available lots total\n"
                + "CarparkID @|yellow,bold 3|@ at Raffles City\n"
                + "@|faint -->|@ @|yellow 522|@ available lots total", result);
    }

    @Test
    void testExtraParameters() throws FileWriteException, NoCarparkFoundException, InvalidCommandException, NoFileFoundException {
        String input = "list please";
        CarparkList carparkList = new CarparkList(validPathAndFile, validBackupPathAndFile);
        Command command = new Parser().parseCommand(input, null, carparkList, null);
        String result = command.execute().showToUser;
        Assertions.assertEquals("There were unrecognized arguments after the `list` command. " +
                "Please try the `list` command again by itself.", result);
    }
}
