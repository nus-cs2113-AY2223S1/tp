package seedu.moneygowhere.data.expense;

import seedu.moneygowhere.common.Messages;
import seedu.moneygowhere.exceptions.ExpenseManagerExpenseNotFoundException;

import java.util.ArrayList;

/**
 * Stores and manages a list of expenses.
 */
@SuppressWarnings({"FieldMayBeFinal", "unused"})
public class ExpenseManager {
    private ArrayList<Expense> expenses;

    public ExpenseManager() {
        expenses = new ArrayList<>();
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
        return new ArrayList<>(expenses);
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
}
