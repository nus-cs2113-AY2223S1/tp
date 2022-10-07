package seedu.duke.command;

import seedu.duke.Storage;
import seedu.duke.Ui;
import seedu.duke.data.TransactionList;

/**
 * Represents a bye command object that will execute the operations for Bye command.
 */
public class ByeCommand extends Command {
    /**
     * Executes the operations related to the command.
     *
     * @param ui An instance of the Ui class.
     * @param transactions An instance of the TransactionList class.
     * @param storage An instance of the Storage class.
     */
    @Override
    public void execute(TransactionList transactions, Ui ui, Storage storage) {
        Ui.showExit();
        //storage.writeToFile(transactions);
    }

    @Override
    public boolean isExit() {
        return true;
    }
}
