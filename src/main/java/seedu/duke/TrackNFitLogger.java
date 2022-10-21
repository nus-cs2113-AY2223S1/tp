package seedu.duke;

import java.io.File;
import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;

//@@author Lars Vogel-reused
public class TrackNFitLogger {

    public static void setUp() throws IOException {
        File directory = new File("./data");
        if (!directory.exists()) {
            directory.mkdir();
        }
        FileHandler fileHandler = new FileHandler("./data/Logging.txt");
        Logger logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
        LogManager.getLogManager().reset();
        logger.setLevel(Level.ALL);
        logger.addHandler(fileHandler);
    }

}
