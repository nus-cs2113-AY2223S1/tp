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
        int i = 1;
        for (Recipe recipe : RecipeList.getRecipes()) {
            result.append(String.format("%n%d. %s: %s",
                    i++, recipe.getTitle(), recipe.getDescription()));
        }
        return new CommandResult(result.toString());
    }

}

