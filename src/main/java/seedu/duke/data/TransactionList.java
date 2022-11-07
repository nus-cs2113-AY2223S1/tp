package seedu.duke.data;

import seedu.duke.data.transaction.Expense;
import seedu.duke.data.transaction.Income;
import seedu.duke.data.transaction.Transaction;
import seedu.duke.exception.InputTransactionInvalidTypeException;

import java.time.LocalDate;
import java.time.Year;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.TreeMap;
import java.util.SortedMap;
import java.util.stream.Collectors;

import static java.lang.Math.abs;
import static seedu.duke.common.Constants.NO_AMOUNT_VALUE;
import static seedu.duke.common.Constants.MAX_AMOUNT_VALUE;
import static seedu.duke.common.Constants.MIN_AMOUNT_VALUE;
import static seedu.duke.common.Constants.MAX_TRANSACTIONS_COUNT;
import static seedu.duke.common.Constants.MIN_TRANSACTIONS_INDEX;
import static seedu.duke.common.DateFormats.DATE_MONTH_PATTERN;
import static seedu.duke.common.InfoMessages.LINE_SEPARATOR;
import static seedu.duke.common.InfoMessages.DOLLAR_SIGN;
import static seedu.duke.common.InfoMessages.NEGATIVE_DOLLAR_SIGN;
import static seedu.duke.common.InfoMessages.COLON_SPACE;
import static seedu.duke.common.InfoMessages.INFO_INCOME;
import static seedu.duke.common.InfoMessages.INFO_EXPENSE;
import static seedu.duke.common.InfoMessages.INFO_SAVINGS;
import static seedu.duke.common.InfoMessages.INFO_BUDGET;
import static seedu.duke.common.InfoMessages.INFO_SPENDING_HABIT;
import static seedu.duke.common.InfoMessages.INFO_STATS_CATEGORIES_HEADER;
import static seedu.duke.common.InfoMessages.INFO_STATS_HABIT_VERY_LOW_SAVINGS;
import static seedu.duke.common.InfoMessages.INFO_STATS_HABIT_LOW_SAVINGS;
import static seedu.duke.common.InfoMessages.INFO_STATS_HABIT_MEDIUM_SAVINGS;
import static seedu.duke.common.InfoMessages.INFO_STATS_HABIT_HIGH_SAVINGS;
import static seedu.duke.common.InfoMessages.INFO_STATS_HABIT_VERY_HIGH_SAVINGS;

//@@author chydarren

/**
 * Represents a list of transactions added by the user into the application.
 *
 * <p>Operations related to modifying the list of transactions are defined under this class.
 * These operations include adding, listing, modifying, deleting and purging.
 */
public class TransactionList {
    private static final String PREFIX_CATEGORY = "[";
    private static final String POSTFIX_CATEGORY = "]";
    private static final String INCOME = "income";
    private static final String EXPENSE = "expense";
    private static final int VERY_HIGH_SAVINGS_RATE = 100;
    private static final int HIGH_SAVINGS_RATE = 75;
    private static final int MEDIUM_SAVINGS_RATE = 50;
    private static final int LOW_SAVINGS_RATE = 25;
    private static final int INDEX_FOR_START_DATE = 0;
    private static final int STARTING_INDEX = 0;
    private static final int INDEX_FOR_END_DATE = 1;
    private static final int INDEX_FOR_INCOME = 0;
    private static final int INDEX_FOR_EXPENSE = 1;
    private static final int INDEX_FOR_SAVINGS = 2;
    private static final int ONE_DAY = 1;
    private static final int ONE_MONTH = 1;
    private static final int NUMBER_OF_DAYS_IN_A_WEEK = 7;
    private static final int NUMBER_OF_ACCOUNT_TYPES = 3;
    private static final int NEGATE_AMOUNT = -1;
    private static final int UNDEFINED_PARAMETER = -1;

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
        Transaction transaction = transactions.get(index);
        transactions.remove(index);

        // Sorts the list after deletion
        Collections.sort(transactions);
        return transaction.toString();
    }

    public String editExpense(String description, int amount, String category, LocalDate date, int index) {
        transactions.remove(index);
        Expense expense = new Expense(description, amount, category, date);
        transactions.add(index, expense);

        // Sorts the list after deletion
        Collections.sort(transactions);
        return expense.toString();
    }

    public String editIncome(String description, int amount, String category, LocalDate date, int index) {
        transactions.remove(index);
        Income income = new Income(description, amount, category, date);
        transactions.add(index, income);

        // Sorts the list after deletion
        Collections.sort(transactions);
        return income.toString();
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

        // Sorts the list after deletion
        Collections.sort(transactions);
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

        // Sorts the list after deletion
        Collections.sort(transactions);
        return income;
    }

    //@@author chinhan99

    /**
     * Adds a transaction of class type Income into the transactions list during storage.
     * This method functions the same as addIncome , but it DOES NOT return anything.
     *
     * @param description More information regarding the transaction, written without any space.
     * @param amount      Value of the transaction in numerical form.
     * @param category    A category for the transaction.
     * @param date        Date of the transaction with format in "yyyyMMdd".
     */

    public void addIncomeDuringStorage(String description, int amount, String category, LocalDate date) {
        Income income = new Income(description, amount, category, date);
        transactions.add(income);

        // Sorts the list after deletion
        Collections.sort(transactions);
    }


    /**
     * Adds a transaction of class type Expense into the transactions list during storage.
     * This method functions the same as addExpense , but it DOES NOT return anything.
     *
     * @param description More information regarding the transaction, written without any space.
     * @param amount      Value of the transaction in numerical form.
     * @param category    A category for the transaction.
     * @param date        Date of the transaction with format in "yyyyMMdd".
     */

    public void addExpenseDuringStorage(String description, int amount, String category, LocalDate date) {
        Expense expense = new Expense(description, amount, category, date);
        transactions.add(expense);

        // Sorts the list after deletion
        Collections.sort(transactions);
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
     * @param type                       The type of transaction.
     * @param category                   A category for the transaction.
     * @param date                       Date of the transaction with format in "yyyyMMdd".
     * @param isContainDateIntervalsTags A boolean value that indicates whether date intervals tags are used in the
     *                                   command.
     * @return A string containing the formatted transaction list.
     * @throws InputTransactionInvalidTypeException If class type cannot be found in the packages.
     */
    public String listTransactions(ArrayList<Transaction> timeTransactions, String type,
                                   String category, LocalDate date, Boolean isContainDateIntervalsTags)
            throws InputTransactionInvalidTypeException {
        String transactionsList = "";

        // Loops each transaction from the time transactions list
        int count = MIN_TRANSACTIONS_INDEX;
        for (Transaction transaction : timeTransactions) {
            // Applies generic filter against each time transaction entry
            if (isMatchListFilters(transaction, type, category, date) && !isContainDateIntervalsTags) {
                transactionsList += Integer.toString(count) + COLON_SPACE + transaction.toString() + LINE_SEPARATOR;
            } else if (isMatchListFilters(transaction, type, category, date)) {
                transactionsList += transaction.toString() + LINE_SEPARATOR;
            }
            count++;
        }
        return transactionsList;
    }

    /**
     * Finds specific transaction(s) based on any keywords inputted by the user.
     *
     * @param keyword A keyword that matches the partial or full description of the transaction.
     * @return A string containing the formatted transaction list.
     */
    public String findTransactions(String keyword) {
        String transactionsList = "";
        int count = MIN_TRANSACTIONS_INDEX;
        // Loops each transaction from the transactions list
        for (Transaction transaction : transactions) {
            // Includes only transactions with their description matching the searching keyword
            if (transaction.getDescription().toLowerCase().contains(keyword.toLowerCase())
                    && keyword != "") {
                transactionsList += Integer.toString(count) + COLON_SPACE + transaction + LINE_SEPARATOR;
            }
            count++;
        }
        return transactionsList;
    }

    /**
     * Reads the transactions list and adds each amount to the categories in categorical savings hashmap.
     *
     * @param timeTransactions   An array list of time-filtered transactions.
     * @param categoricalSavings A hashmap containing all category-amount pair for total savings.
     * @return A hashmap containing all category-amount pair for total savings.
     */
    public HashMap<String, Integer> processCategoricalSavings(ArrayList<Transaction> timeTransactions,
                                                              HashMap<String, Integer> categoricalSavings) {
        for (Transaction transaction : timeTransactions) {
            // Category will be used as a key for the hashmap
            String category = transaction.getCategory();

            // Negates the amount if transaction is an Expense
            int amount = transaction.getAmount();

            if (transaction instanceof Expense) {
                amount *= NEGATE_AMOUNT;
            }

            // Creates a new category with starter amount if category not exists in hashmap
            if (!categoricalSavings.containsKey(category)) {
                categoricalSavings.put(category, amount);
                continue;
            }

            // Adds amount to existing category in hashmap
            categoricalSavings.put(category, categoricalSavings.get(category) + amount);
        }

        return categoricalSavings;
    }

    /**
     * Prints the amount in the correct format, i.e. -$X or $X depending on the amount.
     *
     * @param amount The amount for the transaction.
     * @return A string containing the formatted amount output to be added to the list.
     */
    public String constructAmountOutput(int amount) {
        if (amount >= NO_AMOUNT_VALUE) {
            return DOLLAR_SIGN + Integer.toString(amount);
        } else {
            return NEGATIVE_DOLLAR_SIGN + Integer.toString(abs(amount));
        }
    }

    /**
     * Formats the hashmap of categorical savings into a categorical savings list, using timeTransactions.
     *
     * @param timeTransactions An array list of time-filtered transactions.
     * @return A string that represents the formatted categorical savings list.
     */
    public String listCategoricalSavings(ArrayList<Transaction> timeTransactions) {
        String categoricalSavingsList = "";
        HashMap<String, Integer> categoricalSavings = new HashMap<>();
        // Adds each amount from transactions list to the categories in categorical savings hashmap
        categoricalSavings = processCategoricalSavings(timeTransactions, categoricalSavings);

        // Formats every entry in the hashmap into a categorical savings list
        for (HashMap.Entry<String, Integer> entry : categoricalSavings.entrySet()) {
            categoricalSavingsList += String.format("%s%s%s %s%s", PREFIX_CATEGORY, entry.getKey(),
                    POSTFIX_CATEGORY, constructAmountOutput(entry.getValue()), LINE_SEPARATOR);
        }

        return categoricalSavingsList;
    }

    /**
     * Reads the transactions list and adds each amount to the month and year in monthly savings hashmap.
     *
     * @param monthlyExpenditure A hashmap containing all month-expenditure pair for total expenditure and savings.
     * @return A hashmap containing all month-expenditure pair for total expenditure and savings.
     */
    public SortedMap<LocalDate, int[]> processMonthlyExpenditure(SortedMap<LocalDate, int[]> monthlyExpenditure) {
        for (Transaction transaction : transactions) {
            // Month of date will be used as a key for the hashmap
            LocalDate date = transaction.getDate().withDayOfMonth(ONE_DAY);

            // Checks whether transaction is Income or Expense and places in respective amount type
            int income = NO_AMOUNT_VALUE;
            int expense = NO_AMOUNT_VALUE;

            if (transaction instanceof Income) {
                income = transaction.getAmount();
            } else {
                expense = transaction.getAmount();
            }

            // Creates a new month and year with starter amounts if not exists in hashmap
            if (!monthlyExpenditure.containsKey(date)) {
                monthlyExpenditure.put(date, new int[]{income, expense, income - expense});
                continue;
            }

            // Adds amounts to existing month and year in hashmap
            int updatedIncome = monthlyExpenditure.get(date)[INDEX_FOR_INCOME] + income;
            int updatedExpense = monthlyExpenditure.get(date)[INDEX_FOR_EXPENSE] + expense;

            monthlyExpenditure.put(date, new int[]{updatedIncome, updatedExpense, updatedIncome - updatedExpense});
        }

        return monthlyExpenditure;
    }

    /**
     * Gets comment related to spending habit each month.
     *
     * @param income  Income value of the transaction in numerical form.
     * @param savings Savings value of the transaction in numerical form.
     * @return A string containing the comment related to the spending habit for the month.
     */
    public String getSpendingHabitComment(int income, int savings) {
        if (income >= MIN_AMOUNT_VALUE) {
            // Computes savingsPercentage only when savings are divisible by income, i.e. income is not 0
            int savingsPercentage = VERY_HIGH_SAVINGS_RATE * savings / income;

            if (savingsPercentage >= VERY_HIGH_SAVINGS_RATE) {
                return INFO_STATS_HABIT_VERY_HIGH_SAVINGS.toString();
            } else if (savingsPercentage >= HIGH_SAVINGS_RATE) {
                return INFO_STATS_HABIT_HIGH_SAVINGS.toString();
            } else if (savingsPercentage >= MEDIUM_SAVINGS_RATE) {
                return INFO_STATS_HABIT_MEDIUM_SAVINGS.toString();
            } else if (savingsPercentage >= LOW_SAVINGS_RATE) {
                return INFO_STATS_HABIT_LOW_SAVINGS.toString();
            }
        }
        return INFO_STATS_HABIT_VERY_LOW_SAVINGS.toString();
    }

    /**
     * Formats the hashmap of monthly expenditure into a monthly expenditure list.
     *
     * @return A string that represents the formatted monthly expenditure list.
     */
    public String listMonthlyExpenditure() {
        String monthlyExpenditureList = "";
        SortedMap<LocalDate, int[]> monthlyExpenditure = new TreeMap<>();
        // Adds each amount from transactions list to the month and year in monthly expenditure hashmap
        monthlyExpenditure = processMonthlyExpenditure(monthlyExpenditure);

        // Formats every entry in the hashmap into a monthly expenditure list
        for (SortedMap.Entry<LocalDate, int[]> entry : monthlyExpenditure.entrySet()) {
            monthlyExpenditureList += String.format("%s%s%s%s", PREFIX_CATEGORY,
                    entry.getKey().format(DateTimeFormatter.ofPattern(DATE_MONTH_PATTERN.toString())), POSTFIX_CATEGORY,
                    LINE_SEPARATOR);

            // Puts income, expense, savings values into monthly expenditure list
            Enum[] accountType = {INFO_INCOME, INFO_EXPENSE, INFO_SAVINGS};
            for (int i = STARTING_INDEX; i < NUMBER_OF_ACCOUNT_TYPES; i++) {
                monthlyExpenditureList += String.format("%s%s%s%s", accountType[i], COLON_SPACE,
                        constructAmountOutput(entry.getValue()[i]), LINE_SEPARATOR);
            }

            // Puts monthly budget value into monthly expenditure list
            monthlyExpenditureList += String.format("%s%s%s%s%s", INFO_BUDGET, COLON_SPACE, DOLLAR_SIGN,
                    Budget.getBudget(), LINE_SEPARATOR);


            // Puts spending habit comment into monthly expenditure list
            monthlyExpenditureList += String.format("%s%s%s%s", INFO_SPENDING_HABIT, COLON_SPACE,
                    getSpendingHabitComment(entry.getValue()[INDEX_FOR_INCOME], entry.getValue()[INDEX_FOR_SAVINGS]),
                    LINE_SEPARATOR);

            //@@author wcwy

            // Information on budget is only displayed when displaying a specific month's time insights
            long budgetLeft = Budget.calculateBudgetLeft(entry.getValue()[1]);
            String budgetAdvice = Budget.generateBudgetAdvice(budgetLeft, Budget.hasExceededBudget(budgetLeft));
            monthlyExpenditureList += String.format("%s%s%s", budgetAdvice, LINE_SEPARATOR, LINE_SEPARATOR);

            //@@author chydarren
        }

        return monthlyExpenditureList;
    }

    //@@author paullowse

    /**
     * Produces Categorical saving list for timeTransactions.
     *
     * @param timeTransactions An array list of time-filtered transactions.
     * @param year             A specified year.
     * @param month            A specified month.
     * @param period           A specified period of time.
     * @param number           A specified number of periods.
     * @return String output of transactions for the time period.
     */
    public String listTimeStats(ArrayList<Transaction> timeTransactions, int year, int month, String period,
                                int number) {
        String timeInsightsList = "";

        if (period != null && number != UNDEFINED_PARAMETER) {
            timeInsightsList += "The last " + number + " " + period + ":" + LINE_SEPARATOR + LINE_SEPARATOR
                    + INFO_STATS_CATEGORIES_HEADER + LINE_SEPARATOR;
        } else if (month == UNDEFINED_PARAMETER) {
            timeInsightsList += "Year: " + year + LINE_SEPARATOR + LINE_SEPARATOR + INFO_STATS_CATEGORIES_HEADER
                    + LINE_SEPARATOR;
        } else {
            timeInsightsList += "Year: " + year + ", Month: " + month
                    + LINE_SEPARATOR + LINE_SEPARATOR + INFO_STATS_CATEGORIES_HEADER + LINE_SEPARATOR;
        }

        String categoricalList = listCategoricalSavings(timeTransactions);
        timeInsightsList += categoricalList;

        return timeInsightsList;
    }

    /**
     * Produces Expense, Income and Savings statistics.
     *
     * @param timeTransactions An array list of time-filtered transactions.
     * @return An amount arraylist of Expense and Income.
     */
    public ArrayList<String> processTimeSummaryStats(ArrayList<Transaction> timeTransactions) {
        int timeExpense = NO_AMOUNT_VALUE;
        int timeIncome = NO_AMOUNT_VALUE;
        for (Transaction entry : timeTransactions) {
            String category = entry.getType();
            if (category.equals(EXPENSE)) {
                timeExpense += entry.getAmount();
            } else if (category.equals(INCOME)) {
                timeIncome += entry.getAmount();
            }
        }
        int timeSavings = timeIncome - timeExpense;

        ArrayList<String> timeInsightsValues = new ArrayList<String>();
        timeInsightsValues.add(Integer.toString(timeIncome));
        timeInsightsValues.add(Integer.toString(timeExpense));
        timeInsightsValues.add(Integer.toString(timeSavings));

        if (timeExpense == NO_AMOUNT_VALUE && timeIncome == NO_AMOUNT_VALUE) {
            timeInsightsValues.add("There is no spending habit available.");
        } else {
            timeInsightsValues.add(getSpendingHabitComment(timeIncome, timeSavings));
        }

        return timeInsightsValues;
    }

    //@@author chydarren

    /**
     * Gets all transactions recorded in between specified dates, backdated from given date.
     * E.g. If the date is 21 October 2022 to backdate 3 days, the range will be 17 October to 20 October 2022.
     *
     * @param date         A specified date to backdate N days from occurring date.
     * @param numberOfDays N number of days to backdate, must be minimum 1 day.
     * @return An array list containing all transactions recorded in specified days.
     */
    public static ArrayList<Transaction> getTransactionsByDayRange(LocalDate date, int numberOfDays) {
        ArrayList<Transaction> transactionsByDayRange = new ArrayList<>();

        LocalDate from = date.minusDays(numberOfDays);
        LocalDate to = date.minusDays(ONE_DAY);

        transactionsByDayRange = getTransactionsByDateRange(new LocalDate[]{from, to}, transactionsByDayRange);
        return transactionsByDayRange;
    }

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
        LocalDate from = date.minusDays((dayOfWeek - ONE_DAY)
                + (numberOfWeeks * NUMBER_OF_DAYS_IN_A_WEEK));
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
        LocalDate lastMonth = date.minusMonths(ONE_MONTH);
        LocalDate from = date.minusMonths(numberOfMonths).withDayOfMonth(ONE_DAY);
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
            if (!(transactionDate.isBefore(dateRange[INDEX_FOR_START_DATE])
                    || transactionDate.isAfter(dateRange[INDEX_FOR_END_DATE]))) {
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
    public static long calculateMonthlyTotalExpense(LocalDate date) {
        long totalExpense = 0;
        int month = date.getMonthValue();
        int year = date.getYear();
        for (Transaction transaction : transactions) {
            // As sum of spending is to be calculated, thus only expense type transaction will be considered
            if (!(transaction instanceof Expense)) {
                continue;
            }
            if (transaction.getDate().getMonthValue() == month && transaction.getDate().getYear() == year) {
                /*
                    Since the maximum number of transaction is 1000000 and maximum amount of expense is 10000000,
                    the highest possible expense value is 10^6 * 10^7 = 10^13 < Long.MAX_VALUE (approx 9.22 * 10^18)
                    Therefore, this function is safe from integer overflow UNLESS the max values in
                    common.Constants.java is altered.
                 */

                assert (Long.valueOf(MAX_AMOUNT_VALUE) * Long.valueOf(MAX_TRANSACTIONS_COUNT) > 0)
                        : "Maximum amount and transaction set in Constants.java must not have negative value!";
                assert (Long.valueOf(MAX_AMOUNT_VALUE) * Long.valueOf(MAX_TRANSACTIONS_COUNT)
                        > Long.valueOf(MAX_AMOUNT_VALUE))
                        : "Maximum transaction count value set in Constants.java must be higher than 1!";

                totalExpense += transaction.getAmount();
            }
        }
        return totalExpense;
    }
}
