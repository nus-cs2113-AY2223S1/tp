package seedu.ui;

import java.util.Scanner;

import seedu.commands.CommandResult;
import seedu.exception.ParkingException;

/**
 * Represents a user interface that handles input and output with the user.
 */
public class Ui {
    private static final String SEPARATOR_STRING = "===========================================";
    private static final String RED = "\u001b[31m";
    private static final String GREEN = "\u001b[32m";
    private static final String CLEAR = "\u001b[0m";
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

    /**
     * Print string to command line without new line.
     *
     * @param line String to print.
     */
    public static void print(String line) {
        System.out.print(line);
    }

    /**
     * Print string in GREEN colour.
     *
     * @param line String to print
     * @param isNewLine if true, print newline.
     */
    public static void printGreen(String line, boolean isNewLine) {
        if (isSupportAnsi()) {
            print(GREEN);
        }
        if (isNewLine) {
            println(line);
        } else {
            print(line);
        }
        if (isSupportAnsi()) {
            print(CLEAR);
        }
    }

    /**
     * Print string in RED colour.
     *
     * @param line String to print
     * @param isNewLine if true, print newline.
     */
    public static void printRed(String line, boolean isNewLine) {
        if (isSupportAnsi()) {
            print(RED);
        }
        if (isNewLine) {
            println(line);
        } else {
            print(line);
        }
        if (isSupportAnsi()) {
            print(CLEAR);
        }
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

    /**
     * Shows the result of a command execution to the user.
     * Includes additional formatting of the results of different commands.
     *
     * @param result Command result
     */
    public void printResult(CommandResult result) {
        println(result.showToUser);
    }

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
     * Show error message when saving data to file.
     */
    public void showSaveError() {
        printRed("Something went wrong when saving data.", true);
    }

    /**
     * Show error message when creating file is unsuccessful.
     */
    public void showCreateFileError() {
        printRed("Something wrong happened in file creation.", true);
    }

    /**
     * Show error message when invalid command is entered.
     */
    public void showInvalidCommandError() {
        printRed("Invalid command. Try again.", true);
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
        println("Load data sequence successful!");
    }

    /**
     * Show message when data is successfully updated
     */
    public void showUpdateDataSuccess() {
        println("Updated data successfully!");
    }

    public void showUpdateError() {
        println("Unable to update data!");
    }
    public void showApiKeySaved() {
        println("API key saved successfully and the latest data has been downloaded.");
    }

    public void showAuthError() {
        println("Unable to authenticate API Key!");
    }

    public void showFavouriteAddSuccess(String carparkId) {
        println(String.format("Added Carpark %s to favourites!", carparkId));
    }

    public void showUnfavouriteSuccess(String carparkId) {
        println(String.format("Removed Carpark %s from favourites!", carparkId));
    }

    public void showUpdateFavouriteError() {
        println("Could not update favourite list.");
    }

    /**
     * Changes the scanner for the Ui object. To be used for JUnit testing.
     *
     * @param in New Scanner object to be used.
     */
    public void changeScanner(Scanner in) {
        assert in != null : "Invalid Scanner object!";
        this.in = in;
    }
    public static String getSeparatorString() {
        return SEPARATOR_STRING;
    }
}
