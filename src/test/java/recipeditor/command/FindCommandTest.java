package recipeditor.command;

import org.junit.jupiter.api.Test;
import recipeditor.parser.Parser;
import recipeditor.recipe.Ingredient;
import recipeditor.recipe.Recipe;
import recipeditor.recipe.RecipeList;
import recipeditor.storage.Storage;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class FindCommandTest {

    @Test
    void correctFindRecipeTitleCommand_correctFindCommandFormat_listOfRecipeTitles() {
        Recipe addedRecipe = new Recipe("Example title", "Example description");
        RecipeList.addRecipe(addedRecipe);
        RecipeList.addRecipeTitle(addedRecipe.getTitle());
        Storage.rewriteRecipeListToFile(Storage.ALL_RECIPES_FILE_PATH);
        String recipeFileSourcePath = Storage.titleToFilePath(addedRecipe.getTitle());
        Storage.saveRecipe(addedRecipe, "", recipeFileSourcePath);
        String input = "/find -t title";
        assertEquals(FindCommand.class, Parser.parseCommand(input).getClass());
    }

    @Test
    void correctFindIngredientNameCommand_correctFindCommandFormat_listOfRecipeTitles() {
        Recipe addedRecipe = new Recipe("Example title", "Example description");
        Ingredient newIngredient = new Ingredient("new ingredient", 1, "whole");
        addedRecipe.addIngredient(newIngredient);
        String input = "/find -i ing";
        assertEquals(FindCommand.class, Parser.parseCommand(input).getClass());
    }

    @Test
    void incorrectHelpCommand_missingRecipeFlag_syntaxForFindCommand() {
        String input = "/find ing";
        Command commandExecuted = Parser.parseCommand(input);
        CommandResult commandExecutedResult = commandExecuted.execute();
        String expected = "The correct format should be '/find -<flag> <recipe title/ingredient name>'.\n"
                + "Flags:\n"
                + "-t: Recipe Title\n"
                + "-i: Ingredient name";
        assertEquals(expected, commandExecutedResult.getMessage());
    }

    @Test
    void incorrectHelpCommand_invalidInput_syntaxForFindCommand() {
        String input = "/find -flag input";
        Command commandExecuted = Parser.parseCommand(input);
        CommandResult commandExecutedResult = commandExecuted.execute();
        String expected = "The correct format should be '/find -<flag> <recipe title/ingredient name>'.\n"
                + "Flags:\n"
                + "-t: Recipe Title\n"
                + "-i: Ingredient name";
        assertEquals(expected, commandExecutedResult.getMessage());
        RecipeList.deleteRecipeFromTitle("Example title");
        Storage.deleteRecipeFile("Example title");
        Storage.rewriteRecipeListToFile(Storage.ALL_RECIPES_FILE_PATH);
    }

}
