package recipeditor.command;

import recipeditor.recipe.RecipeList;
import recipeditor.storage.Storage;
import recipeditor.ui.Ui;

public class DeleteCommand extends Command {
    public static final String COMMAND_TYPE = "/delete";
    public static final String CORRECT_FORMAT = "The input should be '/delete (recipeTitle).'";
    private String recipeTitleToDelete;

    /**
     * Construct a delete command including task to delete.
     *
     */
    public DeleteCommand(String recipeTitleToDelete) {
        this.recipeTitleToDelete = recipeTitleToDelete;
    }

    /**
     * Delete the recipe at the given index.
     *
     * @return the result message from execute
     */
    public CommandResult execute() {
        try {
            RecipeList.deleteRecipeFromTitle(recipeTitleToDelete);
            Storage.deleteRecipeFile(recipeTitleToDelete);
            Storage.rewriteRecipeListToFile(Storage.ALL_RECIPES_FILE_PATH);
            return new CommandResult(String.format("\n" + recipeTitleToDelete
                    + " is deleted from the recipe list. %n"));
        } catch (IndexOutOfBoundsException e) {
            Ui.showMessageInline("Current number of saved recipes:", Integer.toString(RecipeList.getSize()));
            return new CommandResult("Delete recipe index out of bound.");
        }
    }

    public static String getCommandType() {
        return COMMAND_TYPE;
    }

}

