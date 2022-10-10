package seedu.duke.ui;

import java.util.ArrayList;
import seedu.duke.user.UserModule;
import seedu.duke.user.UserModuleList;
import seedu.duke.user.UserUniversityList;

import java.util.Scanner;

public class Ui {
    public static final String LINE = "_____________________________________________________________________________\n";
    public static final String LOGO = "                        _____ ______ _____  \n"
            + "                       / ____|  ____|  __ \\ \n"
            + "   ___  __ _ ___ _   _| (___ | |__  | |__) |\n"
            + "  / _ \\/ _` / __| | | |\\___ \\|  __| |  ___/ \n"
            + " |  __/ (_| \\__ \\ |_| |____) | |____| |     \n"
            + "  \\___|\\__,_|___/\\__, |_____/|______|_|     \n"
            + "                  __/ |                     \n"
            + "                 |___/                      \n";

    public static final String SPACING = "     ";

    private static final Scanner sc = new Scanner(System.in);

    /**
     * Greets the user with an introductory message when the program is first started.
     */
    public static String greetUser() {
        String greeting = LOGO
                + "Hello! Welcome to easySEP, your personal companion for planning your student exchange :-)\n"
                + "How may I help you today?\n"
                + LINE;
        return greeting;
    }

    /**
     * Reads the line of input that the user entered to the program.
     *
     * @return The full user input to the program, with leading and trailing whitespaces removed.
     */
    public static String getUserInput() {
        return sc.nextLine().strip();
    }

    /**
     * Bids goodbye to the user when the user decides to terminate the program.
     */
    public static String sayByeToUser() {
        String message = LINE
                + "Goodbye. Hope to see you again soon!\n"
                + LINE;
        return message;
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

    public static void printModule(UserModule module) {
        System.out.print("NUS: ");
        System.out.print(module.getNusCode() + " " + module.getNusTitle());
        System.out.print(" | Partner University: ");
        System.out.print(module.getPuCode() + " " + module.getPuTitle());
        System.out.println(" | Equivalent NUS Credits: " + module.getNusCredit() + " MCs");
    }

    /**
     * Prints an acknowledgement message to inform the user that they have successfully added a module to their list.
     * @param module The module added into the module list
     */
    public static void printModuleAddedAcknowledgement(UserModule module) {
        System.out.print(LINE);
        System.out.print("Success! You added:\n");
        printModule(module);
        System.out.print(LINE);
    }

    /**
     * Prints an acknowledgement message to inform the user that they successfully updated the module in their list.
     * @param module The module updated in the module list
     */
    public static void printModuleUpdatedAcknowledgement(UserModule module) {
        System.out.print(LINE);
        System.out.print("Success! You updated:\n");
        printModule(module);
        System.out.println("With the following comment: " + module.getComment());
        System.out.print(LINE);
    }

    /**
     * Prints an acknowledgement message to inform the user that they successfully deleted the module from their list.
     * @param module The module deleted from the module list
     */
    public static void printModuleDeletedAcknowledgement(UserModule module) {
        System.out.print(LINE);
        System.out.print("Success! You deleted:\n");
        printModule(module);
        System.out.print(LINE);
    }

    /**
     * Prints an acknowledgement message to inform the user that they have successfully created a list for the PU.
     * @param uniName The name of the PU
     */
    public static void printPuListCreatedAcknowledgement(String uniName) {
        System.out.print(LINE);
        System.out.println("Success! You have created a new list for" + uniName);
        System.out.print(LINE);
    }

    /**
     * Prints an acknowledgement message to inform the user that they have successfully deleted the list for the PU.
     * @param uniName The name of the PU
     */
    public static void printPuListDeletedAcknowledgement(String uniName) {
        System.out.print(LINE);
        System.out.println("Success! You deleted the list for" + uniName);
        System.out.print(LINE);
    }

    public static void printModulesInList(ArrayList<UserModule> modules) {
        System.out.print(LINE);
        for (int i = 0; i < modules.size(); i++) {
            System.out.print(i + 1);
            System.out.print(". ");
            printModule(modules.get(i));
        }
        System.out.print(LINE);
    }

    public static void printPuList(UserUniversityList puList) {
        UserModuleList puModulesList = puList.getMyModules();
        ArrayList<UserModule> puModules = puModulesList.getModules();
        printModulesInList(puModules);
    }
}