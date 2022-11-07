package seedu.duke.logger;

import seedu.duke.exception.DukeException;
import seedu.duke.ui.Ui;

import java.io.File;
import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class DukeLogger {
    private Logger logger;
    private static final String LOGS_FILE_PATH = "./log/Duke.log";

    private void createLogFile() throws IOException {
        File file = new File(LOGS_FILE_PATH);
        if (file.getParentFile().mkdir()) {
            file.createNewFile();
        }
    }

    /**
     * Constructor of DukeLogger.
     */
    public DukeLogger() {
        try {
            createLogFile();
            FileHandler fileHandler = new FileHandler(LOGS_FILE_PATH, true);
            fileHandler.setFormatter(new SimpleFormatter());
            logger = Logger.getLogger("DukeLogger");
            logger.addHandler(fileHandler);
            logger.setUseParentHandlers(false);
        } catch (IOException e) {
            Ui.printResponse("Creating logs file...");
        }
    }

    /**
     * Logs information.
     *
     * @param message Message of Logger
     */
    public void info(String message) {
        logger.info(message);
    }

    /**
     * Logs warning.
     *
     * @param message Message of Logger
     */
    public void warning(String message) {
        logger.warning(message);
    }

    /**
     * Logs DukeException.
     */
    public void logDukeException(Exception e) {
        if (e instanceof DukeException) {
            warning(e.getMessage());
        }
    }
}
