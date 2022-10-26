package seedu.duke;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.LogManager;
import java.util.logging.FileHandler;
import java.util.logging.ConsoleHandler;
import seedu.duke.data.DataManager;

public class Duke {
    private static final String EXIT_FLAG = "quit";
    private static final String INVALID_SEM = "invalid semester";
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
        UI.printWelcomeMessage();

        String response;
        String input;
        String currentSemester = getSemester();

        if (!loadData(currentSemester)) {
            System.exit(0);
        }

        boolean isRunning = !Objects.equals(currentSemester, EXIT_FLAG);
        
        while (isRunning) {
            assert (currentSemester.equals("1") || currentSemester.equals("2"))  : "valid semester are only 1 or 2";

            input = UI.getCommandFromUser();
            response = Parser.parseCommand(input, currentSemester);

            if (Objects.equals(response, EXIT_FLAG)) {
                lgr.info("exit flag detected, attempting to exit");
                break;
            }

            saveData();
            System.out.println(response);

            assert isRunning : "this is never set to false, use break to exit loop";
        }

        saveData();

        UI.printGoodbyeMessage();
        lgr.info("exiting Timetabler program...");
    }

    private static void saveData() {
        DataManager.makeSave();
    }

    private static boolean loadData(String currentSemester) {
        DataManager.initDataFile(currentSemester);
        try {
            DataManager.loadTimetableFromDataFile();
        } catch (FileNotFoundException e) {
            UI.printResponse("Error, file not found!");
            return false;
        } catch (Exceptions.FileLoadException e) {
            UI.printResponse("API call failed, are you connected to the internet?\n"
                    + "Program exiting expectedly...");
            return false;
        } catch (IndexOutOfBoundsException e) {
            UI.printResponse("Corrupted file detected. Please delete all data files and relaunch the app.\n"
                    + "Program exiting expectedly...");
            return false;
        }
        return true;
    }

    private static String getSemester() {
        String currentSemester = INVALID_SEM;

        while (currentSemester.equals(INVALID_SEM)) {
            currentSemester = checkSemester(UI.getSemesterFromUser());
        }
        return currentSemester;
    }

    private static String checkSemester(String sem) {
        if (sem.equals("1") || sem.equals("2")) {
            return sem;
        }
        if (sem.equals("0")) {
            return EXIT_FLAG;
        }
        return INVALID_SEM;
    }
}
