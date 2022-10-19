package recipeditor.command;

import recipeditor.recipe.Recipe;
import recipeditor.recipe.RecipeList;

public class EditCommand extends Command {
    public static final String COMMAND_TYPE = "/edit";
    private int index;
    private Recipe editedRecipe;

    public EditCommand(int recipeIndex, Recipe editedRecipe) {
        this.index = recipeIndex;
        this.editedRecipe = editedRecipe;
    }

    @Override
    public CommandResult execute() {
        RecipeList.editRecipe(index, editedRecipe);
        return new CommandResult(editedRecipe.getTitle() + " edited.");
    }
}
