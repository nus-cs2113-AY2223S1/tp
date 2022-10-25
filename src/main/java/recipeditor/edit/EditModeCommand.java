package recipeditor.edit;

import recipeditor.exception.InvalidFlagException;
import recipeditor.exception.ParseException;
import recipeditor.parser.FlagParser;
import recipeditor.parser.FlagType;
import recipeditor.recipe.Recipe;

public abstract class EditModeCommand {

    String[] parsedCommand;
    Recipe recipe;
    String message = "";
    FlagType flag;

    public EditModeCommand(String[] parsedCommand, Recipe recipe) {
        this.parsedCommand = parsedCommand;
        this.recipe = recipe;
        this.flag = FlagParser.checkFlagType(parsedCommand);
    }

    public EditModeCommand(Recipe recipe) {
        this.recipe = recipe;
    }

    public String getMessage() {
        return message;
    }

    public abstract Recipe execute() throws InvalidFlagException, ParseException;

    String showRecipeChanges(Recipe newRecipe, Recipe oldRecipe) {
        return "Old:" + "\n"
                + oldRecipe.getRecipeAttributesFormatted() + "\n"
                + "New:" + "\n"
                + newRecipe.getRecipeAttributesFormatted();
    }

}
