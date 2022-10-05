package seedu.duke.parser;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

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

    /**
     * Convert the string date to LocalDate.
     *
     * @param date The string date under the format "E, MMM dd yyyy".
     * @return The LocalDate-type date.
     */
    public static LocalDate convertStringDateToLocalDate(String date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("E, MMM dd yyyy");
        return LocalDate.parse(date, formatter);
    }

    /**
     * Checks if the input date is valid.
     *
     * @param date The string date to be checked.
     * @return true If the date is valid and otherwise.
     */
    public static boolean isValidDate(String date) {
        try {
            LocalDate.parse(date);
        } catch (DateTimeParseException e) {
            return false;
        }
        return true;
    }
}

