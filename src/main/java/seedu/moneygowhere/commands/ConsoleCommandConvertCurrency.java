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
        Float newAmount = currencyManager.exchangeCurrency(expense, currency);
        BigDecimal newAmountInBigDecimal = new BigDecimal(Float.toString(newAmount));
        expense.setAmount(newAmountInBigDecimal);
        expense.setCurrency(currency);
    }
}
