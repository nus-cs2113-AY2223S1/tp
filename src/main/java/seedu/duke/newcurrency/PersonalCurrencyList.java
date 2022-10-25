package seedu.duke.newcurrency;

import seedu.duke.CurrencyStructure;
import seedu.duke.exception.FinanceException;

import java.io.File;
import java.io.FileNotFoundException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class PersonalCurrencyList {
    public static List<CurrencyStructure> personalCurrencyList = new ArrayList<>();

    public static void initializeCurrencyList() throws FinanceException {
        personalCurrencyList = getListOfAllCurrencies();
    }

    protected static List<CurrencyStructure> getListOfAllCurrencies() throws FinanceException {
        List<CurrencyStructure> currencies = new ArrayList<>();
        Path path = Paths.get("src", "main", "data");
        currencies = readInCurrencies(path);
        return currencies;
    }

    protected static List<CurrencyStructure> readInCurrencies(Path path) throws FinanceException {
        List<CurrencyStructure> personalCurrencyList = new ArrayList<>();
        Path filePath = Paths.get(path.toString(), "currencies.txt");
        File file = new File(filePath.toString());
        Scanner scanner;
        try {
            scanner = new Scanner(file,"UTF-8");
        } catch (FileNotFoundException e) {
            throw new FinanceException(FinanceException.ExceptionCollection.CURRENCY_FILE_NOT_FOUND_EXCEPTION);
        } // create a Scanner using the File as the source
        while (scanner.hasNext()) {
            String line = scanner.nextLine();
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
        scanner.close();
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
        String newCurrency = "!" + abbrName + "," + fullName + "," + symbol + "," + rate;
        return newCurrency;
    }
}
