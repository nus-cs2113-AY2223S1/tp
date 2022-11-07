package seedu.duke;

import java.util.Scanner;

/**
 * Class for user interaction and contains the Scanner object.
 */
public class UI {
    public static final Scanner sc = new Scanner(System.in);
    private static final String PRINTED_GAP = "     ";

    public static final String DOTTED_CHAR = ":";
    public static final String HORIZONTAL_BORDER = "-";
    public static final String TABLE_HEADER = "=";


    /**
     * Prints a given string to the user.
     *
     * @param response String to be printed.
     */
    public static void printResponse(String response) {
        System.out.println(response);
    }

    /**
     * Gets a lesson timeslot from the user when setting lessons.
     *
     * @param listOfTimeslots A string containing all the timeslot options the user has.
     * @return User input of index referring to the preferred timeslot.
     */
    public static String getTimeslotIndexFromUser(String listOfTimeslots) {
        System.out.println("Which is your preferred timeslot? Enter corresponding valid index\n"
                + listOfTimeslots);
        return UI.sc.nextLine();
    }

    /**
     * Gets a lesson to set from the user when setting lessons.
     *
     * @param listOfLessons A string containing all the lesson options the user has.
     * @return User input of index referring to the lesson to be set.
     */
    public static String getLessonIndexFromUser(String listOfLessons) {
        System.out.println("Which lesson type do you want to set? Enter corresponding valid index\n"
                + listOfLessons);
        return UI.sc.nextLine();
    }

    /**
     * Gets a module from the user when setting lessons.
     *
     * @param listOfModules A string containing all the module options the user has.
     * @return User input of index referring to the module to set lesson for.
     */
    public static String getModuleIndexFromUser(String listOfModules) {
        System.out.println("Which module would you like to set lessons for? Enter corresponding valid index\n"
                + listOfModules);
        return sc.nextLine();
    }

    /**
     * Gets the semester to be loaded when the program first starts up.
     *
     * @return User input on choice of semester.
     */
    public static String getSemesterFromUser() {
        System.out.println("Before we begin, enter which Semester it is, 1 or 2. Alternatively, enter 0 to quit.");
        return sc.nextLine();
    }

    /**
     * Gets a module code from the user.
     *
     * @return A string referring to the module code.
     */
    public static String getModuleCodeFromUser() {
        System.out.println("Please enter module code");
        return sc.nextLine();
    }

    /**
     * Prints out list of available commands and allows for user command input.
     *
     * @return A string referring to the input command.
     */
    public static String getCommandFromUser() {
        System.out.println("Here is a list of things I can do, enter the appropriate command to continue!\n"
                + "1. add" + PRINTED_GAP + "2. list" + PRINTED_GAP + "3. info" + PRINTED_GAP
                + "4. set" + PRINTED_GAP + "5. delete" + PRINTED_GAP + "6. allocate" + PRINTED_GAP
                + "7. print" + PRINTED_GAP + "8. quit\n");
        return sc.nextLine();
    }

    /**
     * Prints welcome message.
     */
    public static void printWelcomeMessage() {
        System.out.println(" _____ _                _        _     _           \n"
                + "|_   _(_)              | |      | |   | |          \n"
                + "  | |  _ _ __ ___   ___| |_ __ _| |__ | | ___ _ __ \n"
                + "  | | | | '_ ` _ \\ / _ \\ __/ _` | '_ \\| |/ _ \\ '__|\n"
                + "  | | | | | | | | |  __/ || (_| | |_) | |  __/ |   \n"
                + "  \\_/ |_|_| |_| |_|\\___|\\__\\__,_|_.__/|_|\\___|_|   \n"
                + "                                                   \n"
                + "                                                   ");
    }

    /**
     * Prints goodbye message.
     */
    public static void printGoodbyeMessage() {
        System.out.println(" _____            __   __             ___              _       _ \n"
                + "/  ___|           \\ \\ / /            / _ \\            (_)     | |\n"
                + "\\ `--.  ___  ___   \\ V /___  _   _  / /_\\ \\ __ _  __ _ _ _ __ | |\n"
                + " `--. \\/ _ \\/ _ \\   \\ /  _ \\| | | | |  _  |/ _` |/ _` | | '_ \\| |\n"
                + "/\\__/ /  __/  __/   | | (_) | |_| | | | | | (_| | (_| | | | | |_|\n"
                + "\\____/ \\___|\\___|   \\_/\\___/ \\__,_| \\_| |_/\\__, |\\__,_|_|_| |_(_)\n"
                + "                                            __/ |                \n"
                + "                                           |___/                 ");
    }


    /**
     * Formats the list of lesson types that the user
     * can choose after they have selected a module to set.
     *
     * @param index The associated number tagged to each lesson type.
     * @param lessonType The type of lesson
     * @param userView The object that stores what the user will eventually see.
     */
    public static void displayLessonTypeToSet(int index, String lessonType, StringBuilder userView) {
        userView.append(index)
                .append(". ")
                .append(lessonType)
                .append("\n");
    }

    /**
     * Get user response on what to do in the event of a file load up error.
     *
     * @return User input referring to his choice of action.
     */
    public static String getDataProtocolResponse() {
        String response = "2";
        System.out.println("Oops! Something went wrong. Either a file is corrupted or you are not connected "
                + "to the internet.\n" + "Enter 0 if you are sure you have internet connection - files will be "
                + "deleted and recreated before continuing.\n" + "Enter 1 if you have no internet connection - "
                + "program will quit without losing data.\n");
        while (!response.equals("0") && !response.equals("1")) {
            response = sc.nextLine();
        }
        return response;
    }
}
