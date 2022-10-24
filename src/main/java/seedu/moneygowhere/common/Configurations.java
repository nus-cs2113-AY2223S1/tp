package seedu.moneygowhere.common;

/**
 * Defines configuration parameters used by program.
 */
public class Configurations {
    //@@author xzynos
    //region Defines configuration parameters for ConsoleInterface
    public static final String CONSOLE_INTERFACE_DATE_TIME_INPUT_FORMAT = "dd/MM/yyyy HHmm";
    public static final String CONSOLE_INTERFACE_DATE_TIME_OUTPUT_FORMAT = "dd MMM yyyy HH:mm";

    //endregion

    //@@author LokQiJun
    //region Defines configuration parameters for LocalStorage
    public static final String LOCAL_STORAGE_DIRECTORY = "./Memory/";
    private static final String LOCAL_STORAGE_DATA_FILE = "MoneyGoWhereData.xml";
    public static final String LOCAL_STORAGE_DATA_FILE_PATH = ""
            + LOCAL_STORAGE_DIRECTORY
            + LOCAL_STORAGE_DATA_FILE;

    //endregion

    //@@author xzynos
    //region Defines configuration parameters for LocalLogger
    public static final String LOCAL_LOGGER_LOGGING_DIRECTORY = "./logs/";
    public static final String LOCAL_LOGGER_LOGGING_FILE = "MoneyGoWhere.log";
    public static final String LOCAL_LOGGER_LOGGING_FILE_PATH = ""
            + LOCAL_LOGGER_LOGGING_DIRECTORY
            + LOCAL_LOGGER_LOGGING_FILE;
    public static final boolean LOCAL_LOGGER_LOGGING_FILE_TO_APPEND = true;

    //endregion

    //@@author jeyvia
    //region Defines configuration parameters for CurrencyApi
    public static final String CURRENCY_API_URL = ""
            + "https://cdn.jsdelivr.net/gh/fawazahmed0/currency-api@1/latest/currencies/sgd.json";
    private static final String CURRENCY_API_CURRENCIES_FILE_NAME = "exchangeRates.txt";
    public static final String CURRENCY_API_CURRENCIES_FILE_PATH = ""
            + LOCAL_STORAGE_DIRECTORY
            + CURRENCY_API_CURRENCIES_FILE_NAME;
    public static final int CURRENCY_API_NUMBER_OF_CURRENCIES = 267;
    public static final int CURRENCY_API_IGNORED_LINES = 3;

    //endregion

    //@@author jeyvia
    //region Defines configuration parameters for CurrencyManager
    public static final String CURRENCY_MANAGER_CURRENCY_CODE_SINGAPORE_DOLLARS = "SGD";
    public static final int CURRENCY_MANAGER_CONVERSION_NUMBER_OF_DECIMAL_PLACES = 5;

    //endregion
}
