package seedu.duke.transaction;

import seedu.duke.exception.DateFormatInvalidException;
import seedu.duke.exception.DuplicateException;
import seedu.duke.exception.DurationInvalidException;
import seedu.duke.exception.InvalidTransactionException;
import seedu.duke.exception.ItemNotFoundException;
import seedu.duke.exception.TransactionNotFoundException;
import seedu.duke.exception.UserNotFoundException;
import seedu.duke.item.ItemList;
import seedu.duke.user.UserList;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static seedu.duke.exception.message.ExceptionMessages.MESSAGE_CREATED_DATE_RANGE_INVALID;
import static seedu.duke.exception.message.ExceptionMessages.MESSAGE_DATE_FORMAT_INVALID;
import static seedu.duke.exception.message.ExceptionMessages.MESSAGE_DUPLICATE_TRANSACTION_ID;
import static seedu.duke.exception.message.ExceptionMessages.MESSAGE_DURATION_INVALID;
import static seedu.duke.exception.message.ExceptionMessages.MESSAGE_ITEM_TRANSACTION_OVERLAP;
import static seedu.duke.exception.message.ExceptionMessages.MESSAGE_ITEM_UPDATE_TRANSACTION_OVERLAP;
import static seedu.duke.exception.message.ExceptionMessages.MESSAGE_NUMBER_FORMAT_INVALID;
import static seedu.duke.exception.message.ExceptionMessages.MESSAGE_TX_NOT_FOUND;
import static seedu.duke.exception.message.ExceptionMessages.MESSAGE_UNFINISHED_ITEM_NOT_FOUND;
import static seedu.duke.exception.message.ExceptionMessages.MESSAGE_UNFINISHED_USER_NOT_FOUND;

// @@author bdthanh
public class TransactionList {
    private final ArrayList<Transaction> transactionList;
    private static final int DURATION_INDEX = 2;
    private static final int CREATED_DATE_INDEX = 3;
    private static final int TX_ID_INDEX = 7;

    /**
     * Constructor for TransactionList.
     */
    public TransactionList() {
        this.transactionList = new ArrayList<>();
    }

    /**
     * Constructor for TransactionList.
     *
     * @param transactionList The list of transactions from the stored file
     */
    public TransactionList(ArrayList<Transaction> transactionList) {
        this.transactionList = transactionList;
    }

    /**
     * Gets the size of transaction list.
     *
     * @return The size of transaction list
     */
    public int getSize() {
        return transactionList.size();
    }

    /**
     * Gets the ArrayList of transaction.
     *
     * @return The ArrayList of transaction
     */
    public ArrayList<Transaction> getTransactionList() {
        return this.transactionList;
    }

    /**
     * Adds a new transaction to the list.
     *
     * @param transaction The transaction to be added
     */
    public void addTransaction(Transaction transaction) {
        transactionList.add(transaction);
    }

    /**
     * Updates a transaction duration in the list given its ID.
     *
     * @param transactionId The id of the transaction to be deleted
     * @param duration The new duration
     * @throws TransactionNotFoundException If the transaction cannot be found in the list
     * @throws InvalidTransactionException If there is transaction overlapped
     */

    // @@author jorellesee
    public Transaction updateTransaction(String transactionId, int duration, double moneyTransacted)
            throws TransactionNotFoundException, InvalidTransactionException {
        for (int i = 0; i < this.transactionList.size(); ++i) {
            Transaction tx = this.transactionList.get(i);
            if (tx.getTxId().equals(transactionId)) {
                Transaction updatedTx = tx.update(duration, moneyTransacted);
                checkOldTransactionsOverlapWithUpdated(updatedTx);
                this.transactionList.set(i, updatedTx);
                return updatedTx;
            }
        }
        throw new TransactionNotFoundException(MESSAGE_TX_NOT_FOUND);
    }

    // @@author bdthanh

    /**
     * Deletes a transaction in the list given its ID.
     *
     * @param transactionId The id of the transaction to be deleted
     * @throws TransactionNotFoundException If the transaction cannot be found in the list
     */
    public void deleteTransaction(String transactionId) throws TransactionNotFoundException {
        Transaction transaction = getTransactionById(transactionId);
        transactionList.remove(transaction);
    }

    /**
     * Gets a specific transaction given its ID.
     *
     * @param transactionId The id of the transaction to be found
     * @return The transactions with that id
     * @throws TransactionNotFoundException If the transaction cannot be found in the list
     */
    public Transaction getTransactionById(String transactionId)
            throws TransactionNotFoundException {
        for (Transaction transaction : transactionList) {
            if (transaction.getTxId().equals(transactionId)) {
                return transaction;
            }
        }
        throw new TransactionNotFoundException(MESSAGE_TX_NOT_FOUND);
    }

    // @@author jorellesee
    /**
     * Gets the transactions of a user who is borrowing.
     * 
     * @param userName user to check with
     * @return A list of all matched transactions
     */
    public TransactionList getBorrowTransactionsByUser(String userName) {
        TransactionList returnList = new TransactionList();
        for (Transaction transaction : this.transactionList) {
            if (transaction.getBorrower().equals(userName)) {
                returnList.addTransaction(transaction);
            }
        }
        return returnList;
    }

    /**
     * Gets the transactions of a user who is lending.
     * 
     * @param userName user to check with
     * @return A list of all matched transactions
     */
    public TransactionList getLendTransactionsByUser(String userName) {
        TransactionList returnList = new TransactionList();
        for (Transaction transaction : this.transactionList) {
            if (transaction.getLender().equals(userName)) {
                returnList.addTransaction(transaction);
            }
        }
        return returnList;
    }

    /**
     * Computes total transacted sum.
     * 
     * @return Total sum
     */
    public double getTotalMoneyTransacted() {
        double totalProfit = 0;
        for (Transaction transaction : transactionList) {
            totalProfit += transaction.getMoneyTransacted();
        }
        return totalProfit;
    }

    // @@author bdthanh

    /**
     * Checks if there is a specific borrower given his/her username among unfinished transactions.
     *
     * @param username The username of borrower to be checked
     * @return true If the list contains that person
     */
    public boolean hasThisBorrower(String username) {
        int count = (int) transactionList.stream().filter(t -> !t.isFinished())
                .filter(t -> t.getBorrower().equals(username)).count();
        return count > 0;
    }

    /**
     * Checks if there is a transaction in the list overlap with the new transaction of a same item.
     *
     * @param transactionToCheck The new transaction to check
     * @throws InvalidTransactionException If overlap
     */
    public void checkOldTransactionsOverlapWithNew(Transaction transactionToCheck)
            throws InvalidTransactionException {
        List<Transaction> transactions = transactionList.stream()
                .filter(t -> t.getItemId().equals(transactionToCheck.getItemId()))
                .filter(t -> t.checkOverlapToAddTx(transactionToCheck))
                .collect(Collectors.toList());
        int count = transactions.size();
        if (count > 0) {
            throw new InvalidTransactionException(
                    MESSAGE_ITEM_TRANSACTION_OVERLAP + transactions.get(0).getTxId() + ")");
        }
    }

    public void checkLenderAndBorrowerUnfinishedTx(Transaction transactionToCheck, UserList userList, ItemList itemList)
            throws InvalidTransactionException {
        if (!transactionToCheck.isFinished()) {
            try {
                userList.getUserById(transactionToCheck.getBorrower());
                userList.getUserById(transactionToCheck.getLender());
                itemList.getItemById(transactionToCheck.getItemId());
            } catch (UserNotFoundException e) {
                throw new InvalidTransactionException(MESSAGE_UNFINISHED_USER_NOT_FOUND);
            } catch (ItemNotFoundException e) {
                throw new InvalidTransactionException(MESSAGE_UNFINISHED_ITEM_NOT_FOUND);
            }
        }
    }

    /**
     * Checks if there is a transaction in the list overlap with the update transaction.
     *
     * @param transactionToCheck The update transaction to check
     * @throws InvalidTransactionException If overlap
     */
    public void checkOldTransactionsOverlapWithUpdated(Transaction transactionToCheck)
            throws InvalidTransactionException {
        List<Transaction> transactions = transactionList.stream()
                .filter(t -> t.getItemId().equals(transactionToCheck.getItemId()))
                .filter(t -> t.checkOverlapToUpdateTx(transactionToCheck))
                .collect(Collectors.toList());
        int count = transactions.size();
        if (count > 0) {
            throw new InvalidTransactionException(
                    MESSAGE_ITEM_UPDATE_TRANSACTION_OVERLAP + transactions.get(0).getTxId() + ")");
        }
    }

    /**
     * Checks if there is a specific item given its id among unfinished transactions.
     *
     * @param itemId The id of item to be checked
     * @return true If the list contains that item
     */
    public boolean hasThisItemBeingBorrowed(String itemId) {
        int count = (int) transactionList.stream().filter(t -> !t.isFinished())
                .filter(t -> t.getItemId().equals(itemId)).count();
        return count > 0;
    }

    /**
     * Formats the transaction list information to store in hard-drive.
     *
     * @return A formatted string of transaction list information
     */
    public String convertTransactionListToFileFormat() {
        StringBuilder formattedString = new StringBuilder();
        for (Transaction transaction : transactionList) {
            formattedString.append(transaction.convertTransactionToFileFormat()).append('\n');
        }
        return formattedString.toString();
    }

    private void checkValidId(String transactionId) throws DuplicateException {
        try {
            this.getTransactionById(transactionId);
            throw new DuplicateException(MESSAGE_DUPLICATE_TRANSACTION_ID);
        } catch (TransactionNotFoundException e) {
            return;
        }
    }


    /**
     * Checks if the duration is valid or not.
     *
     * @param duration The input duration
     * @throws DurationInvalidException If the number is less than 0
     */
    private void checkValidDuration(String duration) throws DurationInvalidException {
        try {
            if (Integer.parseInt(duration) < 0 || Integer.parseInt(duration) > 1461) {
                throw new DurationInvalidException(MESSAGE_DURATION_INVALID);
            }
        } catch (NumberFormatException e) {
            throw new NumberFormatException(MESSAGE_NUMBER_FORMAT_INVALID);
        }
    }

    /**
     * Checks if the created Date is valid or not.
     *
     * @param createdAt The input created date of transaction
     * @throws DateFormatInvalidException If the date is in wrong format or after the current day
     */
    private void checkValidCreatedDate(String createdAt) throws DateFormatInvalidException {
        LocalDate validBeginningDate = LocalDate.parse("2016-01-01");
        try {
            if (LocalDate.parse(createdAt).isAfter(LocalDate.now())
                    || LocalDate.parse(createdAt).isBefore(validBeginningDate)) {
                throw new DateFormatInvalidException(MESSAGE_CREATED_DATE_RANGE_INVALID);
            }
        } catch (DateTimeParseException e) {
            throw new DateFormatInvalidException(MESSAGE_DATE_FORMAT_INVALID);
        }
    }

    /**
     * Checks if storage duration created date and transaction ID is valid or not.
     *
     * @param args The array of input arguments
     * @throws DateFormatInvalidException If date of wrong format
     * @throws DurationInvalidException If duration is out of range or wrong format
     * @throws DuplicateException If there is at least one transaction with the same ID
     */
    public void checkValidArgsForStorage(String[] args)
            throws DateFormatInvalidException, DurationInvalidException, DuplicateException {
        checkValidDuration(args[DURATION_INDEX]);
        checkValidCreatedDate(args[CREATED_DATE_INDEX]);
        checkValidId(args[TX_ID_INDEX]);
    }

    /**
     * Checks if input duration and created date is valid or not.
     *
     * @param args The array of input arguments
     * @throws DateFormatInvalidException If date of wrong format
     * @throws DurationInvalidException If duration is out of range or wrong format
     */
    public void checkValidArgsForAdding(String[] args)
            throws DateFormatInvalidException, DurationInvalidException {
        checkValidDuration(args[DURATION_INDEX]);
        checkValidCreatedDate(args[CREATED_DATE_INDEX]);
    }

    /**
     * Overrides toString method of Object to get string representation of TransactionList.
     *
     * @return A string representation of TransactionList
     */
    @Override
    public String toString() {
        StringBuilder listString = new StringBuilder();
        if (transactionList.size() == 0) {
            listString.append("Your requested transaction list is empty");
        } else {
            listString.append("Here are ").append(transactionList.size())
                    .append(" transaction(s) you want to view:");
        }
        int index = 1;
        for (Transaction transaction : transactionList) {
            listString.append('\n').append(index++).append(". ").append(transaction);
        }
        return String.valueOf(listString);
    }
}
