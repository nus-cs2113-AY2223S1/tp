package recipeditor.command;

import recipeditor.recipe.Recipe;
import recipeditor.recipe.RecipeList;

public class AddCommand extends Command {
    public static final String COMMAND_TYPE = "add";
    private Recipe addedRecipe = new Recipe();
    private boolean isValid;

    public AddCommand(boolean isValid, Recipe addedRecipe) {
        this.isValid = isValid;
        this.addedRecipe = addedRecipe;
    }

    public CommandResult execute() {
        // TODO: Execution of command
        if (isValid) {
            RecipeList.getRecipes().add(addedRecipe); //HERE SEEM TO THROW ERROR
            return new CommandResult(COMMAND_TYPE + " Added");
        } else {
            return new CommandResult(COMMAND_TYPE + " Invalid");
        }
    }

}
