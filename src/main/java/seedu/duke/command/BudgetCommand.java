package seedu.duke.command;

//@@author wcwy
import seedu.duke.Storage;
import seedu.duke.Ui;
import seedu.duke.common.HelpMessages;
import seedu.duke.data.Budget;
import seedu.duke.data.TransactionList;
import seedu.duke.exception.MoolahException;
import seedu.duke.exception.StorageWriteErrorException;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import static seedu.duke.command.CommandTag.COMMAND_TAG_BUDGET_AMOUNT;
import static seedu.duke.common.HelpMessages.BUDGET_COMMAND_BASIC_HELP;
import static seedu.duke.common.HelpMessages.BUDGET_COMMAND_DETAILED_HELP;

/**
 * Represents a budget command object that will set the user's monthly budget on the Budget command.
 */
public class BudgetCommand extends Command {
    // The command word used to trigger the execution of Moolah Manager's operations
    public static final String COMMAND_WORD = "BUDGET";

    //@@author wcwy
    private long budgetAmount;

    private static final Logger budgetLogger = Logger.getLogger(BudgetCommand.class.getName());

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
     * Update the monthly budget value stored in the program to new value and display acknowledgement message.
     *
     * @param transactions An instance of the TransactionList class.
     * @param ui           An instance of the Ui class.
     * @param storage      An instance of the Storage class.
     * @throws MoolahException If storage error is found.
     */
    @Override
    public void execute(TransactionList transactions, Ui ui, Storage storage) throws MoolahException {
        try {
            Budget.setBudget(budgetAmount);
            ui.showSetBudgetAcknowledgementMessage(Long.toString(budgetAmount));
            //@@author chinhan99
            storage.writeToFile(transactions.getTransactions());
        } catch (IOException e) {
            throw new StorageWriteErrorException();
        }
        budgetLogger.setLevel(Level.SEVERE);
        budgetLogger.log(Level.INFO, "Budget command has been executed to update the budget to: " + budgetAmount);
    }

    //@@author wcwy

    /**
     * Retrieves the basic help message of the command.
     *
     * @return A string containing the basic help description of the command.
     */
    public static HelpMessages getHelpMessage() {
        return BUDGET_COMMAND_BASIC_HELP;
    }

    /**
     * Retrieves the detailed help message of the command.
     *
     * @return A string containing the detailed help description of the command.
     */
    public static HelpMessages getDetailedHelpMessage() {
        return BUDGET_COMMAND_DETAILED_HELP;
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
