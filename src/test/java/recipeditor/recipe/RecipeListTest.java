package recipeditor.recipe;

import org.junit.Test;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import recipeditor.storage.Storage;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class RecipeListTest {

    private static Recipe recipe;

    @BeforeAll
    static void setUp() {
        recipe = new Recipe("Test title for RecipeList", "Test description");
        RecipeList.addRecipe(recipe);
        RecipeList.addRecipeTitle(recipe.getTitle());
        Storage.rewriteRecipeListToFile();
        String recipeFileSourcePath = Storage.titleToFilePath(recipe.getTitle());
        Storage.saveRecipe(recipe, "", recipeFileSourcePath);

        Ingredient newIngredient = new Ingredient("test ingredient", 1, "whole");
        recipe.addIngredient(newIngredient);
    }


    @Test
    void checksIfRecipeListContainsRecipe_recipeContained_outputTrue() {
        boolean actual = RecipeList.containsRecipe(recipe);
        assertTrue(actual);
    }

    @AfterAll
    static void tearDown() {
        RecipeList.deleteRecipeFromTitle("Test title for RecipeList");
        Storage.deleteRecipeFile("Test title for RecipeList");
        Storage.rewriteRecipeListToFile();
    }

}
