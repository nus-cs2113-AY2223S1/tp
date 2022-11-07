package recipeditor.edit;

import recipeditor.exception.InvalidFlagException;
import recipeditor.exception.ParseException;
import recipeditor.parser.FlagParser;
import recipeditor.parser.FlagType;
import recipeditor.recipe.Recipe;
import recipeditor.ui.Ui;

public class Swap extends EditModeCommand {

    private static int INDEX_LOCATION = 4;
    private static int START_LOCATION = 5;

    public Swap(FlagType ingredientFlag, String[] parsedCommand, Recipe recipe) {
        super(ingredientFlag, parsedCommand, recipe);
        INDEX_LOCATION = FlagParser.getLastFlagIndex(parsedCommand) + 1;
        START_LOCATION = INDEX_LOCATION + 1;
    }

    /**
     * Swap the position of two steps or ingredient in the recipe to edit.
     *
     * @return the edited recipe
     * @throws InvalidFlagException edit command contains invalid flags
     * @throws IndexOutOfBoundsException index out of bound
     */
    @Override
    public Recipe execute() throws InvalidFlagException, IndexOutOfBoundsException {
        try {
            int index1 = Integer.parseInt(parsedCommand[INDEX_LOCATION]) - 1;
            int index2 = Integer.parseInt(parsedCommand[START_LOCATION]) - 1;
            switch (ingredientFlag) {
                case INGREDIENT:
                    try {
                        Ui.showMessage("Swap \"" + recipe.getIngredient(index1).getName()
                                + "\" and \"" + recipe.getIngredient(index2).getName() + "\"");
                        recipe.swapIngredients(index1, index2);
                        return recipe;
                    } catch (IndexOutOfBoundsException e) {
                        throw new IndexOutOfBoundsException("Index out of bound of number of ingredients.");
                    }
                case STEP:
                    try {
                        Ui.showMessage("Swap \"" + recipe.getStep(index1)
                                + "\" and \"" + recipe.getStep(index2) + "\"");
                        recipe.swapSteps(index1, index2);
                        return recipe;
                    } catch (IndexOutOfBoundsException e) {
                        throw new IndexOutOfBoundsException("Index out of bound of number of steps.");
                    }
                default:
                    throw new InvalidFlagException();
            }
        } catch (NumberFormatException n) {
            throw new NumberFormatException("Invalid number format");
        }
    }
}
