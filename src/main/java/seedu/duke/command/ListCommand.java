package seedu.duke.command;

import seedu.duke.Storage;
import seedu.duke.Ui;
import seedu.duke.data.TransactionList;

import static seedu.duke.common.InfoMessages.INFO_LIST;
import static seedu.duke.common.InfoMessages.INFO_LIST_EMPTY;

/**
 * Represents a list command object that will execute the operations for List command.
 */
public class ListCommand extends Command {

    // The command word used to trigger the execution of Moolah Manager's operations.
    public static final String COMMAND_WORD = "LIST";
    // The description for the usage of command.
    public static final String COMMAND_DESCRIPTION = "To list all or some transactions based on selection.";
    // The guiding information for the usage of command.
    public static final String COMMAND_USAGE = "Usage: list [t/TYPE] [c/CATEGORY] [d/DATE] ";
    // The formatting information for the parameters used by the command.
    public static final String COMMAND_PARAMETERS_INFO = "Parameters information: \n"
            + "(Optional) TYPE - The type of transaction. Only \"income\" or \"expense\" is accepted.\n"
            + "(Optional) CATEGORY: A category for the transaction. Only string containing alphabets is accepted.\n"
            + "(Optional) DATE: Date of the transaction. The format must be in \"yyyyMMdd\".";

    /**
     * Executes the operations related to the command.
     *
     * @param ui           An instance of the Ui class.
     * @param transactions An instance of the TransactionList class.
     * @param storage      An instance of the Storage class.
     */
    @Override
    public void execute(TransactionList transactions, Ui ui, Storage storage) {
        String transactionsList = transactions.listTransactions();
        if (transactionsList.isEmpty()) {
            Ui.showInfoMessage(INFO_LIST_EMPTY.toString());
            return;
        }
        Ui.showTransactionsList(transactionsList, INFO_LIST.toString());
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
