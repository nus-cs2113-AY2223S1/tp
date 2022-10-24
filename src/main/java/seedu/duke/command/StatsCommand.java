package seedu.duke.command;

//@@author paullowse
import seedu.duke.Storage;
import seedu.duke.Ui;
import seedu.duke.data.TransactionList;
import seedu.duke.data.transaction.Transaction;
import seedu.duke.exception.InputTransactionInvalidTypeException;
import seedu.duke.exception.StatsInvalidTypeException;
import seedu.duke.exception.MoolahException;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import static seedu.duke.command.CommandTag.COMMAND_TAG_STATS_TYPE;
import static seedu.duke.command.CommandTag.COMMAND_TAG_GLOBAL_MONTH;
import static seedu.duke.command.CommandTag.COMMAND_TAG_GLOBAL_NUMBER;
import static seedu.duke.command.CommandTag.COMMAND_TAG_GLOBAL_PERIOD;
import static seedu.duke.command.CommandTag.COMMAND_TAG_GLOBAL_YEAR;
import static seedu.duke.common.InfoMessages.INFO_STATS_CATEGORIES;
import static seedu.duke.common.InfoMessages.INFO_STATS_MONTHS;
import static seedu.duke.common.InfoMessages.INFO_STATS_EMPTY;
import static seedu.duke.common.InfoMessages.INFO_STATS_EXPENSES;
import static seedu.duke.common.InfoMessages.INFO_STATS_INCOME;
import static seedu.duke.common.InfoMessages.INFO_STATS_SAVINGS;
import static seedu.duke.common.InfoMessages.INFO_STATS_SUMMARY_HEADER;
import static seedu.duke.common.InfoMessages.INFO_STATS_TIME;
import static seedu.duke.common.InfoMessages.LINE_SEPARATOR;

/**
 * Represents a get command object that will execute the operations for Get command.
 */
public class StatsCommand extends ListAndStatsCommand {
    //@@author paullowse
    // The command word used to trigger the execution of Moolah Manager's operations
    public static final String COMMAND_WORD = "STATS";
    // The description for the usage of command
    public static final String COMMAND_DESCRIPTION = "To get statistics of the transactions such"
            + " as the total savings for each category, summary of expenditure over a time period.";
    // The guiding information for the usage of command
    public static final String COMMAND_USAGE = "Usage: stats s/STATS_TYPE [m/MONTH] [y/YEAR] [p/PERIOD] [n/NUMBER]";
    // The formatting information for the parameters used by the command
    public static final String COMMAND_PARAMETERS_INFO = "Parameters information:"
            + LINE_SEPARATOR
            + "STATISTICS_TYPE: The type of statistics to be generated. Only \"categories\", \"months\" or \"time\" "
            + "is accepted."
            + "(Optional) MONTH: Month of the transaction. Only integers within 1 to 12 are accepted. Note that "
            + "month must be accompanied by a year. This tag cannot be used together with [p/PERIOD] or [n/NUMBER] "
            + "tags."
            + LINE_SEPARATOR
            + "(Optional) YEAR: Year of the transaction. Only integers from 1000 onwards are accepted."
            + "This tag cannot be used together with [p/PERIOD] or [n/NUMBER] tags."
            + LINE_SEPARATOR
            + "(Optional) PERIOD: Period of the transaction. Only \"weeks\" or \"months\" is accepted. Note that "
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
    public static final String COMMAND_DETAILED_HELP = COMMAND_HELP + COMMAND_PARAMETERS_INFO
            + LINE_SEPARATOR;

    //@@author chydarren
    private static final int TRUE_AND = 1;
    private static final int TRUE_OR = 2;
    private static final int FALSE = 0;
    private static final String CATEGORIES = "categories";
    private static final String TIME = "time";
    private static final String MONTHS = "months";
    private static Logger statsLogger = Logger.getLogger(StatsCommand.class.getName());
    private String statsType;

    //@@author paullowse

    public StatsCommand() {
        super();
        statsType = "";
    }

    /**
     * Gets the mandatory tags of the command.
     *
     * @return A string array containing all mandatory tags.
     */
    @Override
    public String[] getMandatoryTags() {
        String[] mandatoryTags = new String[]{COMMAND_TAG_STATS_TYPE};
        return mandatoryTags;
    }

    @Override
    public String[] getOptionalTags() {
        String[] optionalTags = new String[]{
            COMMAND_TAG_GLOBAL_MONTH,
            COMMAND_TAG_GLOBAL_YEAR,
            COMMAND_TAG_GLOBAL_NUMBER,
            COMMAND_TAG_GLOBAL_PERIOD,
        };
        return optionalTags;
    }

    //@@author chydarren

    @Override
    public void setStatsType(String statsType) {
        this.statsType = statsType;
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
        statsLogger.setLevel(Level.SEVERE);
        statsLogger.log(Level.INFO, "Entering execution of the Stats command.");

        // Checks if there are any error in the tag combinations related to DateIntervals
        parseDateIntervalsTags();
        listStatsByStatsType(statsType, transactions);
    }

    /**
     * Lists the statistics depending on the type of statistics requested.
     *
     * @param statsType     The type of statistics that is needed.
     * @param transactions  An instance of the TransactionList class.
     * @throws MoolahException If the type of statistics is not recognised.
     */
    private void listStatsByStatsType(String statsType, TransactionList transactions)
            throws MoolahException {
        switch (statsType) {
        case MONTHS:
            statsTypeMonthlyExpenditure(transactions);
            break;
        case CATEGORIES:
            statsLogger.log(Level.INFO, "Stats type has been detected for categorical savings.");
            statsTypeCategoricalSavings(transactions);
            statsLogger.log(Level.INFO, "End of Stats command.");
            break;
        //@@author paullowse
        case TIME:
            statsLogger.log(Level.INFO, "Stats type has been detected for monthly savings.");
            // Stats command uses last N months or years
            if (containPeriodNumber() == TRUE_AND && containMonthYear() == FALSE) {
                statsLogger.log(Level.INFO, "Stats command uses last N months or years.");
                statsTypeTimeSavings(transactions);
            // Stats command uses either monthly or yearly
            } else if (containPeriodNumber() == FALSE && (containMonthYear() == TRUE_OR
                    || containMonthYear() == TRUE_AND)) {
                statsLogger.log(Level.INFO, "Stats command uses either monthly or yearly.");
                statsTypeTimeSavings(transactions);
            // Throws a missing tag if s/time was called without any relevant tags
            } else {
                statsLogger.log(Level.WARNING, "An exception has been caught due to a missing tag");
                throw new StatsInvalidTypeException();
            }
            break;
        //@@author chydarren
        default:
            statsLogger.log(Level.WARNING, "An exception has been caught due to an invalid stats type.");
            throw new StatsInvalidTypeException();
        }
    }

    //@@author chydarren

    /**
     * Display the statistics requested for current amount of savings in each category.
     *
     * @param transactions An instance of the TransactionList class.
     */
    public void statsTypeCategoricalSavings(TransactionList transactions) {
        String categoricalSavingsList = transactions.listCategoricalSavings();

        if (categoricalSavingsList.isEmpty()) {
            statsLogger.log(Level.INFO, "Categorical savings list is empty as there are no transactions available.");
            Ui.showInfoMessage(INFO_STATS_EMPTY.toString());
            return;
        }

        assert !categoricalSavingsList.isEmpty();
        statsLogger.log(Level.INFO, "Categorical savings list is found to contain categories-amount pairs.");
        Ui.showList(categoricalSavingsList, INFO_STATS_CATEGORIES.toString());
    }

    /**
     * Display the statistics requested for the amount of expenditure and savings accumulated over different months.
     *
     * @param transactions An instance of the TransactionList class.
     */
    public void statsTypeMonthlyExpenditure(TransactionList transactions) throws InputTransactionInvalidTypeException {
        String monthlyExpenditureList = transactions.listMonthlyExpenditure();

        if (monthlyExpenditureList.isEmpty()) {
            statsLogger.log(Level.INFO, "Monthly expenditure list is empty as there are no transactions available.");
            Ui.showInfoMessage(INFO_STATS_EMPTY.toString());
            return;
        }

        assert !monthlyExpenditureList.isEmpty();
        statsLogger.log(Level.INFO, "Monthly expenditure list is found to contain month-expenditure pairs.");
        Ui.showList(monthlyExpenditureList, INFO_STATS_MONTHS.toString());
    }

    //@@author paullowse

    /**
     * Calls transactions to get the necessary transaction list, convert the parameters into a String for output.
     * Produces info strings, list of categories and summary statistics.
     *
     * @param transactions An instance of the TransactionList class.
     */
    public void statsTypeTimeSavings(TransactionList transactions) {
        ArrayList<Transaction> timeTransactions = getTimeTransactions(transactions);
        String timeSavingsList = transactions.listTimeStats(timeTransactions, year, month, period, number);

        if (timeSavingsList.isEmpty()) {
            statsLogger.log(Level.INFO, "Categorical savings list is empty as there are no transactions available.");
            Ui.showInfoMessage(INFO_STATS_EMPTY.toString());
            return;
        }

        // summary values
        ArrayList<String> amounts;
        amounts = transactions.processTimeSummaryStats(timeTransactions);

        String incomeMessage = INFO_STATS_SUMMARY_HEADER + LINE_SEPARATOR.toString()
                + INFO_STATS_INCOME + amounts.get(0);
        String expensesMessage = INFO_STATS_EXPENSES + amounts.get(1);
        String savingsMessage = INFO_STATS_SAVINGS + amounts.get(2);

        assert !timeSavingsList.isEmpty();
        statsLogger.log(Level.INFO, "Monthly savings list is found to contain categories-amount pairs.");
        Ui.showStatsList(timeSavingsList, INFO_STATS_TIME.toString(), incomeMessage, expensesMessage, savingsMessage);
    }

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
