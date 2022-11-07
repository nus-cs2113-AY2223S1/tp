package seedu.duke.command;

import org.junit.jupiter.api.Test;
import seedu.duke.command.help.HelpCommand;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class HelpCommandTest {
    private final HelpCommand command = new HelpCommand();

    @Test
    void testCommandsDisplayed() {
        int numCommands = 30;
        int numHeaders = 7;
        int linesPerHeader = 3;
        assertEquals(numCommands + numHeaders * linesPerHeader, command.toString().lines().count());
    }
}
