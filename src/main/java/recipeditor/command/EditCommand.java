package recipeditor.command;

import recipeditor.edit.EditModeCommand;
import recipeditor.edit.Swap;
import recipeditor.edit.Add;
import recipeditor.edit.Change;
import recipeditor.edit.Delete;
import recipeditor.parser.FlagType;
import recipeditor.recipe.Recipe;
import recipeditor.recipe.RecipeList;
import recipeditor.ui.Ui;

public class EditCommand extends Command {
    public static final String COMMAND_TYPE = "/edit";

    public static final String COMMAND_FORMAT = "Syntax for /edit \n" + "\t /edit <index>";
    private final int index;
    private Recipe editedRecipe;

    private FlagType commandType;
    private boolean editSuccess = false;
    private String[] parsed;



    public EditCommand(FlagType commandType, String[] parsed, int recipeIndex, Recipe editedRecipe) {
        this.commandType = commandType;
        this.parsed = parsed;
        this.index = recipeIndex;
        this.editedRecipe = editedRecipe;
    }

    public EditCommand(boolean editSuccess, int recipeIndex, Recipe editedRecipe) {
        this.editSuccess = editSuccess;
        this.index = recipeIndex;
        this.editedRecipe = editedRecipe;
    }

    @Override
    public CommandResult execute() {
        if (editSuccess) {
            RecipeList.editRecipe(index, editedRecipe);
            return new CommandResult(editedRecipe.getTitle() + " edited.");
        } else {
            EditModeCommand cmd;
            switch (commandType) {
            case ADD:
                cmd = new Add(parsed, editedRecipe);
                break;
            case DELETE:
                cmd = new Delete(parsed, editedRecipe);
                break;
            case SWAP:
                cmd = new Swap(parsed, editedRecipe);
                break;
            case CHANGE:
                cmd = new Change(parsed, editedRecipe);
                break;
            default:
                return new CommandResult("Edit failed");
            }
            try {
                this.editedRecipe = cmd.execute();
                RecipeList.editRecipe(index, editedRecipe);
                return new CommandResult(cmd.getMessage() + '\n' + editedRecipe.getTitle() + " edited.");
            } catch (Exception e) {
                return new CommandResult(e.getMessage());
            }
        }
    }

}
