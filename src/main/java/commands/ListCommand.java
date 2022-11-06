package commands;

import seedu.duke.Media;
import seedu.duke.Movie;
import seedu.duke.ReviewList;
import seedu.duke.TvShow;

public class ListCommand extends Commands {
    private static final String OUTPUT_STRING = "---Here are the reviews---\n";
    private static final String MOVIE_STRING = "\nMovies:\n";
    private static final String TV_SHOW_STRING = "\nTV Shows:\n";
    private static final String LIST_DELIMITER = ". ";

    public ListCommand(ReviewList reviews) {
        super(reviews);
    }

    //@@author matthewphua
    /**
     * Outputs list containing all reviews separated by categories.
     * @return Output string with reviews.
     */
    @Override
    public String execute() {
        String moviesList = "";
        String tvShowList = "";
        int movieIndex = 1;
        int tvShowIndex = 1;

        for (int i = 0; i < reviewList.inputs.size(); i++) {
            Media media = reviewList.inputs.get(i);
            if (media instanceof Movie) {
                moviesList += (movieIndex) + LIST_DELIMITER + reviewList.inputs.get(i).toString() + "\n";
                movieIndex += 1;
            } else if (media instanceof TvShow) {
                tvShowList += (tvShowIndex) + LIST_DELIMITER + reviewList.inputs.get(i).toString() + "\n";
                tvShowIndex += 1;
            }
        }
        String output = OUTPUT_STRING + MOVIE_STRING + moviesList + TV_SHOW_STRING + tvShowList;
        output = output.strip();
        return output;
    }

}
