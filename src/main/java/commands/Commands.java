package commands;

import seedu.duke.ReviewList;

import java.util.ArrayList;

public abstract class Commands {
    public ReviewList reviewList;

    public Commands(ReviewList reviews) {
        this.reviewList = reviews;
    }

    /**
     * Executes the command and returns the result.
     *
     * @return the output message from the command
     */
    public abstract String execute();
}
