package recipeditor.command;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import recipeditor.parser.FlagType;
import recipeditor.recipe.Ingredient;
import recipeditor.recipe.Recipe;
import recipeditor.recipe.RecipeList;

public class EditCommandTest {

    private static Recipe recipe;

    @BeforeAll
    public static void setUp() {
        recipe = new Recipe("test title", "test description");
        recipe.addIngredient(new Ingredient("ingredient", 100.0, "g"));
        recipe.addStep("step 1");
        recipe.addStep("finshing step");
    }

    @Test
    public void editCommand_validSyntax_validResult() {
        AddCommand addCommand = new AddCommand(true, recipe);
        addCommand.execute();
        FlagType[] flags = { FlagType.DELETE, FlagType.INGREDIENT };
        String[] parsed = {"/edit", "1", "-del", "-i", "1"};
        EditCommand editCommand = new EditCommand(flags, parsed, 0, recipe, "test title");
        final String actual = editCommand.execute().getMessage();
        new DeleteCommand(recipe.getTitle()).execute();
        String expected = "\n"
                + "test title: ingredient successfully edited.";
        assertEquals(expected, actual);
    }

    @Test
    public void editCommand_validSyntax_invalidInput() {
        AddCommand addCommand = new AddCommand(true, recipe);
        addCommand.execute();
        FlagType[] flags = { FlagType.ADD, FlagType.INGREDIENT };
        String[] parsed = {"/edit", "1", "-add", "-i", "new ingredient"};
        EditCommand editCommand = new EditCommand(flags, parsed, 0, recipe, "test title");
        final String actual = editCommand.execute().getMessage();
        new DeleteCommand(recipe.getTitle()).execute();
        String expected = "/edit syntax is incorrect. Please check again using the '/help edit' command";
        assertEquals(expected, actual);
    }

    @Test
    public void editCommand_outOfBounds() {
        AddCommand addCommand = new AddCommand(true, recipe);
        addCommand.execute();
        FlagType[] flags = { FlagType.ADD, FlagType.INGREDIENT };
        String[] parsed = {"/edit", "1000", "-add", "-i", "new ingredient"};
        EditCommand editCommand = new EditCommand(flags, parsed, 999, recipe, "test title");
        final String actual = editCommand.execute().getMessage();
        new DeleteCommand(recipe.getTitle()).execute();
        String expected = "/edit syntax is incorrect. Please check again using the '/help edit' command";
        assertEquals(expected, actual);
    }
}
