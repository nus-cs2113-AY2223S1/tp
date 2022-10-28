package seedu.duke;

import seedu.duke.data.Budget;

import java.util.Scanner;

import static seedu.duke.common.InfoMessages.INFO_CURRENT_BUDGET;
import static seedu.duke.common.InfoMessages.INFO_DIVIDER;
import static seedu.duke.common.InfoMessages.INFO_GREET;
import static seedu.duke.common.InfoMessages.INFO_HELP_GREET;
import static seedu.duke.common.InfoMessages.INFO_HELP_PROMPT;
import static seedu.duke.common.InfoMessages.INFO_BUDGET_SET_SUCCESSFUL;

/**
 * Displays information and error messages based on the user input and the behavior of the
 * application.
 */
public class Ui {
    //@@author chydarren
    private String input;
    private Scanner in;

    /**
     * Prints each message from a variable messages string line by line into the output stream.
     *
     * @param messages A string of variable length arguments.
     */
    public static void printMessages(String... messages) {
        System.out.println(INFO_DIVIDER);
        // Prints the string of arguments line by line in a loop
        for (String message : messages) {
            System.out.println(message);
        }
        System.out.println(INFO_DIVIDER);
    }

    //@@author paullowse

    /**
     * Initialises the variables of the Ui class.
     */
    public Ui() {
        in = new Scanner(System.in);
    }

    /**
     * Reads the command from the user.
     *
     * @return The command from the user.
     */
    public String readCommand() {
        input = in.nextLine();
        return input;
    }

    //@@author chydarren

    /**
     * Prepares the error message to be displayed to the user.
     *
     * @param message An error message when an exception is handled by the program.
     */
    public static void showErrorMessage(String message) {
        printMessages(message);
    }

    /**
     * Prepares the information message to be displayed to the user.
     *
     * @param message An information message that describes the functionality of the program.
     */
    public static void showInfoMessage(String message) {
        printMessages(message);
    }

    /**
     * Prepares the greeting messages to be displayed to the user.
     */
    public static void showGreeting() {
        printMessages(INFO_GREET.toString(), Budget.generateCurrentMonthBudgetRemainingMessage(),
                INFO_HELP_PROMPT.toString()
        );
    }

    //@@author chinhan99

    /**
     * Prepares the help messages to be displayed to the user.
     *
     * @param message A message that specifies the details of how to use the program.
     */
    public static void showHelp(String message) {
        printMessages(INFO_HELP_GREET.toString(), message);
    }

    //@@author chydarren

    /**
     * Prepares the list messages to be displayed to the user.
     *
     * @param list    A string containing the formatted list.
     * @param message A message that complements with the list.
     */
    public static void showList(String list, String message) {
        printMessages(message, list);
    }

    //@@author paullowse

    /**
     * Prepares the time insights list messages to be displayed to the user.
     *
     * @param list    A string containing the formatted time insights list.
     * @param message A message that complements with the time insights list.
     */
    public static void showTimeInsightsList(String list, String message, String incomeMessage,
                                            String expenseMessage, String savingsMessage) {
        printMessages(message, list, incomeMessage, expenseMessage, savingsMessage);
    }

    //@@author chydarren

    /**
     * Prepares the messages to be displayed to the user when add or delete has been performed on
     * the transaction list.
     *
     * @param message            A message that describes the functionality of the program.
     * @param transactionDetails Details of the action that has been performed on the transaction.
     * @param budgetInfo         A message that contains the monthly budget information.
     */
    public static void showTransactionAction(String message, String transactionDetails, String budgetInfo) {
        printMessages(message, transactionDetails, budgetInfo);
    }

    //@author wcwy

    /**
     * Prepares the messages to be displayed to the user when a new budget is set.
     *
     * @param currentBudget The string representation of the new budget set.
     */
    public static void showSetBudgetAcknowledgementMessage(String currentBudget) {
        printMessages(INFO_BUDGET_SET_SUCCESSFUL.toString(), INFO_CURRENT_BUDGET + currentBudget);
    }


}