package seedu.duke.storage;

import seedu.duke.exception.StoreFailureException;
import seedu.duke.exception.TransactionFileNotFoundException;
import seedu.duke.transaction.Transaction;
import seedu.duke.transaction.TransactionList;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

import static seedu.duke.exception.message.ExceptionMessages.MESSAGE_FILE_NOT_FOUND;
import static seedu.duke.exception.message.ExceptionMessages.MESSAGE_STORE_INVALID;
import static seedu.duke.exception.message.ExceptionMessages.MESSAGE_TRANSACTION_STORAGE_ILLEGALLY_MODIFIED;

//@@author bdthanh
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
    public ArrayList<Transaction> loadData() throws TransactionFileNotFoundException, StoreFailureException {
        try {
            File transactionFile = new File(transactionFilePath);
            ArrayList<Transaction> transactionList = new ArrayList<>();
            Scanner scanner = new Scanner(transactionFile);
            int checkSum = Integer.parseInt(scanner.nextLine());
            while (scanner.hasNext()) {
                String transactionLine = scanner.nextLine();
                String[] splitTransactionLine = transactionLine.split(SEPARATOR);
                Transaction transaction = handleTransactionLine(splitTransactionLine);
                transactionList.add(transaction);
            }
            checkCheckSumWhole(transactionList, checkSum);
            return transactionList;
        } catch (FileNotFoundException e) {
            throw new TransactionFileNotFoundException(MESSAGE_FILE_NOT_FOUND);
        } catch (Exception e) {
            throw new StoreFailureException(MESSAGE_TRANSACTION_STORAGE_ILLEGALLY_MODIFIED);
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
            String formattedTransactionList = transactionList.convertTransactionListToFileFormat();
            fileWriter.write(formattedTransactionList);
            fileWriter.close();
        } catch (IOException e) {
            makeTransactionDir(transactionList);
        }
    }

    private void makeTransactionDir(TransactionList transactionList) throws StoreFailureException {
        int startIndex = transactionFilePath.lastIndexOf("/");
        String fileDirectory =
                transactionFilePath.replace(transactionFilePath.substring(startIndex), "");
        File file = new File(fileDirectory);
        if (file.mkdir()) {
            writeData(transactionList);
        } else {
            throw new StoreFailureException(MESSAGE_STORE_INVALID);
        }
    }

    /**
     * Analyses the information the transactions stored in the file.
     *
     * @param splitTransactionLine The raw transaction information.
     * @return A Transaction with full information.
     */
    public static Transaction handleTransactionLine(String[] splitTransactionLine) throws StoreFailureException {
        assert splitTransactionLine.length == 8 : "Invalid Transaction Line";
        Transaction transaction = getTransactionFromTransactionLine(splitTransactionLine);
        checkCheckSumLine(transaction, Integer.parseInt(splitTransactionLine[7]));
        return transaction;
    }

    private static Transaction getTransactionFromTransactionLine(String[] splitTransactionLine) {
        String transactionId = splitTransactionLine[0];
        String itemName = splitTransactionLine[1];
        String itemId = splitTransactionLine[2];
        String borrowerId = splitTransactionLine[3];
        int duration = Integer.parseInt(splitTransactionLine[4]);
        LocalDate createdAt = LocalDate.parse(splitTransactionLine[5]);
        double money = Double.parseDouble(splitTransactionLine[6]);
        BigDecimal moneyTransacted = BigDecimal.valueOf(money);
        return new Transaction(transactionId, itemName, itemId, borrowerId, duration, createdAt, moneyTransacted);
    }

    private static void checkCheckSumLine(Transaction transaction, int checkSum) throws StoreFailureException {
        if (transaction.toString().length() != checkSum) {
            throw new StoreFailureException(MESSAGE_TRANSACTION_STORAGE_ILLEGALLY_MODIFIED);
        }
    }

    private static void checkCheckSumWhole(ArrayList<Transaction> transactionList, int checkSum)
            throws StoreFailureException {
        if (transactionList.size() != checkSum / 3) {
            throw new StoreFailureException(MESSAGE_TRANSACTION_STORAGE_ILLEGALLY_MODIFIED);
        }
    }
}
