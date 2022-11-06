package recipeditor.command;

import org.junit.jupiter.api.Test;
import recipeditor.parser.Parser;
import recipeditor.recipe.Recipe;
import recipeditor.recipe.RecipeList;
import recipeditor.storage.Storage;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ViewCommandTest {

    @Test
    void completeViewCommand_correctViewCommandFormat_showSpecificRecipe() {
        String testTitle = "Test Title for View Command";
        Recipe addedRecipe = new Recipe(testTitle);
        RecipeList.addRecipe(addedRecipe);
        RecipeList.addRecipeTitle(addedRecipe.getTitle());
        String input = "/view -id 1";
        String expected = "TITLE:\n" + "Test Title for View Command\n" + "\n" + "DESCRIPTION:\n" + "\n" + "\n"
                + "INGREDIENTS: \n" + "\n" + "STEPS: \n" + "\n" + "\n";
        Command commandExecuted = Parser.parseCommand(input);
        CommandResult commandExecutedResult = commandExecuted.execute();
        assertEquals(expected, commandExecutedResult.getMessage());
        assertEquals(ViewCommand.class, Parser.parseCommand(input).getClass());
        RecipeList.deleteRecipeFromTitle(testTitle);
        Storage.deleteRecipeFile(testTitle);
        Storage.rewriteRecipeListToFile();
    }
}