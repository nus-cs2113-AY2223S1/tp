package seedu.duke;

import java.util.ArrayList;

public class ClearCommand extends Commands {

    public ClearCommand(ReviewList reviews) {
        super(reviews);
    }

    @Override
    public String execute() {
        reviewList.inputs.clear();
        return "Your list is now cleared.";
    }
}
