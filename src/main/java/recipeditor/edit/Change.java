package recipeditor.edit;

import recipeditor.exception.InvalidFlagException;
import recipeditor.exception.ParseException;
import recipeditor.parser.FlagParser;
import recipeditor.parser.FlagType;
import recipeditor.recipe.Ingredient;
import recipeditor.recipe.Recipe;
import recipeditor.ui.Ui;

public class Change extends EditModeCommand {
    private static int INDEX_LOCATION = 4;
    private static int START_LOCATION = 5;

    public Change(FlagType ingredientFlag, String[] parsedCommand, Recipe recipe) {
        super(ingredientFlag, parsedCommand, recipe);
        INDEX_LOCATION = FlagParser.getLastFlagIndex(parsedCommand) + 1;
        START_LOCATION = INDEX_LOCATION + 1;
    }

    /**
     * Change an attribute in the recipe to edit.
     *
     * @return the edited recipe
     * @throws ParseException ingredient does not follow correct format to be parsed
     * @throws InvalidFlagException edit command contains invalid flags
     * @throws IndexOutOfBoundsException index out of bound
     */
    @Override
    public Recipe execute() throws InvalidFlagException, ParseException, IndexOutOfBoundsException {
        try {
            StringBuilder newInput = new StringBuilder();

            switch (ingredientFlag) {
            case INGREDIENT:
                for (int i = START_LOCATION; i < parsedCommand.length; i++) {
                    newInput.append(parsedCommand[i]).append(Ui.SPACE_DIVIDER);
                }
                int indexToChange = Integer.parseInt(parsedCommand[INDEX_LOCATION]) - 1;
                Ingredient newIngredient = Ingredient.parsedIngredients(newInput.toString().trim());
                recipe.setIngredient(indexToChange, newIngredient);
                return recipe;
            case STEP:
                for (int i = START_LOCATION; i < parsedCommand.length; i++) {
                    newInput.append(parsedCommand[i]).append(Ui.SPACE_DIVIDER);
                }
                indexToChange = Integer.parseInt(parsedCommand[INDEX_LOCATION]) - 1;
                recipe.setStep(indexToChange, newInput.toString().trim());
                return recipe;
            case TITLE:
                for (int i = INDEX_LOCATION; i < parsedCommand.length; i++) {
                    newInput.append(parsedCommand[i]).append(Ui.SPACE_DIVIDER);
                }
                recipe.setTitle(newInput.toString().trim());
                return recipe;
            case DESCRIPTION:
                for (int i = INDEX_LOCATION; i < parsedCommand.length; i++) {
                    newInput.append(parsedCommand[i]).append(Ui.SPACE_DIVIDER);
                }
                recipe.setDescription(newInput.toString().trim());
                return recipe;
            default:
                throw new InvalidFlagException();
            }
        } catch (NumberFormatException n) {
            throw new NumberFormatException("Invalid number format");
        }
    }
}
