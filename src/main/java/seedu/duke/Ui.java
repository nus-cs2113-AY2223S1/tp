package seedu.duke;

import java.util.Scanner;

public class Ui {
    private final Scanner input;
    public Ui() {
        input = new Scanner(System.in);
    }
    String logo = " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";
    public String greeting = "Hello from\n" + logo;

    public void printGreeting() {
        System.out.print(greeting);
    }

    public static void showLine() {
        System.out.println("____________________________________________________________");
    }

    public static void printExitMessage() {
        showLine();
        System.out.println("Bye!");
        showLine();
    }

    public String readCommand() {
        return input.nextLine();
    }


}
