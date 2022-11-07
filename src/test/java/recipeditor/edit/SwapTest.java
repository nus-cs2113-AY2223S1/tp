package recipeditor.edit;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import recipeditor.exception.InvalidFlagException;
import recipeditor.exception.ParseException;
import recipeditor.parser.FlagType;
import recipeditor.recipe.Ingredient;
import recipeditor.recipe.Recipe;
import recipeditor.recipe.RecipeList;
import recipeditor.storage.Storage;

public class SwapTest {
    private static Recipe recipe;

    @BeforeAll
    public static void setUp() {
        Storage.loadRecipesToRecipeTitlesList();
        recipe = new Recipe("title", "test_description");
        recipe.addIngredient(new Ingredient("ingredient 1", 100.0, "g"));
        recipe.addIngredient(new Ingredient("ingredient 2", 100.0, "g"));
        recipe.addStep("step 1");
        recipe.addStep("step 2");
    }

    @Test
    void swapSteps_correctArgs_correctParsing() throws InvalidFlagException, ParseException {
        String[] input = { "/edit", "1", "-swp", "-s", "1", "2" };
        EditModeCommand ed = new Swap(FlagType.STEP, input, recipe);
        Recipe newRecipe = ed.execute();
        assertEquals(newRecipe.getStep(1), "step 1");
    }

    @Test
    void swapSteps_incorrectArgs_correctParsing() {
        String[] input = { "/edit", "1", "-swp", "-s", "2", "3" };
        EditModeCommand ed = new Swap(FlagType.STEP, input, recipe);
        try {
            ed.execute();
        } catch (IndexOutOfBoundsException i) {
            assert true;
        } catch (Exception i) {
            assert false;
        }
    }

    @Test
    void swapIngredients_correctArgs_correctParsing() throws InvalidFlagException, ParseException {
        String[] input = { "/edit", "1", "-swp", "-i", "1", "2" };
        EditModeCommand ed = new Swap(FlagType.INGREDIENT, input, recipe);
        Recipe newRecipe = ed.execute();
        assertEquals(newRecipe.getIngredient(0).getName(), "ingredient 2");
    }

    @Test
    void swapIngredients_incorrectArgs_correctParsing() {
        String[] input = { "/edit", "1", "-swp", "-i", "2", "3" };
        EditModeCommand ed = new Swap(FlagType.INGREDIENT, input, recipe);
        try {
            ed.execute();
        } catch (IndexOutOfBoundsException i) {
            assert true;
        } catch (Exception i) {
            assert false;
        }
    }

    @AfterAll
    static void tearDown() {
        RecipeList.deleteRecipeFromTitle("title");
        Storage.deleteRecipeFile("title");
        Storage.rewriteRecipeListToFile();
    }
}
