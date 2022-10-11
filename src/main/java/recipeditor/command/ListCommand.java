package recipeditor.command;

import recipeditor.recipe.Recipe;
import recipeditor.recipe.RecipeList;

public class ListCommand extends Command {
    public static final String COMMAND_TYPE = "list";

    /**
     * execute list command, store all recipes in a string in the
     * format of index followed by recipe description.
     *
     * @return a compiled list of all recipes
     */
    public CommandResult execute() {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < RecipeList.getSize(); i++) {
            result.append(String.format("%n%d. %s", i+1, RecipeList.getRecipe(i).getTitle()));
        }
        return new CommandResult(result.toString());
    }
}

