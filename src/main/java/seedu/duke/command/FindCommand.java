package seedu.duke.command;

import seedu.duke.Storage;
import seedu.duke.Ui;
import seedu.duke.data.TransactionList;
import seedu.duke.exception.FindTransactionMissingKeywordsException;

import static seedu.duke.common.InfoMessages.INFO_LIST_FILTERED;
import static seedu.duke.common.InfoMessages.INFO_LIST_UNFILTERED;

/**
 * Represents a find command object that will execute the operations for Find command.
 */
public class FindCommand extends Command {

    // The command word used to trigger the execution of Moolah Manager's operations.
    public static final String COMMAND_WORD = "FIND";
    // The description for the usage of command.
    public static final String COMMAND_DESCRIPTION = "To find ...";
    // The guiding information for the usage of command.
    public static final String COMMAND_USAGE = "Usage: find ...";
    // The formatting information for the parameters used by the command.
    public static final String COMMAND_PARAMETERS_INFO = "Parameters information: \n"
            + "PARAMETERNAME: ... ";

    protected String keywords;

    public FindCommand() {
    }

    /**
     * Initialises the variables of the FindCommand class.
     *
     * @param keywords A string containing the keywords used in the search expression.
     */
    public FindCommand(String keywords) {
        this.keywords = keywords;
    }

    /**
     * Checks the format of find to ensure that it contains keywords used in the search expression.
     *
     * @param keywords A string containing the keywords used in the search expression.
     * @throws FindTransactionMissingKeywordsException If a user does not enter a search expression for Find.
     */
    public void checkFindFormat(String keywords) throws FindTransactionMissingKeywordsException {
        if (keywords.isBlank()) {
            throw new FindTransactionMissingKeywordsException();
        }
    }

    /**
     * Executes the operations related to the command.
     *
     * @param ui           An instance of the Ui class.
     * @param transactions An instance of the TransactionList class.
     * @param storage      An instance of the Storage class.
     */
    @Override
    public void execute(TransactionList transactions, Ui ui, Storage storage) {
        try {
            // Checks the format of find to ensure that it contains keywords used in the search expression
            checkFindFormat(keywords);
            String transactionsList = transactions.findTransactions(keywords);
            if (transactionsList.isEmpty()) {
                ui.showInfoMessage(INFO_LIST_UNFILTERED.toString());
                return;
            }
            ui.showTransactionsList(transactionsList, INFO_LIST_FILTERED.toString());
        } catch (FindTransactionMissingKeywordsException e) {
            ui.showErrorMessage(e.getMessage());
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
