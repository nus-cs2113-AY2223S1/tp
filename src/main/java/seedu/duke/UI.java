package seedu.duke;

import java.util.Scanner;

public class UI {
    final String EXIT_TRIGGER = "bye";
    protected boolean isExit;
    
    public void greetUser() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Welcome to your Movie Reviews bot,\n" + logo);
    }

    public String getInput(Scanner userInputScanner) {
        String userInput = "";
        
        if (userInputScanner.hasNextLine()) {
            userInput = userInputScanner.nextLine();
        }

        if (userInput.equals(EXIT_TRIGGER)) {
            isExit = true;
        }
        
        return userInput;
    }

    public void printExitGreeting() {
        System.out.println("\n---------------\nSee you again!");
    }
}
