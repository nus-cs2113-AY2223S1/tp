package seedu.duke;

import seedu.duke.authentication.Authentication;
import seedu.duke.exception.FinanceException;

public class Duke {
    /**
     * Main entry-point for the java.duke.Duke application.
     */
    public static void main(String[] args) {
        BasicUi.showWelcomeMessage();
        initializeCurrencyList();
        boolean isProgramEnd = false;
        while (!isProgramEnd) {
            isProgramEnd = Authentication.handleAuthenticationRequest();
        }
    }

    private static void initializeCurrencyList(){
        try {
            CurrencyList.initializeCurrencyList();
        } catch (FinanceException e) {
            e.handleException();
        }
    }
}
