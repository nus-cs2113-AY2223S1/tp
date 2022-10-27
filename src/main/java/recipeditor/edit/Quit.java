package recipeditor.edit;

import recipeditor.exception.InvalidFlagException;
import recipeditor.exception.ParseException;
import recipeditor.recipe.Recipe;

public class Quit extends EditModeCommand {

    public Quit(Recipe recipe) {
        super(recipe);
        this.message = "Finish editing.";
    }

    @Override
    public Recipe execute() throws InvalidFlagException, ParseException {
        return recipe;
    }
}

