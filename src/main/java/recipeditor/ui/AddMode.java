package recipeditor.ui;

import recipeditor.recipe.Ingredient;
import recipeditor.recipe.Recipe;
import recipeditor.recipe.RecipeList;

public class AddMode {
    private static final String TITLE = "Recipe Title";
    private static final String DESCRIPTION = "Recipe Description";
    private static final String INGREDIENTS = "Add Ingredients in this format";
    private static final String ING_FORMAT = "\t <ingredient name> / <amount> / <unit>";
    private static final String DONE = "Type \"done\" to finish adding";
    private static final String ENTER = "ENTERING ADD MODE";
    private static final String EXIT = "EXITING ADD MODE";

    private Recipe addedRecipe = new Recipe();


    public void enterAddMode() {
        Ui.clear();
        Ui.showMessage(ENTER, DONE);
        String input = "";
        while (!input.equalsIgnoreCase("done")) {
            input = Ui.readInput();
            Ui.showMessage("Echo: ", input);
        }
        // askTitle();
        // askDescription();
        // askIngredients();
        // addRecipe();
    }

    public void exitAddMode() {
        Ui.showMessage(EXIT);
        Ui.showDivider();
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
        String[] parsed = input.split("/", 3);
        try {
            double amount = convertToDouble(parsed[1]);
            addedRecipe.getIngredients().add(new Ingredient(parsed[0], amount, parsed[2]));
        } catch (Exception e) {
            Ui.showMessage(e.getMessage());
        }
    }

    private double convertToDouble(String amount) throws Exception {
        double amountDouble = Double.parseDouble(amount);
        return amountDouble;
    }

    private void addRecipe() {
        //TODO: Add addedRecipe to Recipe List
        RecipeList.getRecipes().add(addedRecipe);
    }

}
