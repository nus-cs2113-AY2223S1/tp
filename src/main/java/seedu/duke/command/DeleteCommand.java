package seedu.duke.command;

//@@author brian-vb
import seedu.duke.Storage;
import seedu.duke.Ui;
import seedu.duke.common.DateFormats;
import seedu.duke.common.HelpMessages;
import seedu.duke.data.Budget;
import seedu.duke.data.TransactionList;
import seedu.duke.exception.GlobalInvalidIndexException;
import seedu.duke.exception.MoolahException;
import seedu.duke.exception.StorageWriteErrorException;

import java.io.IOException;
import java.time.LocalDate;
import java.util.logging.Level;
import java.util.logging.Logger;

import static seedu.duke.command.CommandTag.COMMAND_TAG_GLOBAL_ENTRY_NUMBER;
import static seedu.duke.common.HelpMessages.DELETE_COMMAND_BASIC_HELP;
import static seedu.duke.common.HelpMessages.DELETE_COMMAND_DETAILED_HELP;
import static seedu.duke.common.InfoMessages.INFO_DELETE;

/**
 * Represents a delete command object that will execute the operations for Delete command.
 */
public class DeleteCommand extends Command {
    //@@author brian-vb
    // The command word used to trigger the execution of Moolah Manager's operations
    public static final String COMMAND_WORD = "DELETE";

    // The optional tags that may exist in the user input
    private static final Logger deleteLogger = Logger.getLogger(DeleteCommand.class.getName());

    private int entryNumber;

    //@@author paullowse
    public DeleteCommand() {
    }

    /**
     * Gets the mandatory tags of the command.
     *
     * @return A string array containing all mandatory tags.
     */
    @Override
    public String[] getMandatoryTags() {
        // The mandatory tags that must exist in the user input
        String[] mandatoryTags = new String[]{COMMAND_TAG_GLOBAL_ENTRY_NUMBER};

        return mandatoryTags;
    }

    @Override
    public void setEntryNumber(int entryNumber) {
        this.entryNumber = entryNumber;
    }

    //@@author brian-vb

    /**
     * Executes the "delete" command. Checks and parses the necessary parameters before deleting transaction.
     *
     * @param ui           An instance of the Ui class.
     * @param transactions An instance of the TransactionList class.
     * @param storage      An instance of the Storage class.
     * @throws GlobalInvalidIndexException If the index inputted is invalid.
     */
    @Override
    public void execute(TransactionList transactions, Ui ui, Storage storage) throws MoolahException {
        try {
            deleteLogger.setLevel(Level.SEVERE);
            deleteLogger.log(Level.INFO, "Delete Command checks whether the index is valid "
                    + "before executing the command.");
            int index = entryNumber;
            boolean check = isIndexValid(transactions, index);
            assert index > 0;

            if (check) {
                LocalDate date = transactions.getEntry(index - 1).getDate();
                String transaction = transactions.deleteTransaction(index - 1);

                long addedMonthExpenseSum = transactions.calculateMonthlyTotalExpense(date);
                String budgetInfo = Budget.generateBudgetRemainingMessage(addedMonthExpenseSum, true,
                        DateFormats.retrieveFormattedMonthAndYear(date));

                Ui.showTransactionAction(INFO_DELETE.toString(), transaction, budgetInfo);
                deleteLogger.log(Level.INFO, "The requested transaction has been deleted "
                        + "and the UI should display the confirmation message respectively.");
                storage.writeToFile(transactions.getTransactions());
            } else {
                deleteLogger.log(Level.WARNING, "InvalidIndexException thrown when the index "
                        + "is invalid.");
                throw new GlobalInvalidIndexException();
            }
        } catch (IOException e) {
            throw new StorageWriteErrorException();
        }
        deleteLogger.log(Level.INFO, "This is the end of the delete command.");
    }

    //@@author wcwy

    /**
     * Retrieves the basic help message of the command.
     *
     * @return A string containing the basic help description of the command.
     */
    public static HelpMessages getHelpMessage() {
        return DELETE_COMMAND_BASIC_HELP;
    }

    /**
     * Retrieves the detailed help message of the command.
     *
     * @return A string containing the detailed help description of the command.
     */
    public static HelpMessages getDetailedHelpMessage() {
        return DELETE_COMMAND_DETAILED_HELP;
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

    /**
     * Performs a check to see if the index is a valid one.
     *
     * @param transactions The list of transactions
     * @param index The input index
     * @return A boolean value that indicates whether the program continues execution.
     */
    public boolean isIndexValid(TransactionList transactions, int index) {
        int numberOfTransactions = transactions.size();
        return (index <= numberOfTransactions) && (index > 0);
    }
}
