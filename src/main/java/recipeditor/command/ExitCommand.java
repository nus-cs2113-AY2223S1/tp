package recipeditor.command;

public class ExitCommand extends Command {
    public static final String TYPE = "exit";


    public CommandResult execute() {
        //TODO: Execution of command
        // This is only the dummy code for testing
        return new CommandResult(TYPE);
    }
}
