package seedu.duke;

import java.util.logging.Level;
import java.util.logging.Logger;

public class Movie extends Media {
    private static Logger logger = Logger.getLogger("MovieLogger");
    public String dateWatched;

    /**.
     * Creates a new movie review
     * @param title Title of movie being reviewed
     * @param rating Rating of the movie watched
     * @param dateWatched Date movie was watched
     */
    public Movie(String title, double rating, String genre, String dateWatched) {
        super(title,rating,genre);
        assert title.length() > 0 : "Movie title length must be more than 0";
        assert rating >= 0 : "Minimum rating must be 0";
        assert dateWatched.length() > 0 : "Length of date must be more than 0";
        this.dateWatched = dateWatched;
        logger.log(Level.INFO, "Added new movie: " + title);
    }

    @Override
    /**
     * Formats the movie review to display as output
     */
    public String toString() {
        return "[Movie] " + super.toString() + " Date watched: " + this.dateWatched + " Genre: " + this.genre;
    }

}
