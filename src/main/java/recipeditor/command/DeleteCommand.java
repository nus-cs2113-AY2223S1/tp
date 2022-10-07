package recipeditor.command;

public class DeleteCommand extends Command {
    public static final String COMMAND_TYPE = "delete";

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


}

