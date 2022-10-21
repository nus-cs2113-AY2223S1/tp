package seedu.duke;

import java.util.Scanner;

public class Duke {
    private Storage storage;
    private Ui ui;
    private Parser parser;
    private ReviewList reviewList;
    private String filepath = "./data/stored.txt";
    private String folderpath = "./data/";
    
    public Duke() {
        //@@author indraneelrp
        // Creating the helper objects that the Duke instance will need
        ui = new Ui();
        reviewList = new ReviewList();
        parser = new Parser(reviewList);
        storage = new Storage(filepath, folderpath);
    }

    public void run() {
        //@@author indraneelrp
        ui.greetUser();
        storage.loadMedia(storage.getMakeFile(), reviewList);

        Scanner userInputScanner = new Scanner(System.in);         
        while (ui.isExit == false) {
            parser.processUserInput(ui.getInput(userInputScanner));
        }
        
        userInputScanner.close();
        storage.updateFile(reviewList);
        ui.printExitGreeting();
    }

    public static void main(String[] args) {
        new Duke().run();
    }
}
