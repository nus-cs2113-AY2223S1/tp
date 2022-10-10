package seedu.duke;

import java.util.Scanner;

public class Duke {
    private Storage storage;
    private UI ui;
    private ReviewList reviewList;
    private String filepath = "data/reviews.txt";

    public static void main(String[] args) {
        UI ui = new UI();
        Scanner in = new Scanner(System.in);
        System.out.println("Hello " + in.nextLine());
    }
}
