package recipeditor.command;

import recipeditor.recipe.Recipe;
import recipeditor.recipe.RecipeList;
import recipeditor.storage.Storage;
import recipeditor.ui.Ui;

public class AddCommand extends Command {
    public static final String COMMAND_TYPE = "/add";
    private final Recipe addedRecipe;
    private final boolean isValid;

    public AddCommand(boolean isValid, Recipe addedRecipe) {
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
            RecipeList.addRecipe(addedRecipe);
            RecipeList.recipeTitles.add(addedRecipe.getTitle());
            Storage.rewriteRecipeListToFile(Storage.ALL_RECIPES_FILE_PATH);
            String recipeFileSourcePath = Storage.titleToFilePath(addedRecipe.getTitle());
            Storage.saveRecipe(addedRecipe, "", recipeFileSourcePath);

            StringBuilder response = new StringBuilder();
            response.append("\"" + addedRecipe.getTitle() + "\" added to the recipe list.\n");
            response.append(String.format("There are %d recipes in the recipe list",
                    RecipeList.recipeTitles.size()));

            return new CommandResult(response.toString());
        } else {
            return new CommandResult("Nothing was added");
        }
    }
}
