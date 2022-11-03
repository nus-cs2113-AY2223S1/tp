package seedu.duke.command;

//@@author chydarren

import seedu.duke.Storage;
import seedu.duke.Ui;
import seedu.duke.common.HelpMessages;
import seedu.duke.data.TransactionList;
import seedu.duke.exception.FindTransactionMissingKeywordsException;
import seedu.duke.exception.MoolahException;

import java.util.logging.Level;
import java.util.logging.Logger;

import static seedu.duke.common.HelpMessages.FIND_COMMAND_BASIC_HELP;
import static seedu.duke.common.HelpMessages.FIND_COMMAND_DETAILED_HELP;
import static seedu.duke.common.InfoMessages.INFO_LIST_FILTERED;
import static seedu.duke.common.InfoMessages.INFO_LIST_FILTERED_EMPTY;

/**
 * Represents a find command object that will execute the operations for Find command.
 */
public class FindCommand extends Command {
    //@@author chydarren
    // The command word used to trigger the execution of Moolah Manager's operations
    public static final String COMMAND_WORD = "FIND";

    private static Logger findLogger = Logger.getLogger(FindCommand.class.getName());
    private String keywords;

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
    public static void checkFindFormat(String keywords) throws FindTransactionMissingKeywordsException {
        findLogger.setLevel(Level.SEVERE);
        if (keywords.isBlank()) {
            findLogger.log(Level.WARNING, "Exception thrown as there are no keywords found.");
            throw new FindTransactionMissingKeywordsException();
        }
    }

    /**
     * Executes the "Find" command. Checks that the input contain a search expression, i.e. keywords
     * before performing any search on the transactions list.
     *
     * @param ui           An instance of the Ui class.
     * @param transactions An instance of the TransactionList class.
     * @param storage      An instance of the Storage class.
     */
    @Override
    public void execute(TransactionList transactions, Ui ui, Storage storage) throws MoolahException {
        findLogger.setLevel(Level.SEVERE);
        findLogger.log(Level.INFO, "Entering execution of the Find command.");

        // Checks the format of find to ensure that it contains keywords used in the search expression
        checkFindFormat(keywords);
        assert !keywords.isBlank();

        findTransactions(transactions, ui, keywords);
    }

    /**
     * Finds a specific or multiple transactions based on any keywords that have been specified.
     *
     * @param transactions An instance of the TransactionList class.
     * @param ui           An instance of the Ui class.
     * @param keywords     A string containing the keywords used in the search expression.
     */
    public void findTransactions(TransactionList transactions, Ui ui, String keywords) {
        // Gets the list of transactions where their description match the searching keywords
        String transactionsList = transactions.findTransactions(keywords);

        // Prints the list if available, else print no matching transactions
        if (transactionsList.isEmpty()) {
            findLogger.log(Level.INFO, "Transactions list is empty as there are no matching "
                    + "transactions.");
            ui.showInfoMessage(INFO_LIST_FILTERED_EMPTY.toString());
            return;
        }

        assert !transactionsList.isEmpty();
        findLogger.log(Level.INFO, "There are matching transactions for the Transactions list.");
        ui.showList(transactionsList, INFO_LIST_FILTERED.toString());
    }

    //@@author wcwy

    /**
     * Retrieves the basic help message of the command.
     *
     * @return A string containing the basic help description of the command.
     */
    public static HelpMessages getHelpMessage() {
        return FIND_COMMAND_BASIC_HELP;
    }

    /**
     * Retrieves the detailed help message of the command.
     *
     * @return A string containing the detailed help description of the command.
     */
    public static HelpMessages getDetailedHelpMessage() {
        return FIND_COMMAND_DETAILED_HELP;
    }

    //@@author paullowse

    /**
     * Enables the program to exit when the Bye command is issued.
     *
     * @return A boolean value that indicates whether the program shall exit.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}