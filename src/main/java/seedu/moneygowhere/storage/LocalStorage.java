package seedu.moneygowhere.storage;

import seedu.moneygowhere.common.Messages;
import seedu.moneygowhere.data.expense.Expense;
import seedu.moneygowhere.data.expense.ExpenseManager;
import seedu.moneygowhere.exceptions.LocalStorageLoadDataInputError;

import static seedu.moneygowhere.common.Configurations.DIRECTORY_PATH;
import static seedu.moneygowhere.common.Configurations.FILE_PATH_EXPENSES;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Scanner;

public class LocalStorage {
    private static final String DIVIDER = " // ";

    public static File initialiseFile() {
        File directory = new File(DIRECTORY_PATH);
        directory.mkdir();
        String newFilePath = new File(FILE_PATH_EXPENSES).getAbsolutePath();
        return new File(newFilePath);
    }

    /**
     * This method loads all tasks in text file.
     */
    public static void loadFromFile(ExpenseManager expenseManager) {
        Expense loadExpense;
        int fileIndex = 1;
        try {
            File f = initialiseFile();
            Scanner s = new Scanner(f);
            String textFromFile;
            while (s.hasNext()) {
                textFromFile = s.nextLine();
                loadExpense = createExpense(textFromFile);
                expenseManager.addExpense(loadExpense);
                ++fileIndex;
            }
            System.out.println(Messages.LOCAL_STORAGE_LOAD_SUCCESS);
        } catch (FileNotFoundException e) {
            System.out.println(Messages.LOCAL_STORAGE_ERROR_NO_LOAD_FILE);
        } catch (LocalStorageLoadDataInputError | NumberFormatException e) {
            System.out.println(Messages.LOCAL_STORAGE_ERROR_IN_LOAD_FILE + fileIndex);
        }
    }

    private static Expense createExpense(String textFromFile) throws LocalStorageLoadDataInputError {
        String[] splitInputs = textFromFile.split(DIVIDER);
        if (splitInputs.length != 5) {
            throw new LocalStorageLoadDataInputError();
        }
        String name = splitInputs[0];
        LocalDateTime dateTime = LocalDateTime.parse(splitInputs[1]);
        String description = splitInputs[2];
        BigDecimal amount = new BigDecimal(splitInputs[3]);
        String category = splitInputs[4];
        return new Expense(name, dateTime, description, amount, category);
    }

    /**
     * This method saves all current expenses into a text file.
     */
    public static void saveToFile(ArrayList<Expense> savedExpenses) {

        try {
            initialiseFile();
            ArrayList<String> compiledData = taskToStringArray(savedExpenses);
            writeToFile(compiledData);
        } catch (IOException e) {
            System.out.println(Messages.LOCAL_STORAGE_ERROR_SAVE_DATA);
        }
    }

    /**
     * This method converts all current expenses stored in expenses manager into a string
     * containing all information of each expense and store them into a collection.
     *
     * @return list of string containing information of all expenses
     */
    private static ArrayList<String> taskToStringArray(ArrayList<Expense> savedExpenses) {
        ArrayList<String> textData = new ArrayList<>();
        for (Expense expense : savedExpenses) {
            textData.add(
                    expense.getName() + DIVIDER
                    + expense.getDateTime().toString() + DIVIDER
                    + expense.getDescription() + DIVIDER
                    + expense.getAmount() + DIVIDER
                    + expense.getCategory()
            );
        }
        return textData;
    }

    /**
     * This method saves information of all expense into a file in the form of string.
     *
     * @param textToWrite list of string containing information of all expenses.
     * @throws IOException when trying to access a file through a wrong path or when file has issues
     */
    private static void writeToFile(ArrayList<String> textToWrite) throws IOException {
        FileWriter fw = new FileWriter(new File(FILE_PATH_EXPENSES).getAbsolutePath(), false);
        for (String task : textToWrite) {
            fw.write(task + "\n");
        }
        fw.close();
    }
}

