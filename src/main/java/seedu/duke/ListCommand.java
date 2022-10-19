package seedu.duke;

public class ListCommand extends Commands {
    private final String outputString = "---Here are the reviews in your list---\n";
    private final String movieString = "\nMovies:\n";
    private final String tvShowString = "\nTV Shows:\n";

    public ListCommand(ReviewList reviews) {
        super(reviews);
    }

    @Override
    public String execute() {
        String moviesList = "";
        String tvShowList = "";
        int movieIndex = 1;
        int tvShowIndex = 1;

        for (int i = 0; i < reviewList.inputs.size(); i++) {
            Media media = reviewList.inputs.get(i);
            if (media instanceof Movie) {
                moviesList += (movieIndex) + ". " + reviewList.inputs.get(i).toString() + "\n";
                movieIndex += 1;
            } else if (media instanceof TvShow) {
                tvShowList += (tvShowIndex) + ". " + reviewList.inputs.get(i).toString() + "\n";
                tvShowIndex += 1;
            }
        }
        String output = outputString + movieString + moviesList + tvShowString + tvShowList;
        output = output.strip();
        return output;
    }

}
