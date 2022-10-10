package seedu.duke.command;

import seedu.duke.Storage;
import seedu.duke.Ui;
import seedu.duke.data.CategoryList;
import seedu.duke.data.TransactionList;
import seedu.duke.exception.ListStatisticsInvalidStatsTypeException;
import seedu.duke.exception.ListStatisticsMissingTagException;
import seedu.duke.exception.MoolahException;

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
            + COMMAND_DESCRIPTION + LINE_SEPARATOR
            + COMMAND_USAGE + LINE_SEPARATOR;
    // Detailed help description
    public static final String COMMAND_DETAILED_HELP = COMMAND_HELP + COMMAND_PARAMETERS_INFO
            + LINE_SEPARATOR;

    private String input;

    public StatsCommand(String input) {
        this.input = input;
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
        /*
        Checks if userInput is in the correct input format by further parsing,
        before passing any tags to the filter for statistics.
        */
        String[] splits = input.split(" ");
        checkTagsExist(splits);

        for (String split : splits) {
            String tag = split.substring(0, 2);
            String parameter = split.substring(2);
            switch (tag) {
            case "s/":
                listStatisticsByStatsType(parameter, transactions);
                break;
            default:
                break;
            }
        }
    }

    private static void listStatisticsByStatsType(String statsType, TransactionList transactions)
            throws ListStatisticsInvalidStatsTypeException {
        /*
        Known issue; currently each repeat use of command will generate more classes, need
        to probably add into constructor and pass in categories
        */
        CategoryList categories = new CategoryList();

        switch (statsType) {
        case "categories":
            categories.calculateTotalAmount(transactions);
            String categoriesList = categories.listCategories();
            if (categoriesList.isEmpty()) {
                Ui.showInfoMessage(INFO_STATS_EMPTY.toString());
                return;
            }
            assert !categoriesList.isEmpty();
            Ui.showTransactionsList(categoriesList, INFO_STATS_CATEGORIES.toString());
            break;
        default:
            throw new ListStatisticsInvalidStatsTypeException();
        }
    }

    /**
     * Checks if the targeted tags exists in the split user inputs.
     *
     * @param splits The user input after the command word, split into a list for every space found.
     * @throws ListStatisticsMissingTagException Missing tag exception.
     */
    private static void checkTagsExist(String[] splits) throws ListStatisticsMissingTagException {
        // TODO: To add the tags into Command class instead
        String[] tags = new String[]{"s/"};
        for (String tag : tags) {
            boolean found = findMatchingTagFromInputs(tag, splits);
            if (!found) {
                throw new ListStatisticsMissingTagException();
            }
        }
    }

    /**
     * Returns a boolean value on whether a tag can be found among the split user inputs.
     *
     * @param tag    A specific tag used to locate the command parameter.
     * @param splits The user input after the command word, split into a list for every space found.
     * @return Whether the tag is found within the split inputs.
     */
    private static boolean findMatchingTagFromInputs(String tag, String[] splits) {
        boolean found = false;
        for (String split : splits) {
            if (split.startsWith(tag)) {
                found = true;
                break;
            }
        }
        return found;
    }

    @Override
    public boolean isExit() {
        return false;
    }
}