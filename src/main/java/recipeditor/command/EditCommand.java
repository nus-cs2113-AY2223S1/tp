package recipeditor.command;

import recipeditor.recipe.Recipe;
import recipeditor.recipe.RecipeList;

public class EditCommand extends Command {
    public static final String COMMAND_TYPE = "/edit";
    public static final String COMMAND_SYNTAX = "Syntax for /edit \n" + "\t /edit <index>";
    private final boolean editSuccess;
    private final int index;
    private final Recipe editedRecipe;

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
