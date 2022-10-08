package recipeditor.ui;

import recipeditor.recipe.Ingredient;
import recipeditor.recipe.Recipe;

public class AddMode {
    private static final String TITLE = "Recipe Title";
    private static final String DESCRIPTION = "Recipe Description";
    private static final String INGREDIENTS = "Add Ingredients in this format";
    private static final String ING_FORMAT = "\t <ingredient name> / <amount> / <unit>";
    private static final String DONE = "Type \"done\" to finish adding";

    private Recipe addedRecipe = new Recipe();


    public void enterAddMode() {
        askTitle();
        askDescription();
        askIngredients();
        addRecipe();
    }

    private void askTitle() {
        Ui.showMessage(TITLE);
        String input = Ui.readInput();
        addedRecipe.setTitle(input);
    }

    private void askDescription() {
        Ui.showMessage(DESCRIPTION);
        String input = Ui.readInput();
        addedRecipe.setDescription(input);
    }

    private void askIngredients() {
        Ui.showMessage(INGREDIENTS, ING_FORMAT, DONE);
        String input = "";
        while (!(input.equalsIgnoreCase("done"))) {
            input = Ui.readInput();
            parsedIngredients(input);
        }
    }

    //TODO: Check for Exception
    private void parsedIngredients(String input) {
        String[] parsed = input.split("/");
        addedRecipe.getIngredients().add(new Ingredient(parsed[0],Integer.parseInt(parsed[1]),parsed[2]));
    }

    private void addRecipe() {
        //TODO: Add addedRecipe to Recipe List
    }

}
