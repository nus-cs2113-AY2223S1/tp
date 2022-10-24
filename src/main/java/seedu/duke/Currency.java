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
        Path path = Paths.get("src", "main", "data");

        // creates the directory
        /* 
        try {
            Files.createDirectories(path);
        } catch (Exception e) {
            System.out.println(e);
        }
        */
        currencies = readInCurrencies(path);
        return currencies;
    }

    public static List<CurrencyStructure> readInCurrencies(Path path) throws FinanceException {
        List<CurrencyStructure> currencyList = new ArrayList<>();
        //File f = new File(path + "/currencies.txt"); // create a File for the given file path
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
            // List<String> items = Arrays.asList(line.split(","));
            String[] items = line.split(", ");
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

    public static void exchangeCommands() throws FinanceException {
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
                        double rate1 = currency1.getRate();
                        double rate2 = currency2.getRate();
                        System.out.println("Exchange rate from " + splitInput[1] + " to " + splitInput[2] + " is "
                                + rate2 / rate1);
                    } catch (IndexOutOfBoundsException e) {
                        boolean isErrorOne = true;
                        try {
                            CurrencyStructure currency = findCurrencyByAbbrName(splitInput[1], allCurrencies);
                            double rate = currency.getRate();
                            System.out.println(
                                    "Exchange rate from " + "USD" + " to " + splitInput[1] + " is " + rate);
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
}
