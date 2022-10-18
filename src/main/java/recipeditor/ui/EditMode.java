package recipeditor.ui;

import recipeditor.recipe.Recipe;
import recipeditor.recipe.RecipeList;

public class EditMode {
    private Recipe originalRecipe;
    private Recipe editedRecipe;

    public void enterEditMode(String recipeTitle) {
        this.originalRecipe = RecipeList.getRecipeFromTitle(recipeTitle);
        if (originalRecipe != null) {
            boolean quit = false;
            String input = "";
            while (!quit) {
                input = Ui.readInput();
                parseEditInput(input);
            }
        }
    }

    public void exitEditMode() {

    }

    public Recipe getEditedRecipe() {
        return editedRecipe;
    }

    private void parseEditInput(String input) {

    }
}
