package seedu.moneygowhere.data.income;

import seedu.moneygowhere.common.Messages;
import seedu.moneygowhere.exceptions.data.income.IncomeManagerIncomeNotFoundException;
import seedu.moneygowhere.storage.LocalStorage;

import java.util.ArrayList;

//@@author penguin-s

/**
 * Stores and manages a list of incomes.
 */
@SuppressWarnings({"FieldMayBeFinal", "unused"})
public class IncomeManager {
    private ArrayList<Income> incomes;

    public IncomeManager() {
        incomes = new ArrayList<>();
    }

    public void addIncome(Income income, LocalStorage localStorage) {
        incomes.add(income);
        localStorage.setSavedIncomes(incomes);
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

    //@@author LokQiJun
    public void setIncomes(ArrayList<Income> savedIncomes) {
        this.incomes = new ArrayList<Income>(savedIncomes);
    }

    public void deleteIncome(int incomeIndex, LocalStorage localStorage)
            throws IncomeManagerIncomeNotFoundException {
        try {
            incomes.remove(incomeIndex);
            localStorage.setSavedIncomes(incomes);
        } catch (IndexOutOfBoundsException exception) {
            throw new IncomeManagerIncomeNotFoundException(Messages.INCOME_MANAGER_ERROR_INCOME_NOT_FOUND);
        }
    }

    public void editIncome(int incomeIndex, Income income, LocalStorage localStorage)
            throws IncomeManagerIncomeNotFoundException {
        try {
            incomes.set(incomeIndex, income);
            localStorage.setSavedIncomes(incomes);
        } catch (IndexOutOfBoundsException exception) {
            throw new IncomeManagerIncomeNotFoundException(Messages.INCOME_MANAGER_ERROR_INCOME_NOT_FOUND);
        }
    }

    public void updateIncomes(ArrayList<Income> incomes) {
        this.incomes = incomes;
    }
}
