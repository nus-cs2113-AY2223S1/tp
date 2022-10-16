package seedu.moneygowhere.data.currency;

import seedu.moneygowhere.common.Messages;
import seedu.moneygowhere.exceptions.CurrencyInvalidException;
import seedu.moneygowhere.exceptions.ExpenseManagerExpenseNotFoundException;

import java.util.HashMap;

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

    public HashMap<String, Float> getExchangeRates() {
        return exchangeRates;
    }

    public boolean hasCurrency(String currency) throws CurrencyInvalidException {
        if (exchangeRates.containsKey(currency)) {
            return true;
        }
        throw new CurrencyInvalidException(Messages.CURRENCY_MANAGER_CURRENCY_NOT_FOUND);
    }
}
