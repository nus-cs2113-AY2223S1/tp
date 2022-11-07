package seedu.duke.command;

//@@author chydarren

import seedu.duke.Storage;
import seedu.duke.Ui;
import seedu.duke.common.HelpMessages;
import seedu.duke.data.TransactionList;
import seedu.duke.data.transaction.Transaction;
import seedu.duke.exception.MoolahException;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.logging.Logger;
import java.util.logging.Level;

import static seedu.duke.command.CommandTag.COMMAND_TAG_TRANSACTION_TYPE;
import static seedu.duke.command.CommandTag.COMMAND_TAG_TRANSACTION_CATEGORY;
import static seedu.duke.command.CommandTag.COMMAND_TAG_TRANSACTION_DATE;
import static seedu.duke.command.CommandTag.COMMAND_TAG_GLOBAL_MONTH;
import static seedu.duke.command.CommandTag.COMMAND_TAG_GLOBAL_YEAR;
import static seedu.duke.command.CommandTag.COMMAND_TAG_GLOBAL_NUMBER;
import static seedu.duke.command.CommandTag.COMMAND_TAG_GLOBAL_PERIOD;

import static seedu.duke.common.HelpMessages.LIST_COMMAND_BASIC_HELP;
import static seedu.duke.common.HelpMessages.LIST_COMMAND_DETAILED_HELP;
import static seedu.duke.common.InfoMessages.INFO_LIST_EMPTY;
import static seedu.duke.common.InfoMessages.INFO_LIST;
import static seedu.duke.common.InfoMessages.INFO_LIST_WITHOUT_INDEXES;
import static seedu.duke.common.InfoMessages.LINE_SEPARATOR;

/**
 * Represents a list command object that will execute the operations for List command.
 */
public class ListCommand extends ListAndStatsCommand {
    //@@author chydarren
    // The command word used to trigger the execution of Moolah Manager's operations
    public static final String COMMAND_WORD = "LIST";

    private static Logger listLogger = Logger.getLogger(ListCommand.class.getName());
    private String category;
    private LocalDate date;
    private String type;

    //@@author paullowse

    /**
     * Initialises the variables of the ListCommand class.
     */
    public ListCommand() {
        super();
        category = "";
        date = null;
        type = "";
    }

    /**
     * Gets the optional tags of the command.
     *
     * @return A string array containing all optional tags.
     */
    @Override
    public String[] getOptionalTags() {
        String[] optionalTags = new String[]{
            COMMAND_TAG_TRANSACTION_TYPE,
            COMMAND_TAG_TRANSACTION_CATEGORY,
            COMMAND_TAG_TRANSACTION_DATE,
            COMMAND_TAG_GLOBAL_MONTH,
            COMMAND_TAG_GLOBAL_YEAR,
            COMMAND_TAG_GLOBAL_NUMBER,
            COMMAND_TAG_GLOBAL_PERIOD
        };
        return optionalTags;
    }

    @Override
    public void setType(String type) {
        this.type = type;
    }

    @Override
    public void setCategory(String category) {
        this.category = category;
    }

    @Override
    public void setDate(LocalDate date) {
        this.date = date;
    }

    //@@author chydarren

    /**
     * Executes the "List" command. Ensure that there are no errors in the tag combinations related
     * to DateIntervals before proceeding to list transactions with/without filters for each transaction
     * object.
     *
     * @param ui           An instance of the Ui class.
     * @param transactions An instance of the TransactionList class.
     * @param storage      An instance of the Storage class.
     */
    @Override
    public void execute(TransactionList transactions, Ui ui, Storage storage) throws MoolahException {
        listLogger.setLevel(Level.SEVERE);
        listLogger.log(Level.INFO, "Entering execution of the List command.");

        // Checks if there are any error in the tag combinations related to DateIntervals
        Boolean isContainDateIntervalsTags = checkContainDateIntervalsTags();
        listTransactions(transactions, ui, isContainDateIntervalsTags);
    }

    /**
     * List all or some transactions based on selection.
     *
     * @param transactions An instance of the TransactionList class.
     * @throws MoolahException If any type of exception has been caught within the function calls.
     */
    private void listTransactions(TransactionList transactions, Ui ui, Boolean isContainDateIntervalsTags)
            throws MoolahException {
        // Gets array list of transactions based on time filters, if any, i.e. year, month, period
        ArrayList<Transaction> timeTransactions = getTimeTransactions(transactions);

        /*
            Gets the list of transactions from the time-filtered array list based on whether each transaction
            matches the type, category or date (if any) filter(s) that have been given
         */
        String transactionsList = transactions.listTransactions(timeTransactions, type, category, date,
                isContainDateIntervalsTags);

        // Prints the list if available, else print no matching transactions
        if (transactionsList.isEmpty()) {
            listLogger.log(Level.INFO, "Transactions list is empty as there are no matching transactions.");
            ui.showInfoMessage(INFO_LIST_EMPTY.toString());
            return;
        } else if (isContainDateIntervalsTags) {
            listLogger.log(Level.INFO, "There are matching transactions for the Transactions list but no "
                    + "indexes will be shown.");
            ui.showList(INFO_LIST_WITHOUT_INDEXES.toString() + LINE_SEPARATOR + transactionsList, INFO_LIST.toString());
            return;
        }

        listLogger.log(Level.INFO, "There are matching transactions for the Transactions list.");
        ui.showList(transactionsList, INFO_LIST.toString());
    }

    //@@author wcwy

    /**
     * Retrieves the basic help message of the command.
     *
     * @return A string containing the basic help description of the command.
     */
    public static HelpMessages getHelpMessage() {
        return LIST_COMMAND_BASIC_HELP;
    }

    /**
     * Retrieves the detailed help message of the command.
     *
     * @return A string containing the detailed help description of the command.
     */
    public static HelpMessages getDetailedHelpMessage() {
        return LIST_COMMAND_DETAILED_HELP;
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