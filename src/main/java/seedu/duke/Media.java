package seedu.duke;

public class Media {
    public String title;
    public double rating;

    /**.
     * Creates a new review
     * @param title Title of media
     * @param rating Rating of media
     */
    public Media(String title, double rating) {
        this.title = title;
        this.rating = rating;
    }

    /**.
     * Formats the review to display as output
     * @return Formatted media review
     */
    public String toString() {
        return this.title + "Rating: " + this.rating;
    }

}
