package recipeditor.edit;

import recipeditor.exception.InvalidFlagException;
import recipeditor.exception.ParseException;
import recipeditor.parser.FlagParser;
import recipeditor.recipe.Ingredient;
import recipeditor.recipe.Recipe;
import recipeditor.ui.Ui;

public class Change extends EditModeCommand {
    private static final String CHANGE_1 = "Enter your changes: ";
    private static final String CHANGE_2 = "Ingredient format: <ingredient name> / <amount_in_float> / <unit>. "
            + "Step format: <step> ";

    public Change(String[] parsedCommand, Recipe recipe) {
        super(parsedCommand, recipe);
    }

    @Override
    public Recipe execute() throws InvalidFlagException, ParseException {
        int indexToChange = Integer.parseInt(parsedCommand[2]) - 1;
        Recipe oldRecipe = recipe;
        Ui.showMessageInline(CHANGE_1);
        Ui.showMessageInline(CHANGE_2);
        String newInput = Ui.readInput();

        switch (flag) {
        case INGREDIENT:
            try {
                Ingredient newIngredient = Ingredient.parsedIngredients(newInput);
                recipe.setIngredient(indexToChange, newIngredient);
                message = ShowRecipeChanges(recipe, oldRecipe);
                return recipe;
            } catch (Exception e) {
                Ui.showMessage(e.getMessage());
            }
        case STEP:
            recipe.setStep(indexToChange, newInput);
            message = ShowRecipeChanges(recipe, oldRecipe);
            return recipe;
        case TITLE:
            recipe.setTitle(newInput);
            message = ShowRecipeChanges(recipe, oldRecipe);
            return recipe;
        case DESCRIPTION:
            recipe.setDescription(newInput);
            message = ShowRecipeChanges(recipe, oldRecipe);
            return recipe;
        default:
            throw new InvalidFlagException();
        }

    }

}
