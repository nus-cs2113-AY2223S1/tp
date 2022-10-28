package recipeditor.command;

import recipeditor.recipe.RecipeList;

public class ListCommand extends Command {
    public static final String COMMAND_TYPE = "/list";

    /**
     * execute list command, store all recipes in a string in the
     * format of index followed by recipe description.
     *
     * @return a compiled list of all recipes
     */
    public CommandResult execute() {
        StringBuilder recipeTitlesList = new StringBuilder();
        for (int i = 0; i < RecipeList.recipeTitles.size(); i++) {
            recipeTitlesList.append(String.format("%n%d. %s", i + 1, RecipeList.recipeTitles.get(i)));
        }
        return new CommandResult(recipeTitlesList.toString());
    }
}
