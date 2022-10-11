package recipeditor.command;

public class ViewCommand extends Command {
    public static final String COMMAND_TYPE = "view";
    public static final String VIEW_MESSAGE = "Which recipe to view?";


    public CommandResult execute() {
        //TODO: Execution of command
        // This is only the dummy code for testing
        return new CommandResult(VIEW_MESSAGE);
    }
}
