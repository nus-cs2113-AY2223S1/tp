package seedu.duke;

public class Ui {
    private static final String indentation = "    ";

    public static void showWelcomeMessage() {
        String logo = "\n"
                +
                "    _____                                              __  __                                         \n"
                +
                "   / ____|                                            |  \\/  |                                        \n"
                +
                "  | |     _   _  _ __  _ __  ___  _ __    ___  _   _  | \\  / |  __ _  _ __    __ _   __ _   ___  _ __ \n"
                +
                "  | |    | | | || '__|| '__|/ _ \\| '_ \\  / __|| | | | | |\\/| | / _` || '_ \\  / _` | / _` | / _ \\| '__|\n"
                +
                "  | |____| |_| || |   | |  |  __/| | | || (__ | |_| | | |  | || (_| || | | || (_| || (_| ||  __/| |   \n"
                +
                "   \\_____|\\__,_||_|   |_|   \\___||_| |_| \\___| \\__, | |_|  |_| \\__,_||_| |_| \\__,_| \\__, | \\___||_|   \n"
                +
                "                                                __/ |                                __/ |            \n"
                +
                "                                               |___/                                |___/             \n";
        showStandardOutput(indentation + "Welcome to " + logo);
    }

    public static void showExceptionMessage(String exceptionMessage) {
        showStandardOutput(indentation + exceptionMessage);
    }

    // Maybe be improved
    public static void showPromptInfo() {
        String promptInfo = "Would you like to register or login?";
        System.out.println(indentation + promptInfo);
        System.out.println();
    }

    public static void showRegisterInfo() {
        final String MESSAGE = "Nice, we will be creating you a new wallet right away, but first of all, \n"
                +
                indentation
                +
                "please first add your username and then a password for your wallet.";
        showStandardOutput(indentation + MESSAGE);
    }

    public static void showRegisterSuccessInfo() {
        final String MESSAGE = "Nice, a new wallet has been created for you, \n"
                +
                indentation
                +
                "You can now login to it";
        showStandardOutput(indentation + MESSAGE);
    }

    public static void showStandardOutput(String output) {
        printSplitLine();
        System.out.println();
        System.out.println(output);
        printSplitLine();
        System.out.println();
    }

    public static void usernameLogin() {
        final String MESSAGE =  "Please enter your username: ";
        showStandardOutput(MESSAGE);
    }

    public static void passwordLogin() {
        final String MESSAGE = "Please enter your password: ";
        showStandardOutput(MESSAGE);
    }

    public static void accountEntry() {
        final String MESSAGE = "Welcome to your account\nPlease enter any commands";
        showStandardOutput(MESSAGE);
    }
    
    private static void printSplitLine() {
        System.out.println(indentation + "____________________________________________________________");
    }
}
