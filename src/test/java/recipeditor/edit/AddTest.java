package recipeditor.edit;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import recipeditor.exception.InvalidFlagException;
import recipeditor.exception.ParseException;
import recipeditor.parser.FlagType;
import recipeditor.recipe.Ingredient;
import recipeditor.recipe.Recipe;
import recipeditor.storage.Storage;

public class AddTest {
    private static Recipe recipe;

    @BeforeAll
    public static void setUp() {
//        Storage.loadRecipesToRecipeList();
        Storage.loadRecipesToRecipeTitlesList();
        recipe = new Recipe("title", "test_description");
        recipe.addIngredient(new Ingredient("ingredient", 100.0, "g"));
        recipe.addStep("step 1");
        recipe.addStep("finishing step");
    }

    @Test
    void addStep_correctArgs_correctParsing() throws InvalidFlagException, ParseException {
        String[] input = { "/edit", "1", "-add", "-s", "new step" };
        EditModeCommand ed = new Add(FlagType.STEP, input, recipe);
        Recipe newRecipe = ed.execute();
        assertEquals(newRecipe.getStep(2), "new step ");
    }

    @Test
    void addIngredient_correctArgs_correctParsing() throws InvalidFlagException, ParseException {
        String[] input = { "/edit", "1", "-add", "-i", "test / 1.0 / unit" };
        EditModeCommand ed = new Add(FlagType.INGREDIENT, input, recipe);
        Recipe newRecipe = ed.execute();
        assertEquals(newRecipe.getIngredient(1).getName(), "test ");
    }

    @Test
    void addIngredient_incorrectArgs_incorrectParsing() {
        String[] input = { "/edit", "1", "-add", "-t", "new title" };
        EditModeCommand ed = new Add(FlagType.TITLE, input, recipe);
        try {
            ed.execute();
        } catch (InvalidFlagException i) {
            assert true;
        } catch (Exception e) {
            assert false;
        }
    }

    @Test
    void addIngredient_correctArgs_incorrectParsing() {
        String[] input = { "/edit", "1", "-add", "-i", "new ingredient" };
        EditModeCommand ed = new Add(FlagType.INGREDIENT, input, recipe);
        try {
            ed.execute();
        } catch (ParseException p) {
            assert true;
        } catch (Exception e) {
            assert false;
        }
    }
}
