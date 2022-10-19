package recipeditor.command;

import recipeditor.recipe.Recipe;
import recipeditor.recipe.RecipeList;
import recipeditor.storage.Storage;

import java.util.logging.Level;
import java.util.logging.Logger;

public class AddCommand extends Command {
    public static final String COMMAND_TYPE = "add";
    private Recipe addedRecipe;
    private boolean isValid;

    public AddCommand(boolean isValid, Recipe addedRecipe) {
        this.isValid = isValid;
        this.addedRecipe = addedRecipe;
    }

    public CommandResult execute() {
        // TODO: Execution of command
        if (isValid) {
            assert addedRecipe != null;
            RecipeList.addRecipe(addedRecipe); //HERE SEEM TO THROW ERROR
            Storage.writeRecipeListToFile();
            return new CommandResult(addedRecipe.getTitle() + " added to the recipe.");
        } else {
            return new CommandResult("invalid " + COMMAND_TYPE);
        }
    }
}
