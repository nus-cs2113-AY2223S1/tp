package seedu.duke.command;

//@@author chydarren

import seedu.duke.Storage;
import seedu.duke.Ui;
import seedu.duke.common.HelpMessages;
import seedu.duke.data.TransactionList;

import java.util.logging.Level;
import java.util.logging.Logger;

import static seedu.duke.command.CommandTag.COMMAND_TAG_FIND_KEYWORD;
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
    private String keyword;

    public FindCommand() {
        keyword = "";
    }

    /**
     * Gets the mandatory tags of the command.
     *
     * @return A string array containing all mandatory tags.
     */
    @Override
    public String[] getMandatoryTags() {
        String[] mandatoryTags = new String[]{COMMAND_TAG_FIND_KEYWORD};
        return mandatoryTags;
    }

    @Override
    public void setKeyword(String keyword) {
        this.keyword = keyword;
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
    public void execute(TransactionList transactions, Ui ui, Storage storage) {
        findLogger.setLevel(Level.SEVERE);
        findLogger.log(Level.INFO, "Entering execution of the Find command.");

        assert !keyword.isBlank();
        findTransactions(transactions, ui, keyword);
    }

    /**
     * Finds a specific or multiple transactions based on any keyword that have been specified.
     *
     * @param transactions An instance of the TransactionList class.
     * @param ui           An instance of the Ui class.
     * @param keyword      A string containing the keyword used in the search expression.
     */
    public void findTransactions(TransactionList transactions, Ui ui, String keyword) {
        // Gets the list of transactions where their description match the searching keyword
        String transactionsList = transactions.findTransactions(keyword);

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