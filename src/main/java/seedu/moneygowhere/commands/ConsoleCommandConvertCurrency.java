package seedu.moneygowhere.commands;

import seedu.moneygowhere.data.currency.CurrencyManager;
import seedu.moneygowhere.data.expense.Expense;

import java.math.BigDecimal;

/**
 * Stores the Convert-Currency command and its arguments.
 */
@SuppressWarnings("FieldMayBeFinal")
public class ConsoleCommandConvertCurrency extends ConsoleCommand {
    private int expenseIndex;
    private String currency;

    public ConsoleCommandConvertCurrency(int expenseIndex, String currency) {
        this.expenseIndex = expenseIndex;
        this.currency = currency;
    }

    public int getExpenseIndex() {
        return expenseIndex;
    }

    public String getCurrency() {
        return currency;
    }

    public void changeCurrency(Expense expense, CurrencyManager currencyManager) {
        BigDecimal newAmount = currencyManager.exchangeCurrency(expense, currency);
        expense.setAmount(newAmount);
        expense.setCurrency(currency);
    }
}
