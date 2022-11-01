package recipeditor.command;

import recipeditor.recipe.RecipeList;
import recipeditor.ui.Ui;

public class ListCommand extends Command {
    public static final String COMMAND_TYPE = "/list";

    /**
     * execute list command, store all recipes in a string in the
     * format of index followed by recipe description.
     *
     * @return a compiled list of all recipes
     */
    public CommandResult execute() {
        try {
            StringBuilder recipeTitlesList = new StringBuilder();
            recipeTitlesList.append(String.format("There are %d recipes in the recipe list",
                    RecipeList.getRecipes().size()));
            for (int i = 0; i < RecipeList.getRecipeTitlesSize(); i++) {
                recipeTitlesList.append(String.format("%n%d. %s", i + 1, RecipeList.getRecipeTitle(i)));
            }
            return new CommandResult(recipeTitlesList.toString());
        } catch (Exception e) {
            return new CommandResult("An error happened.");
        }
    }
}
