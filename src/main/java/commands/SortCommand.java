package commands;

import seedu.duke.Media;
import seedu.duke.ReviewList;

import java.util.Comparator;

public class SortCommand extends Commands {
    String[] userInput;

    public SortCommand(ReviewList reviews, String[] userInput) {
        super(reviews);
        this.userInput = userInput;
    }

    /**
     * This method sorts the review list according to a given field.
     *
     * @return Returns the sorted review list if successful, else returns an error message.
     */
    @Override
    public String execute() {
        String output = "";

        switch (userInput[1]) {
        case "rating":
            this.reviewList.inputs.sort(Comparator.comparing(Media::getRating).reversed());
            break;

        case "title":
            this.reviewList.inputs.sort(Comparator.comparing(Media::getTitle));
            break;

        case "genre":
            this.reviewList.inputs.sort(Comparator.comparing(Media::getGenre));
            break;

        case "date":
            this.reviewList.inputs.sort(Comparator.comparing(Media::getDateWatched).reversed());
            break;

        default:
            output = "Invalid sort field given. Choose any of the following sorting fields: 'rating', 'title', 'genre' "
                    + "or 'date'.";

            return output;
        }

        output += "Your list has been sorted by " + userInput[1] + ".\n";
        Commands listExecutor = new ListCommand(this.reviewList);
        String updatedList = listExecutor.execute();
        output += updatedList;

        return output;
    }
}
