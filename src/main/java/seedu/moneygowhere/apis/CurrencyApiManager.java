package seedu.moneygowhere.apis;

import seedu.moneygowhere.data.currency.CurrencyManager;

//@@author jeyvia
public class CurrencyApiManager {
    public void getCurrencyApi(CurrencyManager currencyManager) {
        CurrencyApi.getJson();
        CurrencyApi.loadFromFile(currencyManager);
    }
}
