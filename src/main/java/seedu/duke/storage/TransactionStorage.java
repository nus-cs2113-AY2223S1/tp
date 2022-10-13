package seedu.duke.storage;

import seedu.duke.exception.StoreFailureException;
import seedu.duke.exception.TransactionFileNotFoundException;
import seedu.duke.transaction.Transaction;
import seedu.duke.transaction.TransactionList;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

import static seedu.duke.exception.ExceptionMessages.MESSAGE_FILE_NOT_FOUND;
import static seedu.duke.exception.ExceptionMessages.MESSAGE_STORE_INVALID;

public class TransactionStorage extends Storage {
    private static final String SEPARATOR = " \\| ";
    private final String transactionFilePath;

    /**
     * Constructor for Storage of Transactions.
     */
    public TransactionStorage(String transactionFilePath) {
        this.transactionFilePath = transactionFilePath;
    }

    /**
     * Read the transactions from a given file.
     *
     * @return The list of transactions stored in the file.
     * @throws TransactionFileNotFoundException If the file cannot be found.
     */
    public ArrayList<Transaction> loadData() throws TransactionFileNotFoundException {
        try {
            File transactionFile = new File(transactionFilePath);
            ArrayList<Transaction> transactionList = new ArrayList<>();
            Scanner scanner = new Scanner(transactionFile);
            while (scanner.hasNext()) {
                String transactionLine = scanner.nextLine();
                String[] splitTransactionLine = transactionLine.split(SEPARATOR);
                Transaction transaction = handleTransactionLine(splitTransactionLine);
                transactionList.add(transaction);
            }
            return transactionList;
        } catch (FileNotFoundException e) {
            throw new TransactionFileNotFoundException(MESSAGE_FILE_NOT_FOUND);
        }
    }

    /**
     * Writes the current transactions to a file when exiting Duke.
     *
     * @param transactionList The list of transactions to be stored.
     * @throws StoreFailureException If there is an exception occurs.
     */
    public void writeData(TransactionList transactionList) throws StoreFailureException {
        try {
            FileWriter fileWriter = new FileWriter(transactionFilePath);
            String formattedTaskList = transactionList.convertTransactionListToFileFormat();
            fileWriter.write(formattedTaskList);
            fileWriter.close();
        } catch (IOException e) {
            int startIndex = transactionFilePath.lastIndexOf("/");
            String fileDirectory = transactionFilePath.replace(transactionFilePath.substring(startIndex), "");
            File file = new File(fileDirectory);
            if (file.mkdir()) {
                writeData(transactionList);
            } else {
                throw new StoreFailureException(MESSAGE_STORE_INVALID);
            }
        }
    }

    /**
     * Analyses the information the tasks stored in the file.
     *
     * @param splitTransactionLine The raw transaction information.
     * @return A Transaction with full information.
     */
    public Transaction handleTransactionLine(String[] splitTransactionLine) {
        String transactionId = splitTransactionLine[0];
        String itemId = splitTransactionLine[1];
        String borrowerId = splitTransactionLine[2];
        int duration = Integer.parseInt(splitTransactionLine[3]);
        LocalDate createdAt = LocalDate.parse(splitTransactionLine[4]);
        return new Transaction(transactionId, itemId, borrowerId, duration, createdAt);
    }
}
