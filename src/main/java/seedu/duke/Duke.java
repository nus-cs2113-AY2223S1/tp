package seedu.duke;

import java.util.Objects;
import java.util.Scanner;

public class Duke {
    private static final String EXIT_FLAG = "quit";

    public static void main(String[] args) {

        System.out.println("Hello from Timetabler!");

        Timetable timetable = new Timetable();
        Scanner in = new Scanner(System.in);
        String response;
        String input;

        System.out.println("Here is a list of things I can do, enter the appropriate command to continue!\n"
                + "1. add\n2. list\n3. delete\n4.quit\n");

        while (true) {
            input = in.nextLine();
            response = Parser.parseCommand(timetable, input);
            if (Objects.equals(response, EXIT_FLAG)) {
                break;
            }
            System.out.println(response);
        }

        System.out.println("See you again!");
    }
}
