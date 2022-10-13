package seedu.moneygowhere.data.income;

import seedu.moneygowhere.common.Messages;
import seedu.moneygowhere.exceptions.IncomeManagerIncomeNotFoundException;

import java.util.ArrayList;

/**
 * Stores and manages a list of incomes.
 */
@SuppressWarnings({"FieldMayBeFinal", "unused"})
public class IncomeManager {
    private ArrayList<Income> incomes;

    public IncomeManager() {
        incomes = new ArrayList<>();
    }

    public void addIncome(Income income) {
        incomes.add(income);
    }

    public Income getIncome(int incomeIndex) throws IncomeManagerIncomeNotFoundException {
        try {
            return incomes.get(incomeIndex);
        } catch (IndexOutOfBoundsException exception) {
            throw new IncomeManagerIncomeNotFoundException(Messages.INCOME_MANAGER_ERROR_INCOME_NOT_FOUND);
        }
    }

    public ArrayList<Income> getIncomes() {
        return new ArrayList<>(incomes);
    }

    public void deleteIncome(int incomeIndex) throws IncomeManagerIncomeNotFoundException {
        try {
            incomes.remove(incomeIndex);
        } catch (IndexOutOfBoundsException exception) {
            throw new IncomeManagerIncomeNotFoundException(Messages.INCOME_MANAGER_ERROR_INCOME_NOT_FOUND);
        }
    }

    public void editIncome(int incomeIndex, Income income) throws IncomeManagerIncomeNotFoundException {
        try {
            incomes.set(incomeIndex, income);
        } catch (IndexOutOfBoundsException exception) {
            throw new IncomeManagerIncomeNotFoundException(Messages.INCOME_MANAGER_ERROR_INCOME_NOT_FOUND);
        }
    }

    public void updateIncomes(ArrayList<Income> incomes) {
        this.incomes = incomes;
    }
}
