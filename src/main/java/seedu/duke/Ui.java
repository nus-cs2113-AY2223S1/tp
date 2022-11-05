package seedu.duke;

import java.util.Scanner;
import java.util.logging.Logger;
import java.util.logging.Level;

public class Ui {
    private static Logger logger = Logger.getLogger("DukeLogger");
    final String exitTrigger = "bye";
    protected boolean isExit;

    /**
     * Prints greeting to user when starting program.
     */
    public void greetUser() {
        System.out.println("Welcome to your myReviews, your personal Movie Reviews bot!");
        logger.log(Level.INFO, "Greeted User");
    }

    /**
     * Retrieves user input.
     * @param userInputScanner scanner to collect input.
     * @return user input as a string.
     */
    public String getInput(Scanner userInputScanner) {
        String userInput = "";
        
        if (userInputScanner.hasNextLine()) {
            userInput = userInputScanner.nextLine();
        }

        if (userInput.equals(exitTrigger)) {
            isExit = true;
            logger.log(Level.INFO, "Exit command given");
        }
        
        return userInput;
    }

    //@@author naz019

    /**
     * Prints the specified input to user.
     * @param toPrint input to print.
     */
    public static void print(String toPrint) {
        System.out.println(toPrint);
    }

    //@@author indraneelrp
    /**
     * Prints exit greeting to user.
     */
    public void printExitGreeting() {
        System.out.println("\n---------------\nSee you again!");
    }
}
