package recipeditor.ui;

import recipeditor.recipe.Recipe;
import recipeditor.recipe.RecipeList;
import recipeditor.command.Command;
import recipeditor.command.InvalidCommand;

public class EditMode {
    private static final String INVALID_INPUT = "Invalid input given.";
    private static final String ENTER = "Entering edit mode. Currently editing: ";
    private static final String EXIT = "Exiting edit mode.";
    private static final String NOT_FOUND = "Recipe not found.";

    private static final String OLD = "Before:";
    private static final String NEW = "After:";

    private Recipe originalRecipe;
    private Recipe editedRecipe;

    public void enterEditMode(String recipeTitle) {
        Ui.showMessageInline(ENTER + recipeTitle);
        originalRecipe = RecipeList.getRecipeFromTitle(recipeTitle);
        editedRecipe = originalRecipe;
        if (originalRecipe != null) {
            Ui.showMessageInline(originalRecipe.getRecipeAttributesFormatted());
            String input = "";
            while (!input.equals("/done")) {
                input = Ui.readInput();
                Ui.showMessageInline(parseEditInput(input));
            }
        } else {
            Ui.showMessageInline(NOT_FOUND);
            Ui.showMessageInline(EXIT);
            return;
        }
        Ui.showMessageInline(OLD);
        Ui.showMessageInline(originalRecipe.getRecipeAttributesFormatted());
        Ui.showMessageInline(NEW);
        Ui.showMessageInline(editedRecipe.getRecipeAttributesFormatted());
        Ui.showMessageInline(EXIT);
    }

    public void deleteStep(int index) {
        editedRecipe.deleteStep(index);
    }

    public void deleteIngredient(int index) {
        editedRecipe.deleteIngredient(index);
    }

    public void swapSteps(int a, int b) {
        String step1 = editedRecipe.getStep(a);
        String step2 = editedRecipe.getStep(b);
        editedRecipe.changeStep(a, step2);
        editedRecipe.changeStep(b, step1);
    }

    public void exitEditMode() {

    }

    public Recipe getOriginalRecipe() {
        return originalRecipe;
    }

    public Recipe getEditedRecipe() {
        return editedRecipe;
    }

    private String parseEditInput(String input) {
        String[] parsed = input.split(" ");
        String commandWord = parsed[0].toLowerCase();
        if (commandWord.charAt(0) != '/') {
            return INVALID_INPUT;
        }

        switch (commandWord) {
        case "/add":
            return "Add command";
        case "/del":
            return "Del command";
        case "/swap":
            return "Swap command";
        case "/view":
            return "View command";
        case "/done":
            return "Quit command";
        default:
            return INVALID_INPUT;
        }
    }
}
