package seedu.duke;

import java.util.Scanner;

public class Ui {

    public static String readCommand() {
        Scanner input = new Scanner(System.in);

        return input.nextLine();
    }
}
