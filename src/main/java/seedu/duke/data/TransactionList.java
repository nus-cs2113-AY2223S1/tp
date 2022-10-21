package seedu.duke.data;

import seedu.duke.data.transaction.Expense;
import seedu.duke.data.transaction.Income;
import seedu.duke.data.transaction.Transaction;
import seedu.duke.exception.InputTransactionUnknownTypeException;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Represents a list of transactions added by the user into the application.
 * Operations related to modifying the list of transactions are defined under this class.
 * These operations include adding, listing, modifying, deleting and purging.
 */
public class TransactionList {
    //@@author chydarren
    private static final String PREFIX_CATEGORY = "[";
    private static final String POSTFIX_CATEGORY = "]";
    private static final String SYMBOL_DOLLAR = "$";
    private static final String LINE_SEPARATOR = System.lineSeparator();

    //@@author chinhan99
    private ArrayList<Transaction> transactions;

    public TransactionList(TransactionList transactionList) {
        transactions = transactionList.getTransactions();
    }

    public ArrayList<Transaction> getTransactions() {
        return transactions;
    }

    //@@author wcwy

    /**
     * Initialises the variables of the TransactionList class.
     */
    public TransactionList() {
        this.transactions = new ArrayList<>();
    }

    //@@author brian-vb

    /**
     * Gets a specific entry from the transactions list, to be used by other classes.
     *
     * @param index An index of the transaction that is to be retrieved.
     * @return The transaction entry from the transactions list.
     */
    public Transaction getEntry(int index) {
        return transactions.get(index);
    }

    /**
     * Gets the number of transactions in the transactions list.
     *
     * @return An integer value that indicates the number of transactions.
     */
    public int size() {
        return transactions.size();
    }

    /**
     * Deletes a transaction from the transactions list based on the specified index.
     *
     * @param index An index of the transaction that is to be retrieved.
     * @return A string tht states the details of the deleted transaction.
     */
    public String deleteTransaction(int index) {
        Transaction transaction = transactions.get(index - 1);
        transactions.remove(index - 1);
        return transaction.toString();
    }

    //@@author wcwy

    /**
     * Adds a transaction of class type Expense into the transactions list.
     *
     * @param description More information regarding the transaction, written without any space.
     * @param amount      Value of the transaction in numerical form.
     * @param category    A category for the transaction.
     * @param date        Date of the transaction with format in "yyyyMMdd".
     * @return A string that states the details of the added expense transaction.
     */
    public String addExpense(String description, int amount, String category, LocalDate date) {
        Expense expense = new Expense(description, amount, category, date);
        transactions.add(expense);
        return expense.toString();
    }

    /**
     * Adds a transaction of class type Income into the transactions list.
     *
     * @param description More information regarding the transaction, written without any space.
     * @param amount      Value of the transaction in numerical form.
     * @param category    A category for the transaction.
     * @param date        Date of the transaction with format in "yyyyMMdd".
     * @return A string that states the details of the added income transaction.
     */
    public String addIncome(String description, int amount, String category, LocalDate date) {
        Income income = new Income(description, amount, category, date);
        transactions.add(income);
        return income.toString();
    }

    //@@author chinhan99

    public void addIncomeDuringStorage(String description, int amount, String category, LocalDate date) {
        Income income = new Income(description, amount, category, date);
        transactions.add(income);
    }


    public void addExpenseDuringStorage(String description, int amount, String category, LocalDate date) {
        Expense expense = new Expense(description, amount, category, date);
        transactions.add(expense);
    }

    //@@author chydarren
    /**
     * Checks whether the transaction belongs to the Income or Expense class type.
     *
     * @param transaction The transaction record from the transactions list.
     * @param classType   The transaction class type that is either Income or Expense.
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
     * @param type        The type of transaction.
     * @param category    A category for the transaction.
     * @param date        Date of the transaction with format in "yyyyMMdd".
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
     * Lists all or some transactions based on selection.
     *
     * @param type     The type of transaction.
     * @param category A category for the transaction.
     * @param date     Date of the transaction with format in "yyyyMMdd".
     * @return A string containing the formatted transaction list.
     * @throws InputTransactionUnknownTypeException If class type cannot be found in the packages.
     */
    public String listTransactions(String type, String category, LocalDate date)
            throws InputTransactionUnknownTypeException {
        String transactionsList = "";
        // Loops each transaction from the transactions list
        for (Transaction transaction : transactions) {
            if (isMatchListFilters(transaction, type, category, date)) {
                transactionsList += transaction.toString() + LINE_SEPARATOR;
            }
        }
        return transactionsList;
    }

    /**
     * Finds specific transaction(s) based on any keywords inputted by the user.
     *
     * @param keywords Any partial or full keyword(s) that matches the details of the transaction.
     * @return A string containing the formatted transaction list.
     */
    public String findTransactions(String keywords) {
        String transactionsList = "";
        // Loops each transaction from the transactions list
        for (Transaction transaction : transactions) {
            // Includes only transactions that contain the keywords used in the search expression
            if (transaction.toString().contains(keywords)) {
                transactionsList += transaction.toString() + LINE_SEPARATOR;
            }
        }
        return transactionsList;
    }

    /**
     * Reads the transactions list and adds each amount to the categories in categorical savings hashmap.
     *
     * @param categoricalSavings A hashmap containing all category-amount pair for total savings.
     * @return A hashmap containing all category-amount pair for total savings.
     */
    public HashMap<String, Integer> processCategoricalSavings(HashMap<String, Integer> categoricalSavings) {
        for (Transaction transaction : transactions) {
            String category = transaction.getCategory();
            int amount = transaction.getAmount();
            // Creates a new category with starter amount if category not exists in hashmap
            if (!categoricalSavings.containsKey(category)) {
                categoricalSavings.put(category, amount);
                continue;
            }
            categoricalSavings.put(category, categoricalSavings.get(category) + amount);
        }

        return categoricalSavings;
    }

    /**
     * Calculates and stores total savings for each transaction category into a hashmap.
     *
     * @return A hashmap containing all category-amount pair for total savings.
     */
    public String listCategoricalSavings() {
        String categoricalSavingsList = "";
        HashMap<String, Integer> categoricalSavings = new HashMap<>();
        categoricalSavings = processCategoricalSavings(categoricalSavings);

        for (Map.Entry<String, Integer> entry : categoricalSavings.entrySet()) {
            categoricalSavingsList += String.format("%s%s%s %s%s%s", PREFIX_CATEGORY, entry.getKey(),
                    POSTFIX_CATEGORY, SYMBOL_DOLLAR, entry.getValue(), LINE_SEPARATOR);
        }

        return categoricalSavingsList;
    }

    //@@author brian-vb
    /**
     * Purges all records in the transactions list.
     */
    public void purgeTransactions() {
        transactions.clear();
    }
}
