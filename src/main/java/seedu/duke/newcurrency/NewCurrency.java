package seedu.duke.newcurrency;

import seedu.duke.Currency2D;
import seedu.duke.CurrencyList;
import seedu.duke.CurrencyStructure;
import seedu.duke.exception.FinanceException;

import java.io.IOException;

import static seedu.duke.CurrencyList.currencyList;
import static seedu.duke.newcurrency.PersonalCurrencyList.personalCurrencyList;
import static seedu.duke.InputManager.receiveInputLine;

public class NewCurrency {

    public static void addNewCurrency() throws IOException, FinanceException {
        try {
            String newCurrency = "";

            NewCurrencyUi.showNewCurrencyCommands();
            NewCurrencyUi.showEnterAbbrPrompt();
            String abbrName = receiveInputLine();
            if (currencyList.contains(abbrName)) {
                throw new FinanceException(FinanceException.ExceptionCollection.EXISITING_CURRENCY);
            }
            else {
                if (abbrName.length() == 3) {
                    NewCurrencyUi.showEnterCurrencyNamePrompt();
                    String fullName = receiveInputLine();

                    NewCurrencyUi.showEnterCurrencySymbolPrompt();
                    String symbol = receiveInputLine();

                    NewCurrencyUi.showEnterCurrencyRatePrompt();

                    Double rate = Double.valueOf(receiveInputLine());

                    if (rate > 0) {
                        //getting the string line
                        newCurrency = PersonalCurrencyList.getPersonalCurrencyFileLine(abbrName, fullName, symbol, rate);
                        int emptyIndex = Currency2D.getEmptyIndex();

                        Currency2D.addNewCurrency(emptyIndex, newCurrency);

                        //abbrName = "!" + abbrName;
                        //Adding to the list of currencies
                        CurrencyStructure currency = new CurrencyStructure(abbrName, fullName, symbol, rate);
                        currencyList.add(currency);
                        personalCurrencyList.add(currency);
                    }
                    else {
                        throw new FinanceException(FinanceException.ExceptionCollection.AMOUNT_NEGATIVE_EXCEPTION);
                    }

                } else {
                    throw new FinanceException(FinanceException.ExceptionCollection.CURRENCY_NAME_NOT_FOUND_EXCEPTION);
                }
            }
        } catch (NumberFormatException e) {
            System.out.println("Make sure that the rate is a double");
        }
    }

    public static void removeCurrency() throws FinanceException {
        NewCurrencyUi.showCurrencyRemovePrompt();
        NewCurrencyUi.showCurrencyRemoveQueryPrompt();
        String abbrName = receiveInputLine();
        CurrencyStructure currenctCurrency = CurrencyList.findCurrencyByAbbrName(abbrName);

        //only if the currency we are trying to remove is personal, can we remove it
        if (personalCurrencyList.contains(currenctCurrency)) {
            Currency2D.removeCurrency(currenctCurrency);
        } else {
            throw new FinanceException(FinanceException.ExceptionCollection.NOT_PERSONAL_CURRENCY);
        }
    }
}
