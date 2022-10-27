package seedu.duke.command;

//@@author wcwy
import seedu.duke.Storage;
import seedu.duke.Ui;
import seedu.duke.data.Budget;
import seedu.duke.data.TransactionList;
import seedu.duke.exception.MoolahException;
import seedu.duke.exception.StorageWriteErrorException;

import java.io.IOException;

import static seedu.duke.command.CommandTag.COMMAND_TAG_BUDGET_AMOUNT;
import static seedu.duke.common.HelpMessages.COMMAND_PARAMETERS_BUDGET;
import static seedu.duke.common.HelpMessages.COMMAND_DESCRIPTION_BUDGET;
import static seedu.duke.common.HelpMessages.COMMAND_USAGE_BUDGET;
import static seedu.duke.common.InfoMessages.LINE_SEPARATOR;

/**
 * Represents a budget command object that will set the user's monthly budget on the Budget command.
 */
public class BudgetCommand extends Command {
    // The command word used to trigger the execution of Moolah Manager's operations
    public static final String COMMAND_WORD = "BUDGET";
    // The formatting information for the parameters used by the command
    public static final String COMMAND_PARAMETERS_INFO = "Parameters information: " + LINE_SEPARATOR
            + COMMAND_PARAMETERS_BUDGET;
    // Basic budget description
    public static final String COMMAND_HELP = "Command Word: " + COMMAND_WORD + LINE_SEPARATOR
            + COMMAND_DESCRIPTION_BUDGET + LINE_SEPARATOR + COMMAND_USAGE_BUDGET + LINE_SEPARATOR;
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
