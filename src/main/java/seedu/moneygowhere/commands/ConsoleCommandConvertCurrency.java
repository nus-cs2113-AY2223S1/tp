package seedu.moneygowhere.commands;

import seedu.moneygowhere.data.currency.CurrencyManager;
import seedu.moneygowhere.data.expense.Expense;

import java.math.BigDecimal;

/**
 * Stores the command Convert-Currency and its arguments.
 */
@SuppressWarnings("FieldMayBeFinal")
public class ConsoleCommandConvertCurrency extends ConsoleCommand {
    private int expenseIndex;
    private String currency;
    private BigDecimal rate;

    public ConsoleCommandConvertCurrency(int expenseIndex, String currency, BigDecimal rate) {
        this.expenseIndex = expenseIndex;
        this.currency = currency;
        this.rate = rate;
    }

    public int getExpenseIndex() {
        return expenseIndex;
    }

    public String getCurrency() {
        return currency;
    }

    public BigDecimal getRate() {
        return rate;
    }

    public void changeCurrency(Expense expense, CurrencyManager currencyManager) {
        BigDecimal newAmount;
        if (rate == null) {
            newAmount = currencyManager.exchangeCurrency(expense, currency);
        } else {
            newAmount = currencyManager.exchangeCurrencyWithRate(expense, rate);
        }
        expense.setAmount(newAmount);
        expense.setCurrency(currency);
    }
}
