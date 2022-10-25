package seedu.duke.parser;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DateParserTest {

    @Test
    void formatDateToString_givenLocalDate_returnFormattedString() {
        LocalDate date = LocalDate.parse("2022-10-25");
        assertEquals("Tue, Oct 25 2022", DateParser.formatDateToString(date));
    }
}