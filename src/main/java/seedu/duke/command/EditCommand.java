package seedu.duke.command;

//@@author brian-vb

import seedu.duke.Storage;
import seedu.duke.Ui;
import seedu.duke.common.DateFormats;
import seedu.duke.data.Budget;
import seedu.duke.data.TransactionList;

import seedu.duke.data.transaction.Transaction;
import seedu.duke.exception.GlobalInvalidIndexException;

import seedu.duke.exception.MoolahException;

import seedu.duke.exception.StorageWriteErrorException;

import java.io.IOException;
import java.time.LocalDate;
import java.util.logging.Level;
import java.util.logging.Logger;

import static seedu.duke.command.CommandTag.COMMAND_TAG_GLOBAL_ENTRY_NUMBER;
import static seedu.duke.command.CommandTag.COMMAND_TAG_TRANSACTION_TYPE;
import static seedu.duke.command.CommandTag.COMMAND_TAG_TRANSACTION_CATEGORY;
import static seedu.duke.command.CommandTag.COMMAND_TAG_TRANSACTION_DATE;
import static seedu.duke.command.CommandTag.COMMAND_TAG_TRANSACTION_AMOUNT;
import static seedu.duke.command.CommandTag.COMMAND_TAG_TRANSACTION_DESCRIPTION;
import static seedu.duke.common.HelpMessages.COMMAND_PARAMETERS_ENTRY;
import static seedu.duke.common.HelpMessages.COMMAND_PARAMETERS_TYPE;
import static seedu.duke.common.HelpMessages.COMMAND_PARAMETERS_CATEGORY;
import static seedu.duke.common.HelpMessages.COMMAND_PARAMETERS_AMOUNT;
import static seedu.duke.common.HelpMessages.COMMAND_PARAMETERS_DATE;
import static seedu.duke.common.HelpMessages.COMMAND_PARAMETERS_DESCRIPTION;
import static seedu.duke.common.HelpMessages.COMMAND_DESCRIPTION_EDIT;
import static seedu.duke.common.HelpMessages.COMMAND_USAGE_EDIT;
import static seedu.duke.common.InfoMessages.INFO_EDIT_EXPENSE;
import static seedu.duke.common.InfoMessages.INFO_EDIT_INCOME;
import static seedu.duke.common.InfoMessages.LINE_SEPARATOR;

/**
 * Represents an edit command object that will execute the operations for Edit command.
 */
public class EditCommand extends Command {
    //@@author brian-vb
    // The command word used to trigger the execution of Moolah Manager's operations
    public static final String COMMAND_WORD = "EDIT";
    // The formatting information for the parameters used by the command
    public static final String COMMAND_PARAMETERS_INFO = "Parameters information:" + LINE_SEPARATOR
            + COMMAND_PARAMETERS_ENTRY + LINE_SEPARATOR + COMMAND_PARAMETERS_TYPE + LINE_SEPARATOR
            + COMMAND_PARAMETERS_CATEGORY + LINE_SEPARATOR + COMMAND_PARAMETERS_AMOUNT + LINE_SEPARATOR
            + COMMAND_PARAMETERS_AMOUNT + LINE_SEPARATOR + COMMAND_PARAMETERS_DATE + LINE_SEPARATOR
            + COMMAND_PARAMETERS_DESCRIPTION;
    // Basic help description
    public static final String COMMAND_HELP = "Command Word: " + COMMAND_WORD + LINE_SEPARATOR
            + COMMAND_DESCRIPTION_EDIT + LINE_SEPARATOR + COMMAND_USAGE_EDIT + LINE_SEPARATOR;
    // Detailed help description
    public static final String COMMAND_DETAILED_HELP = COMMAND_HELP + COMMAND_PARAMETERS_INFO
            + LINE_SEPARATOR;

    //@@author paullowse
    private int entryNumber;
    private String type;
    private String description;
    private int amount;
    private String category;
    private LocalDate date;

    //@@brian-vb
    private static final Logger editLogger = Logger.getLogger(DeleteCommand.class.getName());

    //@@paullowse
    public EditCommand() {
    }

    /**
     * Gets the mandatory tags of the command.
     *
     * @return A string array containing all mandatory tags.
     */
    @Override
    public String[] getMandatoryTags() {
        String[] mandatoryTags = new String[]{COMMAND_TAG_GLOBAL_ENTRY_NUMBER};
        return mandatoryTags;
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
            COMMAND_TAG_TRANSACTION_AMOUNT,
            COMMAND_TAG_TRANSACTION_DATE,
            COMMAND_TAG_TRANSACTION_DESCRIPTION
        };
        return optionalTags;
    }

    @Override
    public void setEntryNumber(int entryNumber) {
        this.entryNumber = entryNumber;
    }

    @Override
    public void setType(String type) {
        this.type = type;
    }

    @Override
    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public void setAmount(int amount) {
        this.amount = amount;
    }

    @Override
    public void setCategory(String category) {
        this.category = category;
    }

    @Override
    public void setDate(LocalDate date) {
        this.date = date;
    }

    //@@author brian-vb
    /**
     * Executes the "edit" command.
     *
     * @param ui           An instance of the Ui class.
     * @param transactions An instance of the TransactionList class.
     * @param storage      An instance of the Storage class.
     */
    @Override
    public void execute(TransactionList transactions, Ui ui, Storage storage) throws MoolahException {

        try {
            editLogger.setLevel(Level.SEVERE);
            editLogger.log(Level.INFO, "Edit Command checks whether the index is valid "
                    + "before executing the command.");
            int index = entryNumber;
            Transaction entry = transactions.getEntry(index - 1);
            boolean isInputValid = true;
            int numberOfTransactions = transactions.size();

            if ((index > numberOfTransactions) || (index <= 0)) {
                isInputValid = false;
            }
            assert index > 0;

            if (isInputValid) {
                String newType = type;
                String newDescription = description;
                int newAmount = amount;
                LocalDate newDate = date;
                String newCategory = category;

                if (newType == null) {
                    newType = entry.getType();
                }

                if (newType.equals("expense")) {
                    if (newDate == null) {
                        newDate = entry.getDate();
                    }

                    if (newDescription == null) {
                        newDescription = entry.getDescription();
                    }

                    if (newCategory == null) {
                        newCategory = entry.getCategory();
                    }

                    if (newAmount == 0) {
                        newAmount = entry.getAmount();
                    }
                    transactions.deleteTransaction(index - 1);
                    String message = transactions.editExpense(newDescription, newAmount, newCategory, newDate, index);

                    long addedMonthExpenseSum = transactions.calculateMonthlyTotalExpense(newDate);
                    String budgetInfo = Budget.generateBudgetRemainingMessage(addedMonthExpenseSum, true,
                            DateFormats.retrieveFormattedMonthAndYear(newDate));

                    Ui.showTransactionAction(INFO_EDIT_EXPENSE.toString(), message, budgetInfo);
                    editLogger.log(Level.INFO, "The requested transaction has been edited "
                            + "and the UI should display the confirmation message respectively.");
                } else {
                    if (newDate == null) {
                        newDate = entry.getDate();
                    }

                    if (newDescription == null) {
                        newDescription = entry.getDescription();
                    }

                    if (newCategory == null) {
                        newCategory = entry.getCategory();
                    }

                    if (newAmount == 0) {
                        newAmount = entry.getAmount();
                    }
                    transactions.deleteTransaction(index - 1);
                    String message = transactions.editIncome(newDescription, newAmount, newCategory, newDate, index);

                    long addedMonthExpenseSum = transactions.calculateMonthlyTotalExpense(newDate);
                    String budgetInfo = Budget.generateBudgetRemainingMessage(addedMonthExpenseSum, true,
                            DateFormats.retrieveFormattedMonthAndYear(newDate));

                    Ui.showTransactionAction(INFO_EDIT_INCOME.toString(), message, budgetInfo);
                    editLogger.log(Level.INFO, "The requested transaction has been edited "
                            + "and the UI should display the confirmation message respectively.");
                }
            } else {
                editLogger.log(Level.WARNING, "InvalidIndexException thrown when the index "
                        + "is invalid.");
                throw new GlobalInvalidIndexException();
            }
            storage.writeToFile(transactions.getTransactions());
        } catch (IOException e) {
            throw new StorageWriteErrorException();
        }
        editLogger.log(Level.INFO, "This is the end of the edit command.");

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
