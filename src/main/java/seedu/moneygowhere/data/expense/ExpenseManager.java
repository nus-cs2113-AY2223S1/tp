package seedu.moneygowhere.data.expense;

import seedu.moneygowhere.commands.ConsoleCommandSortExpense;
import seedu.moneygowhere.common.Messages;
import seedu.moneygowhere.exceptions.ExpenseManagerExpenseNotFoundException;
import seedu.moneygowhere.parser.ConsoleParserConfigurations;

import java.util.ArrayList;
import java.util.Objects;

/**
 * Stores and manages a list of expenses.
 */
@SuppressWarnings({"FieldMayBeFinal", "unused"})
public class ExpenseManager {
    private ArrayList<Expense> expenses;
    private ConsoleCommandSortExpense sortCommandSetting;

    public ExpenseManager() {
        sortCommandSetting = new ConsoleCommandSortExpense(
                ConsoleParserConfigurations.COMMAND_SORT_EXPENSE_ARG_TYPE_VAL_DATE,
                ConsoleParserConfigurations.COMMAND_SORT_EXPENSE_ARG_ORDER_VAL_DESCENDING
        );
        expenses = new ArrayList<>() {
            @Override
            public boolean add(Expense newExpense) {
                super.add(newExpense);
                expenses.sort(sortCommandSetting.getComparator());
                return true;
            }

            @Override
            public Expense set(int expenseIndex, Expense newExpense) {
                Expense oldValue = expenses.get(expenseIndex);
                expenses.set(expenseIndex, newExpense);
                expenses.sort(sortCommandSetting.getComparator());
                return oldValue;
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
        } catch (IndexOutOfBoundsException exception) {
            throw new ExpenseManagerExpenseNotFoundException(Messages.EXPENSE_MANAGER_ERROR_EXPENSE_NOT_FOUND);
        }
    }

    public void updateExpenses(ArrayList<Expense> expenses) {
        this.expenses = expenses;
    }

    public void updateSortExpenses(ConsoleCommandSortExpense commandSortExpense) {
        String type = commandSortExpense.getType();
        String order = commandSortExpense.getOrder();
        sortCommandSetting = new ConsoleCommandSortExpense(type, order);
    }

    public ConsoleCommandSortExpense getSortCommandSetting() {
        return sortCommandSetting;
    }
}
