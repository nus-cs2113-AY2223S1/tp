package seedu.duke;

import org.junit.jupiter.api.Test;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class MediaTest {
    ReviewList reviews;

    @Test
    void newMediaTest() {
        Media inception = new Media("Inception", 4.5, "Sci-fi","10-01-2021");
        assertEquals("Inception",inception.title);
        assertEquals(4.5,inception.rating);
        assertEquals("Sci-fi",inception.genre);
        assertEquals("10-01-2021",inception.dateString);
        assertEquals("Inception Rating:4.5 Genre:Sci-fi",inception.toString());
    }

    @Test
    void convertDate_invalidDay_throwParseException() {
        String dateWatchedString = "32-01-2022";
        Throwable ex = null;

        try {
            DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy", Locale.ENGLISH);
            dateFormat.setLenient(false);
            Date date = dateFormat.parse(dateWatchedString);
        } catch (ParseException e) {
            ex = e;
        }

        assertNotNull(ex);
        assertEquals(ParseException.class, ex.getClass());
    }

    @Test
    void convertDate_invalidMonth_throwParseException() {
        String dateWatchedString = "01-15-2022";
        Throwable ex = null;

        try {
            DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy", Locale.ENGLISH);
            dateFormat.setLenient(false);
            Date date = dateFormat.parse(dateWatchedString);
        } catch (ParseException e) {
            ex = e;
        }

        assertNotNull(ex);
        assertEquals(ParseException.class, ex.getClass());
    }

    @Test
    void convertDate_invalidYear_throwParseException() {
        String dateWatchedString = "01-05-year";
        Throwable ex = null;

        try {
            DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy", Locale.ENGLISH);
            dateFormat.setLenient(false);
            Date date = dateFormat.parse(dateWatchedString);
        } catch (ParseException e) {
            ex = e;
        }

        assertNotNull(ex);
        assertEquals(ParseException.class, ex.getClass());
    }

    @Test
    void convertDate_invalidLeapYear_throwParseException() {
        String dateWatchedString = "29-02-2022";
        Throwable ex = null;

        try {
            DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy", Locale.ENGLISH);
            dateFormat.setLenient(false);
            Date date = dateFormat.parse(dateWatchedString);
        } catch (ParseException e) {
            ex = e;
        }

        assertNotNull(ex);
        assertEquals(ParseException.class, ex.getClass());
    }
}