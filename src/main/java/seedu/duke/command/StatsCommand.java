package seedu.duke.command;

import seedu.duke.Storage;
import seedu.duke.Ui;
import seedu.duke.data.TransactionList;
import seedu.duke.data.transaction.Transaction;
import seedu.duke.exception.ListStatsInvalidStatsTypeException;
import seedu.duke.exception.MoolahException;
import seedu.duke.exception.StatsInvalidYearException;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import static seedu.duke.command.CommandTag.COMMAND_TAG_STATISTICS_TYPE;
import static seedu.duke.command.CommandTag.COMMAND_TAG_STATS_MONTH;
import static seedu.duke.command.CommandTag.COMMAND_TAG_STATS_YEAR;
import static seedu.duke.common.InfoMessages.INFO_STATS_CATEGORIES;
import static seedu.duke.common.InfoMessages.INFO_STATS_EMPTY;
import static seedu.duke.common.InfoMessages.INFO_STATS_TIME;

/**
 * Represents a get command object that will execute the operations for Get command.
 */
public class StatsCommand extends Command {
    private static final String LINE_SEPARATOR = System.lineSeparator();
    // The command word used to trigger the execution of Moolah Manager's operations
    public static final String COMMAND_WORD = "STATS";
    // The description for the usage of command
    public static final String COMMAND_DESCRIPTION = "To get statistics of the transactions such "
            + "as the total savings for each category, summary of expenditure, etc.";
    // The guiding information for the usage of command
    public static final String COMMAND_USAGE = "Usage: stats s/STATISTICS_TYPE";
    // The formatting information for the parameters used by the command
    public static final String COMMAND_PARAMETERS_INFO = "Parameters information:"
            + LINE_SEPARATOR
            + "STATISTICS_TYPE: The type of statistics to be generated. Only \"categories\" is accepted.";

    // Basic help description
    public static final String COMMAND_HELP = "Command Word: " + COMMAND_WORD + LINE_SEPARATOR
            + COMMAND_DESCRIPTION + LINE_SEPARATOR + COMMAND_USAGE + LINE_SEPARATOR;
    // Detailed help description
    public static final String COMMAND_DETAILED_HELP = COMMAND_HELP + COMMAND_PARAMETERS_INFO
            + LINE_SEPARATOR;

    //@@author chydarren
    private static final Logger statsLogger = Logger.getLogger(StatsCommand.class.getName());
    private String statsType;
    private int month = -1;
    private int year = -1;

    //@@author paullowse
    public StatsCommand() {
    }

    /**
     * Gets the mandatory tags of the command.
     *
     * @return A string array containing all mandatory tags.
     */
    @Override
    public String[] getMandatoryTags() {
        String[] mandatoryTags = new String[]{COMMAND_TAG_STATISTICS_TYPE};
        return mandatoryTags;
    }

    @Override
    public String[] getOptionalTags() {
        String[] optionalTags = new String[]{
            COMMAND_TAG_STATS_MONTH,
            COMMAND_TAG_STATS_YEAR
        };
        return optionalTags;
    }


    @Override
    public void setStatsType(String statsType) {
        this.statsType = statsType;
    }

    @Override
    public void setStatsMonth(int month) {
        this.month = month;
    }

    @Override
    public void setStatsYear(int year) {
        this.year = year;
    }

    public int getStatsYear() {
        return year;
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
        statsLogger.setLevel(Level.SEVERE);
        statsLogger.log(Level.INFO, "Entering execution of the Stats command.");

        listStatsByStatsType(statsType, transactions, month, year);
    }

    /**
     * Lists the statistics depending on the type of statistics requested.
     *
     * @param statsType                             The type of statistics that is needed.
     * @param transactions                          An instance of the TransactionList class.
     * @throws ListStatsInvalidStatsTypeException   If the type of statistics is not recognised.
     */
    private static void listStatsByStatsType(String statsType, TransactionList transactions, int month, int year)
            throws ListStatsInvalidStatsTypeException, StatsInvalidYearException {
        switch (statsType) {
        case "categories":
            statsLogger.log(Level.INFO, "Stats type has been detected for categorical savings.");
            statsTypeCategoricalSavings(transactions);
            statsLogger.log(Level.INFO, "End of Stats command.");
            break;
        case "time":
            statsLogger.log(Level.INFO, "Stats type has been detected for monthly savings.");
            if (year == -1) {
                statsLogger.log(Level.WARNING, "An exception has been caught due to a missing year tag");
                throw new StatsInvalidYearException();
            }
            statsTypeTimeSavings(transactions, year, month);
            break;


        default:
            statsLogger.log(Level.WARNING, "An exception has been caught due to an invalid stats type.");
            throw new ListStatsInvalidStatsTypeException();
        }
    }

    /**
     * Display the statistics requested for current amount of savings in each category.
     *
     * @param transactions An instance of the TransactionList class.
     */
    public static void statsTypeCategoricalSavings(TransactionList transactions) {
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

    //@@author paullowse
    public static void statsTypeTimeSavings(TransactionList transactions, int year, int month) {
        //System.out.println(year);
        //System.out.println(month);

        ArrayList<Transaction> timeTransactions;
        // only year
        if (month == -1) {
            timeTransactions = transactions.getTransactionsByYear(year);
        } else {
            timeTransactions = transactions.getTransactionsByMonth(year, month);
        }
        String timeSavingsList = transactions.listTimeStats(timeTransactions, year, month);

        if (timeSavingsList.isEmpty()) {
            statsLogger.log(Level.INFO, "Categorical savings list is empty as there are no transactions available.");
            Ui.showInfoMessage(INFO_STATS_EMPTY.toString());
            return;
        }

        assert !timeSavingsList.isEmpty();
        statsLogger.log(Level.INFO, "Monthly savings list is found to contain categories-amount pairs.");
        Ui.showList(timeSavingsList, INFO_STATS_TIME.toString());

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
