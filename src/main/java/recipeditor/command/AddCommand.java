package recipeditor.command;

import recipeditor.recipe.Recipe;
import recipeditor.recipe.RecipeList;
import recipeditor.storage.Storage;

public class AddCommand extends Command {
    public static final String COMMAND_TYPE = "/add";
    public static final String COMMAND_NAME = "add";
    private static final String COMMAND_FUNCTION = "Edit a new recipe and add it to recipeditor.";

    private static final String NOTHING = "Nothing was added";

    private Recipe addedRecipe;
    private boolean isValid;

    public AddCommand() {
        super(COMMAND_TYPE, COMMAND_FUNCTION);
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
            response.append(String.format("\"%s\" added to the recipe list.\n",addedRecipe.getTitle()));
            response.append(RecipeList.printNumberOfRecipes());

            return new CommandResult(response.toString());
        } else {
            return new CommandResult(NOTHING);
        }
    }
}
