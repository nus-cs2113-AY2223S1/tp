package seedu.duke.command;

import seedu.duke.Storage;
import seedu.duke.Ui;
import seedu.duke.data.CategoryList;
import seedu.duke.data.TransactionList;

import static seedu.duke.common.InfoMessages.INFO_GET_CATEGORIES;

/**
 * Represents a get command object that will execute the operations for Get command.
 */
public class GetCommand extends Command {
    CategoryList categories = new CategoryList();

    /**
     * Executes the operations related to the command.
     *
     * @param ui An instance of the Ui class.
     * @param transactions An instance of the TransactionList class.
     * @param storage An instance of the Storage class.
     */
    @Override
    public void execute(TransactionList transactions, Ui ui, Storage storage) {
        // This will be a method within this method, command will be e.g. "get categories"
        categories.calculateTotalAmount(transactions);
        String categoriesList = categories.listCategories();
        Ui.showTransactionsList(categoriesList, INFO_GET_CATEGORIES.toString());
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
