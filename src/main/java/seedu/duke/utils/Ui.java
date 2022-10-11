package seedu.duke.utils;

import java.util.ArrayList;
import java.util.Scanner;

//@author CheahHaoYi-reused
//Reused from CheahHaoYi/ip Ui class with slight modification

/**
 * The User Interface class that deals with interaction with user.
 * To read input from the terminal and to display responses to the user.
 * Serves as the intermediaries between the user and the internal working of the programme (View in MVC model).
 */
public class Ui {

    private static final String LOGO = "                      \n"
            + "__ __ _____ _____ _____ _____\n"
            + "|  |  |  _  |     |     |     |\n"
            + "|_   _|     | | | |  |  | | | |\n"
            + "  |_| |__|__|_|_|_|_____|_|_|_|\n";


    private static final String MESSAGE_GREET = "Hello from" + System.lineSeparator()
            + LOGO + System.lineSeparator() + "How can I help you today?";
    private static final String MESSAGE_BYE = "Bye bye, See you again";
    private static final String LINE_DIVIDER = "--------------------------------------";

    private ArrayList<String> uiBuffer;
    private Scanner scanner;

    public Ui() {
        uiBuffer = new ArrayList<>();
        scanner = new Scanner(System.in);
    }

    public void close() {
        uiBuffer.clear();
        scanner.close();
    }

    public String readNext() {
        return scanner.nextLine().trim();
    }

    /**
     * Print out the content in the uiBuffer.
     * @param hasLineDivider if to print out a line divider.
     */
    public void displayUi(boolean hasLineDivider) {
        if (hasLineDivider) {
            displayDivider();
        }

        for (String line: uiBuffer) {
            System.out.println(line);
        }

        if (hasLineDivider) {
            displayDivider();
        }

        uiBuffer.clear();
    }

    public void displayUi() {
        //do not print line divider by default
        displayUi(false);
    }

    /**
     * To store String messages into a buffer.
     * @param message the message to add into the buffer.
     */
    public void addMessage(String message) {
        uiBuffer.add(message);
    }

    public void addMessage(ArrayList<String> messages) {
        for (String message: messages) {
            addMessage(message);
        }
    }

    /**
     * Display just one line of message.
     * Generally for the case of displaying just a single line of message.
     * @param message the message to be displayed.
     */
    public void displayMessage(String message) {
        System.out.println(message);
    }

    /**
     * Display the line divider.
     */
    public void displayDivider() {
        System.out.println(LINE_DIVIDER);
    }

    public void startMessage() {
        System.out.println(MESSAGE_GREET);
    }

    public void endMessage() {
        System.out.println(MESSAGE_BYE);
        displayDivider();
    }

}
//@author
