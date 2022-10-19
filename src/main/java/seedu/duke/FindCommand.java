package seedu.duke;

public class FindCommand extends Commands {
    private final String outputString = "---Here are the reviews that match the keyword---\n";
    private final String movieString = "\nMovies:\n";
    private final String tvShowString = "\nTV Shows:\n";
    private final String listDelimeter = ". ";
    protected String searchTerm;

    public FindCommand(ReviewList reviews, String searchTerm) {
        super(reviews);
        this.searchTerm = searchTerm;
    }

    @Override
    public String execute() {
        String moviesList = "";
        String tvShowList = "";
        int movieIndex = 1;
        int tvShowIndex = 1;

        for (int i = 0; i < reviewList.inputs.size(); i++) {
            Media media = reviewList.inputs.get(i);
            if (media instanceof Movie && media.getTitle().contains(searchTerm)) {
                moviesList += (movieIndex) + listDelimeter + reviewList.inputs.get(i).toString() + "\n";
                movieIndex += 1;
            } else if (media instanceof TvShow && media.getTitle().contains(searchTerm)) {
                tvShowList += (tvShowIndex) + listDelimeter + reviewList.inputs.get(i).toString() + "\n";
                tvShowIndex += 1;
            }
        }
        String output = outputString + movieString + moviesList + tvShowString + tvShowList;
        output = output.strip();
        return output;
    }
}
