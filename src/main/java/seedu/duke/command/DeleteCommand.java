package seedu.duke.command;

import seedu.duke.Storage;
import seedu.duke.Ui;
import seedu.duke.data.TransactionList;
import seedu.duke.exception.AddDeleteInvalidIndexException;
import seedu.duke.exception.MoolahException;

import static seedu.duke.common.ErrorMessages.ERROR_ADD_COMMAND_AMOUNT_NOT_NUMERIC;
import static seedu.duke.common.InfoMessages.INFO_DELETE;

/**
 * Represents a delete command object that will execute the operations for Delete command.
 */
public class DeleteCommand extends Command {
    private String input;

    /**
     * Initialises the variables of the DeleteCommand class.
     *
     * @param input A string that represents the index of the task.
     */
    public DeleteCommand(String input) {
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
            Ui.showErrorMessage(ERROR_ADD_COMMAND_AMOUNT_NOT_NUMERIC.toString());
            return;
        }
        if ((index > numberOfTransactions) || (index <= 0)) {
            isInputValid = false;
        }
        if (isInputValid) {
            String transaction = TransactionList.deleteTransaction(transactions, index);
            Ui.showTransactionAction(INFO_DELETE.toString(), transaction);
        } else {
            throw new AddDeleteInvalidIndexException();
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
