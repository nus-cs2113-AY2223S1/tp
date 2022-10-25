package recipeditor.edit;

import recipeditor.exception.InvalidFlagException;
import recipeditor.exception.ParseException;
import recipeditor.recipe.Recipe;

public class View extends EditModeCommand {

    private Recipe oldRecipe;

    public View(String[] parsedCommand, Recipe recipe, Recipe oldRecipe) {
        super(parsedCommand, recipe);
        this.oldRecipe = oldRecipe;
    }

    @Override
    public Recipe execute() throws InvalidFlagException, ParseException {
        message = showRecipeChanges(recipe, oldRecipe);
        return recipe;
    }

}
