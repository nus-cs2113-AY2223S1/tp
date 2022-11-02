package commands;

import seedu.duke.Media;
import seedu.duke.ReviewList;

public class AddCommand extends Commands {
    public Media media;

    public AddCommand(ReviewList reviews, Media media) {
        super(reviews);
        this.media = media;
    }

    //@author matthewphua
    /**
     * Adds the media parameter to review list.
     * @return Confirmation String.
     */
    @Override
    public String execute() {
        this.reviewList.add(this.media);
        return "\tGot it. I've added the following item to the list:\n\t" + media.toString()
                + "\n\n\tNow you have " + reviewList.inputs.size() + " reviews in the list.";
    }
}
