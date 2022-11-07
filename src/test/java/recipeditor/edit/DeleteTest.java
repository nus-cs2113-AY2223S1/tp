package recipeditor.edit;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import recipeditor.command.InvalidCommand;
import recipeditor.exception.InvalidFlagException;
import recipeditor.parser.FlagType;
import recipeditor.recipe.Ingredient;
import recipeditor.recipe.Recipe;
import recipeditor.recipe.RecipeList;
import recipeditor.storage.Storage;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeleteTest {
    private static final String TEST_RECIPE_TITLE = "eR$^.U*0(";
    private static Recipe recipe;

    @BeforeAll
    public static void setUp() {
        Storage.loadRecipesToRecipeTitlesList();
        recipe = new Recipe(TEST_RECIPE_TITLE, "test_description");
        recipe.addIngredient(new Ingredient("ingredient", 100.0, "g"));
        recipe.addStep("step 1");
    }

    @Test
    public void deleteIngredient_validInputFormat_success() {
        String[] parsedCommand = {"/edit", "1", "-del", "-i", "1"};
        FlagType ingredientFlag = FlagType.INGREDIENT;
        Delete delete = new Delete(ingredientFlag, parsedCommand, recipe);
        try {
            String actual = delete.execute().getIngredientAttributesFormatted();
            String expected = new Recipe().getIngredientAttributesFormatted();
            assertEquals(expected, actual);
        } catch (InvalidFlagException e) {
            assert false;
        }
    }

    @Test
    public void deleteStep_validInputFormat_success() {
        String[] parsedCommand = {"/edit", "1", "-del", "-s", "1"};
        FlagType ingredientFlag = FlagType.STEP;
        Delete delete = new Delete(ingredientFlag, parsedCommand, recipe);
        try {
            String actual = delete.execute().getStepAttributesFormatted();
            String expected = new Recipe().getStepAttributesFormatted();
            assertEquals(expected, actual);
        } catch (InvalidFlagException e) {
            assert false;
        }
    }

    @Test
    public void deleteCommand_invalidFlag_errorMessage() {
        String[] parsedCommand = {"/edit", "1", "-del", "-k", "1"};
        FlagType ingredientFlag = FlagType.NULL;
        Delete delete = new Delete(ingredientFlag, parsedCommand, recipe);
        try {
            delete.execute();
            assert false;
        } catch (InvalidFlagException e) {
            assert true;
        }  catch (NumberFormatException n) {
            assert true;
        }
    }

    @Test
    public void delete_noIndex_errorMessage() {
        String[] parsedCommand = {"/edit", "1", "-del", "-i"};
        FlagType ingredientFlag = FlagType.INGREDIENT;
        Delete delete = new Delete(ingredientFlag, parsedCommand, recipe);
        try {
            delete.execute();
            assert false;
        } catch (IndexOutOfBoundsException e) {
            assert true;
        } catch (InvalidFlagException e) {
            assert false;
        }
    }

    @Test
    public void delete_indexOutOfBound_errorMessage() {
        String[] parsedCommand = {"/edit", "1", "-del", "-s", "0"};
        FlagType ingredientFlag = FlagType.STEP;
        Delete delete = new Delete(ingredientFlag, parsedCommand, recipe);
        try {
            delete.execute();
            assert false;
        } catch (IndexOutOfBoundsException e) {
            assert true;
        } catch (InvalidFlagException e) {
            assert false;
        }
    }

    @Test
    public void delete_expectIndexReceiveString_errorMessage() {
        String[] parsedCommand = {"/edit", "1", "-del", "-i", "string"};
        FlagType ingredientFlag = FlagType.INGREDIENT;
        Delete delete = new Delete(ingredientFlag, parsedCommand, recipe);
        try {
            delete.execute();
            assert false;
        } catch (NumberFormatException e) {
            assert true;
        } catch (Exception e) {
            assert false;
        }
    }

    @AfterAll
    static void tearDown() {
        RecipeList.deleteRecipeFromTitle(TEST_RECIPE_TITLE);
        Storage.deleteRecipeFile(TEST_RECIPE_TITLE);
        Storage.rewriteRecipeListToFile();
    }

}
