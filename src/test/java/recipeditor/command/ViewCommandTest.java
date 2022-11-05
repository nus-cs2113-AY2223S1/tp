package recipeditor.command;

import org.junit.jupiter.api.Test;
import recipeditor.parser.Parser;
import recipeditor.recipe.Recipe;
import recipeditor.recipe.RecipeList;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ViewCommandTest {

    @Test
    void completeViewCommand_correctViewCommandFormat_showSpecificRecipe() {
        Recipe addedRecipe = new Recipe("Example Title for View Command");
        RecipeList.addRecipe(addedRecipe);
        RecipeList.addRecipeTitle(addedRecipe.getTitle());
        String input = "/view -id 1";
        String expected = "TITLE:\n" + "Example Title for View Command\n" + "\n" + "DESCRIPTION:\n" + "\n" + "\n"
                + "INGREDIENTS: \n" + "\n" + "STEPS: \n" + "\n" + "\n";
        Command commandExecuted = Parser.parseCommand(input);
        CommandResult commandExecutedResult = commandExecuted.execute();
        assertEquals(expected, commandExecutedResult.getMessage());
        assertEquals(ViewCommand.class, Parser.parseCommand(input).getClass());
        RecipeList.deleteRecipeFromTitle("Example Title for View Command");
    }
}