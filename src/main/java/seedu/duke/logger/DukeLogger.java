package seedu.duke.logger;

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
        file.getParentFile().mkdir();
        file.createNewFile();
    }

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

    public void info(String message) {
        logger.info(message);
    }

    public void warning(String message) {
        logger.warning(message);
    }
}
