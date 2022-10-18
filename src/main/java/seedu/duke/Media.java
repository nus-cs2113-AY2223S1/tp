package seedu.duke;

import java.util.logging.Level;
import java.util.logging.Logger;

public class Media {
    private final Logger logger = Logger.getLogger("mediaLog");
    public String title;
    public double rating;
    public String genre;

    public boolean isFavourite() {
        return favourite;
    }

    public void setFavourite(boolean favourite) {
        this.favourite = favourite;
    }

    public boolean favourite = false;

    /**.
     * Creates a new review
     * @param title Title of media
     * @param rating Rating of media
     * @param genre Genre of show
     */
    public Media(String title, double rating, String genre) {
        assert title.length() > 0 : "Movie title length cannot be 0";
        assert rating >= 0 : "Minimum rating must be 0";
        assert genre.length() > 0 : "Genre length cannot be 0";
        this.title = title;
        this.rating = rating;
        this.genre = genre;
        logger.log(Level.INFO,"Added new Media to list");
    }

    /**.
     * Formats the review to display as output
     * @return Formatted media review
     */
    public String toString() {
        return this.title + " Rating:" + this.rating + " Genre:" + this.genre;
    }

}
