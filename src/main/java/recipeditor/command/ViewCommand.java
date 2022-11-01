package recipeditor.command;

import recipeditor.recipe.Recipe;
import recipeditor.recipe.RecipeList;
import recipeditor.ui.Ui;

public class ViewCommand extends Command {
    public static final String COMMAND_TYPE = "/view";
    public static final String COMMAND_SYNTAX = "/view <index>";
    public static final String COMMAND_FUNCTION = "View the full recipe of "
            + "the sepcific index in the list of recipes";


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
        super(COMMAND_SYNTAX, COMMAND_FUNCTION);
        this.index = index;
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
            Ui.showMessageInline("Current number of saved recipes:", Integer.toString(RecipeList.getSize()));
            return new CommandResult("View recipe index out of bound.");
        }
    }
}
