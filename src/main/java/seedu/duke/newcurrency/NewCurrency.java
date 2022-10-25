package seedu.duke.newcurrency;

import seedu.duke.CurrencyList;
import seedu.duke.CurrencyStructure;
import seedu.duke.exception.FinanceException;

import java.io.IOException;

import static seedu.duke.CurrencyList.currencyList;
import static seedu.duke.newcurrency.PersonalCurrencyList.personalCurrencyList;
import static seedu.duke.InputManager.receiveInputLine;

public class NewCurrency {

    //maybe add some extra thing in the end that shows that it is your own added currency so then everyone can have their own currencies with in the file

    public static void addNewCurrency() throws IOException {

        String newCurrency = "";

        NewCurrencyUi.showNewCurrencyCommands();
        NewCurrencyUi.showEnterAbbrPrompt();
        String abbrName = receiveInputLine();

        NewCurrencyUi.showEnterCurrencyNamePrompt();
        String fullName = receiveInputLine();

        NewCurrencyUi.showEnterCurrencySymbolPrompt();
        String symbol = receiveInputLine();

        NewCurrencyUi.showEnterCurrencyRatePrompt();
        Double rate = Double.valueOf(receiveInputLine());

        //writing to the file
        newCurrency = PersonalCurrencyList.getPersonalCurrencyFileLine(abbrName,fullName,symbol,rate);


        NewCurrencyFileWorkings.writeToCurrencies(newCurrency);

        //Adding to the list of currencies
        CurrencyStructure currency = new CurrencyStructure(abbrName, fullName, symbol, rate);
        currencyList.add(currency);
    }



    public static void removeCurrency() throws FinanceException {
        NewCurrencyUi.showCurrencyRemovePrompt();
        NewCurrencyUi.showCurrencyRemoveQueryPrompt();
        String abbrName = "!" + receiveInputLine();
        CurrencyStructure currenctCurrency = PersonalCurrencyList.findCurrencyByAbbrName(abbrName);

        //only if the currency we are trying to remove is personal, can we remove it
        if (personalCurrencyList.contains(currenctCurrency)) {
            NewCurrencyFileWorkings.deleteFromCurrencies(currenctCurrency);
        } else {
            throw new FinanceException(FinanceException.ExceptionCollection.NOT_PERSONAL_CURRENCY);
        }



        //if the currencies first letter is ! then can remove otherwise not
    }
}
