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

    public static final String COMMAND_FORMAT = "Syntax for /edit GUI: \n"
            + "\t /edit <index> \n\n"
            + "Syntax for /edit CLI: \n"
            + "\t /edit <index> (flags) (parameters) \n"
            + "Command flags: \n"
            + "\t -add (recipe flag) (input) \n"
            + "\t -del (recipe flag) (index) \n"
            + "\t -swp (recipe flag) (index 1) (index 2) \n"
            + "\t -chg (recipe flag) (index) (input) \n"
            + "Recipe flags: \n"
            + "\t -i: ingredient, -s: step, -t: title, -d: description \n"
            + "Flag order does not matter, but you can only add one command flag and one recipe flag.";

    private final int index;
    private Recipe editedRecipe;
    private FlagType[] flags;
    private boolean editSuccess = false;
    private String[] parsed;

    // CLI
    public EditCommand(FlagType[] flags, String[] parsed, int recipeIndex, Recipe editedRecipe) {
        this.flags = flags;
        this.parsed = parsed;
        this.index = recipeIndex;
        this.editedRecipe = editedRecipe;
    }

    // GUI
    public EditCommand(boolean editSuccess, int recipeIndex, Recipe editedRecipe) {
        this.editSuccess = editSuccess;
        this.index = recipeIndex;
        this.editedRecipe = editedRecipe;
    }

    @Override
    public CommandResult execute() {
        if (editSuccess) {
            // GUI Successful
            RecipeList.editRecipe(index, editedRecipe);
            return new CommandResult(editedRecipe.getTitle() + " edited.");
        } else if (flags == null) {
            // GUI Unsuccessful
            return new CommandResult("Edit failed");
        } else {
            // CLI
            EditModeCommand cmd;
            switch (flags[0]) {
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
                cmd.setIngredientFlag(flags[1]);
                this.editedRecipe = cmd.execute();
                RecipeList.editRecipe(index, editedRecipe);
                return new CommandResult(cmd.getMessage() + '\n' + editedRecipe.getTitle() + " edited.");
            } catch (Exception e) {
                return new CommandResult(e.getMessage());
            }
        }
    }
}
