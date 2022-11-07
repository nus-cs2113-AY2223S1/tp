package recipeditor.command;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class InvalidCommandTest {

    @Test
    public void invalidCommand_executeInvalidCommand_invalidMessage() {
        String actual = new InvalidCommand(InvalidCommand.INVALID_MESSAGE).execute().getMessage();
        String expected = "This is not a valid command! " + "Try /help <command type>"
                + " to view the correct syntax." + "\n" + "Available commands: /add, /list, /view, /edit, /find, /delete, /exit, /help";
        assertEquals(actual, expected);
    }
}
