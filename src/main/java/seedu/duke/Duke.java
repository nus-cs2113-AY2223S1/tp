package seedu.duke;

import java.util.Scanner;

public class Duke {

    public static void main(String[] args) {
        UI ui = new UI();
        Scanner in = new Scanner(System.in);
        System.out.println("Hello " + in.nextLine());
    }
}
