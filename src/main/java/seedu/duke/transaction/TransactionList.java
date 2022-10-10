package seedu.duke.transaction;

import seedu.duke.exception.TransactionNotFoundException;

import java.util.ArrayList;

public class TransactionList {
    private ArrayList<Transaction> transactionList;

    public TransactionList() {
        this.transactionList = new ArrayList<>();
    }

    public int getSize() {
        return transactionList.size();
    }

    public void markFinished(String transactionId) throws TransactionNotFoundException {
        Transaction transaction = getTransactionById(transactionId);
        int index = transactionList.indexOf(transaction);
        transactionList.get(index).setAsFinished();
    }

    public void unmarkFinished(String transactionId) throws TransactionNotFoundException {
        Transaction transaction = getTransactionById(transactionId);
        int index = transactionList.indexOf(transaction);
        transactionList.get(index).setAsNotFinished();
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
        throw new TransactionNotFoundException("Cannot find this transaction");
    }

    public boolean hasThisBorrower(String username) {
        for (Transaction transaction : transactionList) {
            if (transaction.getBorrower().equals(username)) {
                return true;
            }
        }
        return false;
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
