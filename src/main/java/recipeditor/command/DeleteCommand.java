package recipeditor.command;

import recipeditor.Recipeditor;
import recipeditor.recipe.Recipe;
import recipeditor.recipe.RecipeList;
import recipeditor.storage.Storage;
import recipeditor.ui.Ui;

import java.io.FileNotFoundException;
import java.util.logging.Level;

public class DeleteCommand extends Command {
    public static final String COMMAND_TYPE = "/delete";
    public static final String CORRECT_FORMAT = "The input should be '/delete (recipeTitle).'";
    private int index;
    private String recipeTitleToDelete;

    /**
     * Construct a delete command including task to delete.
     *
     * @param index the index of task to delete
     */
    public DeleteCommand(String recipeTitleToDelete) {
//        this.index = index;
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
            return new CommandResult(String.format(recipeTitleToDelete + " is deleted.%n"));
        } catch (IndexOutOfBoundsException e) {
            Ui.showMessageInline("Current number of saved recipes:", Integer.toString(RecipeList.getSize()));
            return new CommandResult("Delete recipe index out of bound.");
        }
    }

    public static String getCommandType() {
        return COMMAND_TYPE;
    }

}

