package recipeditor.command;

public class ExitCommand extends Command {
    public static final String COMMAND_TYPE = "/exit";
    public static final String EXIT_MESSAGE = "RecipEditor ends here...";

    public CommandResult execute() {
        return new CommandResult("\n" + EXIT_MESSAGE);
    }
}
