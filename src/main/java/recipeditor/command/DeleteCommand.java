package recipeditor.command;

import recipeditor.recipe.Recipe;
import recipeditor.recipe.RecipeList;
import recipeditor.storage.Storage;
import recipeditor.ui.Ui;

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
     * Delete the recipe at the given index.
     *
     * @return the result message from execute
     */
    public CommandResult execute() {
        try {
            Recipe deletedRecipe = RecipeList.getRecipe(index);
            RecipeList.deleteRecipe(index);
            Storage.writeRecipeListToFile();
            return new CommandResult(String.format(
                    deletedRecipe.getTitle() + " is deleted.%n"));
        } catch (IndexOutOfBoundsException e) {
            Ui.showMessageInline("Current number of saved recipes:", Integer.toString(RecipeList.getSize()));
            return new CommandResult("Delete recipe index out of bound.");
        }
    }

    public static String getCommandType() {
        return COMMAND_TYPE;
    }

}

