package seedu.duke.command;

import seedu.duke.Storage;
import seedu.duke.Ui;
import seedu.duke.data.TransactionList;
import seedu.duke.exception.InputTransactionInvalidTagException;
import seedu.duke.exception.InputTransactionUnknownTypeException;
import seedu.duke.exception.MoolahException;

import java.time.LocalDate;

import static seedu.duke.common.InfoMessages.INFO_LIST;
import static seedu.duke.common.InfoMessages.INFO_LIST_EMPTY;
import static seedu.duke.common.Utilities.checkExtraTagExist;
import static seedu.duke.common.Utilities.parseCategoryTag;
import static seedu.duke.common.Utilities.parseDateTag;
import static seedu.duke.common.Utilities.parseTypeTag;

/**
 * Represents a list command object that will execute the operations for List command.
 */
public class ListCommand extends Command {
    private static final String LINE_SEPARATOR = System.lineSeparator();
    // The command word used to trigger the execution of Moolah Manager's operations
    public static final String COMMAND_WORD = "LIST";
    // The description for the usage of command
    public static final String COMMAND_DESCRIPTION = "To list all or some transactions based on selection.";
    // The guiding information for the usage of command
    public static final String COMMAND_USAGE = "Usage: list [t/TYPE] [c/CATEGORY] [d/DATE]";
    // The formatting information for the parameters used by the command
    public static final String COMMAND_PARAMETERS_INFO = "Parameters information:"
            + LINE_SEPARATOR
            + "(Optional) TYPE - The type of transaction. Only \"income\" or \"expense\" is accepted."
            + LINE_SEPARATOR
            + "(Optional) CATEGORY: A category for the transaction. Only string containing alphabets is accepted."
            + LINE_SEPARATOR
            + "(Optional) DATE: Date of the transaction. The format must be in \"yyyyMMdd\".";

    // Basic help description
    public static final String COMMAND_HELP = "Command Word: " + COMMAND_WORD + LINE_SEPARATOR
            + COMMAND_DESCRIPTION + LINE_SEPARATOR
            + COMMAND_USAGE + LINE_SEPARATOR;
    // Detailed help description
    public static final String COMMAND_DETAILED_HELP = COMMAND_HELP + COMMAND_PARAMETERS_INFO
            + LINE_SEPARATOR;

    private static final int TAG_LIMIT = 3;
    private static final int MINIMUM_TAG_LENGTH = 2;
    private static final String CLASS_TYPE_EXPENSE = "seedu.duke.data.transaction.Expense";
    private static final String CLASS_TYPE_INCOME = "seedu.duke.data.transaction.Income";

    private String input;

    public ListCommand(String input) {
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
        before passing any tags to the filter for transaction list.
        */
        String[] splits = {};
        if (!input.isEmpty()) {
            splits = input.split(" ");
        }

        // Throws exception if there are more than three tags
        checkExtraTagExist(splits, TAG_LIMIT);

        String category = "";
        LocalDate date = null;
        String type = "";

        for (String split : splits) {
            // Throws exception if the tag cannot split
            if (split.length() < MINIMUM_TAG_LENGTH) {
                throw new InputTransactionInvalidTagException();
            }
            assert split.length() >= MINIMUM_TAG_LENGTH;

            String tag = split.substring(0, 2);
            String parameter = split.substring(2);
            switch (tag) {
            case "t/":
                type = parseTypeTag(parameter);
                assert type.equals(CLASS_TYPE_EXPENSE) || type.equals(CLASS_TYPE_INCOME);
                break;
            case "c/":
                category = parseCategoryTag(parameter);
                break;
            case "d/":
                date = parseDateTag(parameter);
                assert date != null;
                break;
            default:
                // Throws exception if the tag is invalid
                throw new InputTransactionInvalidTagException();
            }
        }
        listTransactions(transactions, type, category, date);
    }

    /**
     * List all or some transactions based on selection.
     *
     * @param transactions An instance of the TransactionList class.
     * @param type The type of transaction.
     * @param category A category for the transaction.
     * @param date Date of the transaction with format in "yyyyMMdd".
     * @throws InputTransactionUnknownTypeException If class type cannot be found in the packages.
     */
    private static void listTransactions(TransactionList transactions, String type,
                                         String category, LocalDate date)
            throws InputTransactionUnknownTypeException {
        String transactionsList = transactions.listTransactions(type, category, date);
        if (transactionsList.isEmpty()) {
            Ui.showInfoMessage(INFO_LIST_EMPTY.toString());
            return;
        }
        assert !transactionsList.isEmpty();
        Ui.showTransactionsList(transactionsList, INFO_LIST.toString());
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
