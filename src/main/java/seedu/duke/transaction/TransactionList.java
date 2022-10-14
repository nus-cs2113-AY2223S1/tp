package seedu.duke.transaction;

import seedu.duke.exception.TransactionNotFoundException;

import java.util.ArrayList;

import static seedu.duke.exception.ExceptionMessages.MESSAGE_TX_NOT_FOUND;

public class TransactionList {
    private final ArrayList<Transaction> transactionList;

    public TransactionList() {
        this.transactionList = new ArrayList<>();
    }

    public TransactionList(ArrayList<Transaction> transactionList) {
        this.transactionList = transactionList;
    }

    public int getSize() {
        return transactionList.size();
    }

    public ArrayList<Transaction> getTransactionList() {
        return this.transactionList;
    }

    public void add(Transaction transaction) {
        transactionList.add(transaction);
    }

    public void deleteTransaction(String transactionId) throws TransactionNotFoundException {
        Transaction transaction = getTransactionById(transactionId);
        transactionList.remove(transaction);
    }

    public Transaction getTransactionById(String transactionId) throws TransactionNotFoundException {
        for (Transaction transaction : transactionList) {
            if (transaction.getTxId().equals(transactionId)) {
                return transaction;
            }
        }
        throw new TransactionNotFoundException(MESSAGE_TX_NOT_FOUND);
    }

    public boolean hasThisBorrower(String username) {
        int count = (int) transactionList.stream()
                .filter(t -> !t.isFinished())
                .filter(t -> t.getBorrower().equals(username))
                .count();
        return count > 0;
    }

    public boolean hasThisItemBeingBorrowed(String itemId) {
        int count = (int) transactionList.stream()
                .filter(t -> !t.isFinished())
                .filter(t -> t.getItemId().equals(itemId))
                .count();
        return count > 0;
    }

    public String convertTransactionListToFileFormat() {
        StringBuilder formattedString = new StringBuilder();
        for (Transaction transaction: transactionList) {
            formattedString.append(transaction.convertTransactionToFileFormat());
        }
        return formattedString.toString();
    }

    @Override
    public String toString() {
        StringBuilder listString = new StringBuilder();
        if (transactionList.size() == 0) {
            listString.append("There is no transaction in your list right now");
        } else {
            listString.append("Here are ").append(transactionList.size()).append(" transaction(s) in your list:");
        }
        int index = 1;
        for (Transaction transaction : transactionList) {
            listString.append('\n').append("   ").append(index++).append(". ").append(transaction);
        }
        return String.valueOf(listString);
    }
}
