package recipeditor.ui;

import java.util.NoSuchElementException;
import java.util.Scanner;

import recipeditor.command.CommandResult;

public class Ui {

    public static final String DIVIDER = "____________________________________________________________";
    private static final String START = "Program starting!";
    public static final String COMMAND = "Available commands: /add, /list, /view, /edit, /find, /delete, /exit, /help";
    private static final String EXIT = "Program exiting";
    private static final String PROMPT = ">>> ";

    private static final String LOGO = ",---.          o     ,---.    |o|\n"
            + "|---',---.,---..,---.|--- ,---|.|--- ,---.,---.\n"
            + "|  \\ |---'|    ||   ||    |   |||    |   ||\n"
            + "`   ``---'`---'`|---'`---'`---'``---'`---'`\n"
            + "                |";

    /**
     * Prints divider (dash lines) on the console.
     */
    public static void showDivider() {
        showMessage(DIVIDER);
    }

    /**
     * Prints RecipEditor logo, starting message and list of available commands on the console.
     */
    public static void showGreeting() {
        showMessage(LOGO, START, COMMAND);
    }

    /**
     * Prints edit message on the console.
     */
    public static void showExit() {
        showMessage(EXIT);
    }

    /**
     * Reads user's input from console.
     */
    public static String readInput() {
        showPrompt();
        Scanner s = new Scanner(System.in);
        try {
            return s.nextLine();
        } catch (NoSuchElementException e) {
            return "";
        }
    }

    /**
     * Prints prompt on the console.
     */
    public static void showPrompt() {
        System.out.print(PROMPT);
    }

    /**
     * Prints messages in the same line on the console according to the input,
     * separated with space.
     */
    public static void showMessageInline(String... messages) {
        for (String m : messages) {
            System.out.print(m + " ");
        }
        System.out.println();
    }

    /**
     * Prints messages on the console according to the input,
     * each on a new line.
     */
    public static void showMessage(String... messages) {
        for (String m : messages) {
            System.out.println(m);
        }
    }

    /**
     * Prints messages passed in CommandResult.
     */
    public static void showResult(CommandResult result) {
        showMessage(result.getMessage());
    }

}
