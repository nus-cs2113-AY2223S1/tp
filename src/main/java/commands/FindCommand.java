package commands;

import seedu.duke.Media;
import seedu.duke.Movie;
import seedu.duke.ReviewList;
import seedu.duke.TvShow;

import java.util.ArrayList;

public class FindCommand extends Commands {
    private final String outputString = "---Here are the reviews that match the keyword---\n";
    private final String movieString = "\nMovies:\n";
    private final String tvShowString = "\nTV Shows:\n";
    private final String listDelimiter = ". ";
    protected String searchTerm;

    public FindCommand(ReviewList reviews, String searchTerm) {
        super(reviews);
        this.searchTerm = searchTerm;
    }

    //@@author matthewphua
    /**
     * Finds reviews that match keyword search.
     * @return Outputs list of reviews that match keyword.
     */
    @Override
    public String execute() {
        String moviesList = "";
        String tvShowList = "";
        int movieIndex = 1;
        int tvShowIndex = 1;

        for (int i = 0; i < reviewList.inputs.size(); i++) {
            Media media = reviewList.inputs.get(i);
            if (media instanceof Movie && media.getTitle().contains(searchTerm)) {
                moviesList += (movieIndex) + listDelimiter + reviewList.inputs.get(i).toString() + "\n";
                movieIndex += 1;
            } else if (media instanceof TvShow && media.getTitle().contains(searchTerm)) {
                tvShowList += (tvShowIndex) + listDelimiter + reviewList.inputs.get(i).toString() + "\n";
                tvShowIndex += 1;
            }
        }
        String output = outputString + movieString + moviesList + tvShowString + tvShowList;
        output = output.strip();
        return output;
    }

    //@@author indraneelrp
    public ArrayList<Media> getMatching() {
        ArrayList<Media> found = new ArrayList<>();

        for (int i = 0; i < reviewList.inputs.size(); i++) {
            Media media = reviewList.inputs.get(i);
            if (media instanceof Movie && media.getTitle().contains(searchTerm)) {
                found.add(reviewList.inputs.get(i));
            } else if (media instanceof TvShow && media.getTitle().contains(searchTerm)) {
                found.add(reviewList.inputs.get(i));
            }
        }

        return found;
    }

}
