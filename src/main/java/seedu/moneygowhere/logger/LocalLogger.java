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

/**
 * Provides functions to log data to a file.
 */
@SuppressWarnings("FieldMayBeFinal")
public class LocalLogger {
    private Logger logger;

    /**
     * Creates an instance of {@link LocalLogger}.
     *
     * @throws IOException If an I/O error occurred when creating or writing to a log file.
     */
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
        logger.setLevel(Level.FINEST);
        logger.setUseParentHandlers(false);
        logger.addHandler(fileHandler);
    }

    /**
     * Logs an informational message to a log file.
     *
     * @param message Informational message.
     */
    public void logInformationalMessage(String message) {
        String informationalMessageHeader = "INFO - ";

        logger.log(
                Level.FINE,
                informationalMessageHeader + message
        );
    }

    /**
     * Logs a warning message to a log file.
     *
     * @param message Warning message.
     */
    public void logWarningMessage(String message) {
        String warningMessageHeader = "WARN - ";

        logger.log(
                Level.FINER,
                warningMessageHeader + message
        );
    }

    /**
     * Logs an error message to a log file.
     *
     * @param message Error message.
     */
    public void logErrorMessage(String message) {
        String errorMessageHeader = "ERROR - ";

        logger.log(
                Level.FINEST,
                errorMessageHeader + message
        );
    }

    /**
     * Logs command entered by user to a log file.
     *
     * @param command Command string entered by user.
     */
    public void logCommand(String command) {
        String commandHeader = "COMD - ";

        logger.log(
                Level.FINEST,
                commandHeader + command
        );
    }
}
