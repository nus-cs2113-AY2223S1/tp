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

public class Currency {
    public static List<List<String>> getListOfAllCurrencies() throws IOException {

        List<List<String>> currencies = new ArrayList<>();
        Path path = Paths.get("src","main","data");

        //creates the directory
        try {
            Files.createDirectories(path);
        } catch (Exception e) {
            System.out.println(e);
        }

        try {
            currencies = readInCurrencies(path);
        } catch (FileNotFoundException e) {
            System.out.println("File not found for currencies, you messed up buddy");
        }

        return currencies;
    }

    public static List<List<String>> readInCurrencies(Path path) throws IOException {
        List<List<String>> existingUserNames = new ArrayList<>();
        File f = new File(path + "/currencies.txt"); // create a File for the given file path
        Scanner s = new Scanner(f); // create a Scanner using the File as the source

        while (s.hasNext()) {
            String line = s.nextLine();
            List<String> items = Arrays.asList(line.split(","));
            existingUserNames.add(items);
        }

        return existingUserNames;
    }

    public static void exchangeCommands() throws IOException {
        Ui.showCurrencyEntry();
        List<List<String>> allCurrencies = Currency.getListOfAllCurrencies();
        boolean isCurrencyExit = false;
        while(!isCurrencyExit){
            Ui.showCurrencyOptions();
            String in = InputManager.receiveInputLine().toLowerCase();
            String[] splitInput = in.split(" ");
            String command = splitInput[0];
            try{
                switch (command){
                    case "info":
                        try{
                            int index = findIndexOfCurrency(splitInput[1], allCurrencies);
                            System.out.println("Names: " + allCurrencies.get(index).get(0));
                            System.out.println("Exchange rate with USD: " + allCurrencies.get(index).get(1));
                        }
                        catch (IndexOutOfBoundsException e){
                            Ui.showIncorrectCurrencyInfo(command);
                        }
                        catch (FinanceException e) {
                            e.handleException();
                        }
                        break;
                    case "conversion":
                        try{
                            int index1 = findIndexOfCurrency(splitInput[1], allCurrencies);
                            int index2 = findIndexOfCurrency(splitInput[2], allCurrencies);
                            double rate1 = Double.parseDouble(allCurrencies.get(index1).get(1));
                            double rate2 = Double.parseDouble(allCurrencies.get(index2).get(1));
                            System.out.println("Exchange rate from " + splitInput[1] + " to " + splitInput[2] + " is " + rate2/rate1);
                        }
                        catch (IndexOutOfBoundsException e){
                            boolean isErrorOne = true;
                            try{
                                int index = findIndexOfCurrency(splitInput[1], allCurrencies);
                                double rate = Double.parseDouble(allCurrencies.get(index).get(1));
                                System.out.println("Exchange rate from " + "USD" + " to " + splitInput[1] + " is " + rate);
                            }
                            catch (IndexOutOfBoundsException f){
                                isErrorOne = false;
                                Ui.showIncorrectCurrencyConversion();
                            }
                            catch (FinanceException f){
                                f.handleException();
                            }
                            if(!isErrorOne) {
                                Ui.showIncorrectCurrencyConversion();
                            }
                        }
                        catch (FinanceException e){
                            e.handleException();
                        }
                    break;
                    case "exit":
                        isCurrencyExit = true;
                        break;
                    default:
                        throw new FinanceException(FinanceException.ExceptionCollection.COMMAND_TYPE_EXCEPTION);
                }
            }
            catch (Exception e){
                Ui.showIncorrectCurrencyEntry();
            } catch (FinanceException e) {
                e.handleException();
            }

        }
    }

    private static int findIndexOfCurrency(String s, List<List<String>> allCurrencies) throws FinanceException {
        for(int i = 0; i < allCurrencies.size(); i++){
            if(allCurrencies.get(i).get(0).contains(s)){
                return i;
            }
        }
        throw new FinanceException(FinanceException.ExceptionCollection.CURRENCY_NOT_FOUND);
    }
}
