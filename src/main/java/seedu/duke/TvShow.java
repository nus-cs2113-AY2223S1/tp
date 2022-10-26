package seedu.duke;

import java.util.logging.Level;
import java.util.logging.Logger;

public class TvShow extends Media {
    private static Logger logger = Logger.getLogger("tvLogger");
    public String site;
    public String dateWatched;

    /**.
     * Creates a new Tv Show
     * @param title Title of show
     * @param rating Rating of show
     * @param genre Genre of show
     * @param dateWatched Date that the show was watched
     * @param site Platform or website to watch the show
     */
    public TvShow(String title, double rating, String genre, String dateWatched, String site) {
        super(title, rating, genre, dateWatched);
        assert dateWatched.length() > 0 : "Length of date must be more than 0";
        assert site.length() > 0 : "Length of date must be more than 0";
        this.dateWatched = dateWatched;
        this.site = site;
        logger.log(Level.INFO, "Added new TV Show: " + title);
    }

    @Override
    /**
     * Formats the movie review to display as output
     */
    public String toString() {
        return "[TV Show] " + super.toString() + " Date watched:" + this.dateWatched + " Site: " + site;
    }

    /**
     * Creates the string that will be saved into the file 
     * that represents the media.
     * 
     * @return formatted string representing TvShow information.
     */
    @Override
    public String createFileString() {
        String favourite = "N";
        if (this.isFavourite) {
            favourite = "Y";
        }
        
        String returnString = "T|" + favourite + "|" + super.title + "|" + super.rating 
            + "|" + super.genre + "|" + super.dateString + "|" + this.site;
        
        return returnString;
    }
}
