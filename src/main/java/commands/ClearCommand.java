package commands;

import seedu.duke.ReviewList;

public class ClearCommand extends Commands {
    public ClearCommand(ReviewList reviews) {
        super(reviews);
    }

    //@author matthewphua
    /**
     * Clears all reviews in review list.
     * @return Confirmation of clearance.
     */
    @Override
    public String execute() {
        reviewList.inputs.clear();
        assert reviewList.inputs.size() == 0 : "reviewlist array should be empty";
        return "Your list is now cleared.";
    }
}
