package recipeditor.command;

import recipeditor.recipe.Recipe;
import recipeditor.recipe.RecipeList;

public class ListCommand extends Command {
    public static final String TYPE = "list";

    /**
     * execute list command, store all recipes in a string in the
     * format of <index> <recipe descreption>
     * @return a compiled list of all recipes
     */
    public CommandResult execute() {
        String result = "";
        int i = 1;
        for(Recipe recipe : RecipeList.getRecipes()) {
            result += String.format("%n%d. %s", i++, recipe.toString());
        }
        return new CommandResult(result);
    }
}

