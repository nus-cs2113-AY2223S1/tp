package seedu.duke.transaction;

import seedu.duke.exception.DukeException;

import java.util.ArrayList;

public class TransactionList {
    private ArrayList<Transaction> transactionList;

    public TransactionList() {
        this.transactionList = new ArrayList<>();
    }

    public Transaction getTransaction(int index) throws DukeException {
        try {
            return transactionList.get(index - 1);
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("OOPS!!! The transaction number is out of bound"
                    + "\nThere are only " + transactionList.size() + " transaction(s) in your list");
        }
    }

    public int getSize() {
        return transactionList.size();
    }

    public void markFinished(int index) throws DukeException {
        getTransaction(index).setAsFinished();
    }

    public void unmarkFinished(int index) throws DukeException {
        getTransaction(index).setAsNotFinished();
    }

    public void add(Transaction transaction) {
        transactionList.add(transaction);
    }

    public void delete(int index) throws DukeException {
        transactionList.remove(getTransaction(index));
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
