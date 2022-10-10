package seedu.duke;

public class UI {

    /**
     * Prints the set greeting
     */
    public void greetUser() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
    }

    /**
     * Prints the exit message
     */
    public void printExitGreeting() {
        System.out.println("\nSee you again!");
    }
}
