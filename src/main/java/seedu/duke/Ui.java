package seedu.duke;

import static seedu.duke.common.InfoMessages.INFO_DIVIDER;
import static seedu.duke.common.InfoMessages.INFO_EXIT;
import static seedu.duke.common.InfoMessages.INFO_GREET;
import static seedu.duke.common.InfoMessages.INFO_HELP_GREET;
import static seedu.duke.common.InfoMessages.INFO_HELP_PROMPT;

import java.util.Scanner;

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
     * @param errorMessage An error message when an exception is handled by the program.
     */
    public static void showErrorMessage(String errorMessage) {
        printMessages(errorMessage);
    }

    /**
     * Prepares the information message to be displayed to the user.
     *
     * @param infoMessage An information message that describes the functionality of the program.
     */
    public static void showInfoMessage(String infoMessage) {
        printMessages(infoMessage);
    }

    /**
     * Prepares the greeting messages to be displayed to the user.
     */
    public static void showGreeting() {
        printMessages(INFO_GREET.toString(), INFO_HELP_PROMPT.toString());
    }

    //@@author chinhan99
    /**
     * Prepares the help messages to be displayed to the user.
     *
     * @param helpMessage   A help message that specifies the details of how to use the program.
     */
    public static void showHelp(String helpMessage) {
        printMessages(INFO_HELP_GREET.toString(), helpMessage);
    }

    //@@author chydarren
    /**
     * Prepares the exit message to be displayed to the user.
     */
    public static void showExit() {
        printMessages(INFO_EXIT.toString());
    }

    /**
     * Prepares the messages to be displayed to the user when add or delete has been performed on
     * the transaction list.
     *
     * @param infoMessage           An information message that describes the functionality of
     *                              the program.
     * @param transactionDetails    Details of the action that has been performed on the transaction.
     */
    public static void showTransactionAction(String infoMessage, String transactionDetails) {
        printMessages(infoMessage, transactionDetails);
    }

    /**
     * Prepares the transaction list messages to be displayed to the user.
     *
     * @param transactionsList A string containing the formatted transaction list.
     * @param listMessage      A list message that complements with the transactions list.
     */
    public static void showTransactionsList(String transactionsList, String listMessage) {
        printMessages(listMessage, transactionsList);
    }
}