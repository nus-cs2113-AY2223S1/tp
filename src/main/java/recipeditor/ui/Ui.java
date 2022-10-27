package recipeditor.ui;

import java.io.File;
import java.io.IOException;
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

    public static void showDivider() {
        showMessage(DIVIDER);
    }

    public static void showGreeting() {
        showMessage(LOGO, START, COMMAND);
    }

    public static void showExit() {
        showMessage(EXIT);
    }

    public static String readInput() {
        showPrompt();
        Scanner s = new Scanner(System.in);
        try {
            return s.nextLine();
        } catch (NoSuchElementException e) {
            return "";
        }
    }

    public static void showPrompt() {
        System.out.print(PROMPT);
    }

    public static void showMessageInline(String... messages) {
        for (String m : messages) {
            System.out.print(m + " ");
        }
        System.out.println();
    }

    public static void showMessage(String... messages) {
        for (String m : messages) {
            System.out.println(m);
        }
    }

    public static void showResult(CommandResult result) {
        showMessage(result.getMessage());
    }

    public static void clear() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    public static void printFilePath(File file, String filePath) {
        try {
            if (file.createNewFile()) {
                System.out.printf("File has been created at %s\n", filePath);
            } else {
                System.out.printf("File already exists at %s\n", filePath);
            }
        } catch (IOException e) {
            System.out.println("Error creating file: Could not create file at %s" + filePath);
        }
    }
}
