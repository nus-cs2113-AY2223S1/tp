package recipeditor.command;

import recipeditor.recipe.RecipeList;

public class ListCommand extends Command {
    public static final String COMMAND_TYPE = "/list";
    public static final String COMMAND_NAME = "list";
    private static final String COMMAND_FUNCTION = "Show a list of all recipe titles in recipeditor.";

    public ListCommand() {
        super(COMMAND_TYPE, COMMAND_FUNCTION);
    }

    /**
     * execute list command, store all recipes in a string in the
     * format of index followed by recipe description.
     *
     * @return a compiled list of all recipes
     */
    public CommandResult execute() {

        StringBuilder recipeTitlesList = new StringBuilder();
        recipeTitlesList.append(RecipeList.printNumberOfRecipes());
        for (int i = 0; i < RecipeList.getRecipeTitlesSize(); i++) {
            recipeTitlesList.append(String.format("%n%d. %s", i + 1, RecipeList.getRecipeTitle(i)));
        }
        return new CommandResult(recipeTitlesList.toString());
    }

}
