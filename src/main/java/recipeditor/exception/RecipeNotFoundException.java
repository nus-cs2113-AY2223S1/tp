package recipeditor.exception;

import recipeditor.recipe.Recipe;
import recipeditor.recipe.RecipeList;

public class RecipeNotFoundException extends Exception {

    public RecipeNotFoundException(String recipeTitle) {
        super(recipeTitle + " is not found in the recipe list.");
    }
}
