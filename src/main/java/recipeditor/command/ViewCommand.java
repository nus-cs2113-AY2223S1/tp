package recipeditor.command;

public class ViewCommand extends Command {
    public static final String COMMAND_TYPE = "view";

    private final int index;

    /**
     * Construct a view command including task to view.
     *
     * @param index the index of task view
     */
    public ViewCommand(int index) {
        this.index = index;
    }



    public CommandResult execute() {
        //TODO: Execution of command
        // This is only the dummy code for testing
        return new CommandResult(COMMAND_TYPE);
    }
}
