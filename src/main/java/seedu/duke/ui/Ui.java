package seedu.duke.ui;

import java.util.ArrayDeque;
import java.util.ArrayList;

import seedu.duke.module.ModuleMapping;
import seedu.duke.university.University;
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
                + SPACING + "exit      " + "/exit\n"
                + SPACING + "view      " + "/view MODULES                           "
                + "Displays all existing university modules mappings that are approved in the format\n"
                + "                                                       "
                + "[Partner University Module Code] [Partner University Module Title] "
                +  "[Partner University Module Credits] | [NUS Module Code] [NUS Module Title] "
                + "[NUS Module Credits] in NUS\n"
                + SPACING + "view      " + "/view LISTS                             "
                + "Displays all existing university lists that have been created by the user\n"
                + SPACING + "view      " + "/view u/UNIVERSITY                      "
                + "Displays all modules that have been added to the user's input university’s list in the format\n"
                + "                                                       "
                + "[Home University Module Code] [Home University Module Title] | "
                + "[Partner University Module Code] [Partner University Module Title] | [Equivalent NUS Credits]\n"
                + SPACING + "view      " + "/view DELETE HISTORY                    "
                + "Displays up to 5 most recent modules that the user has deleted\n"
                + SPACING + "view      " + "/view UNIVERSITIES                      "
                + "Displays all universities with module mappings available in database\n"
                + SPACING + "view      " + "/view DATABASE u/UNIVERSITY             "
                + "Displays all modules mappings offered by UNIVERSITY in database\n"
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

    /**
     * Displays to the user information regarding a module.
     * @return Formatted string for each module and its associated code, title, credit and PU information.
     */
    public static String printModule(UserModuleMapping module) {
        String message = "NUS: " + module.getNusCode() + " " + module.getNusTitle() + " | Partner University: "
            + module.getPuCode() + " " + module.getPuTitle() + " | Equivalent NUS Credits: " + module.getNusCredit()
                + " MCs";
        return message;
    }

    /**
     * Displays to the user information regarding a deleted module.
     * @return Formatted string for each module and its associated code, title, credit and PU information, incl PU name.
     */
    public static String printDeletedModule(UserModuleMapping module) {
        String puName = module.getPuName();
        String message = "NUS: " + module.getNusCode() + " " + module.getNusTitle() + " | " + puName + ": "
                + module.getPuCode() + " " + module.getPuTitle() + " | Equivalent NUS Credits: " + module.getNusCredit()
                + " MCs";
        return message;
    }

    /**
     * Prints an acknowledgement message to inform the user that they have successfully added a module to their list.
     * @param module The module added into the module list
     * @return Formatted string for the module added.
     */
    public static String printModuleAddedAcknowledgement(UserModuleMapping module) {
        String message = LINE + "Success! You added:\n" + printModule(module) + "\n" + LINE;
        return message;
    }

    /**
     * Prints an acknowledgement message to inform the user that they successfully updated the module in their list.
     * @param module The module updated in the module list
     * @return Formatted string for the module updated.
     */
    public static String printModuleUpdatedAcknowledgement(UserModuleMapping module) {
        String message = LINE + "Success! You updated:\n" + printModule(module) + "\n" + "With the following comment: "
                + module.getComment() + "\n" + LINE;
        return message;
    }

    /**
     * Prints an acknowledgement message to inform the user that they successfully deleted the module from their list.
     * @param module The module deleted from the module list
     * @return Formatted string for the module deleted.
     */
    public static String printModuleDeletedAcknowledgement(UserModuleMapping module) {
        String message = LINE + "Success! You deleted:\n" + printModule(module) + "\n" + LINE;
        return message;
    }

    /**
     * Prints an acknowledgement message to inform the user that they have successfully created a list for the PU.
     * @param uniName The name of the PU
     * @return Formatted string for the PU list created.
     */
    public static String printPuListCreatedAcknowledgement(String uniName) {
        String message = LINE + "Success! You have created a new list for " + uniName + "\n" + LINE;
        return message;
    }

    /**
     * Prints an acknowledgement message to inform the user that they have successfully deleted the list for the PU.
     * @param uniName The name of the PU
     * @return Formatted string for the PU list deleted.
     */
    public static String printPuListDeletedAcknowledgement(String uniName) {
        String message = LINE + "Success! You deleted the list for " + uniName + "\n" + LINE;
        return message;
    }

    /**
     * Sequentially prints each module stored in the list.
     * @param modules The list of modules to be printed.
     * @return Formatted string for the modules in the list.
     */
    public static String printModulesInUserList(ArrayList<UserModuleMapping> modules) {
        String message = LINE;
        for (int i = 0; i < modules.size(); i++) {
            message += Integer.toString(i + 1);
            message += ". ";
            message += printModule(modules.get(i));
            message += "\n";
        }
        message += LINE;
        return message;
    }

    /**
     * Sequentially prints each module stored in the deletedModules list.
     * @param deletedModules The list of deleted modules to be printed.
     * @return Formatted string for the deleted modules in the list.
     */
    public static String printDeletedModulesHistory(ArrayDeque<UserModuleMapping> deletedModules) {
        String message = LINE;
        int i = 1;
        for (UserModuleMapping module : deletedModules) {
            message += Integer.toString(i++);
            message += ". ";
            message += printDeletedModule(module);
            message += "\n";
        }
        message += LINE;
        return message;
    }

    public static String printPuList(UserUniversityList puList) {
        UserModuleMappingList puModulesList = puList.getMyModules();
        ArrayList<UserModuleMapping> puModules = puModulesList.getModules();
        return printModulesInUserList(puModules);
    }

    public static void printModulesInDatabase(ArrayList<ModuleMapping> modulesInDatabase) {
        for (ModuleMapping moduleMapping : modulesInDatabase) {
            System.out.println(moduleMapping.toString());
        }
    }

    public static void printUniversitiesInDatabase(ArrayList<University> universities) {
        int i = 1;
        for (University university : universities) {
            System.out.println(i + ". " + university.toString());
            i = i + 1;
        }
    }

    public static void printUniversityName(String universityName) {
        System.out.println(universityName);
        System.out.println(LINE);
    }
}