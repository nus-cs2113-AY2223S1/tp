package seedu.moneygowhere.userinterface;

import seedu.moneygowhere.commands.ConsoleCommand;
import seedu.moneygowhere.commands.ConsoleCommandBye;
import seedu.moneygowhere.common.Messages;
import seedu.moneygowhere.exceptions.ConsoleParserCommandNotFoundException;
import seedu.moneygowhere.parser.ConsoleParser;

import java.util.Scanner;

/**
 * Provide functions to interface with the user via standard input and standard output.
 */
@SuppressWarnings({"unused", "FieldMayBeFinal"})
public class ConsoleInterface {
    private Scanner scanner;

    /**
     * Initializes the console interface.
     */
    public ConsoleInterface() {
        scanner = new Scanner(System.in);
    }

    /**
     * Prints the logo to standard out.
     */
    public static void printLogo() {
        String logo = "";
        logo += "  __  __                         _____   __          ___                   \n";
        logo += " |  \\/  |                       / ____|  \\ \\        / / |                  \n";
        logo += " | \\  / | ___  _ __   ___ _   _| |  __  __\\ \\  /\\  / /| |__   ___ _ __ ___ \n";
        logo += " | |\\/| |/ _ \\| '_ \\ / _ \\ | | | | |_ |/ _ \\ \\/  \\/ / | '_ \\ / _ \\ '__/ _ \\\n";
        logo += " | |  | | (_) | | | |  __/ |_| | |__| | (_) \\  /\\  /  | | | |  __/ | |  __/\n";
        logo += " |_|  |_|\\___/|_| |_|\\___|\\__, |\\_____|\\___/ \\/  \\/   |_| |_|\\___|_|  \\___|\n";
        logo += "                           __/ |                                           \n";
        logo += "                          |___/                                            \n";

        System.out.println(logo);

    }

    /**
     * Prints a blank like to standard out.
     */
    public static void printBlankLine() {
        System.out.println();
    }

    /**
     * Prints the greeting message to standard out.
     */
    public static void printGreetingMessage() {
        System.out.println(Messages.CONSOLE_MESSAGE_GREETING);
    }

    /**
     * Prints the goodbye message to standard out.
     */
    public static void printGoodbyeMessage() {
        System.out.println(Messages.CONSOLE_MESSAGE_GOODBYE);
    }

    /**
     * Prints an informational message to standard out.
     *
     * @param message Message to print.
     */
    public static void printInformationalMessage(String message) {
        System.out.println(message);
    }

    /**
     * Prints a warning message to standard out.
     *
     * @param message Message to print.
     */
    public static void printWarningMessage(String message) {
        System.out.println("WARN: " + message);
    }

    /**
     * Prints an error message to standard out.
     *
     * @param message Message to print.
     */
    public static void printErrorMessage(String message) {
        System.out.println("ERROR: " + message);
    }

    /**
     * Reads an input from standard input.
     *
     * @return Input read from standard input.
     */
    public String getConsoleInput() {
        return scanner.nextLine();
    }

    /**
     * Runs the command line interface which the user interacts with.
     */
    @SuppressWarnings("StatementWithEmptyBody")
    public void run() {
        printBlankLine();

        while (true) {
            String consoleInput = getConsoleInput();

            printBlankLine();

            ConsoleCommand consoleCommand = null;
            boolean hasParseError = true;

            try {
                consoleCommand = ConsoleParser.parse(consoleInput);
                hasParseError = false;
            } catch (ConsoleParserCommandNotFoundException e) {
                printErrorMessage(e.getMessage());
            }

            if (hasParseError) {
                // Do nothing if there is a parse error
            } else if (consoleCommand instanceof ConsoleCommandBye) {
                // Terminate the program
                return;
            } else {
                // Do nothing if the command is not found
            }

            printBlankLine();
        }
    }
}
