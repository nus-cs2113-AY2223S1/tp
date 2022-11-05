package recipeditor.command;

import recipeditor.exception.RecipeNotFoundException;
import recipeditor.recipe.Recipe;
import recipeditor.recipe.RecipeList;
import recipeditor.ui.Ui;

public class ViewCommand extends Command {
    public static final String COMMAND_TYPE = "/view";
    public static final String COMMAND_FUNCTION = "View the full recipe of "
            + "the sepcific index in the list of recipes";
    public static final String COMMAND_SYNTAX = "Syntax for /view: \n" + "\t /view <index or title>";

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
            Ui.showMessageInline("Current number of saved recipes:", Integer.toString(RecipeList.getRecipeSize()));
            return new CommandResult("View recipe index out of bound.");
        }
    }
}
