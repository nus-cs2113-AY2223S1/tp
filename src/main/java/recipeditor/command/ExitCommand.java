package recipeditor.command;

import recipeditor.recipe.RecipeList;
import recipeditor.recipe.Recipe;
import recipeditor.storage.Storage;

import java.io.File;


public class ExitCommand extends Command {
    public static final String COMMAND_TYPE = "/exit";
    public static final String EXIT_MESSAGE = "RecipEditor ends here...";
    private static final String COMMAND_SYNTAX = "/exit";
    private static final String COMMAND_FUNCTION = "Exit recipeditor.";

    public ExitCommand() {
        super(COMMAND_SYNTAX, COMMAND_FUNCTION);
    }

    /**
     * Execute exit command, regenerate AllRecipes and the appropriate recipe files.
     *
     * @return CommandResult a message of exiting software
     */
    public CommandResult execute() {

        return new CommandResult(EXIT_MESSAGE);
    }
}
