package seedu.duke.command;

//@@author paullowse

import seedu.duke.Storage;
import seedu.duke.Ui;
import seedu.duke.data.TransactionList;
import seedu.duke.data.transaction.Transaction;
import seedu.duke.exception.GlobalMissingTagException;
import seedu.duke.exception.GlobalUnsupportedTagException;
import seedu.duke.exception.MoolahException;
import seedu.duke.exception.StatsInvalidTypeException;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import static seedu.duke.command.CommandTag.COMMAND_TAG_GLOBAL_MONTH;
import static seedu.duke.command.CommandTag.COMMAND_TAG_GLOBAL_NUMBER;
import static seedu.duke.command.CommandTag.COMMAND_TAG_GLOBAL_PERIOD;
import static seedu.duke.command.CommandTag.COMMAND_TAG_GLOBAL_YEAR;
import static seedu.duke.command.CommandTag.COMMAND_TAG_STATS_TYPE;
import static seedu.duke.common.InfoMessages.COLON_SPACE;
import static seedu.duke.common.InfoMessages.DOLLAR_SIGN;
import static seedu.duke.common.InfoMessages.INFO_EXPENSE;
import static seedu.duke.common.InfoMessages.INFO_INCOME;
import static seedu.duke.common.InfoMessages.INFO_SAVINGS;
import static seedu.duke.common.InfoMessages.INFO_STATS_EMPTY;
import static seedu.duke.common.InfoMessages.INFO_STATS_EXPENDITURE_HEADER;
import static seedu.duke.common.InfoMessages.INFO_STATS_GENERIC;
import static seedu.duke.common.InfoMessages.INFO_STATS_TIME_INSIGHTS;
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
            + "STATISTICS_TYPE: The type of statistics to be generated. Only \"categorical_savings\", "
            + "\"monthly_expenditure\" or \"time_insights\" is accepted."
            + LINE_SEPARATOR
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
    private static final String CATEGORICAL_SAVINGS = "categorical_savings";
    private static final String MONTHLY_EXPENDITURE = "monthly_expenditure";
    private static final String TIME_INSIGHTS = "time_insights";
    private static final int FALSE = 0;
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

        if (!statsType.equals(TIME_INSIGHTS) && (containMonthYear() != FALSE || containPeriodNumber() != FALSE)) {
            // Throws an unsupported tag exception if non-time_insights tag is using date intervals tags
            throw new GlobalUnsupportedTagException();
        } else if (statsType.equals(TIME_INSIGHTS) && containMonthYear() == FALSE && containPeriodNumber() == FALSE) {
            // Throws a missing mandatory tag(s) exception if there are no related tags for time_insights
            throw new GlobalMissingTagException();
        }

        // Checks if there are any error in the tag combinations related to DateIntervals
        parseDateIntervalsTags();
        listStatsByStatsType(transactions);
    }

    /**
     * Lists the statistics depending on the type of statistics requested.
     *
     * @param transactions  An instance of the TransactionList class.
     * @throws MoolahException If the type of statistics is not recognised.
     */
    public void listStatsByStatsType(TransactionList transactions) throws MoolahException {
        switch (statsType) {
        case MONTHLY_EXPENDITURE:
        case CATEGORICAL_SAVINGS:
            statsLogger.log(Level.INFO, "Stats type has been detected for categorical savings "
                    + "or monthly expenditure.");
            statsTypeCategoricalSavingsOrMonthlyExpenditure(transactions);
            break;
        //@@author paullowse
        case TIME_INSIGHTS:
            statsLogger.log(Level.INFO, "Stats type has been detected for time insights.");
            statsTypeTimeInsights(transactions);
            break;
        //@@author chydarren
        default:
            statsLogger.log(Level.WARNING, "An exception has been caught due to an invalid stats type.");
            throw new StatsInvalidTypeException();
        }
    }

    //@@author chydarren

    /**
     * Display the statistics requested for current amount of savings in each category or the monthly expenditure.
     *
     * @param transactions An instance of the TransactionList class.
     */
    public void statsTypeCategoricalSavingsOrMonthlyExpenditure(TransactionList transactions) {
        String genericStatsList = transactions.listMonthlyExpenditure();

        if (statsType.equals(CATEGORICAL_SAVINGS)) {
            // Replaces stats list with categorical savings if stats type is categorical_savings
            genericStatsList = transactions.listCategoricalSavings();
        }

        if (genericStatsList.isEmpty()) {
            statsLogger.log(Level.INFO, "Generic stats list is empty as there are no transactions available.");
            Ui.showInfoMessage(INFO_STATS_EMPTY.toString());
            return;
        }

        assert !genericStatsList.isEmpty();
        statsLogger.log(Level.INFO, "Generic stats list has info available for categorical savings or "
                + "monthly expenditure.");
        Ui.showList(genericStatsList, String.format(INFO_STATS_GENERIC.toString(), statsType));
    }

    //@@author paullowse

    /**
     * Calls transactions to get the necessary transaction list, convert the parameters into a String for output.
     * Produces info strings, list of categories and summary statistics.
     *
     * @param transactions An instance of the TransactionList class.
     */
    public void statsTypeTimeInsights(TransactionList transactions) {
        ArrayList<Transaction> timeTransactions = getTimeTransactions(transactions);
        String timeInsightsList = transactions.listTimeStats(timeTransactions, year, month, period, number);

        if (timeInsightsList.isEmpty()) {
            statsLogger.log(Level.INFO, "Time insights list is empty as there are no transactions available.");
            Ui.showInfoMessage(INFO_STATS_EMPTY.toString());
            return;
        }

        // summary values
        ArrayList<String> amounts;
        amounts = transactions.processTimeSummaryStats(timeTransactions);

        String incomeMessage = INFO_STATS_EXPENDITURE_HEADER + LINE_SEPARATOR.toString()
                + String.format("%s%s%s", INFO_INCOME, COLON_SPACE, DOLLAR_SIGN) + amounts.get(0);
        String expensesMessage = String.format("%s%s%s", INFO_EXPENSE, COLON_SPACE, DOLLAR_SIGN) + amounts.get(1);
        String savingsMessage = String.format("%s%s%s", INFO_SAVINGS, COLON_SPACE, DOLLAR_SIGN) + amounts.get(2);

        assert !timeInsightsList.isEmpty();
        statsLogger.log(Level.INFO, "Time insights list has info available for the specified time period.");
        Ui.showTimeInsightsList(timeInsightsList, INFO_STATS_TIME_INSIGHTS.toString(), incomeMessage, expensesMessage,
                savingsMessage);
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
