package seedu.duke.ui;

import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Map;

import seedu.duke.module.ModuleMapping;
import seedu.duke.timetable.Lesson;
import seedu.duke.university.University;
import seedu.duke.user.UserModuleMapping;
import seedu.duke.user.UserModuleMappingList;
import seedu.duke.user.UserUniversityList;

public class Ui {
    private static final String LINE = "____________________________________________________________________________\n";
    private static final String LOGO = "                        _____ ______ _____  \n"
            + "                       / ____|  ____|  __ \\ \n"
            + "   ___  __ _ ___ _   _| (___ | |__  | |__) |\n"
            + "  / _ \\/ _` / __| | | |\\___ \\|  __| |  ___/ \n"
            + " |  __/ (_| \\__ \\ |_| |____) | |____| |     \n"
            + "  \\___|\\__,_|___/\\__, |_____/|______|_|     \n"
            + "                  __/ |                     \n"
            + "                 |___/                      \n";

    private static final String SPACING = "     ";

    /**
     * Greets the user with an introductory message when the program is first
     * started.
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
                + SPACING + "view      " + "/view DELETE_HISTORY                                  "
                + "Displays up to 5 most recent modules that the user has deleted\n"
                + SPACING + "list      " + "/list MODULES                                         "
                + "Displays all existing university modules mappings that are approved in the format:\n"
                + "                                                                     "
                + "[Partner University Module Code] [Partner University Module Title] "
                + "[Partner University Module Credits] | [NUS Module Code] [NUS Module Title] "
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
                + module.getPuName() + " " + module.getPuCode() + " " + module.getPuTitle()
                + " | Equivalent NUS Credits: " + module.getNusCredit() + " MCs";
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
     * Prints an acknowledgement message to inform the user that they successfully updated information
     * for a chosen module in their list.
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
     * Prints an acknowledgement message to inform the user that they successfully created a list
     * for the chosen partner university.
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
     * Prints an acknowledgement message to inform the user that they successfully deleted the list
     * for the chosen partner university.
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
        message += "Your most recently deleted modules are:\n";
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
     *
     * @param universities The list of universities in the database.
     */
    public static void printUniversitiesInDatabase(ArrayList<University> universities) {
        assert universities.size() > 0 : "List of universities should not be empty";
        int i = 1;
        System.out.print(LINE);
        System.out.println("The universities available in the database are:");
        for (University university : universities) {
            System.out.println(i + ". " + university.toString());
            i++;
        }
        System.out.print(LINE);
    }

    /**
     * Sequentially prints eligible module mappings from a list.
     * The NUS module code and title as well as the equivalent partner university module code and title are printed.
     *
     * @param moduleMappings A list containing module mappings.
     */
    public static void printMappings(ArrayList<ModuleMapping> moduleMappings) {
        assert moduleMappings.size() > 0 : "List of module mappings should not be empty";
        System.out.print(LINE);
        int i = 1;
        System.out.println("The eligible module mappings are:");
        for (ModuleMapping moduleMapping : moduleMappings) {
            System.out.println(i + ". " + moduleMapping.toString());
            i++;
        }
        System.out.print(LINE);
    }

    /**
     * Prints all the lists that the user has favourited.
     * Lists are printed sequentially, starting with the university name and then
     * all modules in that university's list on a new line.
     *
     * @param userFavouriteLists A dictionary containing a user's favourited lists.
     */
    public static void printUserFavouriteLists(HashMap<String, UserUniversityList> userFavouriteLists) {
        assert userFavouriteLists.size() > 0 : "Dictionary of university name to favourite lists should not be empty";
        System.out.print(LINE);
        System.out.println("Your favourite lists are:");
        int i = 1;
        for (Map.Entry<String, UserUniversityList> set : userFavouriteLists.entrySet()) {
            String universityName = set.getKey();
            UserUniversityList universityList = set.getValue();
            if (universityList.isFavourite()) {
                System.out.println(i + ". " + universityName);
                universityList.displayModules();
                i++;
            }
        }
    }

    /**
     * Prints an acknowledgement message to inform the user that they successfully added a list to their favourites.
     *
     * @param universityName The name of the university for which the list is favourited by the user.
     * @return Acknowledgement message to user for adding a list to favourites.
     */
    public static String printFavouriteListAddedAcknowledgement(String universityName) {
        assert universityName.length() > 0 : "University name should not be empty";
        String message = LINE + "Success! You added:\n" + universityName + " to your favourite lists" + "\n" + LINE;
        return message;
    }

    /**
     * Prints an acknowledgement message to inform the user that they successfully deleted a list from their favourites.
     *
     * @param universityName The name of the university for which the list was favourited by the user.
     * @return Acknowledgement message to user for deleting a list from favourites.
     */
    public static String printFavouriteListDeletedAcknowledgement(String universityName) {
        assert universityName.length() > 0 : "University name should not be empty";
        String message = LINE + "Success! You deleted:\n" + universityName + " from your favourite lists" + "\n" + LINE;
        return message;
    }

    /**
     * Prints an acknowledgement message to inform the user that they successfully created a timetable for a university.
     *
     * @param universityName The name of the university for which the timetable was created by the user.
     * @return Acknowledgement message to user for creating a timetable for a university.
     */
    public static String printTimetableCreatedAcknowledgement(String universityName) {
        assert universityName.length() > 0 : "University name should not be empty";
        String message = LINE + "Success! You have created a new timetable for " + universityName + "\n" + LINE;
        return message;
    }

    /**
     * Prints an acknowledgement message to inform the user that they have
     * successfully deleted the timetable for a university.
     *
     * @param universityName The name of the university for which the timetable was deleted by the user.
     * @return Acknowledgement message to user for deleting the timetable for a university.
     */
    public static String printTimetableDeletedAcknowledgement(String universityName) {
        assert universityName.length() > 0 : "University name should not be empty";
        String message = LINE + "Success! You deleted the timetable for " + universityName + "\n" + LINE;
        return message;
    }

    /**
     * Displays to the user information regarding a lesson.
     *
     * @return Formatted string for a lesson and its associated module code, title, start time and end time.
     */
    public static String printLesson(Lesson lesson) {
        assert lesson.getCode().length() > 0 : "Module code length cannot be empty";
        assert lesson.getTitle().length() > 0 : "Module title length cannot be empty";
        assert lesson.getDay().length() > 0 : "Lesson day length cannot be empty";
        assert lesson.getStartTime().length() > 0 : "Lesson start time cannot be empty";
        assert lesson.getEndTime().length() > 0 : "Lesson end time cannot be empty";
        String message = lesson.getStartTime() + "hrs-" + lesson.getEndTime() + "hrs: "
                + lesson.getCode() + " " + lesson.getTitle();
        return message;
    }

    /**
     * Sequentially prints each lesson stored in the timetable's list based on the day.
     *
     * @param lessons The list of lessons to be printed.
     * @return Formatted string for the modules in the list.
     */
    public static String printLessonsByDayInTimetable(ArrayList<Lesson> lessons) {
        assert lessons.size() > 0 : "List of lessons should not be empty";
        String message = "";
        for (int i = 0; i < lessons.size(); i++) {
            message += Integer.toString(i + 1);
            message += ". ";
            message += printLesson(lessons.get(i));
            message += "\n";
        }
        return message;
    }

    /**
     * Prints an acknowledgement message to inform the user that they have
     * successfully added a lesson to the timetable for a university.
     *
     * @param lesson The name of the university for which the timetable was created by the user.
     * @return Acknowledgement message to user for adding a lesson to a university timetable.
     */
    public static String printLessonAddedAcknowledgement(Lesson lesson) {
        String universityName = lesson.getUniversity().getName();
        String day = lesson.getDay();
        day = day.substring(0,1).toUpperCase() + day.substring(1);
        String message = LINE + "Success! You have added a new lesson:\n" + universityName + " "
                + day + " " + printLesson(lesson) + "\n" + LINE;
        return message;
    }

    /**
     * Prints an acknowledgement message to inform the user that they have
     * successfully deleted a lesson from the timetable for a university.
     *
     * @param lesson The name of the university for which the timetable was deleted by the user.
     * @return Acknowledgement message to user for deleting a lesson from a university timetable.
     */
    public static String printLessonDeletedAcknowledgement(Lesson lesson) {
        String universityName = lesson.getUniversity().getName();
        String day = lesson.getDay();
        day = day.substring(0,1).toUpperCase() + day.substring(1);
        String message = LINE + "Success! You have deleted the lesson:\n" + universityName + " "
                + day + " " + printLesson(lesson) + "\n" + LINE;
        return message;
    }

    /**
     * Sequentially prints each day's lessons for a timetable.
     *
     * @param timetable The dictionary containing the lists of lessons to be printed, indexed by weekday.
     */
    public static void printTimetable(HashMap<String, ArrayList<Lesson>> timetable) {
        System.out.print(LINE);
        ArrayList<Lesson> mondayLessonList = timetable.get("monday");
        ArrayList<Lesson> tuesdayLessonList = timetable.get("tuesday");
        ArrayList<Lesson> wednesdayLessonList = timetable.get("wednesday");
        ArrayList<Lesson> thursdayLessonList = timetable.get("thursday");
        ArrayList<Lesson> fridayLessonList = timetable.get("friday");
        System.out.println("Monday:");
        System.out.print(Ui.printLessonsByDayInTimetable(mondayLessonList));
        System.out.println("Tuesday:");
        System.out.print(Ui.printLessonsByDayInTimetable(tuesdayLessonList));
        System.out.println("Wednesday:");
        System.out.print(Ui.printLessonsByDayInTimetable(wednesdayLessonList));
        System.out.println("Thursday:");
        System.out.print(Ui.printLessonsByDayInTimetable(thursdayLessonList));
        System.out.println("Friday:");
        System.out.print(Ui.printLessonsByDayInTimetable(fridayLessonList));
        System.out.print(LINE);
    }
}