package recipeditor.command;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import recipeditor.edit.Add;
import recipeditor.parser.Parser;
import recipeditor.recipe.Ingredient;
import recipeditor.recipe.Recipe;
import recipeditor.recipe.RecipeList;

public class AddCommandTest {

    private static Recipe recipe;
    @BeforeAll
    public static void setUp() {
        recipe = new Recipe("test title", "test_description");
        recipe.addIngredient(new Ingredient("ingredient", 100.0, "g"));
        recipe.addStep("step 1");
        recipe.addStep("finshing step ~");
    }

    @Test
    public void addCommand_valid() {
        AddCommand addCommand = new AddCommand(true, recipe);
        String actual = addCommand.execute().getMessage();
        StringBuilder expected = new StringBuilder();
        expected.append(String.format("\"%s\" added to the recipe list.\n", recipe.getTitle()));
        expected.append(RecipeList.printNumberOfRecipes());
        new DeleteCommand(recipe.getTitle()).execute();
        assertEquals(expected.toString(), actual);
    }

    @Test
    public void addCommand_invalid() {
        AddCommand addCommand = new AddCommand(false, recipe);
        String actual = addCommand.execute().getMessage();
        String expected = "Nothing was added";
        assertEquals(expected, actual);
    }




}
