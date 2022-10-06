package seedu.duke.command;

public class DeleteCommand extends Command {
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

