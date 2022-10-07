package seedu.duke.command;

import seedu.duke.Storage;
import seedu.duke.Ui;
import seedu.duke.data.TransactionList;

public class ByeCommand extends Command {
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
