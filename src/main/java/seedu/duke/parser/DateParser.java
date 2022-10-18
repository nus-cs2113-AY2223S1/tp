package seedu.duke.parser;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DateParser {
    /**
     * Formats the input date to a different format.
     *
     * @param date The input string date.
     * @return A formatted string date "E, MMM dd yyyy".
     */
    public static String formatDateToString(LocalDate date) {
        return date.format(DateTimeFormatter.ofPattern("E, MMM dd yyyy"));
    }
}

