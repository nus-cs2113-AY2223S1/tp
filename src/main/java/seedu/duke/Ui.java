package seedu.duke;

import seedu.duke.data.Budget;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

import static seedu.duke.common.DateFormats.DATE_OUTPUT_PATTERN;
import static seedu.duke.common.InfoMessages.*;

public class Ui {
    //@@author chydarren
    private String input;
    private Scanner in;

    /**
     * Prints each message from a variable messages string line by line into the output stream.
     *
     * @param messages A string of variable arguments.
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
        printMessages(INFO_GREET.toString(), INFO_CURRENT_BUDGET.toString() + Budget.getBudget(),
                INFO_TODAY_DATE + showDateOfTheDay(), INFO_HELP_PROMPT.toString()
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

    /**
     * Prepares the transaction list messages to be displayed to the user.
     *
     * @param list    A string containing the formatted transaction list.
     * @param message A message that complements with the transactions list.
     */
    public static void showList(String list, String message) {
        printMessages(message, list);
    }

    /**
     * Prepares the messages to be displayed to the user when add or delete has been performed on
     * the transaction list.
     *
     * @param message            A message that describes the functionality of the program.
     * @param transactionDetails Details of the action that has been performed on the transaction.
     */
    public static void showTransactionAction(String message, String transactionDetails) {
        printMessages(message, transactionDetails);
    }

    /**
     * Returns the today's date in MMM dd yyyy format.
     *
     * @return Formatted today's date
     */
    public static String showDateOfTheDay() {
        LocalDate todayDate = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATE_OUTPUT_PATTERN.toString());
        return todayDate.format(formatter);
    }
}