package seedu.duke;

import java.util.Scanner;

public class Duke {
    private Storage storage;
    //@@author naz019
    private Ui ui;
    private Parser parser;
    private ReviewList reviewList;
    private String filepath = "data/reviews.txt";
    
    public Duke() {
        // Creating the helper objects that the Duke instance will need
        //@@author naz019
        ui = new Ui();
        reviewList = new ReviewList();
        parser = new Parser(reviewList);
    }

    public void run() {
        ui.greetUser();
        Scanner userInputScanner = new Scanner(System.in);         
        
        while (ui.isExit == false) {
            parser.processUserInput(ui.getInput(userInputScanner));
        }
        
        userInputScanner.close();
        ui.printExitGreeting();
    }

    public static void main(String[] args) {
        new Duke().run();
    }
}
