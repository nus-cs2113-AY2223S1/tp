package recipeditor.command;

import recipeditor.storage.Storage;

public class ExitCommand extends Command {
    public static final String COMMAND_TYPE = "/exit";
    public static final String EXIT_MESSAGE = "RecipEditor ends here...";
    public static final String COMMAND_NAME = "exit";
    private static final String COMMAND_FUNCTION = "Exit recipeditor.";

    public ExitCommand() {
        super(COMMAND_TYPE, COMMAND_FUNCTION);
    }

    /**
     * Execute exit command, regenerate AllRecipes and the appropriate recipe files.
     *
     * @return CommandResult a message of exiting software
     */
    public CommandResult execute() {
        Storage.rewriteRecipeListToFile();

        return new CommandResult(EXIT_MESSAGE);
    }
}
