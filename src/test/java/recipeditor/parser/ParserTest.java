package recipeditor.parser;

import org.junit.jupiter.api.Test;
import recipeditor.command.DeleteCommand;
import recipeditor.command.InvalidCommand;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ParserTest {
    @Test
    void parseEmptyArg_emptyArg_invalidCommand() {
        String input = "";
        assertEquals(InvalidCommand.class, Parser.parseCommand(input));
    }

    @Test
    void incompleteDeleteCommand_missingParameter_invalidCommand() {
        String input = "delete";
        assertEquals(InvalidCommand.class, Parser.parseCommand(input));
    }

    @Test
    void incompleteViewCommand_missingParameter_invalidCommand() {
        String input = "view";
        assertEquals(InvalidCommand.class, Parser.parseCommand(input));
    }

    @Test
    void completeCommand_correctCommandFormat_correspondingCommand() {
        String input = "delete 3";
        assertEquals(DeleteCommand.class, Parser.parseCommand(input));
    }
}