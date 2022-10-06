package seedu.duke.command;

import seedu.duke.Storage;
import seedu.duke.Ui;
import seedu.duke.data.TransactionList;

public class HelpCommand extends Command {
    @Override
    public void execute(TransactionList transactions, Ui ui, Storage storage) {
        Ui.showHelp();
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
