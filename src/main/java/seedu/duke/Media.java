package seedu.duke;

import exceptions.FutureDateException;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Media {
    private final Logger logger = Logger.getLogger("DukeLogger");

    private String title;
    private double rating;
    private String genre;
    private Date dateWatched;
    private String dateString;
    private boolean isFavourite = false;

    public Date getDateWatched() {
        return dateWatched;
    }

    public void setDateWatched(Date dateWatched) {
        this.dateWatched = dateWatched;
    }

    public String getDateString() {
        return dateString;
    }

    public String getGenre() {
        return genre;
    }

    public double getRating() {
        return rating;
    }

    public String getTitle() {
        return title;
    }

    public boolean isFavourite() {
        return isFavourite;
    }

    public void setFavourite(boolean favourite) {
        this.isFavourite = favourite;
    }


    /**.
     * Creates a new review
     * @param title Title of media
     * @param rating Rating of media
     * @param genre Genre of show
     */
    public Media(String title, double rating, String genre, String dateWatchedString) {
        assert title.length() > 0 : "Movie title length cannot be 0";
        assert rating >= 0 : "Minimum rating must be 0";
        assert genre.length() > 0 : "Genre length cannot be 0";
        this.title = title;
        this.rating = rating;
        this.genre = genre;

        convertDate(dateWatchedString);

        logger.log(Level.INFO,"Added new Media to list");
    }

    /**
     * Converts the given date from a String into a Date object.
     *
     * @param dateWatchedString The date given by the user.
     * @throws ParseException If the user provides a date in a format other than dd-MM-yyyy.
     * @throws FutureDateException If the user provides a date in the future.
     */
    public void convertDate(String dateWatchedString) {
        try {
            DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy", Locale.ENGLISH);
            dateFormat.setLenient(false);
            Date date = dateFormat.parse(dateWatchedString);

            Date currentDate = new Date();
            if (currentDate.before(date)) {
                throw new FutureDateException();
            }

            setDateWatched(date);

            String pattern = "dd-MM-yyyy";
            DateFormat df = new SimpleDateFormat(pattern);
            dateString = df.format(getDateWatched());
        } catch (ParseException e) {
            Ui.print("Invalid date format. Use the following format: DD-MM-YYYY.");
        } catch (FutureDateException e) {
            logger.log(Level.INFO,"User attempted to add date from the future.");
        }
    }

    //@@author redders7
    public Media() {
        title = "";
        rating = 0;
        genre = "";
        setDateWatched(null);
    }

    /**.
     * Formats the review to display as output
     * @return Formatted media review
     */
    public String toString() {
        return this.title + " Rating: " + this.rating + "  Genre:" + genre;
    }

    /**
     * Creates the string representation of the media for saving 
     * into the file This method is to be overwritten by children classes.
     * 
     * @return empty string for generic media.
     */
    public String createFileString() {
        return "";
    }

}
