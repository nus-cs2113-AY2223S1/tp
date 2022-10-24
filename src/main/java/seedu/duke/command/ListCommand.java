package seedu.duke.command;

//@@author chydarren
import seedu.duke.Storage;
import seedu.duke.Ui;
import seedu.duke.data.TransactionList;
import seedu.duke.exception.GlobalMissingTagException;
import seedu.duke.exception.InputTransactionInvalidTypeException;
import seedu.duke.exception.MoolahException;

import java.time.LocalDate;
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

/**
 * Represents a list command object that will execute the operations for List command.
 */
public class ListCommand extends Command {
    //@@author chydarren
    private static final String LINE_SEPARATOR = System.lineSeparator();
    // The command word used to trigger the execution of Moolah Manager's operations
    public static final String COMMAND_WORD = "LIST";
    // The description for the usage of command
    public static final String COMMAND_DESCRIPTION = "To list all or some transactions based on selection."
            + " Note that the tags will be joint in the filter based on the 'AND' operation.";
    // The guiding information for the usage of command
    public static final String COMMAND_USAGE = "Usage: list [t/TYPE] [c/CATEGORY] [d/DATE] [m/MONTH] [y/YEAR]";
    // The formatting information for the parameters used by the command
    public static final String COMMAND_PARAMETERS_INFO = "Parameters information:"
            + LINE_SEPARATOR
            + "(Optional) TYPE - The type of transaction. Only \"income\" or \"expense\" is accepted."
            + LINE_SEPARATOR
            + "(Optional) CATEGORY: A category for the transaction. Only string containing alphabets is accepted."
            + LINE_SEPARATOR
            + "(Optional) DATE: Date of the transaction. The format must be in \"yyyyMMdd\"."
            + LINE_SEPARATOR
            + "(Optional) MONTH: Month of the transaction. Only integers within 1 to 12 are accepted. Note that"
            + " month must be accompanied by a year."
            + LINE_SEPARATOR
            + "(Optional) YEAR: Year of the transaction. Only integers from 1000 onwards are accepted.";

    // Basic help description
    public static final String COMMAND_HELP = "Command Word: " + COMMAND_WORD + LINE_SEPARATOR
            + COMMAND_DESCRIPTION + LINE_SEPARATOR + COMMAND_USAGE + LINE_SEPARATOR;
    // Detailed help description
    public static final String COMMAND_DETAILED_HELP = COMMAND_HELP + COMMAND_PARAMETERS_INFO + LINE_SEPARATOR;

    //@@author chydarren
    private static final int UNDEFINED_PARAMETER = -1;
    private static final int TRUE_AND = 1;
    private static final int TRUE_OR = 2;
    private static final int TRUE_INVALID_OR = 3;
    private static final int FALSE = 0;

    private static Logger listLogger = Logger.getLogger(ListCommand.class.getName());
    private String category;
    private LocalDate date;
    private String type;
    private int month;
    private int year;
    private String period;
    private int number;

    //@@author paullowse

    /**
     * Initialises the variables of the ListCommand class.
     */
    public ListCommand() {
        category = "";
        date = null;
        type = "";
        month = UNDEFINED_PARAMETER;
        year = UNDEFINED_PARAMETER;
        period = null;
        number = UNDEFINED_PARAMETER;
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

    @Override
    public void setGlobalMonth(int month) {
        this.month = month;
    }

    @Override
    public void setGlobalYear(int year) {
        this.year = year;
    }

    @Override
    public void setGlobalNumber(int number) {
        this.number = number;
    }

    @Override
    public void setGlobalPeriod(String period) {
        this.period = period;
    }

    //@@author chydarren

    /**
     * Checks if the input contains month or/and year tags.
     *
     * @return 1 if both tags are given, 2 if only month is given, 0 if both are not given.
     */
    public int containMonthYear() {
        if (month != UNDEFINED_PARAMETER && year != UNDEFINED_PARAMETER) {
            return TRUE_AND;
        } else if (year != UNDEFINED_PARAMETER) {
            return TRUE_OR;
        } else if (month != UNDEFINED_PARAMETER) {
            return TRUE_INVALID_OR;
        }
        return FALSE;
    }

    /**
     * Checks if the input contains period and/or number tags.
     *
     * @return 1 if both tags are given, 2 if either period/number is given, 0 if both are not given.
     */
    public int containPeriodNumber() {
        if (period != null && number != UNDEFINED_PARAMETER) {
            return TRUE_AND;
        } else if (period != null || number != UNDEFINED_PARAMETER) {
            return TRUE_OR;
        }
        return FALSE;
    }

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

        // Throws an invalid list tags exception if there are both month/year and period/number
        if (containMonthYear() == TRUE_OR && containPeriodNumber() == TRUE_OR) {
            listLogger.log(Level.WARNING, "An exception has been caught as an invalid combination of tags "
                    + "has been given.");
        // Throws a missing tag exception if month was given without a year
        } else if (containMonthYear() == TRUE_INVALID_OR) {
            listLogger.log(Level.WARNING, "An exception has been caught as a month was given without a year.");
            throw new GlobalMissingTagException();
        // Throws a missing tag if number and period was not given together
        } else if (containPeriodNumber() == TRUE_OR) {
            listLogger.log(Level.WARNING, "An exception has been caught as number and period needs to be "
                    + "given together.");
            throw new GlobalMissingTagException();
        }

        listTransactions(transactions, type, category, date, month, year, number, period);
    }

    /**
     * List all or some transactions based on selection.
     *
     * @param transactions An instance of the TransactionList class.
     * @param type         The type of transaction.
     * @param category     A category for the transaction.
     * @param date         Date of the transaction with format in "yyyyMMdd".
     * @throws InputTransactionInvalidTypeException If class type cannot be found in the packages.
     */
    private static void listTransactions(TransactionList transactions, String type, String category, LocalDate date,
                                         int month, int year, int number, String period)
            throws InputTransactionInvalidTypeException {
        String transactionsList = transactions.listTransactions(type, category, date, month, year, number, period);

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