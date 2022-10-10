package seedu.duke.transaction;

import seedu.duke.Ui;
import seedu.duke.exception.DukeException;

import java.util.ArrayList;

public class TransactionList {
    private ArrayList<Transaction> transactionList;
    private Ui ui = new Ui();

    public TransactionList() {
        this.transactionList = new ArrayList<>();
    }

    public int getSize() {
        return transactionList.size();
    }

    public void markFinished(String transactionId) throws DukeException {
        Transaction transaction = getTransactionById(transactionId);
        int index = transactionList.indexOf(transaction);
        transactionList.get(index).setAsFinished();
    }

    public void unmarkFinished(String transactionId) throws DukeException {
        Transaction transaction = getTransactionById(transactionId);
        int index = transactionList.indexOf(transaction);
        transactionList.get(index).setAsNotFinished();
    }

    public void add(Transaction transaction) {
        transactionList.add(transaction);
    }

    public void deleteTransaction(String transactionId) throws DukeException {
        Transaction transaction = getTransactionById(transactionId);
        transactionList.remove(transaction);
    }

    public Transaction getTransactionById(String transactionId) throws DukeException {
        for (Transaction transaction: transactionList) {
            if (transaction.getTxId().equals(transactionId)) {
                return transaction;
            }
        }
        throw new DukeException("Cannot find this transaction");
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
