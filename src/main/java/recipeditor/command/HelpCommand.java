package recipeditor.command;

public class HelpCommand {

    public static final String COMMAND_TYPE = "/help";

    public static String HELP_MESSAGE = "Available commands: /add, /list, /view, /edit, /find, /delete, /exit, /help ";

    public CommandResult execute(){
        return new CommandResult(HELP_MESSAGE);
    }
}
