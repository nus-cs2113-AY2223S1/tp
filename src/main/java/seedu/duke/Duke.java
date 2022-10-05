package seedu.duke;

public class Duke {
    /**
     * Main entry-point for the java.duke.Duke application.
     */
    public static void main(String[] args) {
        Ui.printGreeting();
        while(true) {
            System.out.println("Please write your command below");
            String name = Ui.readInput();
            System.out.println("Hello " + name);
        }
    }
}
