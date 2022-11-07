package seedu.duke.command;

//@@author brian-vb

import seedu.duke.Storage;
import seedu.duke.Ui;
import seedu.duke.common.DateFormats;
import seedu.duke.common.HelpMessages;
import seedu.duke.data.Budget;
import seedu.duke.data.TransactionList;

import seedu.duke.data.transaction.Transaction;
import seedu.duke.exception.EditCommandEmptyTagsException;
import seedu.duke.exception.EditCommandUnchangedException;
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
import static seedu.duke.common.HelpMessages.EDIT_COMMAND_BASIC_HELP;
import static seedu.duke.common.HelpMessages.EDIT_COMMAND_DETAILED_HELP;
import static seedu.duke.common.InfoMessages.INFO_EDIT_EXPENSE;
import static seedu.duke.common.InfoMessages.INFO_EDIT_INCOME;

/**
 * Represents an edit command object that will execute the operations for Edit command.
 */
public class EditCommand extends Command {
    //@@author brian-vb
    // The command word used to trigger the execution of Moolah Manager's operations
    public static final String COMMAND_WORD = "EDIT";

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
     * Executes the "edit" command. Checks and parses the necessary parameters before deleting transaction.
     *
     * @param ui           An instance of the Ui class.
     * @param transactions An instance of the TransactionList class.
     * @param storage      An instance of the Storage class.
     * @throws GlobalInvalidIndexException If the index inputted is invalid.
     * @throws EditCommandEmptyTagsException If the tags are empty.
     * @throws EditCommandUnchangedException If the tags result in an unchanged transaction.
     */
    @Override
    public void execute(TransactionList transactions, Ui ui, Storage storage) throws MoolahException {

        try {
            editLogger.setLevel(Level.SEVERE);
            editLogger.log(Level.INFO, "Edit Command undergoes multiple checks on various parameters "
                    + "before executing the command.");

            int index = entryNumber;
            String newType = type;
            String newDescription = description;
            int newAmount = amount;
            LocalDate newDate = date;
            String newCategory = category;

            boolean check = isIndexValid(transactions, index);
            boolean secondCheck = isTagsNonEmpty(newType, newDescription, newAmount, newCategory, newDate);

            assert index > 0;

            editLogger.log(Level.INFO, "If the index is invalid, the command will not execute and throw "
                    + "an exception later.");
            if (check) {
                Transaction entry = transactions.getEntry(index - 1);
                boolean thirdCheck = isParametersChanged(entry, newType, newDescription, newAmount,
                        newCategory, newDate);

                editLogger.log(Level.INFO, "If tags are empty or the tags will result in an unedited "
                        + "transaction, the command will stop executing and throw an exception later.");

                if (secondCheck && thirdCheck) {
                    newType = updateType(entry, newType);

                    if (newType.equals("expense")) {
                        newDate = updateDate(entry, newDate);
                        newDescription = updateDescription(entry, newDescription);
                        newCategory = updateCategory(entry, newCategory);
                        newAmount = updateAmount(entry, newAmount);

                        String message = transactions.editExpense(newDescription, newAmount, newCategory,
                                newDate, index - 1);

                        long addedMonthExpenseSum = transactions.calculateMonthlyTotalExpense(newDate);
                        String budgetInfo = Budget.generateBudgetRemainingMessage(addedMonthExpenseSum, true,
                                DateFormats.retrieveFormattedMonthAndYear(newDate));

                        Ui.showTransactionAction(INFO_EDIT_EXPENSE.toString(), message, budgetInfo);
                        editLogger.log(Level.INFO, "The requested transaction has been edited "
                                + "and the UI should display the confirmation message respectively.");
                    } else {
                        newDate = updateDate(entry, newDate);
                        newDescription = updateDescription(entry, newDescription);
                        newCategory = updateCategory(entry, newCategory);
                        newAmount = updateAmount(entry, newAmount);

                        String message = transactions.editIncome(newDescription, newAmount, newCategory,
                                newDate, index - 1);

                        long addedMonthExpenseSum = transactions.calculateMonthlyTotalExpense(newDate);
                        String budgetInfo = Budget.generateBudgetRemainingMessage(addedMonthExpenseSum, true,
                                DateFormats.retrieveFormattedMonthAndYear(newDate));

                        Ui.showTransactionAction(INFO_EDIT_INCOME.toString(), message, budgetInfo);
                        editLogger.log(Level.INFO, "The requested transaction has been edited "
                                + "and the UI should display the confirmation message respectively.");
                    }
                } else {
                    if (!secondCheck) {
                        editLogger.log(Level.WARNING, "EditCommandEmptyTagsException is thrown when the Tags "
                                + "are empty.");
                        throw new EditCommandEmptyTagsException();
                    } else {
                        editLogger.log(Level.WARNING, "EditCommandUnchangedException is thrown when the changed "
                                + "Tags result in an unchanged transaction.");
                        throw new EditCommandUnchangedException();
                    }
                }
            } else {
                editLogger.log(Level.WARNING, "InvalidIndexException is thrown when the index "
                        + "is invalid.");
                throw new GlobalInvalidIndexException();
            }
            storage.writeToFile(transactions.getTransactions());
        } catch (IOException e) {
            throw new StorageWriteErrorException();
        }
        editLogger.log(Level.INFO, "This is the end of the edit command.");

    }

    //@@author wcwy

    /**
     * Retrieves the basic help message of the command.
     *
     * @return A string containing the basic help description of the command.
     */
    public static HelpMessages getHelpMessage() {
        return EDIT_COMMAND_BASIC_HELP;
    }

    /**
     * Retrieves the detailed help message of the command.
     *
     * @return A string containing the detailed help description of the command.
     */
    public static HelpMessages getDetailedHelpMessage() {
        return EDIT_COMMAND_DETAILED_HELP;
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
     * Performs a check to see if the tags are not empty.
     *
     * @param newType The type of transaction
     * @param newDescription The description of the transaction
     * @param newAmount The amount of the transaction
     * @param newCategory The category of the transaction
     * @param newDate The date of the transaction
     * @return A boolean value that indicates whether the program continues execution.
     */
    public boolean isTagsNonEmpty(String newType, String newDescription, int newAmount, String newCategory,
                                  LocalDate newDate) {
        return (newType != null) || (newDescription != null) || (newAmount != 0) || (newCategory != null)
                || (newDate != null);
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

    /**
     * Performs a check to see if the parameters will change the transaction in the entry.
     *
     * @param entry The transaction that is to be edited
     * @param newType The type of transaction
     * @param newDescription The description of the transaction
     * @param newAmount The amount of the transaction
     * @param newCategory The category of the transaction
     * @param newDate The date of the transaction
     * @return A boolean value that indicates whether the program continues execution.
     */
    public boolean isParametersChanged(Transaction entry, String newType, String newDescription, int newAmount,
                                       String newCategory, LocalDate newDate) {
        final String oldType = entry.getType();
        newType = updateType(entry, newType);

        final String oldDescription = entry.getDescription();
        newDescription = updateDescription(entry, newDescription);

        final int oldAmount = entry.getAmount();
        newAmount = updateAmount(entry, newAmount);

        final String oldCategory = entry.getCategory();
        newCategory = updateCategory(entry, newCategory);

        final LocalDate oldDate = entry.getDate();
        newDate = updateDate(entry, newDate);

        return (!oldType.equals(newType))  || (!oldDescription.equals(newDescription))
                || (oldAmount != newAmount) || (!oldCategory.equals(newCategory))
                || (oldDate != newDate);
    }

    /**
     * Updates the type of the transaction locally.
     *
     * @param entry The specific entry to be edited
     * @param type The type of transaction
     * @return A string which contains the updated type.
     */
    public String updateType(Transaction entry, String type) {
        if (type == null) {
            type = entry.getType();
        }
        return type;
    }

    /**
     * Updates the type of the transaction locally.
     *
     * @param entry The specific entry to be edited
     * @param date The date of transaction
     * @return A string which contains the updated date.
     */

    public LocalDate updateDate(Transaction entry, LocalDate date) {
        if (date == null) {
            date = entry.getDate();
        }
        return date;
    }

    /**
     * Updates the type of the transaction locally.
     *
     * @param entry The specific entry to be edited
     * @param description The description of transaction
     * @return A string which contains the updated description.
     */

    public String updateDescription(Transaction entry, String description) {
        if (description == null) {
            description = entry.getDescription();
        }
        return description;
    }

    /**
     * Updates the type of the transaction locally.
     *
     * @param entry The specific entry to be edited
     * @param category The category of transaction
     * @return A string which contains the updated category.
     */

    public String updateCategory(Transaction entry, String category) {
        if (category == null) {
            category = entry.getCategory();
        }
        return category;
    }

    /**
     * Updates the type of the transaction locally.
     *
     * @param entry The specific entry to be edited
     * @param amount The amount of transaction
     * @return A string which contains the updated type.
     */

    public int updateAmount(Transaction entry, int amount) {
        if (amount == 0) {
            amount = entry.getAmount();
        }
        return amount;
    }
}
