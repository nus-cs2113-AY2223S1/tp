package recipeditor.edit;

import recipeditor.exception.InvalidFlagException;
import recipeditor.exception.ParseException;
import recipeditor.parser.FlagParser;
import recipeditor.parser.FlagType;
import recipeditor.recipe.Ingredient;
import recipeditor.recipe.Recipe;

public class Add extends EditModeCommand {

    public Add(FlagType ingredientFlag, String[] parsedCommand, Recipe recipe) {
        super(ingredientFlag, parsedCommand, recipe);
    }

    /**
     * Add a step or ingredient to the recipe to edit.
     *
     * @return the edited recipe
     * @throws ParseException ingredient does not follow correct format to be parsed
     * @throws InvalidFlagException edit command contains invalid flags
     */
    @Override
    public Recipe execute() throws ParseException, InvalidFlagException {
        StringBuilder content = new StringBuilder();
        for (int i = 4; i < parsedCommand.length; i++) {
            content.append(parsedCommand[i]).append(" ");
        }

        switch (ingredientFlag) {
        case STEP:
            recipe.addStep(content.toString());
            message = String.format("Step: %s is added", content);
            return recipe;
        case INGREDIENT:
            try {
                Ingredient newIngredient = Ingredient.parsedIngredients(content.toString());
                recipe.addIngredient(newIngredient);
                message = String.format("Ingredient: %s is added", content);
                return recipe;
            } catch (ParseException e) {
                throw new ParseException();
            }
        default:
            throw new InvalidFlagException();
        }
    }

}
