package seedu.duke.newcurrency;

import seedu.duke.CurrencyList;
import seedu.duke.CurrencyStructure;
import seedu.duke.exception.FinanceException;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import static seedu.duke.CurrencyList.currencyList;
import static seedu.duke.newcurrency.PersonalCurrencyList.personalCurrencyList;

public class NewCurrencyFileWorkings {

    final static Path FILE_PATH = Paths.get("src","main","data");
    public static void writeToCurrencies(String newCurrencyLine) throws IOException {
        Path filePath = Paths.get("src","main","data");
        File f = new File(filePath + "/currencies.txt");
        FileWriter fw = new FileWriter(f, true); // create a FileWriter in append mode
        fw.write(newCurrencyLine);
        fw.write(System.lineSeparator());
        fw.close();
    }

    public static void deleteFromCurrencies(CurrencyStructure currency) throws FinanceException {
        String currencyLine;
        CurrencyStructure currencyListCurrency = CurrencyList.findCurrencyByAbbrName(currency.getAbbrName());
        currencyList.remove(currencyListCurrency);
        personalCurrencyList.remove(currency);
        Path path = Paths.get(FILE_PATH.toString(), "currencies.txt");
        File f = new File(path.toString());
        try {
            FileWriter fileWriter = new FileWriter(f);
            for (CurrencyStructure curCurrency: currencyList){
                if (curCurrency.getAbbrName().charAt(0) == '!') {
                    currencyLine = PersonalCurrencyList.getPersonalCurrencyFileLine(curCurrency.getAbbrName(), curCurrency.getFullName(), curCurrency.getSymbol(), curCurrency.getRate());
                }
                else {
                    currencyLine = CurrencyList.getCurrencyFileLine(curCurrency.getAbbrName(), curCurrency.getFullName(), curCurrency.getSymbol(), curCurrency.getRate());
                }

                fileWriter.write(currencyLine);
                fileWriter.write(System.lineSeparator());
            }
            fileWriter.close();
        } catch (IOException e) {
            throw new FinanceException(FinanceException.ExceptionCollection.USERFILE_WRITE_EXCEPTION);
        }
    }
}
