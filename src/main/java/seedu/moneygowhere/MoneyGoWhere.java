package seedu.moneygowhere;

import seedu.moneygowhere.userinterface.ConsoleInterface;

public class MoneyGoWhere {
    /**
     * Main entry-point for the java.duke.Duke application.
     */
    public static void main(String[] args) {
        ConsoleInterface consoleInterface = new ConsoleInterface();

        ConsoleInterface.printLogo();

        ConsoleInterface.printGreetingMessage();

        consoleInterface.run();

        ConsoleInterface.printGoodbyeMessage();
    }
}
