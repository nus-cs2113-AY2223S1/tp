package seedu.duke.data;

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

    private static ArrayList<Transaction> transactions;

    public TransactionList() {
        this.transactions = new ArrayList<>();
    }

    public Transaction getEntry(int index) {
        return transactions.get(index);
    }

    public static String deleteTransaction(TransactionList input, int index) {
        Transaction transaction = input.getEntry(index);
        transactions.remove(index);
        return transaction.toString();
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
        // Loops each transaction from the transactions list
        for (Transaction transaction : transactions) {
            transactionsList += transaction.toString() + LINE_SEPARATOR;
        }
        return transactionsList;
    }

    public String findTransactions(String keywords) {
        String transactionsList = EMPTY_STRING;
        // Loops each transaction from the transactions list
        for (Transaction transaction : transactions) {
            // Includes only transactions that contain the keywords used in the search expression
            if (transaction.toString().contains(keywords)) {
                transactionsList += transaction.toString() + LINE_SEPARATOR;
            }
        }
        return transactionsList;
    }

    public static void purgeTransactions() {
        transactions.clear();
    }

    public int size() {
        return transactions.size();
    }
}
