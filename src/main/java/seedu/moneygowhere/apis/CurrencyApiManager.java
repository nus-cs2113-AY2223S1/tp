package seedu.moneygowhere.apis;

import seedu.moneygowhere.data.currency.CurrencyManager;

public class CurrencyApiManager {
    public static void getCurrencyApi(CurrencyManager currencyManager) {
        CurrencyApi.getJson();
        CurrencyApi.loadFromFile(currencyManager);
    }
}
