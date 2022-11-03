package seedu.commands;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import seedu.exception.FileWriteException;
import seedu.exception.InvalidCommandException;
import seedu.exception.NoCarparkFoundException;
import seedu.parser.Parser;

public class ExitCommandTest {

    @Test
    void testExit() throws FileWriteException, NoCarparkFoundException, InvalidCommandException {
        String input = "exit";
        Command command = new Parser().parseCommand(input, null, null, null);
        String result = command.execute().showToUser;
        Assertions.assertEquals("Exiting programme.", result);
    }

    @Test
    void testExtraParameters() throws FileWriteException, NoCarparkFoundException, InvalidCommandException {
        String input = "exit please";
        Command command = new Parser().parseCommand(input, null, null, null);
        String result = command.execute().showToUser;
        Assertions.assertEquals("There were unrecognized arguments after the `exit` command. " +
                "Please try the `exit` command again by itself.", result);
    }
}
