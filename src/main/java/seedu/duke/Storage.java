package seedu.duke;

import seedu.duke.command.Command;
import seedu.duke.command.CommandTag;
import seedu.duke.common.DateFormats;
import seedu.duke.data.Budget;
import seedu.duke.data.TransactionList;
import seedu.duke.data.transaction.Transaction;
import seedu.duke.exception.MoolahException;
import seedu.duke.exception.InputBudgetDuplicateException;
import seedu.duke.exception.InputBudgetInvalidAmountException;
import seedu.duke.exception.StorageFileCorruptedTransactionException;
import seedu.duke.exception.StorageFileCorruptedBudgetException;
import seedu.duke.exception.StorageWriteErrorException;
import seedu.duke.parser.ParameterParser;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.ResolverStyle;
import java.util.ArrayList;
import java.util.Scanner;

import static seedu.duke.parser.CommandParser.getCommand;

public class Storage {
    //@@author chinhan99
    private static final String DIRECTORY_PATH = "data";
    private static final String FILE_PATH = "data/duke.txt";
    private static final String DELIMITER = " \\| ";
    private static final int NUMBER_OF_STORED_PARAMETERS = 5;
    private static final int YEAR_TENTH = 10;
    private static final int YEAR_HUNDREDTH = 100;
    private static final int YEAR_THOUSANDTH = 1000;
    private static final int MONTH_TENTH = 10;
    private static final int DAY_TENTH = 10;
    private static final String APPENDED_ZERO_TO_MONTH_BELOW_TEN = "0";
    private static final String APPENDED_ZERO_TO_DAY_BELOW_TEN = "0";
    private static final String APPENDED_ZERO_TO_YEAR_BELOW_THOUSAND = "0";
    private static final String APPENDED_ZEROES_TO_YEAR_BELOW_HUNDRED = "00";
    private static final String APPENDED_ZEROES_TO_YEAR_BELOW_TEN = "000";
    private static final String TYPE_TAG = CommandTag.COMMAND_TAG_TRANSACTION_TYPE;

    // A spacing has to be added in front of the tags so that the string can be properly parsed
    private static final String CATEGORY_TAG_WITH_SPACE = " " + CommandTag.COMMAND_TAG_TRANSACTION_CATEGORY;
    private static final String AMOUNT_TAG_WITH_SPACE = " " + CommandTag.COMMAND_TAG_TRANSACTION_AMOUNT;
    private static final String DATE_TAG_WITH_SPACE = " " + CommandTag.COMMAND_TAG_TRANSACTION_DATE;
    private static final String DESCRIPTION_TAG_WITH_SPACE = " " + CommandTag.COMMAND_TAG_TRANSACTION_DESCRIPTION;
    private static final int TYPE_PARAMETER = 0;
    private static final int CATEGORY_PARAMETER = 1;
    private static final int AMOUNT_PARAMETER = 2;
    private static final int DATE_PARAMETER = 3;
    private static final int DESCRIPTION_PARAMETER = 4;


    private TransactionList storedTransactions;

    public Storage() {
        this.storedTransactions = new TransactionList();
    }


    /**
     * Checks if duke.txt and the directory exists. If any of these does not exist, it would be created.
     *
     * @return The duke.txt file which existed / is created.
     * @throws IOException When there are issues creating the file.
     */

    private static File checkIfFileExist() throws IOException {
        File directory = new File(DIRECTORY_PATH);
        File file = new File(FILE_PATH);


        if (!directory.exists()) {
            directory.mkdir();
            Ui.printMessages("* Created new directory *");
        }
        if (!file.exists()) {
            file.createNewFile();
            Ui.printMessages("* Created new file for use *");
        } else {
            Ui.printMessages("* Existing file detected *");

        }
        return file;
    }

    /**
     * Initializes the duke.txt by checking its existence, then store the data values to the program.
     *
     * @return The TransactionList storing entries which would be used in the program.
     * @throws StorageFileCorruptedTransactionException If there are errors due to corrupted duke.txt transaction data.
     * @throws StorageWriteErrorException               If the file could not be created or could not be written.
     * @throws StorageFileCorruptedBudgetException      If the budget value in duke,txt has been corrupted.
     */
    public TransactionList initializeFile() throws MoolahException {
        try {
            File file = checkIfFileExist();
            Scanner input = new Scanner(file);
            storeFileValuesLocally(input);
            Ui.printMessages("* duke.txt loaded successfully! *");

        } catch (StorageFileCorruptedBudgetException e) {
            // Catch any Budget parsing errors and throw the default StorageInputCorruptedBudgetException
            throw new StorageFileCorruptedBudgetException();
        } catch (StorageFileCorruptedTransactionException e) {
            // Catch any Transaction parsing errors and throw the default StorageInputCorruptedTransactionException
            throw new StorageFileCorruptedTransactionException();
        } catch (IOException e) {
            throw new StorageWriteErrorException();
        }
        return storedTransactions;
    }

    /**
     * Stores budget value from duke.txt to the program by parsing the first line of the file.
     *
     * @param monthlyBudget The budget value to be parsed.
     * @throws StorageFileCorruptedBudgetException If the budget value in duke,txt has been corrupted.
     */
    private void storeBudgetLocally(String monthlyBudget) throws MoolahException {
        try {
            Command command = null;
            ParameterParser.parseBudgetTag(monthlyBudget);
            Budget.setBudget(Long.parseLong(monthlyBudget));


        } catch (InputBudgetDuplicateException e) {
            Ui.printMessages("* Budget value remains as default *");
        } catch (InputBudgetInvalidAmountException e) {
            throw new StorageFileCorruptedBudgetException();
        }
    }

    /**
     * Stores Transaction parameters from duke.txt to the program by parsing each line of the file after Budget value.
     *
     * @param lineString The individual lines from duke.txt which would be parsed.
     * @throws StorageFileCorruptedTransactionException If there are errors due to corrupted duke.txt transaction data.
     */
    private void storeTransactionsLocally(String lineString) throws MoolahException {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DateFormats.DATE_STORAGE_OUTPUT_PATTERN.toString());
        String[] splits = lineString.split(DELIMITER);
        Command command = null;

        if (splits.length != NUMBER_OF_STORED_PARAMETERS) {
            throw new StorageFileCorruptedTransactionException();
        }

        // Stores the parameters based on the sequence of parameters in the storage file duke.txt
        String type = splits[TYPE_PARAMETER];
        String category = splits[CATEGORY_PARAMETER];
        String amountString = splits[AMOUNT_PARAMETER];
        // Date has been formatted in duke.txt and must be synthesized into the correct string format before parsing
        try {
            LocalDate date = LocalDate.parse(splits[DATE_PARAMETER], formatter.withResolverStyle(ResolverStyle.STRICT));
            String dateString = synthesizeDateString(date);


            String description = splits[DESCRIPTION_PARAMETER];

            String parametersInput = TYPE_TAG + type + CATEGORY_TAG_WITH_SPACE + category
                    + AMOUNT_TAG_WITH_SPACE + amountString + DATE_TAG_WITH_SPACE + dateString
                    + DESCRIPTION_TAG_WITH_SPACE + description;

            command = getCommand("add", parametersInput);

            ParameterParser.parse(command, parametersInput);

            // amount would be converted into an integer before being used in the addition of transaction locally
            int amount = Integer.parseInt(splits[AMOUNT_PARAMETER]);

            switch (type) {
            case "expense":
                storedTransactions.addExpenseDuringStorage(description, amount, category, date);
                break;
            case "income":
                storedTransactions.addIncomeDuringStorage(description, amount, category, date);
                break;
            default:
                throw new StorageFileCorruptedTransactionException();
            }
        } catch (DateTimeParseException e) {
            // If the date format is incorrect, which is due to corrupted date information
            throw new StorageFileCorruptedTransactionException();
        } catch (MoolahException e) {
            // If any other exceptions are caught from the parser, throw corrupted transaction exception
            throw new StorageFileCorruptedTransactionException();
        }
    }


    /**
     * Stores values from duke.txt to the program by parsing each line in the file.
     *
     * @param input The input from duke.txt to be processed.
     * @throws MoolahException When there are parsing errors, due to corrupted data.
     */


    private void storeFileValuesLocally(Scanner input) throws MoolahException {


        // Processes the budget value located at the first line of the duke.txt file
        if (input.hasNext()) {
            String monthlyBudget = input.nextLine();
            storeBudgetLocally(monthlyBudget);
        } else {
            // Else, the file would be empty
            Ui.printMessages("* Duke.txt file is currently empty and ready to be written *");
        }

        while (input.hasNext()) {
            String line = input.nextLine();
            storeTransactionsLocally(line);

        }

    }


    /**
     * Synthesizes the date variables into dateString, which would be in the correct format for parsing.
     * When extracting date variables like month from date, zeros in front of the variable would be removed by default.
     * E.g. A month was stored as 9. A zero has to be added, so month would be equal to 09, and correct for parsing.
     *
     * @param date which would be processed into a string.
     * @return dateString, in the correct format to be parsed.
     */

    private String synthesizeDateString(LocalDate date) {

        String dateOfMonth = String.valueOf(date.getDayOfMonth());
        String month = String.valueOf(date.getMonthValue());
        String year = String.valueOf(date.getYear());
        if (date.getMonthValue() < MONTH_TENTH) {
            // If month value is less than 10, a zero has to be added to the front of the variable for parsing reasons
            month = APPENDED_ZERO_TO_MONTH_BELOW_TEN + month;
        }
        if (date.getDayOfMonth() < DAY_TENTH) {
            dateOfMonth = APPENDED_ZERO_TO_DAY_BELOW_TEN + dateOfMonth;
        }
        if (date.getYear() < YEAR_TENTH) {
            year = APPENDED_ZEROES_TO_YEAR_BELOW_TEN + year;
        } else if (date.getYear() < YEAR_HUNDREDTH) {
            year = APPENDED_ZEROES_TO_YEAR_BELOW_HUNDRED + year;
        } else if (date.getYear() < YEAR_THOUSANDTH) {
            year = APPENDED_ZERO_TO_YEAR_BELOW_THOUSAND + year;
        }

        String dateString = dateOfMonth + month + year;
        return dateString;
    }

    /**
     * The function called each time there are changes to the transactions Arraylist.
     *
     * @param transactions The updated Arraylist.
     * @throws IOException If there are issues writing to the duke.txt file.
     */
    public void writeToFile(ArrayList<Transaction> transactions) throws IOException {
        FileWriter fileWriter = new FileWriter(FILE_PATH);
        long monthlyBudget = Budget.getBudget();
        String transactionEntry = "";
        fileWriter.write(monthlyBudget + System.lineSeparator());
        for (Transaction transaction : transactions) {

            // Delimiter constant cannot be used here as it is synthesized into a string
            transactionEntry = transaction.getType() + " | " + transaction.getCategory() + " | "
                    + transaction.getAmount() + " | " + transaction.getDate() + " | "
                    + transaction.getDescription();

            fileWriter.write(transactionEntry + System.lineSeparator());
        }
        fileWriter.close();
    }

}
