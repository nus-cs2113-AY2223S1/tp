package seedu.moneygowhere.logger;

import java.util.logging.Formatter;
import java.util.logging.LogRecord;

//@@author xzynos

/**
 * Provides functions to format log entries.
 */
public class LocalLoggerFormatter extends Formatter {
    /**
     * Formats a {@link LogRecord}.
     *
     * @param logRecord the log record to be formatted.
     * @return Formatted record string.
     */
    @Override
    public String format(LogRecord logRecord) {
        String formattedLog = "";
        formattedLog += logRecord.getMillis();
        formattedLog += " ";
        formattedLog += logRecord.getMessage();
        formattedLog += "\n";

        return formattedLog;
    }
}
