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
    public static final String LOCAL_STORAGE_DIRECTORY = "./Memory/";
    private static final String LOCAL_STORAGE_DATA_FILE = "MoneyGoWhereData.xml";
    public static final String LOCAL_STORAGE_DATA_FILE_PATH = LOCAL_STORAGE_DIRECTORY + LOCAL_STORAGE_DATA_FILE;

    /**
     * Defines configuration parameters for {@link seedu.moneygowhere.logger.LocalLogger}.
     */
    public static final String LOCAL_LOGGER_LOGGING_DIRECTORY = "./logs/";
    public static final String LOCAL_LOGGER_LOGGING_FILE = "MoneyGoWhere.log";
    public static final String LOCAL_LOGGER_LOGGING_FILE_PATH = ""
            + LOCAL_LOGGER_LOGGING_DIRECTORY + LOCAL_LOGGER_LOGGING_FILE;
    public static final boolean LOCAL_LOGGER_LOGGING_FILE_TO_APPEND = true;

    /**
     * Defines configuration parameters for {@link seedu.moneygowhere.currency.CurrencyApi}.
     */
    public static final String CURRENCY_API_URL = ""
            + "https://cdn.jsdelivr.net/gh/fawazahmed0/currency-api@1/latest/currencies/sgd.json";
    private static final String FILE_NAME_CURRENCIES = "exchangeRates.txt";
    public static final String FILE_PATH_CURRENCIES = LOCAL_STORAGE_DIRECTORY + FILE_NAME_CURRENCIES;
    public static final int NUMBER_OF_CURRENCIES = 267;
    public static final int IGNORED_LINES = 3;

    /**
     * Defines configuration parameters for {@link seedu.moneygowhere.data.currency.CurrencyManager}.
     */
    public static final String CURRENCY_SINGAPORE_DOLLARS = "SGD";
    public static final int NUMBER_OF_DECIMAL_PLACES = 5;
}
