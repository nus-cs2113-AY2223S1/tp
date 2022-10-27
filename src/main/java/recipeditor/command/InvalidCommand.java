package recipeditor.command;


public class InvalidCommand extends Command {
    public static final String COMMAND_TYPE = "/invalid";
    public static String INVALID_MESSAGE = "This is not a valid command! Write /help for the the list of commands";
    private final String message;

    public InvalidCommand(String message) {
        this.message = message;

    }

    public CommandResult execute() {
        return new CommandResult(message);
    }

}
