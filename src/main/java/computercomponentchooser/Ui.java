package computercomponentchooser;

public class Ui {
    /**
     * A concatenation of strings that visually represents the word ComputerComponentChooser.
     */
    private static final String LOGO =
              "  _____                     __         \n"
            + " / ___/__  __ _  ___  __ __/ /____ ____\n"
            + "/ /__/ _ \\/  ' \\/ _ \\/ // / __/ -_) __/\n"
            + "\\___/\\___/_/_/_/ .__/\\_,_/\\__/\\__/_/   \n"
            + "              /_/                      \n"
            + "  _____                                   __ \n"
            + " / ___/__  __ _  ___  ___  ___  ___ ___  / /_\n"
            + "/ /__/ _ \\/  ' \\/ _ \\/ _ \\/ _ \\/ -_) _ \\/ __/\n"
            + "\\___/\\___/_/_/_/ .__/\\___/_//_/\\__/_//_/\\__/ \n"
            + "              /_/                            \n"
            + "  _______                        \n"
            + " / ___/ /  ___  ___  ___ ___ ____\n"
            + "/ /__/ _ \\/ _ \\/ _ \\(_-</ -_) __/\n"
            + "\\___/_//_/\\___/\\___/___/\\__/_/   \n";
    /**
     * A string visually repressing a line.
     */
    private static final String LINEBREAK = "____________________________________________________________";

    /**
     * prints the LINEBREAK String.
     */
    static void printLine() {
        System.out.println(LINEBREAK);
    }

    /**
     * prints the LOGO string preceded by a Hello from greeting.
     */
    static void printLogo() {
        System.out.println("Hello from\n" + LOGO);
    }

    static void startSession() {
        //Ui.printLogo();
        Ui.printLine();
        System.out.println("Hello! ComputerComponentChooser at your service!");
        System.out.println("What can I do for you today?");
        Ui.printLine();
    }

    /**
     * Prints the User Interface associated with the end of the program.
     */
    static void endSession() {
        Ui.printLine();
        System.out.println("Bye. Hope to see you again soon!");
        Ui.printLine();
    }
}
