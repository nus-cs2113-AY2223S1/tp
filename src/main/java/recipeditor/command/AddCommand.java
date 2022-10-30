package recipeditor.command;

import recipeditor.recipe.Recipe;
import recipeditor.recipe.RecipeList;
import recipeditor.storage.Storage;

public class AddCommand extends Command {
    public static final String COMMAND_TYPE = "/add";
    private Recipe addedRecipe;
    private boolean isValid;

    public AddCommand(boolean isValid, Recipe addedRecipe) {
        this.isValid = isValid;
        this.addedRecipe = addedRecipe;
    }

    public CommandResult execute() {
        if (isValid) {
            RecipeList.addRecipe(addedRecipe); //HERE SEEM TO THROW ERROR
            RecipeList.recipeTitles.add(addedRecipe.getTitle());
            Storage.appendRecipeToAllRecipeFile(addedRecipe);
            String recipeFileSourcePath = Storage.RECIPES_FOLDER_PATH + "/" + addedRecipe.getTitle();
            Storage.saveRecipeFile(Storage.TEMPORARY_FILE_PATH, recipeFileSourcePath);
            assert addedRecipe != null;
            return new CommandResult(addedRecipe.getTitle() + " added to the recipe.");
        } else {
            return new CommandResult("Add unsuccessful");
        }
    }
}
