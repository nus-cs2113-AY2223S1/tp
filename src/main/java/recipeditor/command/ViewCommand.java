package recipeditor.command;

public class ViewCommand extends Command {
    public static final String COMMAND_TYPE = "view";


    public CommandResult execute() {
        //TODO: Execution of command
        // This is only the dummy code for testing
        return new CommandResult(COMMAND_TYPE);
    }
}
