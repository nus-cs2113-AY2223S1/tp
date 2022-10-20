package seedu.duke;

import java.util.ArrayList;

public class ClearCommand extends Commands {
    public ClearCommand(ReviewList reviews) {
        super(reviews);
    }

    @Override
    public String execute() {
        reviewList.inputs.clear();
        assert reviewList.inputs.size() == 0 : "reviewlist array should be empty";
        return "Your list is now cleared.";
    }
}
