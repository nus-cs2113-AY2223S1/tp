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

    // The command word used to trigger the execution of Moolah Manager's operations.
    public static final String COMMAND_WORD = "GET";
    // The description for the usage of command.
    public static final String COMMAND_DESCRIPTION = "To get ...";
    // The guiding information for the usage of command.
    public static final String COMMAND_USAGE = "Usage: get ...";
    // The formatting information for the parameters used by the command.
    public static final String COMMAND_PARAMETERS_INFO = "Parameters information: \n"
            + "PARAMETERNAME: ... ";

    CategoryList categories = new CategoryList();

    /**
     * Executes the operations related to the command.
     *
     * @param ui           An instance of the Ui class.
     * @param transactions An instance of the TransactionList class.
     * @param storage      An instance of the Storage class.
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
