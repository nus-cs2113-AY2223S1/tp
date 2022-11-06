package recipeditor.edit;

import recipeditor.exception.InvalidFlagException;
import recipeditor.exception.ParseException;
import recipeditor.parser.FlagType;
import recipeditor.recipe.Recipe;

public abstract class EditModeCommand {

    String[] parsedCommand;
    Recipe recipe;
    String message = "";
    FlagType ingredientFlag;

    public EditModeCommand(FlagType ingredientFlag, String[] parsedCommand, Recipe recipe) {
        this.ingredientFlag = ingredientFlag;
        this.parsedCommand = parsedCommand;
        this.recipe = recipe;
    }

    public EditModeCommand(Recipe recipe) {
        this.recipe = recipe;
    }

    public String getMessage() {
        return message;
    }

    public void setIngredientFlag(FlagType ingredientFlag) {
        this.ingredientFlag = ingredientFlag;
    }

    public abstract Recipe execute() throws InvalidFlagException, ParseException;
}
