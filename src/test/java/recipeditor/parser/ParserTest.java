package recipeditor.parser;

import org.junit.jupiter.api.Test;
import recipeditor.command.Command;
import recipeditor.command.CommandResult;
import recipeditor.command.DeleteCommand;
import recipeditor.command.InvalidCommand;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ParserTest {
    @Test
    void parseEmptyArg_emptyArg_invalidCommand() {
        String input = "";
        Command commandExecuted = Parser.parseCommand(input);
        CommandResult commandExecutedResult = commandExecuted.execute();
        assertEquals(InvalidCommand.INVALID_MESSAGE, commandExecutedResult.getMessage());
    }

    @Test
    void incompleteDeleteCommand_missingParameter_invalidCommand() {
        String input = "delete";
        Command commandExecuted = Parser.parseCommand(input);
        CommandResult commandExecutedResult = commandExecuted.execute();
        assertEquals(InvalidCommand.INVALID_MESSAGE, commandExecutedResult.getMessage());
    }

    @Test
    void incompleteViewCommand_missingParameter_invalidCommand() {
        String input = "view";
        Command commandExecuted = Parser.parseCommand(input);
        CommandResult commandExecutedResult = commandExecuted.execute();
        assertEquals(InvalidCommand.INVALID_MESSAGE, commandExecutedResult.getMessage());
    }

    @Test
    void completeCommand_correctCommandFormat_correspondingCommand() {
        String input = "delete 3";
        assertEquals(DeleteCommand.class, Parser.parseCommand(input).getClass());
    }
}