package seedu.duke;

import seedu.duke.command.Command;
import seedu.duke.common.DateFormats;
import seedu.duke.data.TransactionList;
import seedu.duke.data.transaction.Transaction;
import seedu.duke.exception.MoolahException;
import seedu.duke.exception.StorageInputCorruptedException;
import seedu.duke.exception.StorageWriteErrorException;
import seedu.duke.parser.ParameterParser;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Scanner;

import static seedu.duke.parser.CommandParser.getCommand;

public class Storage {
    //@@author chinhan99
    private static final String DIRECTORY_PATH = "data";
    private static final String FILE_PATH = "data/duke.txt";
    private static final String DELIMITER = " \\| ";
    private static final int NUMBER_OF_STORED_PARAMETERS = 5;
    private static final int TENS = 10;
    private static final int HUNDREDS = 100;
    private static final int THOUSANDS = 1000;

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
     * @throws StorageInputCorruptedException If there are errors due to corrupted duke.txt data.
     * @throws StorageWriteErrorException     If the file could not be created or could not be written.
     */
    public TransactionList initializeFile() throws MoolahException {
        try {
            File file = checkIfFileExist();
            Scanner input = new Scanner(file);
            storeFileValuesLocally(storedTransactions, input);
            Ui.printMessages("* duke.txt loaded successfully! *");

        } catch (MoolahException e) {
            // Catch any parsing errors and throw the default StorageInputCorruptedException
            throw new StorageInputCorruptedException();
        } catch (IOException e) {
            throw new StorageWriteErrorException();
        }
        return storedTransactions;
    }

    /**
     * Stores values from duke.txt to the program by parsing each line in the file.
     *
     * @param storedTransactions The TransactionList to hold the stored values from the file.
     * @param input              The input from duke.txt to be processed.
     * @throws MoolahException WHen there are parsing errors, due to corrupted data.
     */

    private void storeFileValuesLocally(TransactionList storedTransactions, Scanner input) throws MoolahException {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DateFormats.DATE_STORAGE_OUTPUT_PATTERN.toString());

        Command command = null;
        while (input.hasNext()) {
            String line = input.nextLine();
            String[] splits = line.split(DELIMITER);
            if (splits.length != NUMBER_OF_STORED_PARAMETERS) {
                throw new StorageInputCorruptedException();
            }

            String type = splits[0];
            String category = splits[1];
            String amountString = splits[2];
            // Date has been formatted in duke.txt and must be synthesized into the correct string format before parsing
            try {
                LocalDate date = LocalDate.parse(splits[3], formatter);
                String dateString = synthesizeDateString(date);


                String description = splits[4];

                String parametersInput = "t/" + type + " c/" + category + " a/" + amountString + " d/" + dateString
                        + " i/" + description;
                command = getCommand("add", parametersInput);

                ParameterParser.parse(command, parametersInput);

                // amount would be converted into an integer before being used in the addition of transaction locally
                int amount = Integer.parseInt(splits[2]);

                switch (type) {
                case "expense":
                    storedTransactions.addExpenseDuringStorage(description, amount, category, date);
                    break;
                case "income":
                    storedTransactions.addIncomeDuringStorage(description, amount, category, date);
                    break;
                default:
                    throw new StorageInputCorruptedException();
                }
            } catch (DateTimeParseException e) {
                // If the date format is incorrect, which is due to corrupted date information
                throw new StorageInputCorruptedException();
            }

        }

    }


    /**
     * Synthesizes the date into dateString , which would be in the correct format to process.
     *
     * @param date which would be processed into a string.
     * @return dateString, in the correct format to be parsed.
     */

    private String synthesizeDateString(LocalDate date) {

        String dateOfMonth = String.valueOf(date.getDayOfMonth());
        String month = String.valueOf(date.getMonthValue());
        String year = String.valueOf(date.getYear());
        if (date.getMonthValue() < TENS) {
            month = "0" + month;
        }
        if (date.getDayOfMonth() < TENS) {
            dateOfMonth = "0" + dateOfMonth;
        }
        if (date.getYear() < TENS) {
            year = "000" + year;
        } else if (date.getYear() < HUNDREDS) {
            year = "00" + year;
        } else if (date.getYear() < THOUSANDS) {
            year = "0" + year;
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
        String transactionEntry = "";
        for (Transaction transaction : transactions) {

            transactionEntry = transaction.getType() + " | " + transaction.getCategory() + " | "
                    + transaction.getAmount() + " | " + transaction.getDate() + " | "
                    + transaction.getDescription();

            fileWriter.write(transactionEntry + System.lineSeparator());
        }
        fileWriter.close();
    }

}
