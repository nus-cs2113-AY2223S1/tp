package seedu.duke.ui;

public class Ui {
    public static final String LINE = "_____________________________________________________________________________\n";
    static final String LOGO = "                        _____ ______ _____  \n"
            + "                       / ____|  ____|  __ \\ \n"
            + "   ___  __ _ ___ _   _| (___ | |__  | |__) |\n"
            + "  / _ \\/ _` / __| | | |\\___ \\|  __| |  ___/ \n"
            + " |  __/ (_| \\__ \\ |_| |____) | |____| |     \n"
            + "  \\___|\\__,_|___/\\__, |_____/|______|_|     \n"
            + "                  __/ |                     \n"
            + "                 |___/                      ";

    public static final String SPACING = "     ";

    /**
     * Greets the user with an introductory message when the program is first started.
     */
    public static void greetUser() {
        System.out.println(LOGO);
        String greeting = "Hello! Welcome to easySEP, your personal companion for planning your student exchange :-)\n"
                + "How may I help you today?\n"
                + LINE;
        System.out.print(greeting);
    }

    /**
     * Bids goodbye to the user when the user decides to terminate the program.
     */
    public static void sayByeToUser() {
        String message = LINE
                + "Goodbye. Hope to see you again soon!\n"
                + LINE;
        System.out.print(message);
    }

    public static void printCommands() {
        System.out.print(LINE);
        System.out.println(SPACING + "COMMAND   " + "FORMAT                                  " + "PURPOSE");
        System.out.println(SPACING + "--------  " + "--------------------------------------  " + "-------");
        System.out.println(SPACING + "create    " + "/create u/UNIVERSITY                    "
                + "Creates an empty module list for the input university");
        System.out.println(SPACING + "view      " + "/view ALL                               "
                + "Displays all existing university lists that have been created by the user");
        System.out.println(SPACING + "view      " + "/view u/UNIVERSITY                      "
                + "Displays all modules that have been added to the input university’s list in the format\n"
                + "[Home University Module Code] → [Partner University Module Code]");
        System.out.println(SPACING + "add       " + "/add u/UNIVERSITY m/MODULECODE          "
                + "Add input Partner University module code to input university list                       ");
        System.out.println(SPACING + "delete    " + "/delete u/UNIVERSITY m/MODULECODE       "
                + "Remove input Partner University module code from input university list                  ");
        System.out.println(SPACING + "delete    " + "/delete u/UNIVERSITY                    "
                + "Delete input university list                        ");
        System.out.println();
        System.out.println(SPACING + "Note: Words in UPPER_CASE are parameters that you should input as a user");
        System.out.print(LINE);
    }

    //to add in acknowledgement/response for user commands
}