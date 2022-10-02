package seedu.duke;

public class Ui {

    public static void showGreeting() {
        System.out.println("____________________________________________________________");
        System.out.println("Hello! I'm your Moolah Manager!");
        System.out.println("Enter <help> if you need the list of commands");
        System.out.println("____________________________________________________________");
    }

    public static void showHelpList() { // To be updated.
        System.out.println("____________________________________________________________");
        System.out.println("Hello! I'm here to help");
        System.out.println("Here are the possible commands! ");
        System.out.println("");
        System.out.println("");
        System.out.println("");
        System.out.println("");
        System.out.println("");
        System.out.println("____________________________________________________________");
    }

    public static void showExitMessage() {
        System.out.println("____________________________________________________________");
        System.out.println("Good bye! See you soon!!");
        System.out.println("____________________________________________________________");
    }

    public static void showWrongCommand() {
        System.out.println("____________________________________________________________");
        System.out.println("You have entered the wrong command. ");
        System.out.println("Please enter <help> for the list of available commands");
        System.out.println("____________________________________________________________");
    }


}