package seedu.duke.data;

import seedu.duke.data.transaction.Expense;
import seedu.duke.data.transaction.Income;
import seedu.duke.data.transaction.Transaction;

import java.util.ArrayList;

/**
 * Represents a list of transactions added by the user into the application.
 * Operations related to modifying the list of transactions are defined under this class.
 * These operations include adding, listing, modifying, deleting and purging.
 */
public class TransactionList {
    private ArrayList<Transaction> transactions;

    public TransactionList() {
        this.transactions = new ArrayList<>();
    }

    public void addExpense(String description, int amount, String category, String date) {
        transactions.add(new Expense(description, amount, category, date));
    }

    public void addIncome(String description, int amount, String category, String date) {
        transactions.add(new Income(description, amount, category, date));
    }

    public void list () {
        for (Transaction t : transactions) {
            System.out.println(t.toString());
        }
    }


}
