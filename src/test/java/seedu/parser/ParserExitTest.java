package seedu.parser;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import commands.Command;
import seedu.exception.FileWriteException;
import seedu.exception.InvalidCommandException;
import seedu.exception.NoCarparkFoundException;

public class ParserExitTest {

    @Test
    void testExit() throws FileWriteException, NoCarparkFoundException, InvalidCommandException {
        String input = "exit";
        Command command = new Parser().parseCommand(input, null, null, null);
        String result = command.execute().showToUser;
        Assertions.assertEquals("Exiting programme.", result);
    }

    @Test
    void testExitCaps() throws FileWriteException, NoCarparkFoundException, InvalidCommandException {
        String input = "EXIT";
        Command command = new Parser().parseCommand(input, null, null, null);
        String result = command.execute().showToUser;
        Assertions.assertEquals("Exiting programme.", result);
    }

    @Test
    void testExitExtraParameters() throws FileWriteException, NoCarparkFoundException, InvalidCommandException {
        String input = "exit please";
        Command command = new Parser().parseCommand(input, null, null, null);
        String result = command.execute().showToUser;
        Assertions.assertEquals("There were unrecognized arguments after the `exit` command. "
                + "Please try the `exit` command again by itself.", result);
    }

}

