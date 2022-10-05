package seedu.duke.data;

import seedu.duke.common.InfoMessages;
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

    public Transaction getEntry(int index) { return transactions.get(index - 1);}

    public void removeEntry(int index) {transactions.remove(index - 1);}

    public static void deleteEntry(TransactionList input, int index) {
        System.out.println(InfoMessages.MESSAGE_INFO_DIVIDER);
        Transaction deleted = input.getEntry(index);
        String information = deleted.getDescription();
        input.removeEntry(index);
        System.out.println("MOOOOOO.... I HAVE DELETED THE FOLLOWING TRANSACTION:" + information);
        System.out.println(InfoMessages.MESSAGE_INFO_DIVIDER);
    }

    public void addExpense(String description, int amount, String category, String date) {
        transactions.add(new Expense(description, amount, category, date));
    }

    public void addIncome(String description, int amount, String category, String date) {
        transactions.add(new Income(description, amount, category, date));
    }

    public void list() {
        for (Transaction t : transactions) {
            System.out.println(t.toString());
        }
    }


}
