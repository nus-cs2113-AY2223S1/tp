package seedu.duke.command;

import seedu.duke.Storage;
import seedu.duke.Ui;
import seedu.duke.data.CategoryList;
import seedu.duke.data.TransactionList;
import seedu.duke.exception.ListStatisticsInvalidStatsTypeException;
import seedu.duke.exception.MoolahException;

import java.util.logging.Level;
import java.util.logging.Logger;

import static seedu.duke.command.CommandTag.COMMAND_TAG_STATISTICS_TYPE;
import static seedu.duke.common.InfoMessages.INFO_STATS_CATEGORIES;
import static seedu.duke.common.InfoMessages.INFO_STATS_EMPTY;

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
        statsLogger.log(Level.INFO, "Stats command starts passing the type of statistics"
                + " and transactions list into the listStatisticsByStatsType method.");

        listStatisticsByStatsType(statsType, transactions);
    }

    @Override
    public void setStatsType(String statsType) {
        this.statsType = statsType;
    }

    /**
     * Lists the statistics depending on the type of statistics requested.
     *
     * @param statsType     The type of statistics that is needed, e.g. categories.
     * @param transactions  An instance of the TransactionList class.
     * @throws ListStatisticsInvalidStatsTypeException If the type of statistics is not recognised.
     */
    private static void listStatisticsByStatsType(String statsType, TransactionList transactions)
            throws ListStatisticsInvalidStatsTypeException {
        statsLogger.log(Level.INFO, "A new instance of CategoryList is created.");
        CategoryList categories = new CategoryList();

        switch (statsType) {
        case "categories":
            statsLogger.log(Level.INFO, "The categories and amount for each category are "
                    + " being tallied and computed.");
            categories.calculateTotalAmount(transactions);
            String categoriesList = categories.listCategories();

            if (categoriesList.isEmpty()) {
                statsLogger.log(Level.INFO, "Categories list is empty, so UI should display that"
                        + " there are no statistics available.");
                Ui.showInfoMessage(INFO_STATS_EMPTY.toString());
                statsLogger.log(Level.INFO, "End of Stats command.");
                return;
            }
            assert !categoriesList.isEmpty();
            statsLogger.log(Level.INFO, "Categories list is available, so UI should display the"
                    + " categories and amount of savings per category.");
            Ui.showTransactionsList(categoriesList, INFO_STATS_CATEGORIES.toString());
            break;
        default:
            statsLogger.log(Level.WARNING, "An exception has been caught due to an invalid statistics type.");
            throw new ListStatisticsInvalidStatsTypeException();
        }

        statsLogger.log(Level.INFO, "End of Stats command.");
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
