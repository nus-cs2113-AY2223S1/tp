package seedu.duke;

import java.util.Scanner;

public class Ui {

    private static Scanner in = new Scanner(System.in);


    public void line() {
        System.out.println("------------------------------");
    }


    public String input() {
        return in.nextLine();
    }

    /**
     * Prints output to terminal.
     *
     * @param output Varargs output for printing
     */
    public void output(String... output) {
        for (String line : output) {
            System.out.println(line);
        }
    }
}
