package recipeditor.recipe;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import recipeditor.exception.ParseException;

public class RecipeTest {
    Recipe recipe = new Recipe("title", "description");

    @Test
    void addIngredient_correctArgs_correctParsing() throws ParseException {
        String input = "test / 1.0 / unit";
        Ingredient ingredient = Ingredient.parsedIngredients(input);
        recipe.addIngredient(ingredient);
        assertEquals(recipe.getIngredients().size(), 1);
    }

    @Test
    void addStep_correctArgs_correctParsing() throws ParseException {
        String input = "step";
        recipe.addStep(input);
        assertEquals(recipe.getSteps().size(), 1);
    }
}
