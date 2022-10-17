package seedu.duke;

import java.util.logging.Level;
import java.util.logging.Logger;

public class Media {
    private final Logger logger = Logger.getLogger("mediaLog");
    public String title;
    public double rating;

    /**.
     * Creates a new review
     * @param title Title of media
     * @param rating Rating of media
     */
    public Media(String title, double rating) {
        assert title.length() > 0 : "Movie title length must be more than 0";
        assert rating >= 0 : "Minimum rating must be 0";
        this.title = title;
        this.rating = rating;
        logger.log(Level.INFO,"Added new Media to list");
    }

    /**.
     * Formats the review to display as output
     * @return Formatted media review
     */
    public String toString() {
        return this.title + "Rating: " + this.rating;
    }

}
