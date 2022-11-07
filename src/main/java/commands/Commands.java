package commands;

import seedu.duke.ReviewList;

public abstract class Commands {
    public ReviewList reviewList;

    public Commands(ReviewList reviews) {
        reviewList = reviews;
    }

    /**
     * Executes the command and returns the result.
     *
     * @return the output message from the command
     */
    public abstract String execute();
}
