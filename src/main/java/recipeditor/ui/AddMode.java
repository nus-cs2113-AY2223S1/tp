package recipeditor.ui;

import recipeditor.recipe.Ingredient;
import recipeditor.recipe.Recipe;
import recipeditor.recipe.RecipeList;

public class AddMode {
    private static final String TITLE = "Recipe Title";
    private static final String DESCRIPTION = "Recipe Description";
    private static final String INGREDIENTS = "Add at least 1 ingredients in this format:  ";
    private static final String FORMAT = "<ingredient name> / <amount_in_float> / <unit>  ";
    private static final String DONE_REMINDER = "Type \"done\" to finish adding";
    private static final String DONE = "done";
    private static final String ENTER = "ENTERING ADD MODE";
    private static final String EXIT = "EXITING ADD MODE";
    private static final String ERROR_PARSING = "Error Parsing Ingredients";

    public Recipe addedRecipe = new Recipe();
    public boolean isValid = false;
    private int stage = 0; // 0 is title 1 is description 2 is the ingredients

    public void enterAddMode() {
        Ui.clear();
        Ui.showMessage(ENTER, DONE_REMINDER);
        String input = "";
        while (!input.equalsIgnoreCase(DONE)) {
            // Ui.showMessageInline("Stage: ", String.valueOf(stage));
            switch (stage) {
            case 0:
                input = askTitle(input);
                break;
            case 1:
                input = askDescription(input);
                break;
            default:
                input = askIngredients(input);
                break;
            }
            Ui.showDivider();
        }
        isValid = (stage >= 3) ? true : false; // Check if all the components are in
    }

    public void exitAddMode() {
        Ui.showMessage(EXIT);
        Ui.showDivider();
    }

    private String askTitle(String input) {
        Ui.showMessage(TITLE);
        input = Ui.readInput();
        addedRecipe.setTitle(input);
        Ui.showMessageInline("Title: ", addedRecipe.getTitle());
        stage++; // Advance to next stage
        return input;

    }

    private String askDescription(String input) {
        Ui.showMessage(DESCRIPTION);
        input = Ui.readInput();
        addedRecipe.setDescription(input);
        Ui.showMessageInline("Description: ", addedRecipe.getDescription());
        stage++; // Advance to next stage
        return input;

    }

    private String askIngredients(String input) {
        Ui.showMessageInline(INGREDIENTS, FORMAT, DONE_REMINDER);
        input = Ui.readInput();
        if (!input.equalsIgnoreCase(DONE)) {
            parsedIngredients(input);
        }
        return input;
    }

    // TODO: Check for Exception
    private void parsedIngredients(String input) {
        String[] parsed = input.split("/", 3);
        try {
            double amount = convertToDouble(parsed[1]);
            addedRecipe.getIngredients().add(new Ingredient(parsed[0], amount, parsed[2]));
            Ui.showMessageInline("Ingredient registered: " + addedRecipe.getIngredients().toString()
                    + "\t To Implement Ingredients toString()"); //TODO:
            stage++;
        } catch (Exception e) {
            Ui.showMessage(ERROR_PARSING);
        }
    }

    private double convertToDouble(String amount) throws Exception {
        double amountDouble = Double.parseDouble(amount);
        return amountDouble;
    }

}
