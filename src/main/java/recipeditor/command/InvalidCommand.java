package recipeditor.command;


public class InvalidCommand extends Command {
    public static final String COMMAND_TYPE = "invalid";
    public static String INVALID_MESSAGE = "This command is invalid! Please write again!";

    public CommandResult execute() {
        return new CommandResult(INVALID_MESSAGE);
    }

}
