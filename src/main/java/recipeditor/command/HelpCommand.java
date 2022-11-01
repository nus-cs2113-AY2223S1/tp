package recipeditor.command;

public class HelpCommand extends Command {

    public static final String COMMAND_TYPE = "/help";
    public static final String HELP_MESSAGE =
            "\n" + "Available commands: /add, /list, /view, /edit, /find, /delete, /exit, /help";

    /**
     * Execute help command, which shows some helper messages.
     *
     * @return CommandResult list of avaliable commands
     */
    public CommandResult execute() {
        return new CommandResult(HELP_MESSAGE);
    }
}
