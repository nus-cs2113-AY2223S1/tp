package seedu.duke;

import seedu.duke.common.ErrorMessages;
import seedu.duke.common.InfoMessages;
import seedu.duke.data.TransactionList;
import seedu.duke.data.transaction.Transaction;
import seedu.duke.exception.AddTransactionInvalidCategoryException;
import seedu.duke.exception.AddTransactionMissingTagException;
import seedu.duke.exception.AddTransactionUnknownTypeException;
import seedu.duke.exception.MoolahException;

import java.util.ArrayList;
import java.util.Scanner;

public class Parser {
    static final boolean IS_EXIT = false;
    static final boolean IS_CONTINUE = true;

    /**
     * Parses the user input and deal with any input error returned.
     *
     * @param inData       The user input.
     * @param transactions The array which would be operated on.
     * @return IS_EXIT If input equals "bye", else return IS_CONTINUE.
     */
    public static boolean parse(String inData, TransactionList transactions) {
        boolean isContinue = IS_CONTINUE;
        try {
            isContinue = processInput(inData, transactions);
        } catch (MoolahException exception) {
            Ui.showErrorMessage(exception.getMessage());
        }
        return isContinue;
    }

    /**
     * Parses the user input and processes it with the transactions arraylist.
     *
     * @param inData       The user input.
     * @param transactions The array which would be operated on.
     * @return IS_EXIT If input equals "bye", else return IS_CONTINUE.
     * @throws MoolahException Any command input exceptions captured by Moolah Manager.
     */
    private static boolean processInput(String inData, TransactionList transactions) throws MoolahException {
        if (inData.equals("help")) {
            Ui.showHelpList();
        } else if (inData.equals("list")) {
            // Prints all transactions if input is equal to "list"
            String transactionsList = transactions.listTransactions();
            if (transactionsList.isEmpty()) {
                Ui.showInfoMessage(InfoMessages.MESSAGE_INFO_LIST_EMPTY.toString());
                return IS_CONTINUE;
            }
            Ui.showTransactionsList(transactionsList, InfoMessages.MESSAGE_INFO_LIST.toString());
        } else if (inData.equals("purge")) {
            // Shows confirmation prompt before deleting all transactions
            Ui.showInfoMessage(InfoMessages.MESSAGE_INFO_WARNING.toString());
            Scanner confirmation = new Scanner(System.in);
            String input = confirmation.nextLine();
            if (input.equals("Yes")) {
                TransactionList.purgeEntries(transactions);
            } else {
                System.out.println("MOOOOOO.... Aborting Command, returning to Home.");
            }
        } else if (inData.equals(("bye"))) {
            Ui.showExitMessage();
            // Exits loop
            return IS_EXIT;
        } else if (inData.isBlank() || inData.isEmpty()) {
            Ui.showInvalidCommand();
        } else if (inData.contains(" ")) {
            // Further parses the user input for user transaction commands
            String[] userInput = inData.split(" ", 2);
            String command = userInput[0];
            switch (command) {
            case "delete":
                /*
                Checks if userInput is in the correct format by further parsing(e.g. such as correct entry numbers)
                before deleting the entry
                */
                int index = Integer.parseInt(userInput[1]);
                TransactionList.deleteEntry(transactions, index);
                break;
            case "add":
                /*
                Checks if userInput is in the correct input format by further parsing,
                before adding entry to arraylist
                */
                parseAddCommand(userInput[1], transactions);
                break;
            case "edit":
                /*
                Checks if userInput is in the correct input format by further parsing,
                before the entry in the arraylist
                */
                break;
            default:
                // For invalid commands
                Ui.showInvalidCommand();
            }
        } else {
            // For any single-word inData , which are not valid commands
            Ui.showInvalidCommand();
        }
        return IS_CONTINUE;
    }

    /**
     * Parses the add transaction command by checking if the compulsory tags exist followed by adding the transaction.
     * Then executes the command to add the transaction into the list.
     *
     * @param userInput The user input after the "add" command word.
     * @throws AddTransactionMissingTagException Exceptions related to "add" command.
     */
    private static void parseAddCommand(String userInput, TransactionList transactions) throws MoolahException {
        String[] splits = userInput.split(" ");
        checkTagsExist(splits);
        // TODO: To check that each parameter is in correct format, e.g. amount should be valid integer/double
        // TODO: To move the add transaction logic below to Command class in Command.execute()
        String description = "";
        int amount = 0;
        String category = "";
        String date = "";
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
                    Ui.printMessages(String.valueOf(ErrorMessages.MESSAGE_ERROR_ADD_COMMAND_INVALID_CATEGORY));
                    inputIsValid = false;
                }

                break;
            case "a/":
                try {
                    amount = Integer.parseInt(parameter);
                } catch (NumberFormatException e) {
                    Ui.showNonNumericError();
                    inputIsValid = false;
                }
                break;
            case "d/":
                date = parameter;
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
                transactions.addExpense(description, amount, category, date);
                break;
            case "income":
                transactions.addIncome(description, amount, category, date);
                break;
            default:
                throw new AddTransactionUnknownTypeException();
            }
        }
    }

    /**
     * Processes the parameter. If it is an invalid parameter an exception error would be thrown.
     *
     * @param parameter The user input after the user tag.
     * @throws AddTransactionInvalidCategoryException Invalid category parameter exception.
     */
    private static void parseCategoryTag(String parameter) throws AddTransactionInvalidCategoryException {
        if (isNumeric(parameter)) {
            throw new AddTransactionInvalidCategoryException();
        }

    }

    /**
     * Check if the parameter contains numeric characters.
     *
     * @param parameter The user input after the user tag.
     * @return true if there are numeric characters within the parameter.
     */
    public static boolean isNumeric(String parameter) {
        char[] characters = parameter.toCharArray();
        for (char character : characters) {
            if (Character.isDigit(character)) {
                return true;
            }
        }
        return false;
    }


    /**
     * Check if the targeted tags exists in the split user inputs.
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
