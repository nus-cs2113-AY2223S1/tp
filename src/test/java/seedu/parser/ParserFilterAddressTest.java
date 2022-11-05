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

public class ParserFilterAddressTest {
    private final String testFileDirectory = "./src/test/java/seedu/testfiles";
    private final Path validPathAndFile = Paths.get(testFileDirectory, "ltaResponse.json");
    private final Path validBackupPathAndFile = Paths.get(testFileDirectory, "ltaResponseBackup.json");

    @Test
    void testFilterAddress() throws FileWriteException, NoCarparkFoundException, InvalidCommandException,
            NoFileFoundException {
        CarparkList carparkList = new CarparkList(validPathAndFile, validBackupPathAndFile);
        String input = "filter -add sun";
        Command command = new Parser().parseCommand(input, null, carparkList, null);
        String result = command.execute().showToUser;
        Assertions.assertEquals("CarparkID @|yellow,bold 1|@ at @|bold,cyan Suntec|@ City\n"
                + "@|faint -->|@ @|yellow 1882|@ available lots total", result);
    }

    @Test
    void testFilterAddCaps() throws FileWriteException, NoCarparkFoundException, InvalidCommandException,
            NoFileFoundException {
        String input = "FILTER -ADDRESS SUN";
        CarparkList carparkList = new CarparkList(validPathAndFile, validBackupPathAndFile);
        Command command = new Parser().parseCommand(input, null, carparkList, null);
        String result = command.execute().showToUser;
        Assertions.assertEquals("CarparkID @|yellow,bold 1|@ at @|bold,cyan Suntec|@ City\n"
                + "@|faint -->|@ @|yellow 1882|@ available lots total", result);
    }

    @Test
    void testFilterIAddressEmptyArgument() throws FileWriteException, NoCarparkFoundException, InvalidCommandException,
            NoFileFoundException {
        String input = "filter -address";
        CarparkList carparkList = new CarparkList(validPathAndFile, validBackupPathAndFile);
        Command command = new Parser().parseCommand(input, null, carparkList, null);
        String result = command.execute().showToUser;
        Assertions.assertEquals("Empty argument. Valid command(s): \n"
                + "`filter -address QUERY` or `fil -add QUERY` \t: Filter carparks based on its Carpark address."
                , result);
    }
}
