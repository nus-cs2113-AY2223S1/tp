package recipeditor.edit;

import recipeditor.exception.InvalidFlagException;
import recipeditor.parser.FlagParser;
import recipeditor.parser.FlagType;
import recipeditor.recipe.Recipe;
import recipeditor.ui.Ui;

public class Delete extends EditModeCommand {

    public Delete(FlagType ingredientFlag, String[] parsedCommand, Recipe recipe) {
        super(ingredientFlag, parsedCommand, recipe);
    }

    /**
     * Delete an attribute in the recipe, either an ingredient or a step.
     *
     * @return the edited recipe
     * @throws InvalidFlagException edit command contains invalid flags
     * @throws IndexOutOfBoundsException index out of bound
     */
    @Override
    public Recipe execute() throws InvalidFlagException, IndexOutOfBoundsException {
        Recipe oldRecipe = recipe;
        int index = Integer.parseInt(parsedCommand[4]) - 1;
        switch (ingredientFlag) {
        case INGREDIENT:
            Ui.showMessage("Delete \"" + recipe.getIngredient(index).getName() + "\"");
            recipe.deleteIngredient(index);
            return recipe;
        case STEP:
            Ui.showMessage("Delete \"" + recipe.getStep(index) + "\"");
            recipe.deleteStep(index);
            return recipe;
        default:
            throw new InvalidFlagException();
        }
    }
}
