package recipeditor.command;

import recipeditor.recipe.RecipeList;
import recipeditor.storage.Storage;
import recipeditor.ui.Ui;

public class DeleteCommand extends Command {
    public static final String COMMAND_TYPE = "/delete";
    public static final String COMMAND_NAME = "delete";
    private static final String COMMAND_SYNTAX =
            '\n' + "\t /delete -id <recipe_index>"
            + '\n' + "\t /delete -t <recipe_title>";
    private static final String COMMAND_FUNCTION =
            "Delete the recipe of given title or index from recipeditor.";
    public static final String CORRECT_FORMAT = "The input should be '/delete <flag> <argument>.'";
    private static final String OUT_OF_BOUND = "Delete recipe index out of bound.";
    private static final String CURRENT = "Current number of saved recipes:";
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
            Storage.rewriteRecipeListToFile();
            return new CommandResult(String.format("\n" + recipeTitleToDelete
                    + " is deleted from the recipe list.%n"));
        } catch (Exception e) {
            Ui.showMessageInline(CURRENT, Integer.toString(RecipeList.getRecipeSize()));
            return new CommandResult(OUT_OF_BOUND);
        }
    }
}