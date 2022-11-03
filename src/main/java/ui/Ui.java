package ui;

import java.util.Scanner;

public class Ui {

    private static final String LOGO = " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";
    private static final String LINE_DIVIDER = "____________________________________________________________";


    public Ui() {
    }

    public void showWelcome() {
        System.out.println("Hello from\n" + LOGO);
        System.out.println("I am the nurse helper!");
    }

    public String readCommand() {
        Scanner in = new Scanner(System.in);
        if (in.hasNextLine()) {
            return in.nextLine();
        }
        return "bye";
    }

    public static void showLine() {
        System.out.println(LINE_DIVIDER);
    }

    public void showError(String message) {
        if (message == null) {
            System.out.println("Sorry, unrecognized operation");
        } else {
            System.out.println(message);
        }
    }
}
