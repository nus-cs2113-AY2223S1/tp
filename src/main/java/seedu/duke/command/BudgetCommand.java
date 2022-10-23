package seedu.duke.command;

//@@author wcwy

import seedu.duke.Storage;
import seedu.duke.Ui;
import seedu.duke.data.Budget;
import seedu.duke.data.TransactionList;
import seedu.duke.exception.MoolahException;

import static seedu.duke.command.CommandTag.COMMAND_TAG_BUDGET_AMOUNT;

/**
 * Represents a budget command object that will set the user's monthly budget on the Budget command.
 */
public class BudgetCommand extends Command {
    private static final String LINE_SEPARATOR = System.lineSeparator();
    // The command word used to trigger the execution of Moolah Manager's operations
    public static final String COMMAND_WORD = "BUDGET";
    // The description for the usage of command
    public static final String COMMAND_DESCRIPTION = "To set the amount of monthly budget.";
    // The guiding information for the usage of command
    public static final String COMMAND_USAGE = "Usage: budget b/BUDGET";
    // The formatting information for the parameters used by the command
    public static final String COMMAND_PARAMETERS_INFO = "Parameters information: "
            + LINE_SEPARATOR
            + "BUDGET - Amount of monthly budget set.";

    // Basic budget description
    public static final String COMMAND_HELP = "Command Word: " + COMMAND_WORD + LINE_SEPARATOR
            + COMMAND_DESCRIPTION + LINE_SEPARATOR + COMMAND_USAGE + LINE_SEPARATOR;
    // Detailed budget description
    public static final String COMMAND_DETAILED_HELP = COMMAND_HELP + COMMAND_PARAMETERS_INFO
            + LINE_SEPARATOR;


    //@@author wcwy
    private long budgetAmount;

    /**
     * Default constructor for BudgetCommand. Budget amount is initialised as 1000 (default value).
     */
    public BudgetCommand() {
        budgetAmount = 1000;
    }

    /**
     * To store the parsed monthly budget amount received from user input.
     *
     * @param budgetAmount Monthly budget amount
     */
    @Override
    public void setBudgetAmount(long budgetAmount) {
        this.budgetAmount = budgetAmount;
    }

    /**
     * Gets the mandatory tags of the command.
     *
     * @return A string array containing all mandatory tags.
     */
    @Override
    public String[] getMandatoryTags() {
        String[] mandatoryTags = new String[]{
                COMMAND_TAG_BUDGET_AMOUNT
        };
        return mandatoryTags;
    }

    /**
     *
     * @param transactions An instance of the TransactionList class.
     * @param ui           An instance of the Ui class.
     * @param storage      An instance of the Storage class.
     * @throws MoolahException
     */
    @Override
    public void execute(TransactionList transactions, Ui ui, Storage storage) throws MoolahException {
        Budget.setBudget(budgetAmount);
        ui.showSetBudgetAcknowledgementMessage(Long.toString(budgetAmount));
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
