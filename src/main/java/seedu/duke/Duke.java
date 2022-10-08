package seedu.duke;

import java.util.Scanner;

public class Duke {

    static boolean isFinished = true;
    /**
     * Main entry-point for the java.duke.Duke application.
     */
    public static void main(String[] args) {
        welcomeMessage();

        Scanner sc = new Scanner(System.in);
        while (isFinished) {
            String command = sc.nextLine();
            if(readInput(command) == false) {
                break;
            }
        }
        goodbyeMessage();

    }

    public static boolean readInput(String command) {

        String[] splitCommand = command.split(" ");
        switch (splitCommand[0]) {
        case "bye":
            isFinished = false;
            return isFinished;
        case "flight_add":
            break;
        }

        return isFinished;
    }

    public static void welcomeMessage() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("What is your name?");

    }

    public static void goodbyeMessage() {
        System.out.println("Thank you, come again! :)\n");
    }
}
