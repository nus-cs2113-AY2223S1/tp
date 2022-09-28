package seedu.moneygowhere.userinterface;

import seedu.moneygowhere.common.Messages;

@SuppressWarnings("unused")
public class ConsoleInterface {
    public static void printLogo() {
        String logo = "";
        logo += "  __  __                         _____   __          ___                   \n";
        logo += " |  \\/  |                       / ____|  \\ \\        / / |                  \n";
        logo += " | \\  / | ___  _ __   ___ _   _| |  __  __\\ \\  /\\  / /| |__   ___ _ __ ___ \n";
        logo += " | |\\/| |/ _ \\| '_ \\ / _ \\ | | | | |_ |/ _ \\ \\/  \\/ / | '_ \\ / _ \\ '__/ _ \\\n";
        logo += " | |  | | (_) | | | |  __/ |_| | |__| | (_) \\  /\\  /  | | | |  __/ | |  __/\n";
        logo += " |_|  |_|\\___/|_| |_|\\___|\\__, |\\_____|\\___/ \\/  \\/   |_| |_|\\___|_|  \\___|\n";
        logo += "                           __/ |                                           \n";
        logo += "                          |___/                                            \n";

        System.out.println(logo);

    }

    public static void printBlankLine() {
        System.out.println();
    }

    public static void printGreetingMessage() {
        System.out.println(Messages.CONSOLE_MESSAGE_GREETING);
    }

    public static void printGoodbyeMessage() {
        System.out.println(Messages.CONSOLE_MESSAGE_GOODBYE);
    }

    public static void printInformationalMessage(String message) {
        System.out.println(message);
    }

    public static void printWarningMessage(String message) {
        System.out.println("WARN: " + message);
    }

    public static void printErrorMessage(String message) {
        System.out.println("ERROR: " + message);
    }

    public void run() {
        printBlankLine();
    }
}
