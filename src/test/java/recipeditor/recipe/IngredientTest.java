package recipeditor.recipe;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;
import recipeditor.command.Command;
import recipeditor.command.CommandResult;
import recipeditor.command.InvalidCommand;
import recipeditor.exception.ParseException;
import recipeditor.parser.Parser;

public class IngredientTest {
    @Test
    void parseIngredient_correctArgs_correctParsing() throws ParseException {
        String input = "test / 1.0 / unit";
        Ingredient ingredient;
        ingredient = Ingredient.parsedIngredients(input);
        assertEquals(ingredient.getName(), "test ");
    }
}
