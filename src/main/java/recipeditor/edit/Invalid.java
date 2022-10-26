package recipeditor.edit;

import recipeditor.exception.InvalidFlagException;
import recipeditor.exception.ParseException;
import recipeditor.recipe.Recipe;

public class Invalid extends EditModeCommand {

    public Invalid(Recipe recipe) {
        super(recipe);
        this.message = "Invalid command.";
    }

    @Override
    public Recipe execute() throws InvalidFlagException, ParseException {
        return recipe;
    }
}
