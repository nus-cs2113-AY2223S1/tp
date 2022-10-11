package recipeditor.command;

import recipeditor.recipe.Recipe;
import recipeditor.recipe.RecipeList;
import recipeditor.ui.Ui;

public class ViewCommand extends Command {
    public static final String COMMAND_TYPE = "view";
    public static final String VIEW_MESSAGE = "Which recipe to view?";

    private final int index;

    /**
     * Construct a view command including task to view.
     *
     * @param index the index of task view
     */
    public ViewCommand(int index) {
        this.index = index;
    }


    /**
     * View the recipe at the given index.
     *
     * @return the result message from execute
     */
    public CommandResult execute() {
        try {
            Recipe recipe = RecipeList.getRecipes().get(index);
            String result = String.format("%nName: %s%nDescription: %s%n",
                    recipe.getTitle(), recipe.getDescription());
            //TODO: have a display all details function from recipe
            return new CommandResult(result);
        } catch (IndexOutOfBoundsException e) {
            Ui.showMessageInline("Current number of saved recipes:", Integer.toString(RecipeList.getRecipes().size()));
            return new CommandResult("View recipe index out of bound.");
        }
    }
}
