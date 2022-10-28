package seedu.duke;

import java.util.ArrayList;
import java.util.List;

import seedu.duke.exception.FinanceException;
import seedu.duke.exception.FinanceException.ExceptionCollection;

public class CurrencyList {
    public static List<CurrencyStructure> currencyList = new ArrayList<>();

    public static void initializeCurrencyList() throws FinanceException{
        currencyList = getListOfAllCurrencies();
    }

    protected static List<CurrencyStructure> getListOfAllCurrencies() throws FinanceException {
        List<CurrencyStructure> currencies = new ArrayList<>();
        currencies = readInCurrencies();
        return currencies;
    }

    protected static List<CurrencyStructure> readInCurrencies() throws FinanceException {
        List<CurrencyStructure> currencyList = new ArrayList<>();
        String[] currenciesArray = Currency2D.getArray();

        for (int i = 0; i < Currency2D.getEmptyIndex(); i++) {
            String line = currenciesArray[i];
            String[] items = line.split(",");
            String abbrName = items[0];
            String fullName = items[1];
            String symbol = items[2];
            double rate = Double.parseDouble(items[3]);
            CurrencyStructure currency = new CurrencyStructure(abbrName, fullName, symbol, rate);
            currencyList.add(currency);
        }
        return currencyList;
    }

    public static CurrencyStructure findCurrencyByAbbrName(String abbrName)
            throws FinanceException {
        for (CurrencyStructure currency : currencyList) {
            if (currency.isMatchedCurrencyByAbbrName(abbrName)) {
                return currency;
            }
        }
        throw new FinanceException(ExceptionCollection.CURRENCY_NAME_NOT_FOUND_EXCEPTION);
    }

    public static String getCurrencyFileLine(String abbrName, String fullName, String symbol, Double rate) {
        String newCurrency = abbrName + "," + fullName + "," + symbol + "," + rate;
        return newCurrency;
    }
}
