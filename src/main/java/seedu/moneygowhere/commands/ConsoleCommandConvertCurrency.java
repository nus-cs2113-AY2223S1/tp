package seedu.moneygowhere.commands;

import java.math.BigDecimal;

//@@author jeyvia

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

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public BigDecimal getRate() {
        return rate;
    }

    public void setRate(BigDecimal rate) {
        this.rate = rate;
    }
}
