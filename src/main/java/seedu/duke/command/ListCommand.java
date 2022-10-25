package seedu.duke.command;

//@@author chydarren
import seedu.duke.Storage;
import seedu.duke.Ui;
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

import static seedu.duke.common.InfoMessages.INFO_LIST;
import static seedu.duke.common.InfoMessages.INFO_LIST_EMPTY;
import static seedu.duke.common.InfoMessages.LINE_SEPARATOR;

/**
 * Represents a list command object that will execute the operations for List command.
 */
public class ListCommand extends ListAndStatsCommand {
    //@@author chydarren
    // The command word used to trigger the execution of Moolah Manager's operations
    public static final String COMMAND_WORD = "LIST";
    // The description for the usage of command
    public static final String COMMAND_DESCRIPTION = "To list all or some transactions based on selection."
            + " Tags will be joined in the filter based on the 'AND' operation.";
    // The guiding information for the usage of command
    public static final String COMMAND_USAGE = "Usage: list [t/TYPE] [c/CATEGORY] [d/DATE] [m/MONTH] [y/YEAR] "
            + "[p/PERIOD] [n/NUMBER]";
    // The formatting information for the parameters used by the command
    public static final String COMMAND_PARAMETERS_INFO = "Parameters information:"
            + LINE_SEPARATOR
            + "(Optional) TYPE - The type of transaction. Only \"income\" or \"expense\" is accepted."
            + LINE_SEPARATOR
            + "(Optional) CATEGORY: A category for the transaction. Only string containing alphabets is accepted."
            + LINE_SEPARATOR
            + "(Optional) DATE: Date of the transaction. The format must be in \"yyyyMMdd\"."
            + LINE_SEPARATOR
            + "(Optional) MONTH: Month of the transaction. Only integers within 1 to 12 are accepted. Note that "
            + "month must be accompanied by a year. This tag cannot be used together with [p/PERIOD] or [n/NUMBER] "
            + "tags."
            + LINE_SEPARATOR
            + "(Optional) YEAR: Year of the transaction. Only integers from 1000 onwards are accepted."
            + "This tag cannot be used together with [p/PERIOD] or [n/NUMBER] tags."
            + LINE_SEPARATOR
            + "(Optional) PERIOD: Period of the transaction. Only \"weeks\" or \"months\" is accepted. Note that"
            + "period must be accompanied by a number to backdate from. This tag cannot be used together with "
            + "[m/MONTH] or [y/YEAR] tags."
            + LINE_SEPARATOR
            + "(Optional) NUMBER: Last number of weeks or months. Only positive integers are accepted. Note that"
            + "number must be accompanied by a period that represents weeks or months. This tag cannot be used "
            + "together with [m/MONTH] or [y/YEAR] tags.";

    // Basic help description
    public static final String COMMAND_HELP = "Command Word: " + COMMAND_WORD + LINE_SEPARATOR
            + COMMAND_DESCRIPTION + LINE_SEPARATOR + COMMAND_USAGE + LINE_SEPARATOR;
    // Detailed help description
    public static final String COMMAND_DETAILED_HELP = COMMAND_HELP + COMMAND_PARAMETERS_INFO + LINE_SEPARATOR;

    //@@author chydarren
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
     * Executes the operations related to the command.
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
        parseDateIntervalsTags();
        listTransactions(transactions);
    }

    /**
     * List all or some transactions based on selection.
     *
     * @param transactions An instance of the TransactionList class.
     * @throws MoolahException If any type of exception has been caught within the function calls.
     */
    private void listTransactions(TransactionList transactions) throws MoolahException {
        ArrayList<Transaction> timeTransactions = getTimeTransactions(transactions);
        String transactionsList = transactions.listTransactions(timeTransactions, type, category, date);

        if (transactionsList.isEmpty()) {
            listLogger.log(Level.INFO, "Transactions list is empty as there are no transactions available.");
            Ui.showInfoMessage(INFO_LIST_EMPTY.toString());
            return;
        }

        assert !transactionsList.isEmpty();
        listLogger.log(Level.INFO, "Transactions list is found to contain transaction records.");
        Ui.showList(transactionsList, INFO_LIST.toString());
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