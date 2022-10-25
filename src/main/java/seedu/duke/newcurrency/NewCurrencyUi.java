package seedu.duke.newcurrency;

import seedu.duke.BasicUi;

public class NewCurrencyUi {

    private static final String INDENTATION = "    ";

    public static void standardForm(String message) {
        System.out.println(INDENTATION + message);
    }

    public static void showNewCurrencyCommands() {
        final String MESSAGE = "OK, let's add you a new currency!";
        BasicUi.showStandardOutput(INDENTATION + MESSAGE);
    }

    public static void showEnterAbbrPrompt() {
        final String MESSAGE = "Please enter your new currency's abbreviation: ";
        standardForm(MESSAGE);
    }
    public static void showEnterCurrencyNamePrompt() {
        final String MESSAGE = "Please enter your new currency's full name: ";
        standardForm(MESSAGE);
    }
    public static void showEnterCurrencySymbolPrompt() {
        final String MESSAGE = "Please enter your new currency's symbol: ";
        standardForm(MESSAGE);
    }
    public static void showEnterCurrencyRatePrompt() {
        final String MESSAGE = "Please enter your new currency's rate: ";
        standardForm(MESSAGE);
    }

    public static void showCurrencyRemovePrompt() {
        final String MESSAGE = "PS! Bear in mind that, only the currencies you yourself have added can be removed";

        BasicUi.showStandardOutput(INDENTATION + MESSAGE);
    }

    public static void showCurrencyRemoveQueryPrompt() {
        final String MESSAGE = "OK, which currency would you like to remove? Enter the abbreviation of that currency: ";
        standardForm(MESSAGE);
    }

    public static void showDeletedPersonalCurrency() {
        final String MESSAGE = "OK, we have removed your personal currency from the currencies list";
        standardForm(MESSAGE);
    }
}
