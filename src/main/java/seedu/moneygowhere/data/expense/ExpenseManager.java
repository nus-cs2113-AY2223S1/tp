package seedu.moneygowhere.data.expense;

import seedu.moneygowhere.commands.ConsoleCommandSortExpense;
import seedu.moneygowhere.common.Messages;
import seedu.moneygowhere.exceptions.data.expense.ExpenseManagerExpenseNotFoundException;
import seedu.moneygowhere.parser.ConsoleParserConfigurations;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;

//@@author xzynos

/**
 * Stores and manages a list of expenses.
 */
@SuppressWarnings({"FieldMayBeFinal", "unused"})
public class ExpenseManager {
    private ArrayList<Expense> expenses;
    private ConsoleCommandSortExpense sortCommandSetting;

    public ExpenseManager() {
        sortCommandSetting = new ConsoleCommandSortExpense(
                ConsoleParserConfigurations.COMMAND_SORT_EXPENSE_ARG_TYPE_VAL_ALPHABETICAL,
                ConsoleParserConfigurations.COMMAND_SORT_EXPENSE_ARG_ORDER_VAL_ASCENDING
        );
        expenses = new ArrayList<>() {
            @Override
            public boolean add(Expense newExpense) {
                super.add(newExpense);
                sortExpenses();
                return true;
            }
        };
    }

    //@@author xzynos
    public void addExpense(Expense expense) {
        expenses.add(expense);
        sortExpenses();
    }

    //@@author xzynos
    public boolean hasExpense(Expense expense) {
        return expenses.contains(expense);
    }

    //@@author xzynos
    public Expense getExpense(int expenseIndex) throws ExpenseManagerExpenseNotFoundException {
        try {
            return expenses.get(expenseIndex);
        } catch (IndexOutOfBoundsException exception) {
            throw new ExpenseManagerExpenseNotFoundException(Messages.EXPENSE_MANAGER_ERROR_EXPENSE_NOT_FOUND);
        }
    }

    //@@author xzynos
    public ArrayList<Expense> getExpenses() {
        return expenses;
    }

    //@@author LokQiJun
    public void setExpenses(ArrayList<Expense> savedExpenses) {
        this.expenses = new ArrayList<>(savedExpenses);
    }

    //@@author LokQiJun
    public void updateExpenses(ArrayList<Expense> newExpenses) {
        for (Expense newExpense : newExpenses) {
            if (!hasExpense(newExpense)) {
                this.expenses.add(newExpense);
            }
        }
    }

    //@@author yuu-chennn
    public ArrayList<Expense> getExpensesByCategory(String categoryName) throws ExpenseManagerExpenseNotFoundException {
        ArrayList<Expense> expensesByCategory = new ArrayList<>();

        try {
            for (Expense expense : expenses) {
                String category = expense.getCategory();
                if (category != null && category.equalsIgnoreCase(categoryName)) {
                    expensesByCategory.add(expense);
                }
            }
        } catch (NullPointerException exception) {
            throw new ExpenseManagerExpenseNotFoundException(Messages.EXPENSE_MANAGER_ERROR_EXPENSE_NOT_FOUND);
        }

        return expensesByCategory;
    }

    //@@author yuu-chennn
    public ArrayList<Expense> getExpensesByName(String expenseName) throws ExpenseManagerExpenseNotFoundException {
        ArrayList<Expense> expensesByName = new ArrayList<>();

        try {
            for (Expense expense : expenses) {
                String name = expense.getName();
                if (name.toLowerCase().contains(expenseName.toLowerCase())) {
                    expensesByName.add(expense);
                }
            }
        } catch (NullPointerException exception) {
            throw new ExpenseManagerExpenseNotFoundException(Messages.EXPENSE_MANAGER_ERROR_EXPENSE_NOT_FOUND);
        }

        return expensesByName;
    }

    //@@author xzynos
    public void deleteExpense(int expenseIndex)
            throws ExpenseManagerExpenseNotFoundException {
        try {
            expenses.remove(expenseIndex);
        } catch (IndexOutOfBoundsException exception) {
            throw new ExpenseManagerExpenseNotFoundException(Messages.EXPENSE_MANAGER_ERROR_EXPENSE_NOT_FOUND);
        }
    }

    //@@author xzynos
    public void editExpense(int expenseIndex, Expense expense)
            throws ExpenseManagerExpenseNotFoundException {
        try {
            expenses.set(expenseIndex, expense);
            sortExpenses();
        } catch (IndexOutOfBoundsException exception) {
            throw new ExpenseManagerExpenseNotFoundException(Messages.EXPENSE_MANAGER_ERROR_EXPENSE_NOT_FOUND);
        }
    }

    //@@author jeyvia
    public void sortExpenses() {
        Comparator<Expense> comparator = getComparator();
        expenses.sort(comparator);
    }

    //@@author jeyvia
    public void updateSortExpenses(ConsoleCommandSortExpense commandSortExpense) {
        String type = commandSortExpense.getType();
        String order = commandSortExpense.getOrder();
        sortCommandSetting = new ConsoleCommandSortExpense(type, order);
        sortExpenses();
    }

    //@@author jeyvia
    public ConsoleCommandSortExpense getSortCommandSetting() {
        return sortCommandSetting;
    }

    //@@author jeyvia

    /**
     * Function to get the comparator based on type of sorting.
     *
     * @return a comparator that corresponds to the type of sorting
     */
    public Comparator<Expense> getComparator() {
        Comparator<Expense> comparator = null;
        if (sortCommandSetting.getType().equalsIgnoreCase(
                ConsoleParserConfigurations.COMMAND_SORT_EXPENSE_ARG_TYPE_VAL_DATE)) {
            comparator = sortByDate;
        } else if (sortCommandSetting.getType().equalsIgnoreCase(
                ConsoleParserConfigurations.COMMAND_SORT_EXPENSE_ARG_TYPE_VAL_ALPHABETICAL)) {
            comparator = sortByAlphabet;
        } else if (sortCommandSetting.getType().equalsIgnoreCase(
                ConsoleParserConfigurations.COMMAND_SORT_EXPENSE_ARG_TYPE_VAL_AMOUNT)) {
            comparator = sortByAmount;
        } else if (sortCommandSetting.getType().equalsIgnoreCase(
                ConsoleParserConfigurations.COMMAND_SORT_EXPENSE_ARG_TYPE_VAL_CURRENCY)) {
            comparator = sortByCurrency;
        }
        return comparator;
    }

    //@@author jeyvia

    /**
     * Comparator sorts by date in ascending or descending order.
     */
    private Comparator<Expense> sortByDate = new Comparator<>() {
        @Override
        public int compare(Expense expense1, Expense expense2) {
            LocalDateTime expenseDate1 = expense1.getDateTime();
            LocalDateTime expenseDate2 = expense2.getDateTime();
            if (expenseDate1.equals(expenseDate2)) {
                return sortByAlphabet.compare(expense1, expense2);
            }
            if (sortCommandSetting.getOrderValue()) {
                return expenseDate1.compareTo(expenseDate2);
            }
            return expenseDate2.compareTo(expenseDate1);
        }
    };

    //@@author jeyvia

    /**
     * Comparator sorts from A to Z if ascending order, Z to A if descending order.
     */
    private Comparator<Expense> sortByAlphabet = new Comparator<>() {
        @Override
        public int compare(Expense expense1, Expense expense2) {
            String expenseName1 = expense1.getName();
            String expenseName2 = expense2.getName();
            if (sortCommandSetting.getOrderValue()) {
                return expenseName1.compareTo(expenseName2);
            }
            return expenseName2.compareTo(expenseName1);
        }
    };

    //@@author jeyvia

    /**
     * Comparator sorts by amount, in ascending/descending order.
     */
    private Comparator<Expense> sortByAmount = new Comparator<>() {
        @Override
        public int compare(Expense expense1, Expense expense2) {
            BigDecimal expenseAmount1 = expense1.getAmount();
            BigDecimal expenseAmount2 = expense2.getAmount();
            if (expenseAmount1.equals(expenseAmount2)) {
                return sortByAlphabet.compare(expense1, expense2);
            }
            if (sortCommandSetting.getOrderValue()) {
                return expenseAmount1.compareTo(expenseAmount2);
            }
            return expenseAmount2.compareTo(expenseAmount1);
        }
    };

    //@@author jeyvia

    /**
     * Comparator sorts currencies from A to Z if ascending order, Z to A if descending order.
     */
    private Comparator<Expense> sortByCurrency = new Comparator<>() {
        @Override
        public int compare(Expense expense1, Expense expense2) {
            String expenseCurrency1 = expense1.getCurrency();
            String expenseCurrency2 = expense2.getCurrency();
            if (expenseCurrency1.equalsIgnoreCase(expenseCurrency2)) {
                return sortByAmount.compare(expense1, expense2);
            }
            if (sortCommandSetting.getOrderValue()) {
                return expenseCurrency1.compareTo(expenseCurrency2);
            }
            return expenseCurrency2.compareTo(expenseCurrency1);
        }
    };
}
