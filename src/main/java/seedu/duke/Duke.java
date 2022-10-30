package seedu.duke;

import java.util.logging.Logger;
import java.io.IOException;
import java.util.Scanner;
import java.util.logging.ConsoleHandler;
import java.util.logging.FileHandler;
import java.util.logging.LogManager;
import java.util.logging.Level;

public class Duke {
    private Storage storage;
    private Ui ui;
    private Parser parser;
    private ReviewList reviewList;
    private String filepath = "./data/stored.txt";
    private String folderpath = "./data/";
    protected static Logger lg = Logger.getLogger("DukeLogger");
    
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
        setupLogger();
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

    private static void setupLogger() {

        LogManager.getLogManager().reset();
        lg.setLevel(Level.ALL);

        ConsoleHandler ch = new ConsoleHandler();
        ch.setLevel(Level.SEVERE);
        lg.addHandler(ch);
    
        try {
            FileHandler fh = new FileHandler("logger.log");
            fh.setLevel(Level.FINE);
            lg.addHandler(fh);
        } catch (IOException e) {
            lg.severe("File logger exception. logger.log file needed.");
        }
    }

    public static void main(String[] args) {
        new Duke().run();
    }
}
