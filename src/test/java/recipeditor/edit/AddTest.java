package recipeditor.edit;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;
import recipeditor.exception.InvalidFlagException;
import recipeditor.exception.ParseException;
import recipeditor.parser.FlagType;
import recipeditor.recipe.Ingredient;
import recipeditor.recipe.Recipe;

public class AddTest {
    Recipe oldRecipe = new Recipe("title", "description");

    @Test
    void addIngredient_correctArgs_correctParsing() throws InvalidFlagException, ParseException {
        String[] input = { null, null, null, null, "test / 1.0 / unit" };
        EditModeCommand ed = new Add(FlagType.INGREDIENT, input, oldRecipe);
        Recipe newRecipe = ed.execute();
        assertEquals(newRecipe.getIngredient(0).getName(), "test ");
    }
}
