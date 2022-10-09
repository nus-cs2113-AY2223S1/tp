package seedu.duke;

import java.util.Objects;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Duke {
    private static final String EXIT_FLAG = "quit";
    private static final String INVALID_SEM = "invalid semester";
    private static final String PRINTED_GAP = "     ";
    public static final Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {

        Logger logger = Logger.getLogger(Duke.class.getName());
        logger.log(Level.INFO, "starting the program");

        System.out.println("Hello from Timetabler!");

        Timetable timetable = new Timetable();
        String response;
        String input;
        String currentSemester = INVALID_SEM;
        boolean flag = true;


        while (currentSemester.equals(INVALID_SEM)) {
            currentSemester = getSemester();
            if (currentSemester.equals(EXIT_FLAG)) {
                flag = false;
            }
        }

        while (flag) {
            System.out.println("Here is a list of things I can do, enter the appropriate command to continue!\n"
                    + "1. add" + PRINTED_GAP + "2. list" + PRINTED_GAP + "3. quit" + PRINTED_GAP + "4. info\n");
            input = sc.nextLine();
            response = Parser.parseCommand(timetable, input, currentSemester);
            if (Objects.equals(response, EXIT_FLAG)) {
                break;
            }
            System.out.println(response);
        }

        System.out.println("See you again!");
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
