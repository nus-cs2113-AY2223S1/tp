package recipeditor.edit;

import recipeditor.exception.InvalidFlagException;
import recipeditor.exception.ParseException;
import recipeditor.parser.FlagType;
import recipeditor.recipe.Ingredient;
import recipeditor.recipe.Recipe;

public class Change extends EditModeCommand {
    private static final String CHANGE_1 = "Enter your changes: ";
    private static final String CHANGE_2 = "Ingredient format: <ingredient name> / <amount_in_float> / <unit>. "
            + "Step format: <step> ";
    private static final int INDEX_LOCATION = 4;
    private static final int START_LOCATION = 5;

    public Change(FlagType ingredientFlag, String[] parsedCommand, Recipe recipe) {
        super(ingredientFlag, parsedCommand, recipe);
    }

    /**
     * Change an attribute in the recipe to edit
     *
     * @return the edited recipe
     * @throws ParseException ingredient does not follow correct format to be parsed
     * @throws InvalidFlagException edit command contains invalid flags
     */
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
            for (int i = INDEX_LOCATION; i < parsedCommand.length; i++) {
                newInput.append(parsedCommand[i]).append(" ");
            }
            recipe.setTitle(newInput.toString().trim());
            return recipe;
        case DESCRIPTION:
            for (int i = INDEX_LOCATION; i < parsedCommand.length; i++) {
                newInput.append(parsedCommand[i]).append(" ");
            }
            recipe.setDescription(newInput.toString().trim());
            return recipe;
        default:
            throw new InvalidFlagException();
        }
    }
}
