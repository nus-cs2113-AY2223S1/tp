package seedu.duke.data;

import seedu.duke.data.transaction.Expense;
import seedu.duke.data.transaction.Income;
import seedu.duke.data.transaction.Transaction;
import seedu.duke.exception.InputTransactionInvalidTypeException;

import java.time.LocalDate;
import java.time.Year;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.stream.Collectors;

import static seedu.duke.common.Constants.MAX_AMOUNT_VALUE;
import static seedu.duke.common.Constants.MAX_TRANSACTIONS_COUNT;
import static seedu.duke.common.InfoMessages.INFO_STATS_CATEGORIES_HEADER;

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
    private static final String INCOME = "income";
    private static final String EXPENSE = "expense";
    private static final String MONTHS = "months";
    private static final String WEEKS = "weeks";
    private static final int START = 0;
    private static final int END = 1;
    private static final int UNDEFINED_PARAMETER = -1;
    private static final String LINE_SEPARATOR = System.lineSeparator();

    //@@author chinhan99
    private static ArrayList<Transaction> transactions;

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
     * @return The expense object created and added to the list.
     */
    public Expense addExpense(String description, int amount, String category, LocalDate date) {
        Expense expense = new Expense(description, amount, category, date);
        transactions.add(expense);
        return expense;
    }

    /**
     * Adds a transaction of class type Income into the transactions list.
     *
     * @param description More information regarding the transaction, written without any space.
     * @param amount      Value of the transaction in numerical form.
     * @param category    A category for the transaction.
     * @param date        Date of the transaction with format in "yyyyMMdd".
     * @return The income object created and added to the transaction list.
     */
    public Income addIncome(String description, int amount, String category, LocalDate date) {
        Income income = new Income(description, amount, category, date);
        transactions.add(income);
        return income;
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
     * @throws InputTransactionInvalidTypeException If class type cannot be found in the packages.
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
     * @throws InputTransactionInvalidTypeException If class type cannot be found in the packages.
     */
    public boolean isMatchListFilters(Transaction transaction, String type, String category,
                                      LocalDate date) throws InputTransactionInvalidTypeException {
        boolean isMatch;
        try {
            isMatch = ((type.isEmpty() || isTransactionInstance(transaction, type))
                    && (category.isEmpty() || transaction.getCategory().equals(category))
                    && (date == null || transaction.getDate().equals(date)));
        } catch (ClassNotFoundException e) {
            throw new InputTransactionInvalidTypeException();
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
     * @throws InputTransactionInvalidTypeException If class type cannot be found in the packages.
     */
    public String listTransactions(ArrayList<Transaction> timeTransactions, String type, String category, LocalDate date)
            throws InputTransactionInvalidTypeException {
        String transactionsList = "";

        // Loops each transaction from the time transactions list
        for (Transaction transaction : timeTransactions) {
            // Applies generic filter against each time transaction entry
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
                transactionsList += transaction + LINE_SEPARATOR;
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
        // Adds each amount from transactions list to the categories in categorical savings hashmap
        categoricalSavings = processCategoricalSavings(categoricalSavings);

        // Formats every entry in the hashmap into a categorical savings list
        for (HashMap.Entry<String, Integer> entry : categoricalSavings.entrySet()) {
            categoricalSavingsList += String.format("%s%s%s %s%s%s", PREFIX_CATEGORY, entry.getKey(),
                    POSTFIX_CATEGORY, SYMBOL_DOLLAR, entry.getValue(), LINE_SEPARATOR);
        }

        return categoricalSavingsList;
    }

    //@@author paullowse

    /**
     * Produces Categorical saving list for timeTransactions.
     *
     * @param timeTransactions  An instance of the TransactionList class.
     * @param year              A specified year.
     * @param month             A specified month.
     * @param period            A specified period of time.
     * @param number            A specified number of periods.
     * @return String output of transactions for the time period.
     */
    public String listTimeStats(ArrayList<Transaction> timeTransactions, int year, int month, String period,
                                int number) {
        String timeSavingsList = "";

        if (period != null && number != UNDEFINED_PARAMETER) {
            timeSavingsList += "The past " + number + " " + period + ": " + LINE_SEPARATOR + LINE_SEPARATOR
                    + INFO_STATS_CATEGORIES_HEADER + LINE_SEPARATOR;
        } else if (month == UNDEFINED_PARAMETER) {
            timeSavingsList += "Year: " + year + LINE_SEPARATOR + LINE_SEPARATOR + INFO_STATS_CATEGORIES_HEADER
                    + LINE_SEPARATOR;
        } else {
            timeSavingsList += "Year: " + year + ", Month: " + month
                    + LINE_SEPARATOR + LINE_SEPARATOR + INFO_STATS_CATEGORIES_HEADER + LINE_SEPARATOR;
        }

        // Formats every entry in the hashmap into a categorical savings list
        for (Transaction entry : timeTransactions) {
            timeSavingsList += String.format("%s%s%s %s%s%s", PREFIX_CATEGORY, entry.getCategory(),
                    POSTFIX_CATEGORY, SYMBOL_DOLLAR, entry.getAmount(), LINE_SEPARATOR);
        }

        return timeSavingsList;
    }

    /**
     * Produces Expense, Income and Savings statistics.
     *
     * @param timeTransactions  An instance of the TransactionList class.
     * @return An amount arraylist of Expense and Income.
     */
    public ArrayList<String> processTimeSummaryStats(ArrayList<Transaction> timeTransactions) {
        int timeExpense = 0;
        int timeIncome = 0;
        for (Transaction entry : timeTransactions) {
            String category = entry.getType();
            if (category.equals(EXPENSE)) {
                timeExpense += entry.getAmount();
            } else if (category.equals(INCOME)) {
                timeIncome += entry.getAmount();
            }
        }
        int timeSavings = timeIncome - timeExpense;

        ArrayList<String> amounts = new ArrayList<String>();
        amounts.add(Integer.toString(timeIncome));
        amounts.add(Integer.toString(timeExpense));
        amounts.add(Integer.toString(timeSavings));
        return amounts;
    }

    //@@author chydarren

    /**
     * Gets all transactions recorded in between specified weeks, backdated from given date.
     * E.g. If the date is 21 October 2022 to backdate 2 weeks, the range will be 3 October to 16 October 2022.
     *
     * @param date          A specified date to backdate N weeks from occurring week.
     * @param numberOfWeeks N number of weeks to backdate, must be minimum 1 week.
     * @return An array list containing all transactions recorded in specified weeks.
     */
    public static ArrayList<Transaction> getTransactionsByWeekRange(LocalDate date, int numberOfWeeks) {
        ArrayList<Transaction> transactionsByWeekRange = new ArrayList<>();

        // Solution below adapted from https://stackoverflow.com/a/51356522
        // Gets the range of dates for the last N number of weeks from occurring week
        int dayOfWeek = date.getDayOfWeek().getValue();
        LocalDate from = date.minusDays((dayOfWeek - 1) + (numberOfWeeks * 7));
        LocalDate to = date.minusDays(dayOfWeek);

        transactionsByWeekRange = getTransactionsByDateRange(new LocalDate[]{from, to}, transactionsByWeekRange);
        return transactionsByWeekRange;
    }

    /**
     * Gets all transactions recorded in between specified months, backdated from given date.
     * E.g. If the date is 21 October 2022 to backdate 2 months, the range will be 1 August to 30 September 2022.
     *
     * @param date           A specified date to backdate N months from occurring month.
     * @param numberOfMonths N number of months to backdate, must be minimum 1 month.
     * @return An array list containing all transactions recorded in specified months.
     */
    public static ArrayList<Transaction> getTransactionsByMonthRange(LocalDate date, int numberOfMonths) {
        ArrayList<Transaction> transactionsByMonthRange = new ArrayList<>();

        // Solution below adapted from https://stackoverflow.com/a/51356522
        // Gets the range of dates for the last N number of months from occurring month
        LocalDate lastMonth = date.minusMonths(1);
        LocalDate from = date.minusMonths(numberOfMonths).withDayOfMonth(1);
        LocalDate to = lastMonth.withDayOfMonth(lastMonth.getMonth().maxLength());

        transactionsByMonthRange = getTransactionsByDateRange(new LocalDate[]{from, to}, transactionsByMonthRange);
        return transactionsByMonthRange;
    }

    /**
     * Gets all transactions recorded on a specific year.
     *
     * @param year A specified year.
     * @return An array list containing all transactions recorded on a specific year.
     */
    public ArrayList<Transaction> getTransactionsByYear(int year) {
        //@@author chydarren-reused
        //Reused from https://stackoverflow.com/a/69440139
        // with minor modifications
        ArrayList<Transaction> transactionsByYear = (ArrayList<Transaction>) transactions.stream()
                .filter(transaction -> Year.from(transaction.getDate()).equals(Year.of(year)))
                .collect(Collectors.toList());
        //@@author

        return transactionsByYear;
    }

    /**
     * Gets all transactions recorded on a specific month.
     *
     * @param year  A specified year.
     * @param month A specified month within the year.
     * @return An array list containing all transactions recorded on a specific month.
     */
    public ArrayList<Transaction> getTransactionsByMonth(int year, int month) {
        //@@author chydarren-reused
        //Reused from https://stackoverflow.com/a/69440139
        ArrayList<Transaction> transactionsByMonth = (ArrayList<Transaction>) transactions.stream()
                .filter(transaction -> YearMonth.from(transaction.getDate()).equals(YearMonth.of(year, month)))
                .collect(Collectors.toList());
        //@@author

        return transactionsByMonth;
    }

    /**
     * Gets all transactions recorded on the last N weeks or months.
     *
     * @param dateRange A specified range of dates.
     * @return An array list containing all transactions recorded on the last N number of weeks or months.
     */
    public static ArrayList<Transaction> getTransactionsByDateRange(LocalDate[] dateRange,
                                                                    ArrayList<Transaction> transactionsByDateRange) {

        for (Transaction transaction : transactions) {
            LocalDate transactionDate = transaction.getDate();
            // Transaction is added into the filtered array list if it falls within the expected date range
            if (!(transactionDate.isBefore(dateRange[START]) || transactionDate.isAfter(dateRange[END]))) {
                transactionsByDateRange.add(transaction);
            }
        }

        return transactionsByDateRange;
    }

    //@@author brian-vb

    /**
     * Purges all records in the transactions list.
     */
    public void purgeTransactions() {
        transactions.clear();
    }

    //@@author wcwy

    /**
     * Calculates the total expenses spent in the month and year of the provided date, and returns the sum as a long.
     *
     * @param date A date object in which the monthly total expenses calculated is based on.
     * @return A long value indicating the amount of expenses spent in the month.
     */
    public long calculateMonthlyTotalExpense(LocalDate date) {
        long totalExpense = 0;
        int month = date.getMonthValue();
        int year = date.getYear();
        for (Transaction transaction : transactions) {
            if (transaction.getDate().getMonthValue() == month && transaction.getDate().getYear() == year) {
                /*
                    Since the maximum number of transaction is 1000000 and maximum amount of expense is 10000000,
                    the highest possible expense value is 10^6 * 10^7 = 10^15 < Long.MAX_VALUE (approx 9.22 * 10^18)
                    Therefore, this function is safe from integer overflow UNLESS the max values in
                    common.Constants.java is altered.
                 */

                assert (Long.valueOf(MAX_AMOUNT_VALUE) * Long.valueOf(MAX_TRANSACTIONS_COUNT) > 0);
                assert (Long.valueOf(MAX_AMOUNT_VALUE) * Long.valueOf(MAX_TRANSACTIONS_COUNT)
                        > Long.valueOf(MAX_AMOUNT_VALUE));

                totalExpense += transaction.getAmount();
            }
        }
        return totalExpense;
    }
}
