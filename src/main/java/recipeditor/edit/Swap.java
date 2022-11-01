package recipeditor.edit;

import recipeditor.exception.InvalidFlagException;
import recipeditor.exception.ParseException;
import recipeditor.parser.FlagParser;
import recipeditor.parser.FlagType;
import recipeditor.recipe.Recipe;
import recipeditor.ui.Ui;

public class Swap extends EditModeCommand {

    public Swap(FlagType ingredientFlag, String[] parsedCommand, Recipe recipe) {
        super(ingredientFlag, parsedCommand, recipe);
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
        int index1 = Integer.parseInt(parsedCommand[4]) - 1;
        int index2 = Integer.parseInt(parsedCommand[5]) - 1;
        switch (ingredientFlag) {
        case INGREDIENT:
            try {
                Ui.showMessage("Swap \"" + recipe.getIngredient(index1).getName()
                        + "\" and \"" + recipe.getIngredient(index2).getName() + "\"");
                recipe.swapIngredients(index1, index2);
                return recipe;
            } catch (Exception e) {
                throw new IndexOutOfBoundsException("Index out of bound of number of ingredients.");
            }
        case STEP:
            try {
                Ui.showMessage("Swap \"" + recipe.getStep(index1)
                        + "\" and \"" + recipe.getStep(index2) + "\"");
                recipe.swapSteps(index1, index2);
                return recipe;
            } catch (Exception e) {
                throw new IndexOutOfBoundsException("Index out of bound of number of steps.");
            }
        default:
            throw new InvalidFlagException();
        }
    }
}
