package seedu.moneygowhere.data.expense;

import seedu.moneygowhere.commands.ConsoleCommandSortExpense;
import seedu.moneygowhere.common.Messages;
import seedu.moneygowhere.exceptions.ExpenseManagerExpenseNotFoundException;
import seedu.moneygowhere.parser.ConsoleParserConfigurations;

import java.util.ArrayList;
import java.util.Comparator;

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

    public void addExpense(Expense expense) {
        expenses.add(expense);
    }

    public Expense getExpense(int expenseIndex) throws ExpenseManagerExpenseNotFoundException {
        try {
            return expenses.get(expenseIndex);
        } catch (IndexOutOfBoundsException exception) {
            throw new ExpenseManagerExpenseNotFoundException(Messages.EXPENSE_MANAGER_ERROR_EXPENSE_NOT_FOUND);
        }
    }

    public ArrayList<Expense> getExpenses() {
        return expenses;
    }

    public ArrayList<Expense> getExpensesByCategory(String categoryName) {
        ArrayList<Expense> expensesByCategory = new ArrayList<>();

        for (Expense expense: expenses) {
            if (expense.getCategory().equals(categoryName)) {
                expensesByCategory.add(expense);
            }
        }

        return expensesByCategory;
    }

    public void deleteExpense(int expenseIndex) throws ExpenseManagerExpenseNotFoundException {
        try {
            expenses.remove(expenseIndex);
        } catch (IndexOutOfBoundsException exception) {
            throw new ExpenseManagerExpenseNotFoundException(Messages.EXPENSE_MANAGER_ERROR_EXPENSE_NOT_FOUND);
        }
    }

    public void editExpense(int expenseIndex, Expense expense) throws ExpenseManagerExpenseNotFoundException {
        try {
            expenses.set(expenseIndex, expense);
            sortExpenses();
        } catch (IndexOutOfBoundsException exception) {
            throw new ExpenseManagerExpenseNotFoundException(Messages.EXPENSE_MANAGER_ERROR_EXPENSE_NOT_FOUND);
        }
    }

    public void sortExpenses() {
        Comparator<Expense> comparator = sortCommandSetting.getComparator();
        expenses.sort(comparator);
    }

    public void updateSortExpenses(ConsoleCommandSortExpense commandSortExpense) {
        String type = commandSortExpense.getType();
        String order = commandSortExpense.getOrder();
        sortCommandSetting = new ConsoleCommandSortExpense(type, order);
        sortExpenses();
    }

    public ConsoleCommandSortExpense getSortCommandSetting() {
        return sortCommandSetting;
    }
}
