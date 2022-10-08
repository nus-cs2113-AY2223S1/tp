package recipeditor.command;

public class ViewCommand extends Command {
    public static final String TYPE = "view";


    public CommandResult execute() {
        //TODO: Execution of command
        // This is only the dummy code for testing
        return new CommandResult(TYPE);
    }
}
