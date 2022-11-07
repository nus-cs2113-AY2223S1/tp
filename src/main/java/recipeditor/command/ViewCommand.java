package recipeditor.command;

import recipeditor.exception.RecipeNotFoundException;
import recipeditor.recipe.Recipe;
import recipeditor.recipe.RecipeList;
import recipeditor.ui.Ui;

public class ViewCommand extends Command {
    public static final String COMMAND_TYPE = "/view";
    private static final String COMMAND_FUNCTION = "View the full recipe of "
            + "the specific index or title in the list of recipes";
    private static final String COMMAND_SYNTAX = "\n" + "\t /view -id <recipe_index>"
            + '\n' + "\t /view -t <recipe_title> ";
    public static final String COMMAND_NAME = "view";
    public static final String CORRECT_FORMAT = "The input should be '/view <flag> <argument>.'";
    private static final String CURRENT = "Current number of saved recipes:";
    private static final String OUT_OF_BOUND = "View recipe index out of bound.";

    private int index;

    public ViewCommand() {
        super(COMMAND_SYNTAX, COMMAND_FUNCTION);
    }

    /**
     * Construct a view command including task to view.
     *
     * @param index the index of task view
     */
    public ViewCommand(int index) {
        this();
        this.index = index;
    }

    /**
     * Construct a view command including task to view.
     *
     * @param title the title of task view
     */
    public ViewCommand(String title) throws RecipeNotFoundException {
        this();
        this.index = RecipeList.getRecipeIndexFromTitle(title);
    }


    /**
     * View the recipe at the given index.
     *
     * @return the result message from execute
     */
    public CommandResult execute() {
        try {
            Recipe recipe = RecipeList.getRecipe(index);
            String commandResultOutput = recipe.getRecipeAttributesFormatted();
            return new CommandResult(commandResultOutput);
        } catch (IndexOutOfBoundsException e) {
            Ui.showMessageInline(CURRENT, Integer.toString(RecipeList.getRecipeSize()));
            return new CommandResult(OUT_OF_BOUND);
        }
    }
}
