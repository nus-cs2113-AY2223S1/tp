package recipeditor.command;

public class DeleteCommand extends Command {
    public static final String TYPE = "delete";
    private final int index;

    /**
     * Construct a delete command including task to delete.
     *
     * @param index the index of task to delete
     */
    public DeleteCommand(int index) {
        this.index = index;
    }

    /**
     * get the index of task to delete.
     *
     * @return the index of task to delete
     */
    public int getIndex() {
        return this.index;
    }


    public CommandResult execute() {
        //TODO: Execution of command
        // This is only the dummy code for testing
        return new CommandResult(TYPE);
    }

}

