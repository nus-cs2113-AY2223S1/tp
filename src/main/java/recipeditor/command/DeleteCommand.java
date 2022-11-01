package recipeditor.command;

import recipeditor.edit.Delete;
import recipeditor.recipe.RecipeList;
import recipeditor.storage.Storage;
import recipeditor.ui.Ui;

public class DeleteCommand extends Command {
    public static final String COMMAND_TYPE = "/delete";
    private static final String COMMAND_SYNTAX = "/delete <title>";
    private static final String COMMAND_FUNCTION = "Delete the recipe of given title from recipeditor.";
    public static final String CORRECT_FORMAT = "The input should be '/delete (index or title).'";
    private String recipeTitleToDelete;

    public DeleteCommand() {
        super(COMMAND_SYNTAX, COMMAND_FUNCTION);
    }

    /**
     * Construct a delete command including task to delete.
     */
    public DeleteCommand(String recipeTitleToDelete) {
        this();
        this.recipeTitleToDelete = recipeTitleToDelete;
    }

    /**
     * Construct a delete command including index to delete.
     *
     */
    public DeleteCommand(int recipeIndexToDelete) {
        try {
            this.recipeTitleToDelete = RecipeList.getTitleFromIndex(recipeIndexToDelete);
        } catch (IndexOutOfBoundsException i) {
            this.recipeTitleToDelete = null;
        }
    }

    /**
     * Delete the recipe at the given title or index.
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
        } catch (Exception e) {
            Ui.showMessageInline("Current number of saved recipes:", Integer.toString(RecipeList.getSize()));
            return new CommandResult("Delete recipe index out of bound.");
        }
    }
}

