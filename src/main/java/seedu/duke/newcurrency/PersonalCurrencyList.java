package seedu.duke.newcurrency;

import seedu.duke.Currency2D;
import seedu.duke.CurrencyStructure;
import seedu.duke.exception.FinanceException;

import java.io.File;
import java.io.FileNotFoundException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class PersonalCurrencyList {
    public static List<CurrencyStructure> personalCurrencyList = new ArrayList<>();

    public static void initializeCurrencyList() throws FinanceException {
        personalCurrencyList = getListOfAllCurrencies();
    }

    protected static List<CurrencyStructure> getListOfAllCurrencies() throws FinanceException {
        List<CurrencyStructure> currencies = new ArrayList<>();
        currencies = readInCurrencies();
        return currencies;
    }

    protected static List<CurrencyStructure> readInCurrencies() throws FinanceException {
        List<CurrencyStructure> personalCurrencyList = new ArrayList<>();
        String[] currenciesArray = Currency2D.getArray();

        for (int i = 0; i < Currency2D.getEmptyIndex(); i++) {
            String line = currenciesArray[i];
            String[] items = line.split(",");
            String abbrName = items[0];
            if (abbrName.charAt(0) == '!') {
                String fullName = items[1];
                String symbol = items[2];
                double rate = Double.parseDouble(items[3]);
                CurrencyStructure currency = new CurrencyStructure(abbrName, fullName, symbol, rate);

                personalCurrencyList.add(currency);
            }

        }
        return personalCurrencyList;
    }

    public static CurrencyStructure findCurrencyByAbbrName(String abbrName)
            throws FinanceException {
        for (CurrencyStructure currency : personalCurrencyList) {
            if (currency.isMatchedCurrencyByAbbrName(abbrName)) {
                return currency;
            }
        }
        throw new FinanceException(FinanceException.ExceptionCollection.CURRENCY_NAME_NOT_FOUND_EXCEPTION);
    }

    public static String getPersonalCurrencyFileLine(String abbrName, String fullName, String symbol, Double rate) {
        String newCurrency = abbrName + "," + fullName + "," + symbol + "," + rate;
        return newCurrency;
    }
}
