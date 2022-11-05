package recipeditor.command;

import recipeditor.recipe.RecipeList;
import recipeditor.recipe.Recipe;
import recipeditor.storage.Storage;

import java.io.File;


public class ExitCommand extends Command {
    public static final String EXIT_MESSAGE = "RecipEditor ends here...";
    private static final String COMMAND_SYNTAX = "/exit";
    private static final String COMMAND_FUNCTION = "Exit recipeditor.";

    public ExitCommand() {
        super(COMMAND_SYNTAX, COMMAND_FUNCTION);
    }

    /**
     * Execute exit command, regenerate AllRecipes and the appropriate recipe files.
     *
     * @return CommandResult a message of exiting software
     */
    public CommandResult execute() {
        // AllRecipe
        Storage.rewriteRecipeListToFile(Storage.ALL_RECIPES_FILE_PATH);

        //Delete all recipes in the folder
        File directory = new File(Storage.RECIPES_FOLDER_PATH);
        for (File file : directory.listFiles()) {
            file.delete();
        }
        //Overwrite the files
        for (Recipe r : RecipeList.getRecipes()) {
            String recipeFileSourcePath = Storage.titleToFilePath(r.getTitle());
            Storage.saveRecipe(r, "", recipeFileSourcePath);
        }
        return new CommandResult(EXIT_MESSAGE);
    }
}
