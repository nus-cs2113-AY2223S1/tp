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
        StringBuilder newInput = new StringBuilder();

        switch (ingredientFlag) {
        case INGREDIENT:
            for (int i = 4; i < parsedCommand.length; i++) {
                newInput.append(parsedCommand[i]);
            }
            int indexToChange = Integer.parseInt(parsedCommand[1]) - 1;
            Ingredient newIngredient = Ingredient.parsedIngredients(newInput.toString());
            recipe.setIngredient(indexToChange, newIngredient);
            return recipe;
        case STEP:
            for (int i = 4; i < parsedCommand.length; i++) {
                newInput.append(parsedCommand[i]);
            }
            indexToChange = Integer.parseInt(parsedCommand[1]) - 1;
            recipe.setStep(indexToChange, newInput.toString());
            return recipe;
        case TITLE:
            for (int i = 4; i < parsedCommand.length; i++) {
                newInput.append(parsedCommand[i]);
            }
            recipe.setTitle(newInput.toString());
            return recipe;
        case DESCRIPTION:
            for (int i = 4; i < parsedCommand.length; i++) {
                newInput.append(parsedCommand[i]);
            }
            recipe.setDescription(newInput.toString());
            return recipe;
        default:
            throw new InvalidFlagException();
        }

    }

}
