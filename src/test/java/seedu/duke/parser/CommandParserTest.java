package seedu.duke.parser;

import org.junit.jupiter.api.Test;
import seedu.duke.command.ByeCommand;
import seedu.duke.command.Command;
import seedu.duke.exception.GlobalInvalidCommandException;
import seedu.duke.exception.MoolahException;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

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
}
