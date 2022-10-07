package seedu.duke.command;

import seedu.duke.Storage;
import seedu.duke.Ui;
import seedu.duke.data.TransactionList;

/**
 * Represents a help command object that will execute the operations for Help command.
 */
public class HelpCommand extends Command {
    /**
     * Executes the operations related to the command.
     *
     * @param ui An instance of the Ui class.
     * @param transactions An instance of the TransactionList class.
     * @param storage An instance of the Storage class.
     */
    @Override
    public void execute(TransactionList transactions, Ui ui, Storage storage) {
        Ui.showHelp();
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
