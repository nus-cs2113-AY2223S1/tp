package recipeditor.command;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static recipeditor.parser.FlagType.TITLE;
import static recipeditor.parser.FlagType.DESCRIPTION;
import static recipeditor.parser.FlagType.INGREDIENT;

import recipeditor.parser.FlagType;
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
    void correctFindRecipeTitleCommand_correctFindCommandFormat_findCommandClassExecuted() {
        String input = "/find -t title";
        assertEquals(FindCommand.class, Parser.parseCommand(input).getClass());
    }

    @Test
    public void correctFindRecipeTitleCommand_correctFindCommand_listOfRecipeTitles() {
        FlagType flag = TITLE;
        String actual = new FindCommand(flag, "title").execute().getMessage();
        String expected = "1. Test title for Find Command";
        assertEquals(actual.trim(), expected.trim());
    }

    @Test
    void correctFindIngredientNameCommand_correctFindCommandFormat_findCommandClassExecuted() {
        String input = "/find -i ing";
        assertEquals(FindCommand.class, Parser.parseCommand(input).getClass());
    }

    @Test
    public void correctFindIngredientNameCommand_correctFindCommand_listOfRecipeTitles() {
        FlagType flag = INGREDIENT;
        String actual = new FindCommand(flag, "ingredient").execute().getMessage();
        String expected = "1. Test title for Find Command";
        assertEquals(actual.trim(), expected.trim());
    }

    @Test
    void incorrectFindCommand_missingRecipeFlag_syntaxForFindCommandMessage() {
        String actual = new FindCommand(null, "ingredient").execute().getMessage();
        String expected = "The correct format should be '/find -<flag> <recipe title/ingredient name>'.\n"
                + "Flags:\n"
                + "-t: Recipe Title\n"
                + "-i: Ingredient name";
        assertEquals(expected, actual);
    }

    @Test
    void incorrectFindCommand_incorrectRecipeFlag_syntaxForFindCommandMessage() {
        String actual = new FindCommand(DESCRIPTION, "ingredient").execute().getMessage();
        String expected = "Incorrect flag!\n\n"
                + "Flags:\n"
                + "-t: Recipe Title\n"
                + "-i: Ingredient name";
        assertEquals(expected, actual);
    }

    @AfterAll
    static void tearDown() {
        RecipeList.deleteRecipeFromTitle("Test title for Find Command");
        Storage.deleteRecipeFile("Test title for Find Command");
        Storage.rewriteRecipeListToFile();
    }
}
