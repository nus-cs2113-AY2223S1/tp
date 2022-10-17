package seedu.moneygowhere.data.currency;

import seedu.moneygowhere.common.Configurations;
import seedu.moneygowhere.common.Messages;
import seedu.moneygowhere.data.expense.Expense;
import seedu.moneygowhere.exceptions.CurrencyInvalidException;

import java.util.HashMap;

/**
 * Stores and manages a HashMap of currencies.
 */
public class CurrencyManager {
    private HashMap<String, Float> exchangeRates;

    public CurrencyManager() {
        exchangeRates = new HashMap<>();
    }

    public void addCurrency(Currency currency) {
        String currencyCode = currency.getCurrencyCode();
        Float rate = currency.getRate();
        exchangeRates.put(currencyCode, rate);
    }

    public boolean hasCurrency(String currency) throws CurrencyInvalidException {
        if (exchangeRates.containsKey(currency)) {
            return true;
        }
        throw new CurrencyInvalidException(Messages.CURRENCY_MANAGER_CURRENCY_NOT_FOUND);
    }

    private Float getRate(String currency) {
        return exchangeRates.get(currency);
    }

    private Float convertToSgd(String currency, Float amount) {
        Float rate = getRate(currency);
        return (amount / rate);
    }

    private Float convertToNewCurrency(Float amountInSgd, String newCurrency) {
        Float rate = getRate(newCurrency);
        return (amountInSgd * rate);
    }

    public Float exchangeCurrency(Expense expense, String newCurrency) {
        String oldCurrency = expense.getCurrency();
        Float amountInSgd = expense.getAmount().floatValue();
        if (!(oldCurrency.equals(Configurations.CURRENCY_SINGAPORE_DOLLARS))) {
            amountInSgd = convertToSgd(oldCurrency, amountInSgd);
        }
        if (newCurrency.equals(Configurations.CURRENCY_SINGAPORE_DOLLARS)) {
            return amountInSgd;
        }
        Float amountInNewCurrency = convertToNewCurrency(amountInSgd, newCurrency);
        return amountInNewCurrency;
    }
}
