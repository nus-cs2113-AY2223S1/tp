package seedu.moneygowhere.common;

/**
 * Defines configuration parameters used by program.
 */
public class Configurations {
    /**
     * Defines configuration parameters for {@link seedu.moneygowhere.userinterface.ConsoleInterface}.
     */
    public static final String CONSOLE_INTERFACE_DATE_TIME_INPUT_FORMAT = "dd/MM/yyyy HHmm";
    public static final String CONSOLE_INTERFACE_DATE_TIME_OUTPUT_FORMAT = "dd MMM yyyy HH:mm";

    /**
     * Defines configuration parameters for {@link seedu.moneygowhere.storage.LocalStorage}.
     */
    public static final String DIRECTORY_PATH = "./Memory/";
    private static final String FILE_NAME_EXPENSES = "expenses.txt";
    public static final String FILE_PATH_EXPENSES = DIRECTORY_PATH + FILE_NAME_EXPENSES;

    /**
     * Defines configuration parameters for {@link seedu.moneygowhere.logger.LocalLogger}.
     */
    public static final String LOCAL_LOGGER_LOGGING_DIRECTORY = "./logs/";
    public static final String LOCAL_LOGGER_LOGGING_FILE = "MoneyGoWhere.log";
    public static final String LOCAL_LOGGER_LOGGING_FILE_PATH = ""
            + LOCAL_LOGGER_LOGGING_DIRECTORY + LOCAL_LOGGER_LOGGING_FILE;
    public static final boolean LOCAL_LOGGER_LOGGING_FILE_TO_APPEND = true;
}
