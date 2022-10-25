package recipeditor.edit;

import recipeditor.exception.InvalidFlagException;
import recipeditor.exception.ParseException;
import recipeditor.parser.FlagParser;
import recipeditor.parser.FlagType;
import recipeditor.recipe.Ingredient;
import recipeditor.recipe.Recipe;

public class Add extends EditModeCommand {

//    private String[] parsedCommand;
//    private Recipe recipe;
//    private String message = "";
//    FlagType flag;

    public Add(String[] parsedCommand, Recipe recipe) {
        super(parsedCommand, recipe);
    }

    @Override
    public Recipe execute() throws ParseException, InvalidFlagException {
        StringBuilder content = new StringBuilder();
        for (int i = 2; i < parsedCommand.length; i++) {
            content.append(parsedCommand[i]).append(" ");
        }

        switch (flag) {
        case STEP:
            recipe.addStep(content.toString());
            message = String.format("Step: %s is added", content.toString());
            return recipe;
        case INGREDIENT:
            try {
                Ingredient newIngredient = Ingredient.parsedIngredients(content.toString());
                recipe.addIngredient(newIngredient);
                message = String.format("Ingredient: %s is added", content.toString());
                return recipe;
            } catch (ParseException e) {
                throw new ParseException();
            }
        default:
            throw new InvalidFlagException();
        }
    }

}
