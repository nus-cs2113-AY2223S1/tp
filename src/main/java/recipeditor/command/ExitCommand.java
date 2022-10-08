package recipeditor.command;

public class ExitCommand extends Command {
    public static final String COMMAND_TYPE = "exit";


    public CommandResult execute() {
        //TODO: Execution of command
        // This is only the dummy code for testing
        return new CommandResult(COMMAND_TYPE);
    }
}
