package computercomponentchooser;

import java.util.Scanner;

/**
 * Handles interactions with the User.
 */
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

    private final Parser parser;

    private final EditParser editParser;

    /**
     * Initializes a new Ui object.
     *
     * @param parser The parser to be used.
     * @param editParser The editParser to be used.
     */
    public Ui(Parser parser, EditParser editParser) {
        this.parser = parser;
        this.editParser = editParser;
    }

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

    /**
     * Prints the welcome message.
     */
    void startSession() {

        Ui.printLogo();
        Ui.printLine();
        System.out.println("Hello! ComputerComponentChooser at your service!");
        System.out.println("What can I do for you today?");
        Ui.printLine();
    }

    /**
     * Prints the User Interface associated with the end of the program.
     */
    void endSession() {
        Ui.printLine();
        System.out.println("Bye. Hope to see you again soon!");
        Ui.printLine();
    }

    /**
     * Reads in user/stored input line by line.
     */
    public void readLine() {
        Scanner in = new Scanner(System.in);
        String line;
        do {
            line = in.nextLine();
            if (Parser.checkEdit(line)) {
                while (!EditParser.checkBack(line)) {
                    editParser.parse(line);
                    line = in.nextLine();
                }
            }
            parser.parse(line);
        } while (!Parser.checkBye(line));
    }
}
