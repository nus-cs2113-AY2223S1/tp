package seedu.duke;

import java.util.Scanner;

public class Ui {
    private Scanner in;
    public Ui() {
        in =  new Scanner(System.in);
    }

    public void printOutput(String output) {
        System.out.println(output);
    }

    public String readNextLine() {
        return in.nextLine();
    }
}
