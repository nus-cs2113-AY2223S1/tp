package seedu.moneygowhere.data.currency;

import seedu.moneygowhere.common.Configurations;
import seedu.moneygowhere.common.Messages;
import seedu.moneygowhere.data.expense.Expense;
import seedu.moneygowhere.exceptions.CurrencyInvalidException;
import seedu.moneygowhere.exceptions.CurrencyRatesNotFoundException;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.HashMap;

/**
 * Stores and manages a HashMap of currencies.
 */
public class CurrencyManager {
    private HashMap<String, BigDecimal> exchangeRates;

    public CurrencyManager() {
        exchangeRates = new HashMap<>();
    }

    public void addCurrency(Currency currency) {
        String currencyCode = currency.getCurrencyCode();
        BigDecimal rate = currency.getRate();
        exchangeRates.put(currencyCode, rate);
    }

    public boolean hasCurrency(String currency) throws CurrencyInvalidException, CurrencyRatesNotFoundException {
        if (exchangeRates.containsKey(currency)) {
            return true;
        }
        if (exchangeRates.isEmpty()) {
            System.out.println("no way");
            throw new CurrencyRatesNotFoundException(Messages.CURRENCY_MANAGER_RATES_NOT_FOUND);
        }
        System.out.println("lolol");
        throw new CurrencyInvalidException(Messages.CURRENCY_MANAGER_CURRENCY_NOT_FOUND);
    }

    private BigDecimal getRate(String currency) {
        return exchangeRates.get(currency);
    }

    private BigDecimal convertToSgd(String currency, BigDecimal amount) {
        BigDecimal rate = getRate(currency);
        return (amount.divide(rate, Configurations.NUMBER_OF_DECIMAL_PLACES, RoundingMode.HALF_UP));
    }

    private BigDecimal convertToNewCurrency(BigDecimal amountInSgd, String newCurrency) {
        BigDecimal rate = getRate(newCurrency);
        return (amountInSgd.multiply(rate));
    }

    public BigDecimal exchangeCurrency(Expense expense, String newCurrency) {
        String oldCurrency = expense.getCurrency();
        BigDecimal amountInSgd = expense.getAmount();
        if (!(oldCurrency.equalsIgnoreCase(Configurations.CURRENCY_SINGAPORE_DOLLARS))) {
            amountInSgd = convertToSgd(oldCurrency, amountInSgd);
        }
        if (newCurrency.equalsIgnoreCase(Configurations.CURRENCY_SINGAPORE_DOLLARS)) {
            return amountInSgd;
        }
        BigDecimal amountInNewCurrency = convertToNewCurrency(amountInSgd, newCurrency);
        return amountInNewCurrency;
    }

    public BigDecimal exchangeCurrencyWithRate(Expense expense, BigDecimal rate) {
        BigDecimal amount = expense.getAmount();
        BigDecimal amountInNewCurrency = amount.multiply(rate);
        return amountInNewCurrency;
    }
}
