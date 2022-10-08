package recipeditor.command;

import recipeditor.recipe.Recipe;
import recipeditor.recipe.RecipeList;

public class DeleteCommand extends Command {

    public static final String COMMAND_TYPE = "delete";
  
    private final int index;

    /**
     * Construct a delete command including task to delete.
     *
     * @param index the index of task to delete
     */
    public DeleteCommand(int index) {
        this.index = index;
    }

    /**
     * get the index of task to delete.
     *
     * @return the index of task to delete
     */
    public int getIndex() {
        return this.index;
    }

    public CommandResult execute() {
        Recipe deletedRecipe = RecipeList.getRecipes().get(index);
        RecipeList.getRecipes().remove(index);
        return new CommandResult(deletedRecipe + " is deleted.%n");
    }

}

