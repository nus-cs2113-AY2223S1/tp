package seedu.duke.command;

import seedu.duke.Storage;
import seedu.duke.Ui;
import seedu.duke.data.TransactionList;
import seedu.duke.exception.MoolahException;

public abstract class Command {
    public abstract void execute(TransactionList transactions, Ui ui, Storage storage) throws MoolahException;

    public abstract boolean isExit();
}
