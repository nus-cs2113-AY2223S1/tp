package seedu.duke.data;

import seedu.duke.common.InfoMessages;
import seedu.duke.data.transaction.Expense;
import seedu.duke.data.transaction.Income;
import seedu.duke.data.transaction.Transaction;

import java.time.LocalDate;
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
        input.empty();
    }

    private void empty() {
        transactions.clear();
    }

    private Transaction getEntry(int index) {
        return transactions.get(index - 1);
    }

    private void removeEntry(int index) {
        transactions.remove(index - 1);
    }

    public static String deleteTransaction(TransactionList input, int index) {
        Transaction transaction = input.getEntry(index);
        input.removeEntry(index);
        return transaction.getDescription();
    }

    public String addExpense(String description, int amount, String category, LocalDate date) {
        Expense expense = new Expense(description, amount, category, date);
        transactions.add(expense);
        return expense.toString();
    }

    public String addIncome(String description, int amount, String category, LocalDate date) {
        Income income = new Income(description, amount, category, date);
        transactions.add(income);
        return income.toString();
    }

    public String listTransactions() {
        String transactionsList = EMPTY_STRING;
        // Loops each task from the transactions list
        for (Transaction transaction : transactions) {
            transactionsList += transaction.toString() + LINE_SEPARATOR;
        }
        if (!transactionsList.isEmpty()) {
            // Includes the count of the transactions with the transaction list
            transactionsList += String.format(InfoMessages.INFO_TRANSACTION_COUNT.toString(),
                    transactions.size());
        }

        return transactionsList;
    }

    public int size() {
        return transactions.size();
    }
}
