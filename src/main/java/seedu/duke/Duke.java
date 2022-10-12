package seedu.duke;

import java.io.IOException;
import java.util.Objects;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.LogManager;
import java.util.logging.FileHandler;
import java.util.logging.ConsoleHandler;

public class Duke {
    private static final String EXIT_FLAG = "quit";
    private static final String INVALID_SEM = "invalid semester";
    private static final String PRINTED_GAP = "     ";
    public static final Scanner sc = new Scanner(System.in);
    private static final Logger lgr = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

    private static void setupLogger() {
        LogManager.getLogManager().reset();
        lgr.setLevel(Level.ALL);

        ConsoleHandler ch = new ConsoleHandler();
        ch.setLevel(Level.SEVERE);
        lgr.addHandler(ch);

        try {
            FileHandler fh = new FileHandler("logger.log");
            fh.setLevel(Level.FINE);
            lgr.addHandler(fh);
        } catch (IOException e) {
            lgr.severe("File logger io exception. Ensure logger.log file exists.");
        }
    }

    public static void main(String[] args) {

        setupLogger();

        lgr.info("starting Timetabler program...");

        System.out.println(" _____ _                _        _     _           \n"
                + "|_   _(_)              | |      | |   | |          \n"
                + "  | |  _ _ __ ___   ___| |_ __ _| |__ | | ___ _ __ \n"
                + "  | | | | '_ ` _ \\ / _ \\ __/ _` | '_ \\| |/ _ \\ '__|\n"
                + "  | | | | | | | | |  __/ || (_| | |_) | |  __/ |   \n"
                + "  \\_/ |_|_| |_| |_|\\___|\\__\\__,_|_.__/|_|\\___|_|   \n"
                + "                                                   \n"
                + "                                                   ");

        Timetable timetable = new Timetable();
        String response;
        String input;
        String currentSemester = INVALID_SEM;
        boolean isRunning = true;


        while (currentSemester.equals(INVALID_SEM)) {
            currentSemester = getSemester();
            if (currentSemester.equals(EXIT_FLAG)) {
                isRunning = false;
            }
        }

        while (isRunning) {
            assert (currentSemester.equals("1") || currentSemester.equals("2"))  : "valid semester are only 1 or 2";

            System.out.println("Here is a list of things I can do, enter the appropriate command to continue!\n"
                    + "1. add" + PRINTED_GAP + "2. list" + PRINTED_GAP + "3. info" + PRINTED_GAP
                    + "4. set" + PRINTED_GAP + "5. delete" + PRINTED_GAP + "6. quit\n");

            input = sc.nextLine();
            response = Parser.parseCommand(timetable, input, currentSemester);


            if (Objects.equals(response, EXIT_FLAG)) {
                lgr.info("exit flag detected, attempting to exit");
                break;
            }
            System.out.println(response);
            assert isRunning : "this is never set to false, use break to exit loop";
        }

        System.out.println(" _____            __   __             ___              _       _ \n"
                + "/  ___|           \\ \\ / /            / _ \\            (_)     | |\n"
                + "\\ `--.  ___  ___   \\ V /___  _   _  / /_\\ \\ __ _  __ _ _ _ __ | |\n"
                + " `--. \\/ _ \\/ _ \\   \\ /  _ \\| | | | |  _  |/ _` |/ _` | | '_ \\| |\n"
                + "/\\__/ /  __/  __/   | | (_) | |_| | | | | | (_| | (_| | | | | |_|\n"
                + "\\____/ \\___|\\___|   \\_/\\___/ \\__,_| \\_| |_/\\__, |\\__,_|_|_| |_(_)\n"
                + "                                            __/ |                \n"
                + "                                           |___/                 ");

        lgr.info("exiting Timetabler program...");
    }

    private static String getSemester() {
        System.out.println("Before we begin, enter which Semester it is, 1 or 2. Alternatively, enter 0 to quit.");
        String sem = sc.nextLine();
        if (sem.equals("1") || sem.equals("2")) {
            return sem;
        }
        if (sem.equals("0")) {
            return EXIT_FLAG;
        }
        return INVALID_SEM;
    }
}
