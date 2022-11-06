package seedu.duke;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import seedu.duke.exception.FinanceException;
import seedu.duke.exception.FinanceException.ExceptionCollection;

public class Currency {
    public static List<CurrencyStructure> getListOfAllCurrencies() throws FinanceException {
        List<CurrencyStructure> currencies = new ArrayList<>();

        currencies = readInCurrencies();
        return currencies;
    }

    public static List<CurrencyStructure> readInCurrencies() throws FinanceException {
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

    public static void exchangeCommands(CurrencyStructure defaultCurrency) throws FinanceException {
        BasicUi.showCurrencyEntry();
        List<CurrencyStructure> allCurrencies = getListOfAllCurrencies();
        boolean isCurrencyExit = false;
        while (!isCurrencyExit) {
            BasicUi.showCurrencyOptions();
            String in = InputManager.receiveInputLine().toLowerCase();
            String[] splitInput = in.split(" ");
            String command = splitInput[0];
            try {
                switch (command) {
                case "info":
                    try {
                        CurrencyStructure currency = findCurrencyByAbbrName(splitInput[1], allCurrencies);
                        BasicUi.showCurrencyInfo(currency);
                    } catch (IndexOutOfBoundsException e) {
                        BasicUi.showIncorrectCurrencyInfo(command);
                    } catch (FinanceException e) {
                        e.handleException();
                    }
                    break;
                case "conversion":
                    try {
                        CurrencyStructure currency1 = findCurrencyByAbbrName(splitInput[1], allCurrencies);
                        CurrencyStructure currency2 = findCurrencyByAbbrName(splitInput[2], allCurrencies);
                        double rate = currency1.getRate()/currency2.getRate();
                        System.out.printf("Exchange rate from " + splitInput[1] + " to " + splitInput[2] + " is %.4f %n"
                                , rate);
                        System.out.println();
                    } catch (IndexOutOfBoundsException e) {
                        boolean isErrorOne = true;
                        try {
                            CurrencyStructure currency = findCurrencyByAbbrName(splitInput[1], allCurrencies);
                            double rate = currency.getRate()/defaultCurrency.getRate();
                            System.out.printf(
                                    "Exchange rate from " + defaultCurrency.getSymbol() + " to " + splitInput[1] + " is %.4f %n"
                                            , rate);
                            System.out.println();
                        } catch (IndexOutOfBoundsException f) {
                            isErrorOne = false;
                            BasicUi.showIncorrectCurrencyConversion();
                        } catch (FinanceException f) {
                            f.handleException();
                        }
                        if (!isErrorOne) {
                            BasicUi.showIncorrectCurrencyConversion();
                        }
                    } catch (FinanceException e) {
                        e.handleException();
                    }
                    break;
                case "exit":
                    isCurrencyExit = true;
                    break;
                case "list":
                    System.out.println("\t\t\tALL CURRENCIES");
                    for (CurrencyStructure x : allCurrencies){
                        BasicUi.showCurrencyInfo(x);
                    }
                    break;
                default:
                    throw new FinanceException(ExceptionCollection.COMMAND_TYPE_EXCEPTION);
                }
            } catch (Exception e) {
                BasicUi.showIncorrectCurrencyEntry();
            } catch (FinanceException e) {
                e.handleException();
            }
        }
    }

    public static CurrencyStructure findCurrencyByAbbrName(String abbrName, List<CurrencyStructure> allCurrencies)
            throws FinanceException {
        for (CurrencyStructure currency: allCurrencies){
            if (currency.isMatchedCurrencyByAbbrName(abbrName)) {
                return currency;
            }
        }
        throw new FinanceException(ExceptionCollection.CURRENCY_NAME_NOT_FOUND_EXCEPTION);
    }

    public static int findIndexOfCurrency(String s, List<List<String>> allCurrencies) throws FinanceException {
        for(int i = 0; i < allCurrencies.size(); i++){
            if(allCurrencies.get(i).get(0).contains(s)){
                return i;
            }
        }
        throw new FinanceException(FinanceException.ExceptionCollection.CURRENCY_NOT_FOUND);
    }
}
