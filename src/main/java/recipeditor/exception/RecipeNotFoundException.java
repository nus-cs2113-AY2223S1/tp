package recipeditor.exception;

public class RecipeNotFoundException extends Exception {

    public RecipeNotFoundException(String recipeTitle) {
        super(recipeTitle + " is not found in the recipe list.");
    }
}
