package recipeditor.command;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import recipeditor.parser.Parser;
import recipeditor.recipe.Ingredient;
import recipeditor.recipe.Recipe;
import recipeditor.recipe.RecipeList;
import recipeditor.storage.Storage;

public class FindCommandTest {

    private static Recipe recipe;

    @BeforeAll
    static void setUp() {
        recipe = new Recipe("Test title for Find Command", "Test description");
        RecipeList.addRecipe(recipe);
        RecipeList.addRecipeTitle(recipe.getTitle());
        Storage.rewriteRecipeListToFile();
        String recipeFileSourcePath = Storage.titleToFilePath(recipe.getTitle());
        Storage.saveRecipe(recipe, "", recipeFileSourcePath);

        Ingredient newIngredient = new Ingredient("test ingredient", 1, "whole");
        recipe.addIngredient(newIngredient);
    }

    @Test
    void correctFindRecipeTitleCommand_correctFindCommandFormat_listOfRecipeTitles() {
        String input = "/find -t title";
        assertEquals(FindCommand.class, Parser.parseCommand(input).getClass());
    }

    @Test
    void correctFindIngredientNameCommand_correctFindCommandFormat_listOfRecipeTitles() {
        String input = "/find -i ing";
        assertEquals(FindCommand.class, Parser.parseCommand(input).getClass());
    }

    @Test
    void incorrectHelpCommand_missingRecipeFlag_syntaxForFindCommand() {
        String input = "/find ing";
        Command commandExecuted = Parser.parseCommand(input);
        CommandResult commandExecutedResult = commandExecuted.execute();
        String expected = "The correct format should be '/find -<flag> <recipe title/ingredient name>'.\n"
                + "Flags:\n"
                + "-t: Recipe Title\n"
                + "-i: Ingredient name";
        assertEquals(expected, commandExecutedResult.getMessage());
    }

    @Test
    void incorrectHelpCommand_invalidInput_syntaxForFindCommand() {
        String input = "/find -flag input";
        Command commandExecuted = Parser.parseCommand(input);
        CommandResult commandExecutedResult = commandExecuted.execute();
        String expected = "The correct format should be '/find -<flag> <recipe title/ingredient name>'.\n"
                + "Flags:\n"
                + "-t: Recipe Title\n"
                + "-i: Ingredient name";
        assertEquals(expected, commandExecutedResult.getMessage());
    }

    @AfterAll
    static void end() {
        RecipeList.deleteRecipeFromTitle("Test title for Find Command");
        Storage.deleteRecipeFile("ETest title for Find Command");
        Storage.rewriteRecipeListToFile();
    }
}
