package recipeditor.command;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import recipeditor.parser.Parser;
import recipeditor.recipe.Recipe;
import recipeditor.recipe.RecipeList;
import recipeditor.ui.Ui;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class ListCommandTest {
    @Test
    void completeListCommand_correctListCommandFormat_listRecipeTitles() {
        Recipe addedRecipe = new Recipe("Example Title");
        RecipeList.addRecipe(addedRecipe);
        RecipeList.addRecipeTitle(addedRecipe.getTitle());
        String input = "/list";
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        Command commandExecuted = Parser.parseCommand(input);
        CommandResult commandExecutedResult = commandExecuted.execute();
        System.setOut(new PrintStream(outContent));
        Ui.showMessage(commandExecutedResult.getMessage());
        String expected = "There are 1 recipes in the recipe list" + System.lineSeparator() + "1. Example Title";
        assertEquals(expected.trim(), outContent.toString().trim());
        assertEquals(ListCommand.class, Parser.parseCommand(input).getClass());
        RecipeList.deleteRecipeFromTitle("Example Title");
    }

}
