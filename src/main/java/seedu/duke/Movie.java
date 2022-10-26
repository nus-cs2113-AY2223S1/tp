package seedu.duke;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Movie extends Media {
    private static Logger logger = Logger.getLogger("MovieLogger");

    /**.
     * Creates a new movie review
     * @param title Title of movie being reviewed
     * @param rating Rating of the movie watched
     * @param dateWatchedString Date movie was watched
     */
    public Movie(String title, double rating, String genre, String dateWatchedString) {
        super(title, rating, genre, dateWatchedString);
        assert title.length() > 0 : "Movie title length must be more than 0";
        assert rating >= 0 : "Minimum rating must be 0";
        assert dateWatchedString.length() > 0 : "Length of date must be more than 0";
        logger.log(Level.INFO, "Added new movie: " + title);
    }

    @Override
    /**
     * Formats the movie review to display as output.
     */
    public String toString() {
        return "[Movie] " + super.toString() + " Date watched:" + super.dateString;
    }

    /**
     * Creates the string that will be saved into the file 
     * that represents the media.
     * 
     * @return formatted string representing Movie information.
     */
    @Override
    public String createFileString() {
        String favourite = "N";
        if (this.isFavourite) {
            favourite = "Y";
        }
        return "M|" + favourite + "|" + super.title + "|" + super.rating + "|" + super.genre + "|" + super.dateString;
    }
}
