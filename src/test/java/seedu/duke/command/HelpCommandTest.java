package seedu.duke.command;

import org.junit.jupiter.api.Test;
import seedu.duke.command.help.HelpCommand;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class HelpCommandTest {
    private final HelpCommand command = new HelpCommand();

    @Test
    void testCommandsDisplayed() {
        int numCommands = 22;
        int numHeaders = 7;
        int linesPerHeader = 3;
        assertEquals(command.toString().lines().count(), numCommands + numHeaders * linesPerHeader);
    }
}
