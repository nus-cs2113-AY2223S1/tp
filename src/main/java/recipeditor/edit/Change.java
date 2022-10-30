package recipeditor.edit;

import recipeditor.exception.InvalidFlagException;
import recipeditor.exception.ParseException;
import recipeditor.recipe.Ingredient;
import recipeditor.recipe.Recipe;

public class Change extends EditModeCommand {
    private static final String CHANGE_1 = "Enter your changes: ";
    private static final String CHANGE_2 = "Ingredient format: <ingredient name> / <amount_in_float> / <unit>. "
            + "Step format: <step> ";
    private static final int INDEX_LOCATION = 4;
    private static final int START_LOCATION = 5;

    public Change(String[] parsedCommand, Recipe recipe) {
        super(parsedCommand, recipe);
    }

    @Override
    public Recipe execute() throws InvalidFlagException, ParseException {
        StringBuilder newInput = new StringBuilder();

        switch (ingredientFlag) {
        case INGREDIENT:
            for (int i = START_LOCATION; i < parsedCommand.length; i++) {
                newInput.append(parsedCommand[i]).append(" ");
            }
            int indexToChange = Integer.parseInt(parsedCommand[INDEX_LOCATION]) - 1;
            Ingredient newIngredient = Ingredient.parsedIngredients(newInput.toString().trim());
            recipe.setIngredient(indexToChange, newIngredient);
            return recipe;
        case STEP:
            for (int i = START_LOCATION; i < parsedCommand.length; i++) {
                newInput.append(parsedCommand[i]).append(" ");
            }
            indexToChange = Integer.parseInt(parsedCommand[INDEX_LOCATION]) - 1;
            recipe.setStep(indexToChange, newInput.toString().trim());
            return recipe;
        case TITLE:
            for (int i = START_LOCATION; i < parsedCommand.length; i++) {
                newInput.append(parsedCommand[i]).append(" ");
            }
            recipe.setTitle(newInput.toString().trim());
            return recipe;
        case DESCRIPTION:
            for (int i = START_LOCATION; i < parsedCommand.length; i++) {
                newInput.append(parsedCommand[i]).append(" ");
            }
            recipe.setDescription(newInput.toString().trim());
            return recipe;
        default:
            throw new InvalidFlagException();
        }
    }
}
