package seedu.duke.command;

import seedu.duke.Storage;
import seedu.duke.Ui;

import seedu.duke.data.TransactionList;
import seedu.duke.exception.MoolahException;
import seedu.duke.exception.AddTransactionInvalidAmountException;
import seedu.duke.exception.AddTransactionMissingParameterException;
import seedu.duke.exception.AddTransactionMissingTagException;
import seedu.duke.exception.InputTransactionInvalidTagException;
import seedu.duke.exception.InputTransactionUnknownTypeException;

import java.time.LocalDate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static seedu.duke.common.InfoMessages.INFO_ADD_EXPENSE;
import static seedu.duke.common.InfoMessages.INFO_ADD_INCOME;
import static seedu.duke.common.Utilities.checkExtraTagExist;
import static seedu.duke.common.Utilities.parseCategoryTag;
import static seedu.duke.common.Utilities.parseDateTag;

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

    private static final int TAG_LIMIT = 5;

    private String input;

    public AddCommand(String input) {
        this.input = input;
    }

    /**
     * Executes the "add" command. Check and parse the necessary parameters before adding transaction.
     *
     * @param ui           An instance of the Ui class.
     * @param transactions An instance of the TransactionList class.
     * @param storage      An instance of the Storage class.
     */
    @Override
    public void execute(TransactionList transactions, Ui ui, Storage storage) throws MoolahException {
        /*
        Checks if userInput is in the correct input format by further parsing,
        before adding entry to arraylist
        */
        String[] splits = input.split(" ");

        // Throws exception if there are more than five tags
        checkExtraTagExist(splits, TAG_LIMIT);
        // Throws exception if there are no mandatory tags
        checkTagsExist(splits);
        // Throws exception if there are no parameters
        checkParameterExist(splits);

        String description = "";
        int amount = 0;
        String category = "";
        LocalDate date = null;
        String type = "";

        for (String split : splits) {
            String tag = split.substring(0, 2);
            String parameter = split.substring(2);
            switch (tag) {
            case "t/":
                type = parameter;
                break;
            case "c/":
                category = parseCategoryTag(parameter);
                break;
            case "a/":
                amount = parseAmountTag(parameter);
                break;
            case "d/":
                date = parseDateTag(parameter);
                break;
            case "i/":
                description = parameter;
                break;
            default:
                throw new InputTransactionInvalidTagException();
            }
        }
        assert date != null;
        addTransactionByType(transactions, type, description, amount, category, date);
    }

    @Override
    public boolean isExit() {
        return false;
    }

    private static void addTransactionByType(TransactionList transactions, String type, String description, int amount,
                                             String category, LocalDate date)
            throws InputTransactionUnknownTypeException {

        switch (type) {
        case "expense":
            String expense = transactions.addExpense(description, amount, category, date);
            Ui.showTransactionAction(INFO_ADD_EXPENSE.toString(), expense);
            break;
        case "income":
            String income = transactions.addIncome(description, amount, category, date);
            Ui.showTransactionAction(INFO_ADD_INCOME.toString(), income);
            break;
        default:
            throw new InputTransactionUnknownTypeException();
        }
    }

    /**
     * Checks if there are missing parameter within the user input.
     * If the split.length() is <= 2, it means that only the tag exists , and there is no parameter after the tag.
     *
     * @param splits The user input after the command word, split into a list for every space found.
     * @throws AddTransactionMissingParameterException Extra tag exception.
     */
    private static void checkParameterExist(String[] splits) throws AddTransactionMissingParameterException {
        for (String split : splits) {
            if (split.length() <= 2) {
                throw new AddTransactionMissingParameterException();
            }
        }
    }

    /**
     * Checks if the targeted tags exists in the split user inputs.
     *
     * @param splits The user input after the command word, split into a list for every space found.
     * @throws AddTransactionMissingTagException Missing tag exception.
     */
    private static void checkTagsExist(String[] splits) throws AddTransactionMissingTagException {
        // TODO: To add the tags into Command class instead
        String[] tags = new String[]{"t/", "c/", "a/", "d/", "i/"};
        for (String tag : tags) {
            boolean found = findMatchingTagFromInputs(tag, splits);
            if (!found) {
                throw new AddTransactionMissingTagException();
            }
        }
    }

    /**
     * Checks if the parameter contains alphabetical characters.
     *
     * @param parameter The user input after the user tag.
     * @return true if there are alphabetical characters within the parameter.
     */
    public static boolean containAlphabet(String parameter) {
        char[] characters = parameter.toCharArray();
        for (char character : characters) {
            if (Character.isAlphabetic(character)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Parses the user parameter input for the amount and returns it.
     *
     * @param parameter The user input after the user tag.
     * @return The amount integer if no exceptions are thrown.
     * @throws AddTransactionInvalidAmountException Invalid amount format exception.
     */
    private static int parseAmountTag(String parameter) throws MoolahException {
        Pattern specialSymbols = Pattern.compile("[!@#$%&*()_+=|<>?{}\\[\\]~-]");
        Matcher hasSpecialSymbols = specialSymbols.matcher(parameter);
        try {
            if (containAlphabet(parameter) || hasSpecialSymbols.find()) {
                throw new AddTransactionInvalidAmountException();
            }
            int amount = Integer.parseInt(parameter);
            if (amount < 0 || amount > 10000000) {
                throw new AddTransactionInvalidAmountException();
            }
            return amount;

        } catch (NumberFormatException e) {
            throw new AddTransactionInvalidAmountException();
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

}
