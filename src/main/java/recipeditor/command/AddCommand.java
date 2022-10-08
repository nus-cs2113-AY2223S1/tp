package recipeditor.command;

public class AddCommand extends Command {
    public static final String TYPE = "add";


    public CommandResult execute() {
        //TODO: Execution of command
        // This is only the dummy code for testing
        return new CommandResult(TYPE);
    }
}
