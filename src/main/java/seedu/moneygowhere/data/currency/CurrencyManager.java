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

    private BigDecimal convertToSgd(Expense expense) throws CurrencyInvalidException {
        try {
            String currency = expense.getCurrency();
            BigDecimal amount = expense.getAmount();
            BigDecimal rate;
            rate = getRate(currency);
            BigDecimal amountInSgd = amount.divide(
                    rate,
                    Configurations.CURRENCY_MANAGER_CONVERSION_NUMBER_OF_DECIMAL_PLACES,
                    RoundingMode.HALF_UP
            );
            return amountInSgd;
        } catch (NullPointerException exception) {
            throw new CurrencyInvalidException(Messages.CURRENCY_MANAGER_ERROR_CUSTOM_RATE_NOT_FOUND);
        }
    }

    public BigDecimal exchangeCurrency(Expense expense, String currency, BigDecimal rate)
            throws CurrencyInvalidException {
        BigDecimal amount = expense.getAmount();

        BigDecimal amountInNewCurrency;

        if (rate != null) {
            amountInNewCurrency = amount.multiply(rate);
        } else {
            String oldCurrency = expense.getCurrency();

            BigDecimal amountInSgd;

            if (!(oldCurrency.equalsIgnoreCase(Configurations.CURRENCY_MANAGER_CURRENCY_CODE_SINGAPORE_DOLLARS))) {
                amountInSgd = convertToSgd(expense);
            } else {
                amountInSgd = amount;
            }

            if (currency.equalsIgnoreCase(Configurations.CURRENCY_MANAGER_CURRENCY_CODE_SINGAPORE_DOLLARS)) {
                return amountInSgd;
            }

            BigDecimal newRate = getRate(currency);

            amountInNewCurrency = amountInSgd.multiply(newRate);
        }

        return amountInNewCurrency;
    }

    public void changeCurrency(Expense expense, String currency, BigDecimal rate) throws CurrencyInvalidException {
        BigDecimal newAmount = exchangeCurrency(expense, currency, rate);
        BigDecimal newAmountRounded = newAmount.setScale(
                Configurations.CURRENCY_MANAGER_CONVERSION_NUMBER_OF_DECIMAL_PLACES,
                RoundingMode.HALF_UP
        );
        BigDecimal newAmountStrippedTrailingZeros = newAmountRounded.stripTrailingZeros();
        expense.setAmount(newAmountStrippedTrailingZeros);
        expense.setCurrency(currency);
    }
}
