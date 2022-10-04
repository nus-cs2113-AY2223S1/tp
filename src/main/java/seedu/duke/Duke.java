package seedu.duke;

import java.util.Scanner;

import seedu.duke.command.Parser;
import seedu.duke.module.Module;
import seedu.duke.university.University;

public class Duke {
    /**
     * Main entry-point for the java.duke.Duke application.
     */
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("What is your name?");

        Storage.loadDatabase();
        // Scanner in = new Scanner(System.in);
        // System.out.println("Hello " + in.nextLine());
    }
}
