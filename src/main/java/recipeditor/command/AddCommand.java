package recipeditor.command;

import recipeditor.recipe.Recipe;
import recipeditor.recipe.RecipeList;
import recipeditor.storage.Storage;

public class AddCommand extends Command {
    public static final String COMMAND_TYPE = "/add";
    private static final String COMMAND_SYNTAX = "/add";
    private static final String COMMAND_FUNCTION = "Edit a new recipe and add it to recipeditor.";
    private Recipe addedRecipe;
    private boolean isValid;

    public AddCommand() {
        super(COMMAND_SYNTAX, COMMAND_FUNCTION);
    }

    public AddCommand(boolean isValid, Recipe addedRecipe) {
        this();
        this.isValid = isValid;
        this.addedRecipe = addedRecipe;
    }


    /**
     * Execute an add command, which adds recipe into RecipeList and storage.
     *
     * @return CommandResult successful or failed addition message
     */
    public CommandResult execute() {
        if (isValid) {
            assert addedRecipe != null;
            RecipeList.addRecipe(addedRecipe);
            RecipeList.addRecipeTitle(addedRecipe.getTitle());
            Storage.rewriteRecipeListToFile();
            String recipeFileSourcePath = Storage.titleToFilePath(addedRecipe.getTitle());
            Storage.saveRecipe(addedRecipe, "", recipeFileSourcePath);
            StringBuilder response = new StringBuilder();
            response.append("\"" + addedRecipe.getTitle() + "\" added to the recipe list.\n");
            response.append(String.format("There are %d recipes in the recipe list",
                    RecipeList.getRecipeTitlesSize()));

            return new CommandResult(response.toString());
        } else {
            return new CommandResult("Nothing was added");
        }
    }
}
