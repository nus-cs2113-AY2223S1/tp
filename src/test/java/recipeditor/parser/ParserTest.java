package recipeditor.parser;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.Assert;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import recipeditor.command.*;
import recipeditor.recipe.Ingredient;
import recipeditor.recipe.Recipe;
import recipeditor.recipe.RecipeList;
import recipeditor.storage.Storage;
import recipeditor.ui.Ui;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.PrintStream;

class ParserTest {

    @BeforeAll
    public static void setUp() {
        Recipe recipe = new Recipe("test title", "test_description");
        recipe.addIngredient(new Ingredient("ingredient", 100.0, "g"));
        recipe.addStep("step 1");
        recipe.addStep("finshing step ~");
        RecipeList.addRecipe(recipe);
        RecipeList.addRecipeTitle(recipe.getTitle());
    }

    @AfterAll
    public static void tearDown() {
        RecipeList.deleteRecipeFromTitle("test title");
    }

    @Test
    void parseList_mixOfDifferentCases_returnListOfRecipeTitles() {
        Parser parse = new Parser();
        assertTrue(parse.parseCommand("/LiST") instanceof ListCommand);
    }

    @Test
    void parseView_mixOfDifferentCases_returnViewOfSpecificRecipe() {
        Parser parse = new Parser();
        assertTrue(parse.parseCommand("/VIEw -id 1") instanceof ViewCommand);
    }

    @Test
    void parseExit_mixOfDifferentCases_exitProgram() {
        Parser parse = new Parser();
        assertTrue(parse.parseCommand("/Exit") instanceof ExitCommand);
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
        String input = "/edit 1 -i";
        Command commandExecuted = Parser.parseCommand(input);
        CommandResult commandExecutedResult = commandExecuted.execute();
        assertEquals(EditCommand.COMMAND_SYNTAX, commandExecutedResult.getMessage());
    }

    @Test
    void completeEditCommand_correctEditCommandFormat_correspondingEditCommand() {
        String input = "/edit 1 -add -i tomato/2/whole";
        assertEquals(EditCommand.class, Parser.parseCommand(input).getClass());
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
    void completeFindRecipeTitleCommand_correctFindCommandFormat_FindRecipeTitleThatContainsFindInput() {
        String input = "/find -t title";
        String expected = System.lineSeparator() + "1. test title";
        Command commandExecuted = Parser.parseCommand(input);
        CommandResult commandExecutedResult = commandExecuted.execute();
        assertEquals(expected, commandExecutedResult.getMessage());
        assertEquals(FindCommand.class, Parser.parseCommand(input).getClass());
    }

    @Test
    void incompleteHelpCommand_incorrectHelpCommandFormat_correctFormatForHelpCommand() {
        String input = "/help";
        Command commandExecuted = Parser.parseCommand(input);
        CommandResult commandExecutedResult = commandExecuted.execute();
        String expected = "Try /help <command type>\n"
                + "Available commands: /add, /list, /view, /edit, /find, /delete, /exit, /help";
        assertEquals(expected, commandExecutedResult.getMessage());
        assertEquals(InvalidCommand.class, Parser.parseCommand(input).getClass());
    }

    @Test
    void completeListCommand_correctListCommandFormat_listRecipeTitles() {
        String input = "/list";
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        Command commandExecuted = Parser.parseCommand(input);
        CommandResult commandExecutedResult = commandExecuted.execute();
        System.setOut(new PrintStream(outContent));
        Ui.showMessage(commandExecutedResult.getMessage());
        String expected = "There are 1 recipes in the recipe list" + System.lineSeparator() + "1. test title";
        assertEquals(expected.trim(), outContent.toString().trim());
        assertEquals(ListCommand.class, Parser.parseCommand(input).getClass());
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
        String input = "/view -id 1";
        String expected = RecipeList.getRecipe(0).getRecipeAttributesFormatted();
        Command commandExecuted = Parser.parseCommand(input);
        CommandResult commandExecutedResult = commandExecuted.execute();
        assertEquals(expected, commandExecutedResult.getMessage());
        assertEquals(ViewCommand.class, Parser.parseCommand(input).getClass());
    }

    @Test
    void deleteCommand_byIndex_success() {
        String input = "/delete -id 1";
        Command deleteCommand = Parser.parseCommand(input);
        String expected = '\n' + RecipeList.getRecipe(0).getTitle()
                + " is deleted from the recipe list." + '\n';
        String commandExecutedResult = deleteCommand.execute().getMessage();
        setUp();
        assertEquals(expected, commandExecutedResult);
        assertEquals(DeleteCommand.class, Parser.parseCommand(input).getClass());
    }

    @Test
    public void deleteCommand_byIndex_indexOutOfBound() {
        String input = "/delete -id 0";
        Command deleteCommand = Parser.parseCommand(input);
        String expected = new InvalidCommand(DeleteCommand.CORRECT_FORMAT).execute().getMessage();
        String commandExecutedResult = deleteCommand.execute().getMessage();
        assertEquals(expected, commandExecutedResult);
        assertEquals(InvalidCommand.class, Parser.parseCommand(input).getClass());
    }

    @Test
    public void deleteCommand_byIndex_invalidIndex() {
        String input = "/delete -id index";
        Command deleteCommand = Parser.parseCommand(input);
        String expected = new InvalidCommand(DeleteCommand.CORRECT_FORMAT).execute().getMessage();
        String commandExecutedResult = deleteCommand.execute().getMessage();
        assertEquals(expected, commandExecutedResult);
        assertEquals(InvalidCommand.class, Parser.parseCommand(input).getClass());
    }

    @Test
    public void deleteCommand_byTitle_success() {
        String input = "/delete -t test title";
        Command deleteCommand = Parser.parseCommand(input);
        String expected = '\n' + RecipeList.getRecipe(0).getTitle()
                + " is deleted from the recipe list." + '\n';
        String commandExecutedResult = deleteCommand.execute().getMessage();
        setUp();
        assertEquals(expected, commandExecutedResult);
        assertEquals(DeleteCommand.class, Parser.parseCommand(input).getClass());
    }

    @Test
    public void deleteCommand_byTitle_missingTitle() {
        String input = "/delete -t ";
        Command deleteCommand = Parser.parseCommand(input);
        String expected = new InvalidCommand(DeleteCommand.CORRECT_FORMAT).execute().getMessage();
        String commandExecutedResult = deleteCommand.execute().getMessage();
        assertEquals(expected, commandExecutedResult);
        assertEquals(InvalidCommand.class, Parser.parseCommand(input).getClass());
    }

    @Test
    public void deleteCommand_invalidFlag() {
        String input = "/delete -d ";
        Command deleteCommand = Parser.parseCommand(input);
        String expected = new InvalidCommand(DeleteCommand.CORRECT_FORMAT).execute().getMessage();
        String commandExecutedResult = deleteCommand.execute().getMessage();
        assertEquals(expected, commandExecutedResult);
        assertEquals(InvalidCommand.class, Parser.parseCommand(input).getClass());
    }

    @Test
    public void viewCommand_byIndex_indexOutOfBound() {
        String input = "/view -id 0";
        Command viewCommand = Parser.parseCommand(input);
        String expected = new InvalidCommand(ViewCommand.COMMAND_SYNTAX).execute().getMessage();
        String commandExecutedResult = viewCommand.execute().getMessage();
        assertEquals(expected, commandExecutedResult);
        assertEquals(InvalidCommand.class, Parser.parseCommand(input).getClass());
    }

    @Test
    public void viewCommand_byIndex_invalidIndex() {
        String input = "/view -id index";
        Command viewCommand = Parser.parseCommand(input);
        String expected = new InvalidCommand(ViewCommand.COMMAND_SYNTAX).execute().getMessage();
        String commandExecutedResult = viewCommand.execute().getMessage();
        assertEquals(expected, commandExecutedResult);
        assertEquals(InvalidCommand.class, Parser.parseCommand(input).getClass());
    }

    @Test
    public void viewCommand_byTitle_success() {
        String input = "/view -t test title";
        Command viewCommand = Parser.parseCommand(input);
        String expected = RecipeList.getRecipe(0).getRecipeAttributesFormatted();
        String commandExecutedResult = viewCommand.execute().getMessage();
        assertEquals(expected, commandExecutedResult);
        assertEquals(ViewCommand.class, Parser.parseCommand(input).getClass());
    }

    @Test
    public void viewCommand_byTitle_missingTitle() {
        String input = "/view -t ";
        Command viewCommand = Parser.parseCommand(input);
        String expected = new InvalidCommand(ViewCommand.COMMAND_SYNTAX).execute().getMessage();
        String commandExecutedResult = viewCommand.execute().getMessage();
        assertEquals(expected, commandExecutedResult);
        assertEquals(InvalidCommand.class, Parser.parseCommand(input).getClass());
    }

    @Test
    public void viewCommand_invalidFlag() {
        String input = "/view -d ";
        Command viewCommand = Parser.parseCommand(input);
        String expected = new InvalidCommand(ViewCommand.COMMAND_SYNTAX).execute().getMessage();
        String commandExecutedResult = viewCommand.execute().getMessage();
        assertEquals(expected, commandExecutedResult);
        assertEquals(InvalidCommand.class, Parser.parseCommand(input).getClass());
    }

    @Test
    public void addCommand_missingTemplateFile() {
        File file = new File(Storage.TEMPLATE_FILE_PATH);
        file.delete();
        Command addCommand = Parser.parseCommand("/add");
        String actual = addCommand.execute().getMessage();
        String expected = new InvalidCommand(
                InvalidCommand.TEMPLATE_FILE_MISSING_MESSAGE).execute().getMessage();
        assertEquals(expected, actual);
        assert addCommand instanceof InvalidCommand;
    }

}