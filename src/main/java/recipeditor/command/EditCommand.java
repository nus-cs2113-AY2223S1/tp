package recipeditor.command;

import recipeditor.recipe.Recipe;
import recipeditor.recipe.RecipeList;

public class EditCommand extends Command {
    public static final String COMMAND_TYPE = "/edit";
    private boolean editSuccess;
    private int index;
    private Recipe editedRecipe;

    public EditCommand(boolean editSuccess, int recipeIndex, Recipe editedRecipe) {
        this.editSuccess = editSuccess;
        this.index = recipeIndex;
        this.editedRecipe = editedRecipe;
    }

    @Override
    public CommandResult execute() {
        if (editSuccess) {
            RecipeList.editRecipe(index, editedRecipe);
            return new CommandResult(editedRecipe.getTitle() + " edited.");
        } else {
            return new CommandResult("Edit failed.");
        }
    }
}
