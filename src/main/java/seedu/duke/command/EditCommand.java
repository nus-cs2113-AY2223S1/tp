package seedu.duke.command;

import seedu.duke.Storage;
import seedu.duke.Ui;
import seedu.duke.data.TransactionList;

public class EditCommand extends Command {
    private String input;

    public EditCommand(String input) {
        this.input = input;
    }

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
