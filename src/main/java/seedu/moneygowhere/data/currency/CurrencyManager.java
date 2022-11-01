package seedu.moneygowhere.data.currency;

import seedu.moneygowhere.common.Configurations;
import seedu.moneygowhere.common.Messages;
import seedu.moneygowhere.data.expense.Expense;
import seedu.moneygowhere.exceptions.data.currency.CurrencyInvalidException;
import seedu.moneygowhere.exceptions.data.currency.CurrencyRatesNotFoundException;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.HashMap;

//@@author jeyvia

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
            throw new CurrencyRatesNotFoundException(Messages.CURRENCY_MANAGER_ERROR_RATES_NOT_FOUND);
        }
        throw new CurrencyInvalidException(Messages.CURRENCY_MANAGER_ERROR_CURRENCY_NOT_FOUND);
    }

    private BigDecimal getRate(String currency) {
        return exchangeRates.get(currency);
    }

    private BigDecimal convertToSgd(Expense expense) {
        String currency = expense.getCurrency();
        BigDecimal amount = expense.getAmount();
        BigDecimal rate;
        if (exchangeRates.containsKey(currency)) {
            rate = getRate(currency);
        } else {
            rate = expense.getRate();
        }
        return (amount.divide(
                rate,
                Configurations.CURRENCY_MANAGER_CONVERSION_NUMBER_OF_DECIMAL_PLACES,
                RoundingMode.HALF_UP
        ));
    }

    public BigDecimal exchangeCurrency(Expense expense, String currency, BigDecimal rate) {
        String oldCurrency = expense.getCurrency();
        BigDecimal amount = expense.getAmount();
        BigDecimal amountInSgd;
        if (!(oldCurrency.equalsIgnoreCase(Configurations.CURRENCY_MANAGER_CURRENCY_CODE_SINGAPORE_DOLLARS))) {
            amountInSgd = convertToSgd(expense);
        } else {
            amountInSgd = amount;
        }
        if (currency.equalsIgnoreCase(Configurations.CURRENCY_MANAGER_CURRENCY_CODE_SINGAPORE_DOLLARS)) {
            return amountInSgd;
        }
        BigDecimal amountInNewCurrency;
        if (rate == null) {
            BigDecimal newRate = getRate(currency);
            amountInNewCurrency = amount.multiply(newRate);
        } else {
            amountInNewCurrency = amount.multiply(rate);
            exchangeRates.put(currency, rate);
            expense.setRate(rate);
        }
        return amountInNewCurrency;
    }

    public void changeCurrency(Expense expense, String currency, BigDecimal rate) {
        BigDecimal newAmount = exchangeCurrency(expense, currency, rate);
        BigDecimal newAmountRounded = newAmount.setScale(
                Configurations.CURRENCY_MANAGER_CONVERSION_NUMBER_OF_DECIMAL_PLACES,
                RoundingMode.HALF_UP
        );
        expense.setAmount(newAmountRounded);
        expense.setCurrency(currency);
    }
}
