package seedu.duke;

import seedu.duke.common.ErrorMessages;
import seedu.duke.common.InfoMessages;

public class Ui {
    /**
     * Prints each message from a variable messages string line by line into the output stream.
     *
     * @param messages A string of variable arguments.
     */

    //@@author chydarren-reused
    // Reused from https://github.com/se-edu/addressbook-level2/blob/master/src/seedu/addressbook/ui/TextUi.java
    // with minor modifications
    public static void printMessages(String... messages) {
        System.out.println(InfoMessages.MESSAGE_INFO_DIVIDER.toString());
        // Prints the string of arguments line by line in a loop
        for (String message : messages) {
            System.out.println(message);
        }
        System.out.println(InfoMessages.MESSAGE_INFO_DIVIDER.toString());
    }
    //@@author

    public static void showGreeting() {
        printMessages(InfoMessages.MESSAGE_INFO_GREET.toString(), InfoMessages.MESSAGE_INFO_HELP_PROMPT.toString());
    }

    public static void showHelpList() {
        // To include the other messages for commands
        printMessages(InfoMessages.MESSAGE_INFO_HELP_GREET.toString());
    }

    public static void showTransactionsList(String transactionsList, String message) {
        printMessages(message, transactionsList);
    }

    public static void showExitMessage() {
        printMessages(InfoMessages.MESSAGE_INFO_EXIT.toString());
    }

    public static void showErrorMessage(String errorMessage) {
        printMessages(errorMessage);
    }


    public static void showNonNumericError() {
        printMessages("Non-Numeric input detected! Please enter a numerical amount!");
    }

    public static void showInfoMessage(String infoMessage) {
        printMessages(infoMessage);
    }

    public static void showInvalidCommand() {
        printMessages(ErrorMessages.MESSAGE_ERROR_INVALID_COMMAND.toString(),
                InfoMessages.MESSAGE_INFO_HELP_PROMPT.toString());
    }
}