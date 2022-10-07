package seedu.duke.command;

import seedu.duke.Storage;
import seedu.duke.Ui;

import seedu.duke.data.TransactionList;
import seedu.duke.exception.AddTransactionInvalidCategoryException;
import seedu.duke.exception.AddTransactionInvalidDateException;
import seedu.duke.exception.AddTransactionMissingTagException;
import seedu.duke.exception.AddTransactionUnknownTypeException;
import seedu.duke.exception.MoolahException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import static seedu.duke.common.DateFormats.DATE_INPUT_PATTERN;
import static seedu.duke.common.ErrorMessages.ERROR_ADD_COMMAND_AMOUNT_NOT_NUMERIC;
import static seedu.duke.common.InfoMessages.INFO_ADD_EXPENSE;
import static seedu.duke.common.InfoMessages.INFO_ADD_INCOME;

/**
 * Represents an add command object that will execute the operations for Add command.
 */
public class AddCommand extends Command {

    private String input;

    public AddCommand(String input) {
        this.input = input;
    }

    /**
     * Executes the operations related to the command.
     *
     * @param ui An instance of the Ui class.
     * @param transactions An instance of the TransactionList class.
     * @param storage An instance of the Storage class.
     */
    @Override
    public void execute(TransactionList transactions, Ui ui, Storage storage) throws MoolahException {
        /*
        Checks if userInput is in the correct input format by further parsing,
        before adding entry to arraylist
        */
        /**
         * Parses the add transaction command by checking if the compulsory tags exist followed by
         * adding the transaction.
         * Then executes the command to add the transaction into the list.
         *
         * @param userInput The user input after the "add" command word.
         * @throws AddTransactionMissingTagException Exceptions related to "add" command.
         */
        String[] splits = input.split(" ");
        checkTagsExist(splits);
        // TODO: To check that each parameter is in correct format, e.g. amount should be valid integer/double
        // TODO: To move the add transaction logic below to Command class in Command.execute()
        String description = "";
        int amount = 0;
        String category = "";
        LocalDate date = null;
        String type = "";
        boolean inputIsValid = true;

        for (String split : splits) {
            String tag = split.substring(0, 2);
            String parameter = split.substring(2);
            switch (tag) {
            case "t/":
                type = parameter;
                break;
            case "c/":
                try {
                    parseCategoryTag(parameter);
                    category = parameter;
                } catch (AddTransactionInvalidCategoryException e) {
                    Ui.printMessages(e.getMessage());
                    inputIsValid = false;
                }
                break;
            case "a/":
                try {
                    amount = Integer.parseInt(parameter);
                } catch (NumberFormatException e) {
                    Ui.showErrorMessage(ERROR_ADD_COMMAND_AMOUNT_NOT_NUMERIC.toString());
                    inputIsValid = false;
                }
                break;
            case "d/":
                date = parseDateTag(parameter);
                break;
            case "i/":
                description = parameter;
                break;
            default:
                break;
            }
        }
        if (inputIsValid) {
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
                throw new AddTransactionUnknownTypeException();
            }
        }
    }


    @Override
    public boolean isExit() {
        return false;
    }


    // the other add functions

    /**
     * Processes the parameter. If it is an invalid parameter an exception error would be thrown.
     *
     * @param parameter The user input after the user tag.
     * @throws AddTransactionInvalidCategoryException Invalid category parameter exception.
     */
    private static void parseCategoryTag(String parameter) throws AddTransactionInvalidCategoryException {
        if (containNumeric(parameter)) {
            throw new AddTransactionInvalidCategoryException();
        }

    }

    /**
     * Checks if the parameter contains numeric characters.
     *
     * @param parameter The user input after the user tag.
     * @return true if there are numeric characters within the parameter.
     */
    public static boolean containNumeric(String parameter) {
        char[] characters = parameter.toCharArray();
        for (char character : characters) {
            if (Character.isDigit(character)) {
                return true;
            }
        }
        return false;
    }


    /**
     * Parse the user parameter input for date into a LocalDate object and returns it.
     *
     * @param parameter The user input after the user tag.
     * @return The LocalDate object parsed from user input given.
     * @throws AddTransactionInvalidDateException Invalid date format exception.
     */
    private static LocalDate parseDateTag(String parameter) throws AddTransactionInvalidDateException {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATE_INPUT_PATTERN.toString());
            LocalDate date = LocalDate.parse(parameter, formatter);
            return date;
        } catch (DateTimeParseException exception) {
            throw new AddTransactionInvalidDateException();
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
