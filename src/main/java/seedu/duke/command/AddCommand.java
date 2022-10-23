package seedu.duke.command;

//@@author wcwy
import seedu.duke.Storage;
import seedu.duke.Ui;

import seedu.duke.data.Budget;
import seedu.duke.data.TransactionList;
import seedu.duke.data.transaction.Transaction;
import seedu.duke.data.transaction.Expense;
import seedu.duke.data.transaction.Income;
import seedu.duke.exception.InputTransactionInvalidTypeException;
import seedu.duke.exception.MoolahException;
import seedu.duke.exception.StorageWriteErrorException;
import seedu.duke.exception.MaximumTransactionCountException;

//@@author chinhan99
import java.io.IOException;
import java.time.LocalDate;
import java.util.logging.Level;
import java.util.logging.Logger;

import static seedu.duke.command.CommandTag.COMMAND_TAG_TRANSACTION_AMOUNT;
import static seedu.duke.command.CommandTag.COMMAND_TAG_TRANSACTION_CATEGORY;
import static seedu.duke.command.CommandTag.COMMAND_TAG_TRANSACTION_DATE;
import static seedu.duke.command.CommandTag.COMMAND_TAG_TRANSACTION_DESCRIPTION;
import static seedu.duke.command.CommandTag.COMMAND_TAG_TRANSACTION_TYPE;

import static seedu.duke.common.Constants.MAX_TRANSACTIONS_COUNT;
import static seedu.duke.common.InfoMessages.INFO_ADD_EXPENSE;
import static seedu.duke.common.InfoMessages.INFO_ADD_INCOME;

/**
 * Represents an add command object that will execute the operations for Add command.
 */
public class AddCommand extends Command {
    //@@author chinhan99
    private static final String LINE_SEPARATOR = System.lineSeparator();
    // The command word used to trigger the execution of Moolah Manager's operations
    public static final String COMMAND_WORD = "ADD";

    // The description for the usage of command
    public static final String COMMAND_DESCRIPTION = "To add a new transaction entry, which could be "
            + "either an \"income\" or an \"expense\" into the transaction list.";
    // The guiding information for the usage of command
    public static final String COMMAND_USAGE = "Usage: add t/TYPE c/CATEGORY a/AMOUNT d/DATE i/DESCRIPTION";
    // The formatting information for the parameters used by the command
    public static final String COMMAND_PARAMETERS_INFO = "Parameters information:"
            + LINE_SEPARATOR
            + "TYPE: The type of transaction. Only \"income\" or \"expense\" is accepted." + LINE_SEPARATOR
            + "CATEGORY: A category for the transaction. Only string containing alphabets is accepted."
            + LINE_SEPARATOR
            + "AMOUNT: Value of the transaction in numerical form. Only integer within 0 and 10000000 is accepted."
            + LINE_SEPARATOR + "DATE: Date of the transaction. The format must be in \"yyyyMMdd\"." + LINE_SEPARATOR
            + "DESCRIPTION: More information regarding the transaction, written without any space.";
    // Basic help description
    public static final String COMMAND_HELP = "Command Word: " + COMMAND_WORD + LINE_SEPARATOR
            + COMMAND_DESCRIPTION + LINE_SEPARATOR + COMMAND_USAGE + LINE_SEPARATOR;
    // Detailed help description
    public static final String COMMAND_DETAILED_HELP = COMMAND_HELP + COMMAND_PARAMETERS_INFO + LINE_SEPARATOR;

    private static final Logger addLogger = Logger.getLogger(AddCommand.class.getName());

    //@@author paullowse
    private String type;
    private String description;
    private int amount;
    private String category;
    private LocalDate date;
    private Transaction transactionCreated;

    public AddCommand() {
    }

    /**
     * Initialises the variables of the AddCommand class.
     */
    public AddCommand(String type, String description, int amount, String category, LocalDate date) {
        this.type = type;
        this.description = description;
        this.amount = amount;
        this.category = category;
        this.date = date;
    }

    //@@author wcwy

    /**
     * Gets the mandatory tags of the command.
     *
     * @return A string array containing all mandatory tags.
     */
    @Override
    public String[] getMandatoryTags() {

        String[] mandatoryTags = new String[]{
            COMMAND_TAG_TRANSACTION_TYPE,
            COMMAND_TAG_TRANSACTION_CATEGORY,
            COMMAND_TAG_TRANSACTION_AMOUNT,
            COMMAND_TAG_TRANSACTION_DATE,
            COMMAND_TAG_TRANSACTION_DESCRIPTION
        };
        return mandatoryTags;
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

    public void setTransactionCreated(Transaction transaction) {
        this.transactionCreated = transaction;
    }

    /**
     * Executes the "add" command. Checks and parses the necessary parameters before adding transaction.
     *
     * @param ui           An instance of the Ui class.
     * @param transactions An instance of the TransactionList class.
     * @param storage      An instance of the Storage class.
     * @throws InputTransactionInvalidTypeException If the type of transaction is not recognised or on storage error.
     */
    @Override
    public void execute(TransactionList transactions, Ui ui, Storage storage) throws MoolahException {
        //@@author chinhan99
        try {
            addLogger.setLevel(Level.SEVERE);
            addLogger.log(Level.INFO, "Add Command checks the type of the transaction "
                    + "before adding into the transaction class.");
            assert date != null;

            //@@author wcwy
            checkTransactionCapacity(transactions);
            String messageBanner = addTransaction(transactions);
            long addedMonthExpenseSum = transactions.calculateMonthlyTotalExpense(date);
            String budgetLeft = Budget.getBudgetLeft(addedMonthExpenseSum);

            Ui.showTransactionAction(messageBanner, transactionCreated.toString(), budgetLeft);

            //@@author chinhan99
            storage.writeToFile(transactions.getTransactions());
        } catch (IOException e) {
            throw new StorageWriteErrorException();
        }
        addLogger.log(Level.INFO, "End of Add command.");
    }

    //@@author wcwy

    /**
     * Checks if the number of transactions in the transaction list has reached the capacity.
     * This prevents the number of transactions added to the list to be more than the capacity.
     *
     * <p>With this, it is possible for the application to compute the maximum and minimum value budget difference
     * to prevent integer overflow.
     *
     * @param transactions The list of transactions in the application.
     * @throws MaximumTransactionCountException If the transaction list capacity has been reached.
     */
    public static void checkTransactionCapacity(TransactionList transactions) throws MaximumTransactionCountException {
        assert transactions != null;
        // The expected maximum number of transactions allowed to store is only one million
        if (transactions.size() == MAX_TRANSACTIONS_COUNT) {
            addLogger.log(Level.WARNING, "A transaction is attempted to be stored beyond its capacity");
            throw new MaximumTransactionCountException();
        }
    }

    /**
     * Adds the transaction into the transaction list based on the parameters stored by the AddCommand object.
     *
     * <p>Store the transaction object created in transactionCreated and return the message banner based on the
     * transaction type created.
     *
     * @param transactions The list of transactions in the application.
     * @returns A string containing the message banner based on the type of transaction created.
     * @throws InputTransactionInvalidTypeException If the type of the transactions
     */
    private String addTransaction(TransactionList transactions) throws InputTransactionInvalidTypeException {
        assert (transactions != null);
        String messageBanner = "";
        Transaction transaction;
        switch (type) {
        case Expense.TRANSACTION_NAME:
            transaction = transactions.addExpense(description, amount, category, date);
            messageBanner = INFO_ADD_EXPENSE.toString();
            addLogger.log(Level.INFO, "New expense transaction has been added "
                    + "and the UI should display acknowledgment message respectively.");
            break;
        case Income.TRANSACTION_NAME:
            transaction = transactions.addIncome(description, amount, category, date);
            messageBanner = INFO_ADD_INCOME.toString();
            addLogger.log(Level.INFO, "New income transaction has been added "
                    + "and the UI should display acknowledgment message respectively.");
            break;
        default:
            addLogger.log(Level.SEVERE, "The parsed type of transaction stored in addCommand is unknown!");
            throw new InputTransactionInvalidTypeException();
        }
        setTransactionCreated(transaction);
        return messageBanner;
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
