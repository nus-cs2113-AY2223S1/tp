package seedu.duke;

import java.util.Scanner;

public class Ui {

    private static boolean inputIsEmpty(String rawInput) {
        return rawInput.trim().isEmpty();
    }

    public static String readCommand() {
        Scanner input = new Scanner(System.in);

        String rawInput = input.nextLine();
        boolean isEmpty = inputIsEmpty(rawInput);
        while (isEmpty) {
            rawInput = input.nextLine();
            isEmpty = inputIsEmpty(rawInput);
        }

        return rawInput;
    }
}
