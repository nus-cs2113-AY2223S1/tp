package seedu.ui;

import java.util.Scanner;

/**
 * Represents a user interface that handles input and output with the user.
 */
public class Ui {

    private final Scanner in;

    public Ui() {
        this.in = new Scanner(System.in);
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
     * Prints ASCII art of a car and parKING logo.
     */
    //@@author eehongchan-reused
    //Reused from https://www.asciiart.eu/vehicles/cars and http://patorjk.com/software/taag/
    // with minor modifications
    private void showLogo() {
        System.out.println(
            "    ____\n"
                + " __/  |_\\_\n"
                + "|  _     _``-.\n"
                + "'-(_)---(_)--'   _  _____ _  _  ___ \n"
                + "  _ __  __ _ _ _| |/ /_ _| \\| |/ __|\n"
                + " | '_ \\/ _` | '_| ' < | || .` | (_ |\n"
                + " | .__/\\__,_|_| |_|\\_\\___|_|\\_|\\___|\n"
                + " |_|                                ");
    }
    //@@author

    /**
     * Prompts user to enter name and prints welcome message.
     */
    private void askName() {
        System.out.println("What is your name?");
        String name = getLine();
        System.out.printf("Welcome to parKING, %s!\n", name);
    }

    /**
     * Prints ASCII art and logo, and asks user for their name.
     */
    public void greetUser() {
        showLogo();
        askName();
    }

    /**
     * Show error message when fetching data is unsuccessful.
     */
    public void showFetchError() {
        System.out.println("Something went wrong when fetching data.");
    }

    /**
     * Show error message when fetching data took too long.
     */
    public void showFetchTimeout() {
        System.out.println("Fetch Timeout, try again!");
    }

    /**
     * Show error message when saving data to file.
     */
    public void showSaveError() {
        System.out.println("Something went wrong when saving data.");
    }

    /**
     * Show error message when creating file is unsuccessful.
     */
    public void showCreateFileError() {
        System.out.println("Something wrong happened in file creation.");
    }
}
