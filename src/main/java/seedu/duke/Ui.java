package seedu.duke;

import static seedu.duke.common.ErrorMessages.ERROR_INVALID_COMMAND;
import static seedu.duke.common.InfoMessages.INFO_DIVIDER;
import static seedu.duke.common.InfoMessages.INFO_EXIT;
import static seedu.duke.common.InfoMessages.INFO_GREET;
import static seedu.duke.common.InfoMessages.INFO_HELP_GREET;
import static seedu.duke.common.InfoMessages.INFO_HELP_PROMPT;

import java.util.Scanner;

public class Ui {
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

    public Ui() {
        in = new Scanner(System.in);
    }

    public String readCommand() {
        input = in.nextLine();
        return input.trim();
    }

    public static void showErrorMessage(String errorMessage) {
        printMessages(errorMessage);
    }

    public static void showInfoMessage(String infoMessage) {
        printMessages(infoMessage);
    }

    public static void showGreeting() {
        printMessages(INFO_GREET.toString(), INFO_HELP_PROMPT.toString());
    }

    public static void showHelp() {
        // To include the other messages for commands
        printMessages(INFO_HELP_GREET.toString());
    }

    public static void showExit() {
        printMessages(INFO_EXIT.toString());
    }

    public static void showInvalidCommand() {
        printMessages(ERROR_INVALID_COMMAND.toString(), INFO_HELP_PROMPT.toString());
    }

    public static void showTransactionAction(String infoMessage, String transactionDetails) {
        printMessages(infoMessage, transactionDetails);
    }

    public static void showTransactionsList(String transactionsList, String message) {
        printMessages(message, transactionsList);
    }
}