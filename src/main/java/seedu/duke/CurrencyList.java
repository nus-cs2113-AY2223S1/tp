package seedu.duke;

import java.io.File;
import java.io.FileNotFoundException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import seedu.duke.exception.FinanceException;
import seedu.duke.exception.FinanceException.ExceptionCollection;

public class CurrencyList {
    protected static List<CurrencyStructure> currencyList = new ArrayList<>();

    public static void initializeCurrencyList() throws FinanceException{
        currencyList = getListOfAllCurrencies();
    }

    protected static List<CurrencyStructure> getListOfAllCurrencies() throws FinanceException {
        List<CurrencyStructure> currencies = new ArrayList<>();
        Path path = Paths.get("src", "main", "data");
        currencies = readInCurrencies(path);
        return currencies;
    }

    protected static List<CurrencyStructure> readInCurrencies(Path path) throws FinanceException {
        List<CurrencyStructure> currencyList = new ArrayList<>();
        Path filePath = Paths.get(path.toString(), "currencies.txt");
        File file = new File(filePath.toString());
        Scanner scanner;
        try {
            scanner = new Scanner(file,"UTF-8");
        } catch (FileNotFoundException e) {
            throw new FinanceException(ExceptionCollection.CURRENCY_FILE_NOT_FOUND_EXCEPTION);
        } // create a Scanner using the File as the source
        while (scanner.hasNext()) {
            String line = scanner.nextLine();
            String[] items = line.split(",");
            String abbrName = items[0];
            String fullName = items[1];
            String symbol = items[2];
            double rate = Double.parseDouble(items[3]);
            CurrencyStructure currency = new CurrencyStructure(abbrName, fullName, symbol, rate);
            currencyList.add(currency);
        }
        scanner.close();
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

}
