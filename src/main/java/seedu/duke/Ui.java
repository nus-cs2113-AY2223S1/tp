package seedu.duke;

import java.util.Scanner;
import java.util.logging.Logger;
import java.util.logging.Level;

public class Ui {
    private final Logger logger = Logger.getLogger("UILog");
    final String exitTrigger = "bye";
    protected boolean isExit;
    
    public void greetUser() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Welcome to your Movie Reviews bot,\n" + logo);
        logger.log(Level.INFO, "Greeted User");
    }

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

    public static void print(String toPrint) {
        System.out.println(toPrint);
    }

    public void printExitGreeting() {
        System.out.println("\n---------------\nSee you again!");
    }
}
