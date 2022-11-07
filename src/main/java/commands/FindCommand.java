package commands;

import seedu.duke.Media;
import seedu.duke.Movie;
import seedu.duke.ReviewList;
import seedu.duke.TvShow;

import java.util.ArrayList;

public class FindCommand extends Commands {
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
        ReviewList matchedReviews = new ReviewList();

        for (int i = 0; i < reviewList.inputs.size(); i++) {
            Media media = reviewList.inputs.get(i);
            if (media.getTitle().contains(searchTerm)) {
                matchedReviews.add(media);
            }
        }

        ListCommand outputList = new ListCommand(matchedReviews);
        String output = outputList.execute();
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
