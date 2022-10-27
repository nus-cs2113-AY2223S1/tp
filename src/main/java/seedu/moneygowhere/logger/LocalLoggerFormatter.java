package seedu.moneygowhere.logger;

import java.util.logging.Formatter;
import java.util.logging.LogRecord;

//@@author xzynos
public class LocalLoggerFormatter extends Formatter {
    @Override
    public String format(LogRecord logRecord) {
        String formattedLog = "";
        formattedLog += logRecord.getMillis();
        formattedLog += " - ";
        formattedLog += logRecord.getMessage();
        formattedLog += "\n";

        return formattedLog;
    }
}
