package seedu.duke;

import java.util.Scanner;

public class UI {
    public static final Scanner sc = new Scanner(System.in);
    private static final String PRINTED_GAP = "     ";

    public static void printResponse(String response) {
        System.out.println(response);
    }

    public static String getTimeslotIndexFromUser(String listOfTimeslots) {
        System.out.println("Which is your preferred timeslot? Enter corresponding valid index\n"
                + listOfTimeslots);
        return UI.sc.nextLine();
    }

    public static String getLessonIndexFromUser(String listOfLessons) {
        System.out.println("Which lesson type do you want to set? Enter corresponding valid index\n"
                + listOfLessons);
        return UI.sc.nextLine();
    }

    public static String getModuleIndexFromUser(String listOfModules) {
        System.out.println("Which module would you like to set lessons for? Enter corresponding valid index\n"
                + listOfModules);
        return sc.nextLine();
    }

    public static String getSemesterFromUser() {
        System.out.println("Before we begin, enter which Semester it is, 1 or 2. Alternatively, enter 0 to quit.");
        return sc.nextLine();
    }

    public static String getCommandFromUser() {
        System.out.println("Here is a list of things I can do, enter the appropriate command to continue!\n"
                + "1. add" + PRINTED_GAP + "2. list" + PRINTED_GAP + "3. info" + PRINTED_GAP
                + "4. set" + PRINTED_GAP + "5. delete" + PRINTED_GAP + "6. print" + PRINTED_GAP + "7. quit\n");
        return sc.nextLine();
    }

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
}
