package recipeditor.command;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import recipeditor.parser.FlagType;
import recipeditor.recipe.Ingredient;
import recipeditor.recipe.Recipe;
import recipeditor.recipe.RecipeList;
import recipeditor.storage.Storage;

public class EditCommandTest {

    private static Recipe recipe;

    @BeforeAll
    public static void setUp() {
        recipe = new Recipe("Test title", "Test description");
        RecipeList.addRecipe(recipe);
        RecipeList.addRecipeTitle(recipe.getTitle());
        Storage.rewriteRecipeListToFile();
        String recipeFileSourcePath = Storage.titleToFilePath(recipe.getTitle());
        Storage.saveRecipe(recipe, "", recipeFileSourcePath);

        recipe.addIngredient(new Ingredient("ingredient", 100.0, "g"));
        recipe.addIngredient(new Ingredient("new ingredient", 1, "whole"));
        recipe.addStep("step 1");
        recipe.addStep("finishing step");
    }

    @Test
    public void editCommand_validDeleteRecipeFlag_validResult() {
        FlagType[] flags = { FlagType.DELETE, FlagType.INGREDIENT };
        String[] parsed = {"/edit", "1", "-del", "-i", "1"};
        EditCommand editCommand = new EditCommand(flags, parsed, 0, recipe, "test title");
        String actual = editCommand.execute().getMessage();
        String expected = "\n" + "Test title: ingredient successfully edited.";
        assertEquals(expected, actual);
    }

    @Test
    public void editCommand_incorrectIngredientFormat_invalidSyntaxMessage() {
        FlagType[] flags = { FlagType.ADD, FlagType.INGREDIENT };
        String[] parsed = {"/edit", "1", "-add", "-i", "new ingredient"};
        EditCommand editCommand = new EditCommand(flags, parsed, 0, recipe, "test title");
        String actual = editCommand.execute().getMessage();
        String expected = "/edit syntax is incorrect. Please check again using the '/help edit' command";
        assertEquals(expected, actual);
    }

    @Test
    public void editCommand_outOfBounds_incorrectSyntaxMessage() {
        FlagType[] flags = { FlagType.ADD, FlagType.INGREDIENT };
        String[] parsed = {"/edit", "1000", "-add", "-i", "new ingredient"};
        EditCommand editCommand = new EditCommand(flags, parsed, 999, recipe, "test title");
        String actual = editCommand.execute().getMessage();
        String expected = "/edit syntax is incorrect. Please check again using the '/help edit' command";
        assertEquals(expected, actual);
    }

    @AfterAll
    static void tearDown() {
        RecipeList.deleteRecipeFromTitle("Test title");
        Storage.deleteRecipeFile("Test title");
        Storage.rewriteRecipeListToFile();
    }
}
