package recipeditor.recipe;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;
import recipeditor.exception.ParseException;
import recipeditor.ui.Ui;

public class IngredientTest {

    @Test
    void parseIngredient_validFormat_success() {
        String ingredient = "new ig/100.0/g";
        try {
            Ingredient parsedIngredient =  Ingredient.parsedIngredients(ingredient);
            assertEquals("new ig", parsedIngredient.getName());
            assertEquals(100.0, parsedIngredient.getAmount());
            assertEquals("g", parsedIngredient.getUnit());
        } catch (Exception e) {
            assert false;
        }
    }

    @Test
    void parseIngredient_emptyFields_fail() {
        String ingredient = "new ig/100.0/";
        try {
            Ingredient.parsedIngredients(ingredient);
            assert false;
        } catch (ParseException e) {
            assertEquals(new ParseException(Ingredient.INGREDIENT_FORMAT).getMessage(), e.getMessage());
        }
    }

    @Test
    void parseIngredient_wrongFormat_fail() {
        String ingredient = "new ig";
        try {
            Ingredient.parsedIngredients(ingredient);
            assert false;
        } catch (ParseException e) {
            assertEquals(new ParseException(Ingredient.INGREDIENT_FORMAT).getMessage(), e.getMessage());
        }
    }

    @Test
    void parseIngredient_wrongFormatWithSlash_fail() {
        String ingredient = "new ig//";
        try {
            Ingredient.parsedIngredients(ingredient);
            assert false;
        } catch (ParseException e) {
            assertEquals(new ParseException(Ingredient.INGREDIENT_FORMAT).getMessage(), e.getMessage());
        }
    }
}
