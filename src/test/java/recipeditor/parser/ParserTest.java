package recipeditor.parser;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

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
import recipeditor.ui.Ui;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

class ParserTest {
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
        String input = "/delete 3";
        assertEquals(DeleteCommand.class, Parser.parseCommand(input).getClass());
    }

    @Test
    void incorrectEditCommand_wrongParameter_correctFormatForEditCommand() {
        String input = "/edit 1 -i";
        Command commandExecuted = Parser.parseCommand(input);
        CommandResult commandExecutedResult = commandExecuted.execute();
        assertEquals(EditCommand.COMMAND_SYNTAX, commandExecutedResult.getMessage());
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

    @Test
    void completeExitCommand_correctExitCommandFormat_ExitProgram() {
        String input = "/exit";
        Command commandExecuted = Parser.parseCommand(input);
        CommandResult commandExecutedResult = commandExecuted.execute();
        assertEquals(ExitCommand.EXIT_MESSAGE, commandExecutedResult.getMessage());
        assertEquals(ExitCommand.class, Parser.parseCommand(input).getClass());
    }

    @Test
    void incorrectFindCommand_wrongParameter_correctFormatForFindCommand() {
        String input = "/find";
        Command commandExecuted = Parser.parseCommand(input);
        CommandResult commandExecutedResult = commandExecuted.execute();
        assertEquals(FindCommand.CORRECT_FORMAT, commandExecutedResult.getMessage());
    }

    @Test
    void completeFindCommand_correctFindCommandFormat_FindRecipeTitleThatContainsFindInput() {
        Recipe addedRecipe = new Recipe("Example Title for Find Command");
        RecipeList.addRecipe(addedRecipe);
        RecipeList.addRecipeTitle(addedRecipe.getTitle());
        String input = "/find title";
        String expected = System.lineSeparator() + "1. Example Title for Find Command";
        Command commandExecuted = Parser.parseCommand(input);
        CommandResult commandExecutedResult = commandExecuted.execute();
        assertEquals(expected, commandExecutedResult.getMessage());
        assertEquals(FindCommand.class, Parser.parseCommand(input).getClass());
        RecipeList.deleteRecipeFromTitle("Example Title for Find Command");
    }

    @Test
    void incompleteHelpCommand_incorrectHelpCommandFormat_correctFormatForHelpCommand() {
        String input = "/help";
        Command commandExecuted = Parser.parseCommand(input);
        CommandResult commandExecutedResult = commandExecuted.execute();
        String expected = "Syntax: /help <command>\n"
               + "Description: Show help message for the given command.";
        assertEquals(expected, commandExecutedResult.getMessage());
        assertEquals(HelpCommand.class, Parser.parseCommand(input).getClass());
    }

    @Test
    void completeListCommand_correctListCommandFormat_listRecipeTitles() {
        Recipe addedRecipe = new Recipe("Example Title");
        RecipeList.addRecipe(addedRecipe);
        RecipeList.addRecipeTitle(addedRecipe.getTitle());
        String input = "/list";
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        Command commandExecuted = Parser.parseCommand(input);
        CommandResult commandExecutedResult = commandExecuted.execute();
        System.setOut(new PrintStream(outContent));
        Ui.showMessage(commandExecutedResult.getMessage());
        String expected = "There are 1 recipes in the recipe list" + System.lineSeparator() + "1. Example Title";
        assertEquals(expected.trim(), outContent.toString().trim());
        assertEquals(ListCommand.class, Parser.parseCommand(input).getClass());
        RecipeList.deleteRecipeFromTitle("Example Title");
    }

    @Test
    void incompleteViewCommand_incorrectViewCommandFormat_correctFormatForViewCommand() {
        String input = "/view title";
        Command commandExecuted = Parser.parseCommand(input);
        CommandResult commandExecutedResult = commandExecuted.execute();
        assertEquals(ViewCommand.COMMAND_SYNTAX, commandExecutedResult.getMessage());
        assertEquals(InvalidCommand.class, Parser.parseCommand(input).getClass());
    }

    @Test
    void completeViewCommand_correctViewCommandFormat_showSpecificRecipe() {
        Recipe addedRecipe = new Recipe("Example Title for View Command");
        RecipeList.addRecipe(addedRecipe);
        RecipeList.addRecipeTitle(addedRecipe.getTitle());
        String input = "/view 1";
        String expected = "TITLE:\n" + "Example Title for View Command\n" + "\n" + "DESCRIPTION:\n" + "\n" + "\n"
                + "INGREDIENTS: \n" + "\n" + "STEPS: \n" + "\n" + "\n";
        Command commandExecuted = Parser.parseCommand(input);
        CommandResult commandExecutedResult = commandExecuted.execute();
        assertEquals(expected, commandExecutedResult.getMessage());
        assertEquals(ViewCommand.class, Parser.parseCommand(input).getClass());
        RecipeList.deleteRecipeFromTitle("Example Title for View Command");
    }
}