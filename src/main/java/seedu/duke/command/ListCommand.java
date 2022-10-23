package seedu.duke.command;

import seedu.duke.Storage;
import seedu.duke.Ui;
import seedu.duke.data.TransactionList;
import seedu.duke.exception.InputMissingTagException;
import seedu.duke.exception.InputTransactionUnknownTypeException;
import seedu.duke.exception.ListStatsInvalidStatsTypeException;
import seedu.duke.exception.MoolahException;

import java.time.LocalDate;
import java.util.logging.Logger;
import java.util.logging.Level;

import static seedu.duke.command.CommandTag.COMMAND_TAG_TRANSACTION_TYPE;
import static seedu.duke.command.CommandTag.COMMAND_TAG_TRANSACTION_CATEGORY;
import static seedu.duke.command.CommandTag.COMMAND_TAG_TRANSACTION_DATE;
import static seedu.duke.command.CommandTag.COMMAND_TAG_STATS_MONTH;
import static seedu.duke.command.CommandTag.COMMAND_TAG_STATS_YEAR;
import static seedu.duke.common.InfoMessages.INFO_LIST;
import static seedu.duke.common.InfoMessages.INFO_LIST_EMPTY;

/**
 * Represents a list command object that will execute the operations for List command.
 */
public class ListCommand extends Command {
    private static final String LINE_SEPARATOR = System.lineSeparator();
    // The command word used to trigger the execution of Moolah Manager's operations
    public static final String COMMAND_WORD = "LIST";
    // The description for the usage of command
    public static final String COMMAND_DESCRIPTION = "To list all or some transactions based on selection.";
    // The guiding information for the usage of command
    public static final String COMMAND_USAGE = "Usage: list [t/TYPE] [c/CATEGORY] [d/DATE] [m/MONTH] [y/YEAR]";
    // The formatting information for the parameters used by the command
    public static final String COMMAND_PARAMETERS_INFO = "Parameters information:"
            + LINE_SEPARATOR
            + "(Optional) TYPE - The type of transaction. Only \"income\" or \"expense\" is accepted."
            + LINE_SEPARATOR
            + "(Optional) CATEGORY: A category for the transaction. Only string containing alphabets is accepted."
            + LINE_SEPARATOR + "(Optional) DATE: Date of the transaction. The format must be in \"yyyyMMdd\".";

    // Basic help description
    public static final String COMMAND_HELP = "Command Word: " + COMMAND_WORD + LINE_SEPARATOR
            + COMMAND_DESCRIPTION + LINE_SEPARATOR + COMMAND_USAGE + LINE_SEPARATOR;
    // Detailed help description
    public static final String COMMAND_DETAILED_HELP = COMMAND_HELP + COMMAND_PARAMETERS_INFO + LINE_SEPARATOR;

    //@@author chydarren
    private static final Logger listLogger = Logger.getLogger(ListCommand.class.getName());

    //@@author paullowse
    private String category;
    private LocalDate date;
    private String type;
    private int month;
    private int year;

    /**
     * Initialises the variables of the ListCommand class.
     */
    public ListCommand() {
        category = "";
        date = null;
        type = "";
        month = -1;
        year = -1;
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
            COMMAND_TAG_STATS_MONTH,
            COMMAND_TAG_STATS_YEAR
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

    @Override
    public void setStatsMonth(int month) {
        this.month = month;
    }

    @Override
    public void setStatsYear(int year) {
        this.year = year;
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

        listTransactions(transactions, type, category, date, month, year);
    }

    /**
     * List all or some transactions based on selection.
     *
     * @param transactions An instance of the TransactionList class.
     * @param type         The type of transaction.
     * @param category     A category for the transaction.
     * @param date         Date of the transaction with format in "yyyyMMdd".
     * @throws InputTransactionUnknownTypeException If class type cannot be found in the packages.
     */
    private static void listTransactions(TransactionList transactions, String type, String category, LocalDate date,
                                         int month, int year)
            throws InputTransactionUnknownTypeException, InputMissingTagException {
        if (month != -1 && year == -1) {
            listLogger.log(Level.WARNING, "An exception has been caught as a month was given without a year.");
            throw new InputMissingTagException();
        }

        String transactionsList = transactions.listTransactions(type, category, date);

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