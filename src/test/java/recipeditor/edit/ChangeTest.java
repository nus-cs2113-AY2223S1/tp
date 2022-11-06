package recipeditor.edit;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import recipeditor.exception.InvalidFlagException;
import recipeditor.exception.ParseException;
import recipeditor.parser.FlagType;
import recipeditor.recipe.Ingredient;
import recipeditor.recipe.Recipe;
import recipeditor.storage.Storage;

public class ChangeTest {
    private static Recipe recipe;

    @BeforeAll
    public static void setUp() {
        Storage.loadRecipesToRecipeTitlesList();
        recipe = new Recipe("title", "test_description");
        recipe.addIngredient(new Ingredient("ingredient", 100.0, "g"));
        recipe.addStep("step 1");
        recipe.addStep("finishing step");
    }

    @Test
    void changeIngredient_correctArgs_correctParsing() throws InvalidFlagException, ParseException {
        String newIngredient = "new ingredient / 100.0 / g";
        String[] input = { "/edit", "1", "-chg", "-i", "1", newIngredient };
        EditModeCommand ed = new Change(FlagType.INGREDIENT, input, recipe);
        Recipe newRecipe = ed.execute();
        assertEquals(newRecipe.getIngredient(0).getName(), "new ingredient ");
    }

    @Test
    void changeStep_correctArgs_correctParsing() throws InvalidFlagException, ParseException {
        String newStep = "new step";
        String[] input = { "/edit", "1", "-chg", "-s", "2", newStep };
        EditModeCommand ed = new Change(FlagType.STEP, input, recipe);
        Recipe newRecipe = ed.execute();
        assertEquals(newRecipe.getStep(1), newStep);
    }

    @Test
    void changeTitle_correctArgs_correctParsing() throws InvalidFlagException, ParseException {
        String newTitle = "new title";
        String[] input = { "/edit", "1", "-chg", "-t", newTitle };
        EditModeCommand ed = new Change(FlagType.TITLE, input, recipe);
        Recipe newRecipe = ed.execute();
        assertEquals(newRecipe.getTitle(), newTitle);
    }

    @Test
    void changeDescription_correctArgs_correctParsing() throws InvalidFlagException, ParseException {
        String newDescription = "new description";
        String[] input = { "/edit", "1", "-chg", "-d", newDescription };
        EditModeCommand ed = new Change(FlagType.DESCRIPTION, input, recipe);
        Recipe newRecipe = ed.execute();
        assertEquals(newRecipe.getDescription(), newDescription);
    }
}
