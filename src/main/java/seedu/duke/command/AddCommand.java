package seedu.duke.command;

import seedu.duke.Storage;
import seedu.duke.Ui;

import seedu.duke.data.TransactionList;
import seedu.duke.data.transaction.Expense;
import seedu.duke.data.transaction.Income;
import seedu.duke.exception.MoolahException;
import seedu.duke.exception.InputTransactionUnknownTypeException;

import java.time.LocalDate;

import static seedu.duke.command.CommandTag.COMMAND_TAG_TRANSACTION_TYPE;
import static seedu.duke.command.CommandTag.COMMAND_TAG_TRANSACTION_CATEGORY;
import static seedu.duke.command.CommandTag.COMMAND_TAG_TRANSACTION_DATE;
import static seedu.duke.command.CommandTag.COMMAND_TAG_TRANSACTION_AMOUNT;
import static seedu.duke.command.CommandTag.COMMAND_TAG_TRANSACTION_DESCRIPTION;
import static seedu.duke.common.InfoMessages.INFO_ADD_EXPENSE;
import static seedu.duke.common.InfoMessages.INFO_ADD_INCOME;

/**
 * Represents an add command object that will execute the operations for Add command.
 */
public class AddCommand extends Command {
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
    public static final String COMMAND_HELP = "Command Word: " + COMMAND_WORD + "\n" + COMMAND_DESCRIPTION + "\n"
            + COMMAND_USAGE + "\n";
    // Detailed help description
    public static final String COMMAND_DETAILED_HELP = COMMAND_HELP + COMMAND_PARAMETERS_INFO + "\n";

    private String type;
    private String description;
    private int amount;
    private String category;
    private LocalDate date;

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

    /**
     * Executes the "add" command. Checks and parses the necessary parameters before adding transaction.
     *
     * @param ui           An instance of the Ui class.
     * @param transactions An instance of the TransactionList class.
     * @param storage      An instance of the Storage class.
     * @throws InputTransactionUnknownTypeException If the type of transaction is not recognised.
     */
    @Override
    public void execute(TransactionList transactions, Ui ui, Storage storage) throws MoolahException {

        assert date != null;
        switch (type) {
        case Expense.TRANSACTION_NAME:
            String expense = transactions.addExpense(description, amount, category, date);
            Ui.showTransactionAction(INFO_ADD_EXPENSE.toString(), expense);
            break;
        case Income.TRANSACTION_NAME:
            String income = transactions.addIncome(description, amount, category, date);
            Ui.showTransactionAction(INFO_ADD_INCOME.toString(), income);
            break;
        default:
            throw new InputTransactionUnknownTypeException();
        }
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
