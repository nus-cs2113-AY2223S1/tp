package recipeditor.command;

public class ListCommand extends Command {
    public static final String TYPE = "list";



    public CommandResult execute() {
        //TODO: Execution of command
        // This is only the dummy code for testing
        return new CommandResult(TYPE);
    }
}

