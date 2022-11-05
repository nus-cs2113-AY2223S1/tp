package seedu.parser;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import commands.Command;
import seedu.data.CarparkList;
import seedu.exception.FileWriteException;
import seedu.exception.InvalidCommandException;
import seedu.exception.NoCarparkFoundException;
import seedu.exception.NoFileFoundException;

import java.nio.file.Path;
import java.nio.file.Paths;

public class ParserListText {
    private final String testFileDirectory = "./src/test/java/seedu/testfiles";
    private final Path validPathAndFile = Paths.get(testFileDirectory, "ltaResponse.json");
    private final Path validBackupPathAndFile = Paths.get(testFileDirectory, "ltaResponseBackup.json");

    private final Path invalidPathAndFile = Paths.get(testFileDirectory, "invalid.json");
    private final Path invalidBackupPathAndFile = Paths.get(testFileDirectory, "invalidBackup.json");

    @Test
    void testListValid() throws FileWriteException, NoCarparkFoundException, InvalidCommandException,
            NoFileFoundException {
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
    void testListInvalid() throws FileWriteException, NoFileFoundException {
        CarparkList carparkList = new CarparkList(invalidPathAndFile, invalidBackupPathAndFile);
        String input = "list";
        Assertions.assertThrows(NoFileFoundException.class, () -> {
            new Parser().parseCommand(input, null, carparkList, null).execute();
        }, "A critical error has occured. Please contact the developer!");
    }

    @Test
    void testExitCaps() throws FileWriteException, NoCarparkFoundException, InvalidCommandException,
            NoFileFoundException {
        CarparkList carparkList = new CarparkList(validPathAndFile, validBackupPathAndFile);
        String input = "LIST";
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
    void testListExtraParameters() throws FileWriteException, NoCarparkFoundException, InvalidCommandException {
        String input = "list please";
        Command command = new Parser().parseCommand(input, null, null, null);
        String result = command.execute().showToUser;
        Assertions.assertEquals("There were unrecognized arguments after the `list` command. "
                + "Please try the `list` command again by itself.", result);
    }

}
