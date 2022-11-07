package commands;

import exceptions.DukeException;
import seedu.duke.Media;
import seedu.duke.ReviewList;
import seedu.duke.Ui;

import java.util.Comparator;



public class SortCommand extends Commands {
    String[] userInput;
    static final int SORT_FIELD = 1;

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

        try {
            if (userInput.length != 2) {
                throw new DukeException();
            }

            switch (userInput[SORT_FIELD]) {
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
                output = "Invalid sort field given. Choose any of the following sorting fields: 'rating', 'title', "
                        + "'genre' or 'date'.";

                return output;
            }

            output += "Your list has been sorted by " + userInput[1] + ".\n";
            Commands listExecutor = new ListCommand(this.reviewList);
            String updatedList = listExecutor.execute();
            output += updatedList;

            return output;
        } catch (DukeException e) {
            Ui.print("Incomplete or wrongly formatted command, try again.");
        }

        return output;

    }
}
