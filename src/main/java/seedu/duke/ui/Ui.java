package seedu.duke.ui;

import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Map;

import seedu.duke.module.ModuleMapping;
import seedu.duke.university.University;
import seedu.duke.user.UserModuleMapping;
import seedu.duke.user.UserModuleMappingList;
import seedu.duke.user.UserUniversityList;

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
     * @return The full user input to the program with leading and trailing whitespaces removed.
     */
    public static String getUserInput() {
        Scanner sc = new Scanner(System.in);
        return sc.nextLine().strip();
    }

    /**
     * Bids goodbye to the user when the user decides to terminate the program.
     */
    public static String sayByeToUser() {
        String goodbyeMessage = LINE
                + "Goodbye. Hope to see you again soon!\n"
                + LINE;
        return goodbyeMessage;
    }

    /**
     * Informs the user about valid commands that the program recognises and the purpose they serve.
     * Serves as a reminder for users to input commands in their corresponding eligible formats.
     *
     * @return Formatted string with instructions on the commands and their associated format and purpose.
     */
    public static String printCommands() {
        String message = LINE
                + SPACING + "COMMAND   " + "FORMAT                                                " + "PURPOSE\n"
                + SPACING + "--------  " + "--------------------------------------                " + "-------\n"
                + SPACING + "help      " + "/help                                                 "
                + "Displays eligible user commands for the program\n"
                + SPACING + "create    " + "/create u/UNIVERSITY_NAME_IN_UNDERSCORES              "
                + "Creates an empty module list for the input university\n"
                + SPACING + "favourite " + "/favourite add/UNIVERSITY_NAME_IN_UNDERSCORES         "
                + "Adds a university list to the user's favourites\n"
                + SPACING + "favourite " + "/favourite del/UNIVERSITY_NAME_IN_UNDERSCORES         "
                + "Deletes a university list from the user's favourites\n"
                + SPACING + "favourite " + "/favourite view/                                      "
                + "View the user's favourite university lists\n"
                + SPACING + "exit      " + "/exit                                                 "
                + "Terminate the program\n"
                + SPACING + "view      " + "/view LISTS                                           "
                + "Displays all existing university lists that have been created by the user\n"
                + SPACING + "view      " + "/view u/UNIVERSITY_NAME_IN_UNDERSCORES                "
                + "Displays all the modules that have been added to the user's input university's list in the format:\n"
                + "                                                                     "
                + "[Home University Module Code] [Home University Module Title] | "
                + "[Partner University Module Code] [Partner University Module Title] | [Equivalent NUS Credits]\n"
                + SPACING + "view      " + "/view DELETE HISTORY                                  "
                + "Displays up to 5 most recent modules that the user has deleted\n"
                + SPACING + "list      " + "/list MODULES                                         "
                + "Displays all existing university modules mappings that are approved in the format:\n"
                + "                                                                     "
                + "[Partner University Module Code] [Partner University Module Title] "
                + "[Partner University Module Credits] | [NUS Module Code] | [NUS Module Title] | "
                + "[NUS Module Credits] in NUS\n"
                + SPACING + "list      " + "/list UNIVERSITIES                                    "
                + "Displays all universities with module mappings available in database\n"
                + SPACING + "list      " + "/list m/MODULECODE                                    "
                + "List all module mappings for NUS MODULECODE in database\n"
                + SPACING + "list      " + "/list u/UNIVERSITY_NAME_IN_UNDERSCORES                "
                + "List all module mappings offered by UNIVERSITY in database\n"
                + SPACING + "add       " + "/add u/UNIVERSITY_NAME_IN_UNDERSCORES m/MODULECODE    "
                + "Add input Partner University module code to input university list\n"
                + SPACING + "delete    " + "/delete u/UNIVERSITY_NAME_IN_UNDERSCORES m/MODULECODE "
                + "Remove input Partner University module code from input university list\n"
                + SPACING + "delete    " + "/delete u/UNIVERSITY_NAME_IN_UNDERSCORES              "
                + "Delete input university list\n\n"
                + SPACING + "Note: Words in UPPER_CASE are parameters that you should input as a user\n"
                + SPACING + "Note: There should not be spaces in parameters, replace with underscore instead\n"
                + LINE;
        return message;
    }

    public static void printExceptionMessage(Exception e) {
        System.out.println(e.getMessage());
    }

    /**
     * Displays to the user information regarding a module.
     * 
     * @return Formatted string for a module and its associated NUS code, title, modular credits and PU information.
     */
    public static String printModule(UserModuleMapping module) {
        assert module.getPuCode().length() > 0 : "PU module code length cannot be empty";
        assert module.getPuTitle().length() > 0 : "PU module title length cannot be empty";
        assert module.getPuCredit().length() > 0 : "PU module credits length cannot be empty";
        assert module.getPuName().length() > 0 : "PU name length cannot be empty";
        assert module.getNusCode().length() > 0 : "NUS module code length cannot be empty";
        assert module.getNusTitle().length() > 0 : "NUS module title length cannot be empty";
        assert module.getNusCredit().length() > 0 : "NUS module credits length cannot be empty";
        String message = "NUS: " + module.getNusCode() + " " + module.getNusTitle() + " | Partner University: "
                + module.getPuCode() + " " + module.getPuTitle() + " | Equivalent NUS Credits: " + module.getNusCredit()
                + " MCs";
        return message;
    }

    /**
     * Prints an acknowledgement message to inform the user that they successfully added a module to their list.
     * 
     * @param module The module added into the module list
     * @return Formatted string for the module added.
     */
    public static String printModuleAddedAcknowledgement(UserModuleMapping module) {
        String message = LINE + "Success! You added:\n" + printModule(module) + "\n" + LINE;
        return message;
    }

    /**
     * Prints an acknowledgement message to inform the user that they successfully updated
     * information for a chosen module in their list.
     * 
     * @param module The module updated in the module list
     * @return Formatted string for the module updated.
     */
    public static String printModuleUpdatedAcknowledgement(UserModuleMapping module) {
        String message = LINE + "Success! You updated:\n" + printModule(module) + "\n" + "With the following comment: "
                + module.getComment() + "\n" + LINE;
        return message;
    }

    /**
     * Prints an acknowledgement message to inform the user that they successfully deleted
     * the chosen module from their list.
     * 
     * @param module The module deleted from the module list
     * @return Formatted string for the module deleted.
     */
    public static String printModuleDeletedAcknowledgement(UserModuleMapping module) {
        String message = LINE + "Success! You deleted:\n" + printModule(module) + "\n" + LINE;
        return message;
    }

    /**
     * Prints an acknowledgement message to inform the user that they successfully created a list for
     * the chosen partner university.
     * 
     * @param universityName The name of the partner university
     * @return Formatted string for the partner university list created.
     */
    public static String printPuListCreatedAcknowledgement(String universityName) {
        assert universityName.length() > 0 : "University name should not be empty";
        String message = LINE + "Success! You have created a new list for " + universityName + "\n" + LINE;
        return message;
    }

    /**
     * Prints an acknowledgement message to inform the user that they successfully deleted the list for
     * the chosen partner university.
     *
     * @param universityName The name of the partner university
     * @return Formatted string for the partner university list deleted.
     */
    public static String printPuListDeletedAcknowledgement(String universityName) {
        assert universityName.length() > 0 : "University name should not be empty";
        String message = LINE + "Success! You deleted the list for " + universityName + "\n" + LINE;
        return message;
    }

    /**
     * Sequentially prints each module stored in the list.
     * 
     * @param modules The list of modules to be printed.
     * @return Formatted string for the modules in the list.
     */
    public static String printModulesInUserList(ArrayList<UserModuleMapping> modules) {
        assert modules.size() > 0 : "List of module mappings should not be empty";
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
     * 
     * @param deletedModules The list of deleted modules to be printed.
     * @return Formatted string for the deleted modules in the list.
     */
    public static String printDeletedModulesHistory(ArrayDeque<UserModuleMapping> deletedModules) {
        assert deletedModules.size() > 0 : "List of module mappings should not be empty";
        String message = LINE;
        int i = 1;
        for (UserModuleMapping module : deletedModules) {
            message += Integer.toString(i++);
            message += ". ";
            message += printModule(module);
            message += "\n";
        }
        message += LINE;
        return message;
    }

    public static String printPuList(UserUniversityList puList) {
        UserModuleMappingList puModulesList = puList.getMyModules();
        ArrayList<UserModuleMapping> puModules = puModulesList.getModules();
        assert puModules.size() > 0 : "List of module mappings should not be empty";
        return printModulesInUserList(puModules);
    }


    /**
     * Prints the name of all universities available in the database.
     * @param universities The list of universities in the database.
     */
    public static void printUniversitiesInDatabase(ArrayList<University> universities) {
        assert universities.size() > 0 : "List of universities should not be empty";
        int i = 1;
        for (University university : universities) {
            System.out.println(i + ". " + university.toString());
            i++;
        }
    }

    /**
     * Sequentially prints eligible module mappings from a list.
     * The NUS module code and title as well as the equivalent partner university
     * module code and title are printed.
     * @param moduleMappings A list containing module mappings.
     */
    public static void printMappings(ArrayList<ModuleMapping> moduleMappings) {
        assert moduleMappings.size() > 0 : "List of module mappings should not be empty";
        for (ModuleMapping moduleMapping : moduleMappings) {
            System.out.println(moduleMapping.toString());
        }
    }

    /**
     * Prints all the lists that the user has favourited.
     * Lists are printed sequentially, starting with the university name
     * and then all modules in that university's list on a new line.
     * @param userFavouriteLists A dictionary containing a user's favourited lists.
     */
    public static void printUserFavouriteLists(HashMap<String, UserUniversityList> userFavouriteLists) {
        assert userFavouriteLists.size() > 0 : "Dictionary of university name to favourited lists should not be empty";
        for (Map.Entry<String, UserUniversityList> set : userFavouriteLists.entrySet()) {
            String universityName = set.getKey();
            UserUniversityList universityList = set.getValue();
            System.out.println(universityName);
            universityList.displayModules();
        }
    }

    /**
     * Prints an acknowledgement message to inform the user that they have
     * successfully added a list to their favourites.
     * @param universityName The name of the university for which the list is favourited by the user.
     * @return Acknowledgement message to user for adding a list to favourites.
     */
    public static String printFavouriteListAddedAcknowledgement(String universityName) {
        assert universityName.length() > 0 : "University name should not be empty";
        String message = LINE + "Success! You added:\n" + universityName + "to your favourited lists" + "\n" + LINE;
        return message;
    }

    /**
     * Prints an acknowledgement message to inform the user that they have
     * successfully deleted a list from their favourites.
     * @param universityName The name of the university for which the list was favourited by the user.
     * @return Acknowledgement message to user for deleting a list from favourites.
     */
    public static String printFavouriteListDeletedAcknowledgement(String universityName) {
        assert universityName.length() > 0 : "University name should not be empty";
        String message = LINE + "Success! You deleted:\n" + universityName + "from your favourited lists" + "\n" + LINE;
        return message;
    }
}