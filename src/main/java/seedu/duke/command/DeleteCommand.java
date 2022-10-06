package seedu.duke.command;

import seedu.duke.Storage;
import seedu.duke.Ui;
import seedu.duke.data.TransactionList;
import seedu.duke.exception.AddDeleteInvalidIndexException;
import seedu.duke.exception.MoolahException;

public class DeleteCommand extends Command {

    private String input;

    public DeleteCommand(String input) {
        this.input = input;
    }

    @Override
    public void execute(TransactionList transactions, Ui ui, Storage storage) throws MoolahException {
        /*
        Checks if userInput is in the correct input format by further parsing,
        before adding entry to arraylist
        */
        boolean isInputValid = true;
        int index;
        int numberOfTransactions;
        numberOfTransactions = transactions.size();
        try {
            index = Integer.parseInt(input);
        } catch (NumberFormatException e) {
            Ui.showNonNumericError();
            return;
        }
        if ((index > numberOfTransactions) || (index <= 0)) {
            isInputValid = false;
        }
        if (isInputValid) {
            TransactionList.deleteEntry(transactions, index);
        } else {
            throw new AddDeleteInvalidIndexException();
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
