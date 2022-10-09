package seedu.duke;

public class Movie extends Media {
    public String dateWatched;

    /**.
     * Creates a new movie review
     * @param title Title of movie being reviewed
     * @param rating Rating of the movie watched
     * @param dateWatched Date movie was watched
     */
    public Movie(String title, double rating, String dateWatched) {
        super(title,rating);
        this.dateWatched = dateWatched;
    }

    @Override
    /**
     * Formats the movie review to display as output
     */
    public String toString() {
        return "[Movie] " + super.toString() + " Date watched: " + this.dateWatched;
    }

}
