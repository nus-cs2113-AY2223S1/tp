package seedu.duke.command;

import seedu.duke.Storage;
import seedu.duke.Ui;
import seedu.duke.data.TransactionList;

/**
 * Represents an edit command object that will execute the operations for Edit command.
 */
public class EditCommand extends Command {
    private String input;

    public EditCommand(String input) {
        this.input = input;
    }

    /**
     * Executes the operations related to the command.
     *
     * @param ui An instance of the Ui class.
     * @param transactions An instance of the TransactionList class.
     * @param storage An instance of the Storage class.
     */
    @Override
    public void execute(TransactionList transactions, Ui ui, Storage storage) {
        /*
        Checks if userInput is in the correct input format by further parsing,
        before the entry in the arraylist
        */
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
