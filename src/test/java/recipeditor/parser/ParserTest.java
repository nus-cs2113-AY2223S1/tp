package recipeditor.parser;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeAll;
import recipeditor.command.Command;
import recipeditor.command.CommandResult;
import recipeditor.command.DeleteCommand;
import recipeditor.command.EditCommand;
import recipeditor.command.ExitCommand;
import recipeditor.command.FindCommand;
import recipeditor.command.InvalidCommand;
import recipeditor.command.ListCommand;
import recipeditor.command.ViewCommand;
import recipeditor.exception.RecipeNotFoundException;
import recipeditor.recipe.Ingredient;
import recipeditor.recipe.Recipe;
import recipeditor.recipe.RecipeList;
import recipeditor.storage.Storage;

class ParserTest {

    private static final String TEST_RECIPE_TITLE = "eR$^.U*0(";
    private static Recipe recipe;

    @BeforeAll
    public static void setUp() {
        Storage.loadRecipesToRecipeTitlesList();
        recipe = new Recipe(TEST_RECIPE_TITLE, "test_description");
        recipe.addIngredient(new Ingredient("ingredient", 100.0, "g"));
        recipe.addStep("step 1");
        recipe.addStep("finishing step ~");
        RecipeList.addRecipe(recipe);
        RecipeList.addRecipeTitle(recipe.getTitle());
        Storage.rewriteRecipeListToFile();
        String recipeFileSourcePath = Storage.titleToFilePath(recipe.getTitle());
        Storage.saveRecipe(recipe, "", recipeFileSourcePath);
    }

    @AfterAll
    public static void tearDown() {
        RecipeList.deleteRecipeFromTitle(TEST_RECIPE_TITLE);
        Storage.deleteRecipeFile(TEST_RECIPE_TITLE);
        Storage.rewriteRecipeListToFile();
    }

    public void resetDeletedRecipe() {
        RecipeList.addRecipe(recipe);
        RecipeList.addRecipeTitle(recipe.getTitle());
        Storage.rewriteRecipeListToFile();
        String recipeFileSourcePath = Storage.titleToFilePath(recipe.getTitle());
        Storage.saveRecipe(recipe, "", recipeFileSourcePath);
    }

    @Test
    void parseList_mixOfDifferentCases_returnListOfRecipeTitles() {
        assertTrue(Parser.parseCommand("/LiST") instanceof ListCommand);
    }

    @Test
    void parseView_mixOfDifferentCases_returnViewOfSpecificRecipe() {
        assertTrue(Parser.parseCommand("/VIEw -id 1") instanceof ViewCommand);
    }

    @Test
    void parseExit_mixOfDifferentCases_exitProgram() {
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
    void incorrectEditCommand_invalidIndex_correctFormatForEditCommand() {
        String input = "/edit tilapia dum";
        Command commandExecuted = Parser.parseCommand(input);
        CommandResult commandExecutedResult = commandExecuted.execute();
        assertEquals(InvalidCommand.INDEX_NOT_VALID, commandExecutedResult.getMessage());
    }

    @Test
    void incorrectEditCommand_outOfBoundIndex_correctFormatForEditCommand() {
        String input = "/edit 150 dum";
        Command commandExecuted = Parser.parseCommand(input);
        CommandResult commandExecutedResult = commandExecuted.execute();
        assertEquals(InvalidCommand.INDEX_OUT_OF_RANGE_MESSAGE, commandExecutedResult.getMessage());
    }

    @Test
    void incorrectEditCommand_missingCommandFlag() {
        String input = "/edit 1 -i";
        try {
            Parser.parseCommand(input);
        } catch (Exception e) {
            assert e.getMessage().equals("command");
        }
    }

    @Test
    void incorrectEditCommand_missingIngredientFlag() {
        String input = "/edit 1 -add";
        try {
            Parser.parseCommand(input);
        } catch (Exception e) {
            assert e.getMessage().equals("recipe");
        }
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
    void incompleteHelpCommand_incorrectHelpCommandFormat_correctFormatForHelpCommand() {
        String input = "/help";
        Command commandExecuted = Parser.parseCommand(input);
        CommandResult commandExecutedResult = commandExecuted.execute();
        String expected = "Try /help <command type>";
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
        int index = RecipeList.getRecipeSize();
        String input = "/delete -id " + index;
        Command deleteCommand = Parser.parseCommand(input);
        String expected = String.format("\n" + recipe.getTitle()
                + " is deleted from the recipe list.%n");
        String commandExecutedResult = deleteCommand.execute().getMessage();
        resetDeletedRecipe();
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
        String input = "/delete -t " + TEST_RECIPE_TITLE;
        Command deleteCommand = Parser.parseCommand(input);
        String expected = String.format("\n" + recipe.getTitle()
                + " is deleted from the recipe list.%n");
        String commandExecutedResult = deleteCommand.execute().getMessage();
        resetDeletedRecipe();
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
    public void viewCommand_byTitle_success() throws RecipeNotFoundException {
        String input = "/view -t " + TEST_RECIPE_TITLE;
        Command viewCommand = Parser.parseCommand(input);
        int recipeIndex = RecipeList.getRecipeIndexFromTitle(TEST_RECIPE_TITLE);
        String expected = RecipeList.getRecipe(recipeIndex).getRecipeAttributesFormatted();
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
}