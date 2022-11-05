package recipeditor.parser;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import recipeditor.command.Command;
import recipeditor.command.CommandResult;
import recipeditor.command.DeleteCommand;
import recipeditor.command.EditCommand;
import recipeditor.command.ExitCommand;
import recipeditor.command.FindCommand;
import recipeditor.command.HelpCommand;
import recipeditor.command.InvalidCommand;
import recipeditor.command.ListCommand;
import recipeditor.command.ViewCommand;

import recipeditor.recipe.Recipe;
import recipeditor.recipe.RecipeList;


class ParserTest {

    @Test
    void parseList_mixOfDifferentCases_returnListOfRecipeTitles() {
        Parser parse = new Parser();
        assertTrue(Parser.parseCommand("/LiST") instanceof ListCommand);
    }

    @Test
    void parseView_mixOfDifferentCases_returnViewOfSpecificRecipe() {
        Parser parse = new Parser();
        assertTrue(Parser.parseCommand("/VIEw -id 1") instanceof ViewCommand);
    }

    @Test
    void parseExit_mixOfDifferentCases_exitProgram() {
        Parser parse = new Parser();
        assertTrue(Parser.parseCommand("/Exit") instanceof ExitCommand);
    }

    @Test
    void parseEmptyArg_emptyArg_invalidCommand() {
        String input = "";
        Command commandExecuted = Parser.parseCommand(input);
        CommandResult commandExecutedResult = commandExecuted.execute();
        assertEquals(InvalidCommand.INVALID_MESSAGE, commandExecutedResult.getMessage());
    }

    @Test
    void incorrectAddCommand_wrongParameter_invalidCommand() {
        String input = "add";
        Command commandExecuted = Parser.parseCommand(input);
        CommandResult commandExecutedResult = commandExecuted.execute();
        assertEquals(InvalidCommand.INVALID_MESSAGE, commandExecutedResult.getMessage());
    }

    @Test
    void incompleteDeleteCommand_missingParameter_correctFormatForDeleteCommand() {
        String input = "/delete";
        Command commandExecuted = Parser.parseCommand(input);
        CommandResult commandExecutedResult = commandExecuted.execute();
        assertEquals(DeleteCommand.CORRECT_FORMAT, commandExecutedResult.getMessage());
    }

    @Test
    void completeDeleteCommand_correctDeleteCommandFormat_correspondingDeleteCommand() {
        String input = "/delete -id 3";
        assertEquals(DeleteCommand.class, Parser.parseCommand(input).getClass());
    }

    @Test
    void incorrectEditCommand_wrongParameter_correctFormatForEditCommand() {
        String input = "/edit 100";
        Command commandExecuted = Parser.parseCommand(input);
        CommandResult commandExecutedResult = commandExecuted.execute();
        assertEquals(InvalidCommand.INDEX_OUT_OF_RANGE_MESSAGE, commandExecutedResult.getMessage());
    }

    @Test
    void completeEditCommand_correctEditCommandFormat_correspondingEditCommand() {
        Recipe addedRecipe = new Recipe();
        RecipeList.addRecipe(addedRecipe);
        RecipeList.addRecipeTitle(addedRecipe.getTitle());
        String input = "/edit 1 -add -i tomato/2/whole";
        assertEquals(EditCommand.class, Parser.parseCommand(input).getClass());
        RecipeList.deleteRecipeFromTitle("");
    }

    //    @Test
    //    void completeExitCommand_correctExitCommandFormat_ExitProgram() {
    //        String input = "/exit";
    //        Command commandExecuted = Parser.parseCommand(input);
    //        CommandResult commandExecutedResult = commandExecuted.execute();
    //        assertEquals(ExitCommand.EXIT_MESSAGE, commandExecutedResult.getMessage());
    //        assertEquals(ExitCommand.class, Parser.parseCommand(input).getClass());
    //    }

    @Test
    void incorrectFindCommand_wrongParameter_correctFormatForFindCommand() {
        String input = "/find";
        Command commandExecuted = Parser.parseCommand(input);
        CommandResult commandExecutedResult = commandExecuted.execute();
        assertEquals(FindCommand.CORRECT_FORMAT, commandExecutedResult.getMessage());
    }

    @Test
    void incompleteHelpCommand_incorrectHelpCommandFormat_correctFormatForHelpCommand() {
        String input = "/help";
        Command commandExecuted = Parser.parseCommand(input);
        CommandResult commandExecutedResult = commandExecuted.execute();
        String expected = "Try /help <command type>\n" + "Available commands: /add, /list, /view, /edit, /find,"
                + " /delete, /exit, /help";
        assertEquals(expected, commandExecutedResult.getMessage());
        assertEquals(InvalidCommand.class, Parser.parseCommand(input).getClass());
    }



    @Test
    void incompleteViewCommand_incorrectViewCommandFormat_correctFormatForViewCommand() {
        String input = "/view title";
        Command commandExecuted = Parser.parseCommand(input);
        CommandResult commandExecutedResult = commandExecuted.execute();
        assertEquals(ViewCommand.COMMAND_SYNTAX, commandExecutedResult.getMessage());
        assertEquals(InvalidCommand.class, Parser.parseCommand(input).getClass());
    }
}