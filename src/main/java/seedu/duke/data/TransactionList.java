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
    private static final String EMPTY_STRING = "";
    private static final String LINE_SEPARATOR = System.lineSeparator();

    private ArrayList<Transaction> transactions;

    public TransactionList() {
        this.transactions = new ArrayList<>();
    }

    public static void purgeEntries(TransactionList input) {
        System.out.println(InfoMessages.MESSAGE_INFO_DIVIDER);
        input.empty();
        System.out.println("MOOOOOO.... All of your transactions have been purged.");
        System.out.println(InfoMessages.MESSAGE_INFO_DIVIDER);
    }

    private void empty() {
        transactions.clear();
    }

    private Transaction getEntry(int index) { return transactions.get(index - 1); }

    private void removeEntry(int index) { transactions.remove(index - 1); }

    public static void deleteEntry(TransactionList input, int index) {
        System.out.println(InfoMessages.MESSAGE_INFO_DIVIDER);
        Transaction deleted = input.getEntry(index);
        String information = deleted.getDescription();
        input.removeEntry(index);
        System.out.println("MOOOOOO.... I HAVE DELETED THE FOLLOWING TRANSACTION: " + information);
        System.out.println(InfoMessages.MESSAGE_INFO_DIVIDER);
    }

    public void addExpense(String description, int amount, String category, String date) {
        transactions.add(new Expense(description, amount, category, date));
        System.out.println(InfoMessages.MESSAGE_INFO_DIVIDER);
        System.out.println("MOOOOOO... I have added an Expense Transaction.");
        System.out.println(InfoMessages.MESSAGE_INFO_DIVIDER);
    }

    public void addIncome(String description, int amount, String category, String date) {
        transactions.add(new Income(description, amount, category, date));
        System.out.println(InfoMessages.MESSAGE_INFO_DIVIDER);
        System.out.println("MOOOOOO... I have added an Income Transaction.");
        System.out.println(InfoMessages.MESSAGE_INFO_DIVIDER);
    }

    public String listTransactions() {
        String transactionsList = EMPTY_STRING;
        // Loops each task from the transactions list
        for (Transaction transaction : transactions) {
            transactionsList += transaction.toString() + LINE_SEPARATOR;
        }
        if (!transactionsList.equals(EMPTY_STRING)) {
            // Includes the count of the transactions with the transaction list
            transactionsList += String.format(InfoMessages.MESSAGE_INFO_TRANSACTION_COUNT.toString(),
                    transactions.size());
        }

        return transactionsList;
    }


}
