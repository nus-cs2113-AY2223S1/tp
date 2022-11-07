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
        Recipe addedRecipe = new Recipe("Test Title");
        RecipeList.addRecipe(addedRecipe);
        RecipeList.addRecipeTitle(addedRecipe.getTitle());
        String input = "/list";
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        Command commandExecuted = Parser.parseCommand(input);
        CommandResult commandExecutedResult = commandExecuted.execute();
        System.setOut(new PrintStream(outContent));
        Ui.showMessage(commandExecutedResult.getMessage());
        StringBuilder recipeTitlesList = new StringBuilder();
        recipeTitlesList.append(RecipeList.printNumberOfRecipes());
        for (int i = 0; i < RecipeList.getRecipeTitlesSize(); i++) {
            recipeTitlesList.append(String.format("%n%d. %s", i + 1, RecipeList.getRecipeTitle(i)));
        }
        String expected = recipeTitlesList.toString();
        assertEquals(expected.trim(), outContent.toString().trim());
        assertEquals(ListCommand.class, Parser.parseCommand(input).getClass());
        RecipeList.deleteRecipeFromTitle("Test Title");
    }

}
