package seedu.ui;

import static org.fusesource.jansi.Ansi.Color.GREEN;
import static org.fusesource.jansi.Ansi.Color.RED;
import static org.fusesource.jansi.Ansi.ansi;

import java.util.Scanner;

import org.fusesource.jansi.Ansi;
import org.fusesource.jansi.AnsiConsole;

import seedu.commands.CommandResult;
import seedu.exception.ParkingException;

/**
 * Represents a user interface that handles input and output with the user.
 */
public class Ui {
    private static final String SEPARATOR_STRING = "===========================================";

    private Scanner in;
    public Ui() {
        this.in = new Scanner(System.in);
    }

    /**
     * Print string to command line with new line.
     *
     * @param line String to print.
     */
    public static void println(String line) {
        System.out.println(line);
    }
    public static void println(Ansi line) {
        System.out.println(line);
    }


    /**
     * Print string to command line without new line.
     *
     * @param line String to print.
     */
    public static void print(String line) {
        System.out.print(line);
    }

    public static void print(Ansi line) {
        System.out.print(line);
    }

    /**
     * Print string in GREEN colour.
     *
     * @param line String to print
     * @param isNewLine if true, print newline.
     */
    public static void printGreen(String line, boolean isNewLine) {
        AnsiConsole.systemInstall();
        if (isNewLine) {
            println(ansi().fg(GREEN).a(line).reset());
        } else {
            print(ansi().fg(GREEN).a(line).reset());
        }
        AnsiConsole.systemUninstall();
    }

    /**
     * Print string in RED colour.
     *
     * @param line String to print
     * @param isNewLine if true, print newline.
     */
    public static void printRed(String line, boolean isNewLine) {
        AnsiConsole.systemInstall();
        if (isNewLine) {
            println(ansi().fg(RED).a(line).reset());
        } else {
            print(ansi().fg(RED).a(line).reset());
        }
        AnsiConsole.systemUninstall();
    }

    /**
     * Print string to render.
     *
     * @param line String to print
     * @param isNewLine if true, print newline.
     */
    public static void printRenderedString(String line, boolean isNewLine) {
        AnsiConsole.systemInstall();
        if (isNewLine) {
            println(ansi().render(line));
        } else {
            print(ansi().render(line));
        }
        AnsiConsole.systemUninstall();
    }

    /**
     * Checks whether the system support ANSI encoding.
     *
     * @return true if it supports ANSI.
     */
    public static boolean isSupportAnsi() {
        String systemOS = System.getProperty("os.name").toLowerCase();
        if (systemOS.contains("mac")) {
            return true;
        }
        return false;
    }

    //@@author eehongchan
    /**
     * Asks for input and returns user input.
     *
     * @return User input.
     */
    public String getLine() {
        return in.nextLine();
    }

    /**
     * Asks user to enter a command and returns user input.
     *
     * @return User input.
     */
    public String getCommand() {
        print("\nEnter a command: ");
        return getLine();
    }

    /**
     * Prints ASCII art of a car and parKING logo.
     */
    //@@author eehongchan-reused
    //Reused from https://www.asciiart.eu/vehicles/cars and http://patorjk.com/software/taag/
    // with minor modifications
    private void showLogo() {
        println(
                "    ____\n"
                        + " __/  |_\\_\n"
                        + "|  _     _``-.\n"
                        + "'-(_)---(_)--'   _  _____ _  _  ___ \n"
                        + "  _ __  __ _ _ _| |/ /_ _| \\| |/ __|\n"
                        + " | '_ \\/ _` | '_| ' < | || .` | (_ |\n"
                        + " | .__/\\__,_|_| |_|\\_\\___|_|\\_|\\___|\n"
                        + " |_|                                ");
    }

    /**
     * Prints ASCII art and logo, and asks user for their name.
     */
    public void greetUser() {
        showLogo();
    }
    //@@author

    /**
     * Shows the result of a command execution to the user.
     * Includes additional formatting of the results of different commands.
     *
     * @param result Command result
     */
    public void printResult(CommandResult result) {
        switch (result.getStatus()) {
        case FAIL:
            printRed(result.showToUser, true);
            break;
        case SUCCESS:
            printGreen(result.showToUser, true);
            break;
        default:
            printRenderedString(result.showToUser, true);
            break;
        }
    }

    //@@author eehongchan
    /**
     * Print exception message.
     *
     * @param e {@link ParkingException} exception
     */
    public static void printError(ParkingException e) {
        printRed(e.getMessage(), true);
    }

    /**
     * Show error message when fetching data is unsuccessful.
     */
    public void showFetchError() {
        printRed("Something went wrong when fetching data, trying again...", true);
    }

    /**
     * Show error message when fetching data took too long.
     */
    public void showFetchTimeout() {
        printRed("Fetch Timeout, trying again...", true);
    }

    /**
     * Show error message when creating file is unsuccessful.
     */
    public void showCreateFileError() {
        printRed("Something wrong happened in file creation.", true);
    }

    /**
     * Show goodbye message before user quits program.
     */
    public void showByeMessage() {
        println("Goodbye.");
    }

    /**
     * Show message when loading data.
     */
    public void showLoadingDataMessage() {
        println("Trying to load data...");
    }

    /**
     * Show message when data is successfully loaded.
     */
    public void showLoadingDataSuccess() {
        printGreen("Load data sequence successful!", true);
    }

    /**
     * Changes the scanner for the Ui object. To be used for JUnit testing.
     *
     * @param in New Scanner object to be used.
     */
    public void changeScanner(Scanner in) {
        assert in != null : "Invalid Scanner object! Scanner object cannot be null!";
        this.in = in;
    }
    public static String getSeparatorString() {
        return SEPARATOR_STRING;
    }
}
