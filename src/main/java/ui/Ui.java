package ui;

import java.util.Scanner;

public class Ui {

    public static void showWelcome(){
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("I am the nurse helper!");
    }

    public static String readCommand(){
        Scanner in = new Scanner(System.in);
        return in.nextLine();
    }

    public static void showLine(){
        System.out.println("____________________________________________________________");
    }

    public static void showError(String message) {
        if (message == null) {
            System.out.println("Sorry, unrecognized operation");
        } else {
            System.out.println(message);
        }
    }
}
