package recipeditor.command;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import recipeditor.exception.RecipeNotFoundException;
import recipeditor.recipe.Ingredient;
import recipeditor.recipe.Recipe;
import recipeditor.recipe.RecipeList;
import recipeditor.parser.Parser;
import recipeditor.storage.Storage;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ViewCommandTest {

    @BeforeAll
    public static void setUp() {
        Recipe recipe = new Recipe("test_title", "test_description");
        recipe.addIngredient(new Ingredient("ingredient", 100.0, "g"));
        recipe.addStep("step 1");
        recipe.addStep("finshing step ~");
        RecipeList.addRecipe(recipe);
    }

    @AfterAll
    public static void tearDown() {
        RecipeList.deleteRecipeFromTitle("test_title");
    }

    @Test
    public void constructAndExecuteViewCommand_byIndex_success() {
        ViewCommand viewCommand = new ViewCommand(0);
        assertEquals(viewCommand.COMMAND_TYPE, "/view");
        String expectedResult = new CommandResult(
                RecipeList.getRecipe(0).getRecipeAttributesFormatted()).getMessage();
        String actualResult = viewCommand.execute().getMessage();
        assertEquals(expectedResult, actualResult);
    }

    @Test
    public void executeViewCommand_byIndex_indexOutOfBound() {
        ViewCommand viewCommand = new ViewCommand(1);
        String expectedResult = new CommandResult("View recipe index out of bound.").getMessage();
        String actualResult = viewCommand.execute().getMessage();
        assertEquals(expectedResult, actualResult);
    }

    @Test
    public void constructViewCommand_byTitle_noSuchTitle() {
        try {
            new ViewCommand("dummy");
        } catch (RecipeNotFoundException e) {
            assertEquals(e.getMessage(),
                    new RecipeNotFoundException("dummy").getMessage());
        }
    }

    @Test
    public void constructViewCommand_byTitle_emptyTitle() {
        try {
            new ViewCommand("");
        } catch (RecipeNotFoundException e) {
            assertEquals(e.getMessage(),
                    new RecipeNotFoundException("").getMessage());
        }
    }

    @Test
    void completeViewCommand_correctViewCommandFormat_showSpecificRecipe() throws RecipeNotFoundException {
        String testTitle = "Test Title for View Command";
        Recipe addedRecipe = new Recipe(testTitle);
        RecipeList.addRecipe(addedRecipe);
        RecipeList.addRecipeTitle(addedRecipe.getTitle());
        int index = RecipeList.getRecipeIndexFromTitle(testTitle) + 1;
        String input = "/view -id " + index;
        String expected = "TITLE:\n" + "Test Title for View Command\n" + "\n" + "DESCRIPTION:\n" + "\n" + "\n"
                + "INGREDIENTS: \n" + "\n" + "STEPS: \n";
        Command commandExecuted = Parser.parseCommand(input);
        CommandResult commandExecutedResult = commandExecuted.execute();
        assertEquals(expected, commandExecutedResult.getMessage());
        assertEquals(ViewCommand.class, Parser.parseCommand(input).getClass());
        RecipeList.deleteRecipeFromTitle(testTitle);
        Storage.deleteRecipeFile(testTitle);
        Storage.rewriteRecipeListToFile();
    }

}