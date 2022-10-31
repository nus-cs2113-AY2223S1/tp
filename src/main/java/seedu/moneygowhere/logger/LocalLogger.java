package seedu.moneygowhere.logger;

import seedu.moneygowhere.MoneyGoWhere;
import seedu.moneygowhere.common.Configurations;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;

//@@author xzynos
@SuppressWarnings("FieldMayBeFinal")
public class LocalLogger {
    private Logger logger;

    public LocalLogger() throws IOException {
        Path localLoggerLoggingDirectory = Paths.get(Configurations.LOCAL_LOGGER_LOGGING_DIRECTORY);
        if (!Files.exists(localLoggerLoggingDirectory)) {
            Files.createDirectories(localLoggerLoggingDirectory);
        }
        Path localLoggerLoggingFilePath = Paths.get(Configurations.LOCAL_LOGGER_LOGGING_FILE_PATH);
        if (!Files.exists(localLoggerLoggingFilePath)) {
            Files.createFile(localLoggerLoggingFilePath);
        }

        FileHandler fileHandler = new FileHandler(
                Configurations.LOCAL_LOGGER_LOGGING_FILE_PATH,
                Configurations.LOCAL_LOGGER_LOGGING_FILE_TO_APPEND
        );
        fileHandler.setFormatter(new LocalLoggerFormatter());

        logger = Logger.getLogger(MoneyGoWhere.class.getName());
        logger.setUseParentHandlers(false);
        logger.addHandler(fileHandler);
    }

    public void logInfo(String message) {
        logger.log(Level.INFO, message);
    }
}
