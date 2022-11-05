package recipeditor.command;

import recipeditor.edit.EditModeCommand;
import recipeditor.edit.Swap;
import recipeditor.edit.Add;
import recipeditor.edit.Change;
import recipeditor.edit.Delete;
import recipeditor.parser.FlagType;
import recipeditor.recipe.Recipe;
import recipeditor.recipe.RecipeList;

public class EditCommand extends Command {
    public static final String COMMAND_TYPE = "/edit";
    public static final String NO_EDIT = "No edit was made";
    private static final String COMMAND_FUNCTION = "\nEdit an existing recipe by: \n"
            + "-add Add a new step or ingredient \n"
            + "-del Delete an existing step or ingredient \n"
            + "-swp Swap the order of two ingredients or steps \n"
            + "-chg Change the title, description, ingredients or steps \n";


    public static final String COMMAND_SYNTAX = "\nSyntax for /edit GUI: \n"
            + "\t /edit <index> \n\n"
            + "Syntax for /edit CLI: \n"
            + "\t /edit <recipe index> (command flags) (parameters) \n"
            + "Command flags: \n"
            + "\t -add (recipe flag) (input) \n"
            + "\t -del (recipe flag) (index) \n"
            + "\t -swp (recipe flag) (index 1) (index 2) \n"
            + "\t -chg (recipe flag) (index) (input) \n"
            + "Recipe flags: \n"
            + "\t -i: ingredient, -s: step, -t: title, -d: description \n";
    private int index;
    private String oldTitle;
    private Recipe editedRecipe;
    private FlagType[] flags;
    private boolean editSuccess = false;
    private String[] parsed;

    public EditCommand() {
        super(COMMAND_SYNTAX, COMMAND_FUNCTION);
    }

    // CLI
    public EditCommand(FlagType[] flags, String[] parsed, int recipeIndex, Recipe editedRecipe, String oldTitle) {
        this();
        this.flags = flags;
        this.parsed = parsed;
        this.index = recipeIndex;
        this.editedRecipe = editedRecipe;
        this.oldTitle = oldTitle;
    }

    // GUI
    public EditCommand(boolean editSuccess, int recipeIndex, Recipe editedRecipe, String oldTitle) {
        this();
        this.editSuccess = editSuccess;
        this.index = recipeIndex;
        this.editedRecipe = editedRecipe;
        this.oldTitle = oldTitle;
    }

    /**
     * Execute the edit command, enter either GUI or CLI interface depending on the
     * edit command input.
     *
     * @return CommandResult successful or failed message on edition
     */
    @Override
    public CommandResult execute() {
        if (editSuccess) {
            // GUI Successful
            RecipeList.editRecipe(index, editedRecipe, oldTitle);
            return new CommandResult(editedRecipe.getTitle() + " edited.");
        } else if (flags == null) {
            // GUI Unsuccessful
            return new CommandResult(NO_EDIT);
        } else {
            // CLI
            EditModeCommand cmd;
            switch (flags[0]) {
            case ADD:
                cmd = new Add(flags[1], parsed, editedRecipe);
                break;
            case DELETE:
                cmd = new Delete(flags[1], parsed, editedRecipe);
                break;
            case SWAP:
                cmd = new Swap(flags[1], parsed, editedRecipe);
                break;
            case CHANGE:
                cmd = new Change(flags[1], parsed, editedRecipe);
                break;
            default:
                return new CommandResult("Edit failed");
            }
            try {
                this.editedRecipe = cmd.execute();
                RecipeList.editRecipe(index, editedRecipe, oldTitle);
                return new CommandResult(cmd.getMessage() + '\n' + editedRecipe.getTitle() + ": "
                        + flags[1].toString().toLowerCase() + " edited.");
            } catch (IndexOutOfBoundsException e) {
                return new CommandResult("Index specified is out of range!");
            } catch (Exception e) {
                return new CommandResult("/edit syntax is incorrect. "
                        + "Please check again using the '/help edit' command");
            }
        }
    }
}
