package seedu.duke.storage;

import seedu.duke.exception.StoreFailureException;
import seedu.duke.exception.TransactionFileNotFoundException;
import seedu.duke.item.ItemList;
import seedu.duke.transaction.Transaction;
import seedu.duke.transaction.TransactionList;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Scanner;

import static seedu.duke.exception.message.ExceptionMessages.MESSAGE_FILE_NOT_FOUND;
import static seedu.duke.exception.message.ExceptionMessages.MESSAGE_NUM_OF_ARGS_INVALID;
import static seedu.duke.exception.message.ExceptionMessages.MESSAGE_STORAGE_REASON;
import static seedu.duke.exception.message.ExceptionMessages.MESSAGE_STORE_INVALID;
import static seedu.duke.exception.message.ExceptionMessages.MESSAGE_TO_FIX_FILES;
import static seedu.duke.exception.message.ExceptionMessages.MESSAGE_TRANSACTION_STORAGE_ILLEGALLY_MODIFIED;
import static seedu.duke.exception.message.ExceptionMessages.MESSAGE_VALUE_OF_ARGS_INVALID;

// @@author bdthanh
public class TransactionStorage extends Storage {
    private static final String SEPARATOR = "\\|";
    private static final int ITEM_ID_INDEX = 0;
    private static final int BORROWER_INDEX = 1;
    private static final int DURATION_INDEX = 2;
    private static final int DATE_INDEX = 3;
    private static final int LENDER_INDEX = 4;
    private static final int ITEM_NAME_INDEX = 5;
    private static final int MONEY_INDEX = 6;
    private static final int TX_ID_INDEX = 7;
    private static final int NUM_OF_ARGS = 8;

    private final String transactionFilePath;
    private final ItemList itemList;
    private final TransactionList transactionList;

    /**
     * Constructor for Storage of Transactions.
     */
    public TransactionStorage(String transactionFilePath, ItemList itemList) {
        this.transactionFilePath = transactionFilePath;
        this.itemList = itemList;
        this.transactionList = new TransactionList();
    }

    /**
     * Read the transactions from a given file.
     *
     * @return The list of transactions stored in the file.
     * @throws TransactionFileNotFoundException If the file cannot be found
     * @throws StoreFailureException            If there is a failure loading.
     */
    public TransactionList loadData()
            throws TransactionFileNotFoundException, StoreFailureException {
        int lineNo = 0;
        try {
            File transactionFile = new File(transactionFilePath);
            Scanner scanner = new Scanner(transactionFile);
            while (scanner.hasNext()) {
                lineNo++;
                String transactionLine = scanner.nextLine();
                String[] splitTransactionLine = transactionLine.split(SEPARATOR);
                trimArrayValues(splitTransactionLine);
                Transaction transaction = handleTransactionLine(splitTransactionLine);
                transactionList.addTransaction(transaction);
            }
            return transactionList;
        } catch (FileNotFoundException e) {
            throw new TransactionFileNotFoundException(MESSAGE_FILE_NOT_FOUND);
        } catch (Exception e) {
            throw new StoreFailureException(
                    MESSAGE_TRANSACTION_STORAGE_ILLEGALLY_MODIFIED + lineNo + MESSAGE_STORAGE_REASON
                            + e.getMessage() + "\n" + MESSAGE_TO_FIX_FILES);
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
     * Analyses the information the transactions stored in the file
     * and checks if valid or not.
     *
     * @param splitTransactionLine The raw transaction information.
     * @return A Transaction with full information.
     */
    public Transaction handleTransactionLine(String[] splitTransactionLine) throws Exception {
        checkIfArgsEmpty(splitTransactionLine, NUM_OF_ARGS,
                MESSAGE_NUM_OF_ARGS_INVALID, MESSAGE_VALUE_OF_ARGS_INVALID);
        transactionList.checkValidArgsForStorage(splitTransactionLine);
        itemList.checkNameOwnerPriceOfItemMatching(splitTransactionLine[ITEM_ID_INDEX],
                splitTransactionLine[ITEM_NAME_INDEX], splitTransactionLine[LENDER_INDEX]);
        Transaction transaction = getTransactionFromTransactionLine(splitTransactionLine);
        transactionList.checkOldTransactionsOverlapWithNew(transaction);
        return transaction;
    }

    private static Transaction getTransactionFromTransactionLine(String[] splitTransactionLine) {
        String transactionId = splitTransactionLine[TX_ID_INDEX];
        String itemName = splitTransactionLine[ITEM_NAME_INDEX];
        String itemId = splitTransactionLine[ITEM_ID_INDEX];
        String lenderId = splitTransactionLine[LENDER_INDEX];
        String borrowerId = splitTransactionLine[BORROWER_INDEX];
        int duration = Integer.parseInt(splitTransactionLine[DURATION_INDEX]);
        LocalDate createdAt = LocalDate.parse(splitTransactionLine[DATE_INDEX]);
        double moneyTransacted = Double.parseDouble(splitTransactionLine[MONEY_INDEX]);
        return new Transaction(transactionId, itemName, itemId, borrowerId,
                lenderId, duration, createdAt, moneyTransacted);
    }
}
