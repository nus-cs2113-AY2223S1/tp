package seedu.duke.ui;

import java.util.ArrayList;
import seedu.duke.user.UserModuleMapping;
import seedu.duke.user.UserModuleMappingList;
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
        Scanner sc = new Scanner(System.in);
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

    /**
     * Informs the user on the valid commands that have to be passed into the program.
     * @return Formatted string with instructions on the commands and their associated format and purpose.
     */
    public static String printCommands() {
        String message = LINE
                + SPACING + "COMMAND   " + "FORMAT                                  " + "PURPOSE\n"
                + SPACING + "--------  " + "--------------------------------------  " + "-------\n"
                + SPACING + "create    " + "/create u/UNIVERSITY                    "
                + "Creates an empty module list for the input university\n"
                + SPACING + "view      " + "/view MODULES                           "
                + "Displays all existing university modules mappings that are approved in the format\n"
                + "                                                       "
                + "[NUS Module Code] [NUS Module Title] [NUS Module Credits] -> [Partner University Name] "
                + "[Partner University Module Code] [Partner University Title]\n"
                + SPACING + "view      " + "/view LISTS                             "
                + "Displays all existing university lists that have been created by the user\n"
                + SPACING + "view      " + "/view u/UNIVERSITY                      "
                + "Displays all modules that have been added to the input university’s list in the format\n"
                + "                                                       "
                + "[Home University Module Code] → [Partner University Module Code]\n"
                + SPACING + "add       " + "/add u/UNIVERSITY m/MODULECODE          "
                + "Add input Partner University module code to input university list                       \n"
                + SPACING + "delete    " + "/delete u/UNIVERSITY m/MODULECODE       "
                + "Remove input Partner University module code from input university list                  \n"
                + SPACING + "delete    " + "/delete u/UNIVERSITY                    "
                + "Delete input university list                        \n\n"
                + SPACING + "Note: Words in UPPER_CASE are parameters that you should input as a user\n"
                + SPACING + "Note: There should not be spaces in parameters, replace with underscore instead\n"
                + LINE;
        return message;
    }

<<<<<<< HEAD
    //to add in acknowledgement/response for user commands

    public static void printModule(UserModuleMapping module) {
        System.out.print("NUS: ");
        System.out.print(module.getNusCode() + " " + module.getNusTitle());
        System.out.print(" | Partner University: ");
        System.out.print(module.getPuCode() + " " + module.getPuTitle());
        System.out.println(" | Equivalent NUS Credits: " + module.getNusCredit() + " MCs");
=======
    /**
     * Displays to the user information regarding a module.
     * @return Formatted string for each module and its associated code, title, credit and PU information.
     */
    public static String printModule(UserModule module) {
        String message = "NUS: " + module.getNusCode() + " " + module.getNusTitle() + " | Partner University: "
            + module.getPuCode() + " " + module.getPuTitle() + " | Equivalent NUS Credits: " + module.getNusCredit()
                + " MCs";
        return message;
>>>>>>> 35aea1e241df9aa08fdbf42c8d40193942d28d33
    }

    /**
     * Prints an acknowledgement message to inform the user that they have successfully added a module to their list.
     * @param module The module added into the module list
     * @return Formatted string for the module added.
     */
<<<<<<< HEAD
    public static void printModuleAddedAcknowledgement(UserModuleMapping module) {
        System.out.print(LINE);
        System.out.print("Success! You added:\n");
        printModule(module);
        System.out.print(LINE);
=======
    public static String printModuleAddedAcknowledgement(UserModule module) {
        String message = LINE + "Success! You added:\n" + printModule(module) + "\n" + LINE;
        return message;
>>>>>>> 35aea1e241df9aa08fdbf42c8d40193942d28d33
    }

    /**
     * Prints an acknowledgement message to inform the user that they successfully updated the module in their list.
     * @param module The module updated in the module list
     * @return Formatted string for the module updated.
     */
<<<<<<< HEAD
    public static void printModuleUpdatedAcknowledgement(UserModuleMapping module) {
        System.out.print(LINE);
        System.out.print("Success! You updated:\n");
        printModule(module);
        System.out.println("With the following comment: " + module.getComment());
        System.out.print(LINE);
=======
    public static String printModuleUpdatedAcknowledgement(UserModule module) {
        String message = LINE + "Success! You updated:\n" + printModule(module) + "\n" + "With the following comment: "
                + module.getComment() + "\n" + LINE;
        return message;
>>>>>>> 35aea1e241df9aa08fdbf42c8d40193942d28d33
    }

    /**
     * Prints an acknowledgement message to inform the user that they successfully deleted the module from their list.
     * @param module The module deleted from the module list
     * @return Formatted string for the module deleted.
     */
<<<<<<< HEAD
    public static void printModuleDeletedAcknowledgement(UserModuleMapping module) {
        System.out.print(LINE);
        System.out.print("Success! You deleted:\n");
        printModule(module);
        System.out.print(LINE);
=======
    public static String printModuleDeletedAcknowledgement(UserModule module) {
        String message = LINE + "Success! You deleted:\n" + printModule(module) + "\n" + LINE;
        return message;
>>>>>>> 35aea1e241df9aa08fdbf42c8d40193942d28d33
    }

    /**
     * Prints an acknowledgement message to inform the user that they have successfully created a list for the PU.
     * @param uniName The name of the PU
     * @return Formatted string for the PU list created.
     */
    public static String printPuListCreatedAcknowledgement(String uniName) {
        String message = LINE + "Success! You have created a new list for" + uniName + "\n" + LINE;
        return message;
    }

    /**
     * Prints an acknowledgement message to inform the user that they have successfully deleted the list for the PU.
     * @param uniName The name of the PU
     * @return Formatted string for the PU list deleted.
     */
    public static String printPuListDeletedAcknowledgement(String uniName) {
        String message = LINE + "Success! You deleted the list for" + uniName + "\n" + LINE;
        return message;
    }

<<<<<<< HEAD
    public static void printModulesInList(ArrayList<UserModuleMapping> modules) {
        System.out.print(LINE);
=======
    /**
     * Sequentially prints each module stored in the list.
     * @param modules The list of modules to be printed.
     * @return Formatted string for the modules in the list.
     */
    public static String printModulesInList(ArrayList<UserModule> modules) {
        String message = LINE;
>>>>>>> 35aea1e241df9aa08fdbf42c8d40193942d28d33
        for (int i = 0; i < modules.size(); i++) {
            message += Integer.toString(i + 1);
            message += ". ";
            message += printModule(modules.get(i));
            message += "\n";
        }
        message += LINE;
        return message;
    }

<<<<<<< HEAD
    public static void printPuList(UserUniversityList puList) {
        UserModuleMappingList puModulesList = puList.getMyModules();
        ArrayList<UserModuleMapping> puModules = puModulesList.getModules();
        printModulesInList(puModules);
=======
    public static String printPuList(UserUniversityList puList) {
        UserModuleList puModulesList = puList.getMyModules();
        ArrayList<UserModule> puModules = puModulesList.getModules();
        return printModulesInList(puModules);
>>>>>>> 35aea1e241df9aa08fdbf42c8d40193942d28d33
    }
}