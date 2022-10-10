package seedu.duke.data;

import seedu.duke.data.transaction.Expense;
import seedu.duke.data.transaction.Income;
import seedu.duke.data.transaction.Transaction;
import seedu.duke.exception.InputTransactionUnknownTypeException;

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
        Transaction transaction = input.getEntry(index - 1);
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

    /**
     * Checks whether the transaction belongs to the Income or Expense class type.
     *
     * @param transaction The transaction record from the transactions list.
     * @param classType The transaction class type that is either Income or Expense.
     * @return A boolean value indicating whether transaction record belongs to the given class type.
     * @throws InputTransactionUnknownTypeException If class type cannot be found in the packages.
     */
    public boolean isTransactionInstance(Object transaction, String classType) throws ClassNotFoundException {
        return Class.forName(classType).isInstance(transaction);
    }

    /**
     * Checks whether a transaction fulfills the given filter criteria.
     *
     * @param transaction The transaction record from the transactions list.
     * @param type The type of transaction.
     * @param category A category for the transaction.
     * @param date Date of the transaction with format in "yyyyMMdd".
     * @return A string containing the formatted transaction list.
     * @throws InputTransactionUnknownTypeException If class type cannot be found in the packages.
     */
    public boolean isMatchListFilters(Transaction transaction, String type, String category,
                                       LocalDate date) throws InputTransactionUnknownTypeException {
        boolean isMatch;
        try {
            isMatch = ((type.isEmpty() || isTransactionInstance(transaction, type))
                    && (category.isEmpty() || transaction.getCategory().equals(category))
                    && (date == null || transaction.getDate().equals(date)));
        } catch (ClassNotFoundException e) {
            throw new InputTransactionUnknownTypeException();
        }
        return isMatch;
    }

    /**
     * List all or some transactions based on selection.
     *
     * @param type The type of transaction.
     * @param category A category for the transaction.
     * @param date Date of the transaction with format in "yyyyMMdd".
     * @return A string containing the formatted transaction list.
     * @throws InputTransactionUnknownTypeException If class type cannot be found in the packages.
     */
    public String listTransactions(String type, String category, LocalDate date)
            throws InputTransactionUnknownTypeException {
        String transactionsList = EMPTY_STRING;
        // Loops each transaction from the transactions list
        for (Transaction transaction : transactions) {
            if (isMatchListFilters(transaction, type, category, date)) {
                transactionsList += transaction.toString() + LINE_SEPARATOR;
            }
        }
        return transactionsList;
    }

    /**
     * Find specific transaction(s) based on any keywords inputted by the user.
     *
     * @param keywords Any partial or full keyword(s) that matches the details of the transaction.
     * @return A string containing the formatted transaction list.
     */
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
