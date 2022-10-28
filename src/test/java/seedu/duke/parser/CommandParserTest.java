package seedu.duke.parser;

import org.junit.jupiter.api.Test;
import seedu.duke.command.ByeCommand;
import seedu.duke.command.Command;
import seedu.duke.exception.GlobalInvalidCommandException;
import seedu.duke.exception.MoolahException;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class CommandParserTest {
    //@@author wcwy
    @Test
    public void parse_unsupportedCommandWord_exceptionThrown() {
        assertThrows(
            GlobalInvalidCommandException.class,
            () -> CommandParser.parse("rm -rf")
        );
    }

    @Test
    public void parse_supportedCommandWordLowerCase_expectCorrectCommandSubclass() throws MoolahException {
        Command command = CommandParser.parse("bye");
        assertTrue(command instanceof ByeCommand);
    }

    @Test
    public void parse_supportedCommandWordRandomCase_expectCorrectCommandSubclass() throws MoolahException {
        Command command = CommandParser.parse("bYe");
        assertTrue(command instanceof ByeCommand);
    }

    //@@author chydarren
    @Test
    public void splitInput_commandWordWithoutParameter_expectOneToken() {
        String[] inputTokens = CommandParser.splitInput("bye");
        assertEquals(inputTokens[0], "bye");
        assertEquals(inputTokens[1], "");
    }

    @Test
    public void splitInput_commandWordWithParameter_expectTwoTokens() {
        String[] inputTokens = CommandParser.splitInput("find helloworld");
        assertEquals(inputTokens[0], "find");
        assertEquals(inputTokens[1], "helloworld");
    }
}
