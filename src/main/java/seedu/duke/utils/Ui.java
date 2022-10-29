package seedu.duke.utils;

import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Scanner;

import org.apache.commons.lang3.StringUtils;

import seedu.duke.command.ByeCommand;

//@author CheahHaoYi-reused
//Reused from CheahHaoYi/ip Ui class with slight modification

/**
 * The User Interface class that deals with interaction with user.
 * To read input from the terminal and to display responses to the user.
 * Serves as the intermediaries between the user and the internal working of the programme (View in MVC model).
 */
public class Ui {

    public static final String LOGO = "                      " + System.lineSeparator()
            + "__ __ _____ _____ _____ _____" + System.lineSeparator()
            + "|  |  |  _  |     |     |     |" + System.lineSeparator()
            + "|_   _|     | | | |  |  | | | |" + System.lineSeparator()
            + "  |_| |__|__|_|_|_|_____|_|_|_|" + System.lineSeparator();

    public static final String MESSAGE_GREET = "Hello from" + System.lineSeparator()
            + LOGO + System.lineSeparator() + "How can I help you today?"
            + System.lineSeparator() + "Enter \"help\" to get started!";
    public static final String MESSAGE_BYE = "Bye bye, See you again";
    public static final String LINE_DIVIDER = StringUtils.repeat("-", 80);
    public static final String INDENT = StringUtils.repeat(" ", 6);

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

    public void clearUiBuffer() {
        uiBuffer.clear();
    }
    private String sanitizeInput(String input) {
        return input.replaceAll("[\\P{ASCII}]", "");
    }

    public String readNext() {
        try {
            return sanitizeInput(scanner.nextLine().trim());
        } catch (NoSuchElementException | IllegalStateException e) {
            return ByeCommand.COMMAND_WORD;
        }
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
     * @param hasIndent whether to indent a message to the right, false by default if not specified by user
     */
    public void addMessage(String message, boolean hasIndent) {
        if (hasIndent) {
            uiBuffer.add(INDENT + message);
        } else {
            uiBuffer.add(message);
        }
    }

    public void addMessage(String message) {
        addMessage(message, false);
    }

    public void addMessage(ArrayList<String> messages, boolean hasIndent) {
        for (String message: messages) {
            addMessage(message, hasIndent);
        }
    }

    public void addMessage(ArrayList<String> messages) {
        addMessage(messages, false);
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

    /**
     * To display a user prompt for the user to input information.
     *
     * @param semester the semester that the user is currently planning for.
     */
    public void displayUserPrompt(int semester) {
        String promptFormat = "Sem " + "[" + currentUserSemester(semester)
                + "]" + " >> ";
        System.out.print(promptFormat);
    }

    private String currentUserSemester(int semester) {
        assert semester >= 1 && semester <= 4 : "Invalid Semester given";
        switch (semester) {
        case (1):
            return "1";
        case (2):
            return "2";
        case (3):
            return "ST1";
        case (4):
            return "ST2";
        default:
            return "Unknown";
        }
    }
}
//@author
