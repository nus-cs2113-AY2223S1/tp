package seedu.moneygowhere;

import seedu.moneygowhere.userinterface.ConsoleInterface;

//@@author xzynos

/**
 * Defines the entrypoint of the MoneyGoWhere application.
 */
public class MoneyGoWhere {
    /**
     * Initializes application and starts interaction with user.
     */
    public static void main(String[] args) {
        ConsoleInterface consoleInterface = new ConsoleInterface();

        ConsoleInterface.printLogo();

        ConsoleInterface.printGreetingMessage();

        consoleInterface.run();

        ConsoleInterface.printGoodbyeMessage();
    }
}
