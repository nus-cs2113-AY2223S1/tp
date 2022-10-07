package seedu.duke.command;

import seedu.duke.Storage;
import seedu.duke.Ui;
import seedu.duke.data.TransactionList;
import seedu.duke.exception.MoolahException;

/**
 * Represents an object that can be inherited by other command objects.
 */
public abstract class Command {
    /**
     * Executes the operations related to the command.
     *
     * @param ui An instance of the Ui class.
     * @param transactions An instance of the TransactionList class.
     * @param storage An instance of the Storage class.
     */
    public abstract void execute(TransactionList transactions, Ui ui, Storage storage) throws MoolahException;

    public abstract boolean isExit();
}
