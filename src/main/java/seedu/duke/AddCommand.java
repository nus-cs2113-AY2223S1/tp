package seedu.duke;

import java.util.Objects;

public class AddCommand extends Commands {
    Media media;

    public AddCommand(ReviewList reviews, Media media) {
        super(reviews);
        this.media = media;
    }

    @Override
    public String execute() {
        this.reviewList.add(this.media);
        return "\tGot it. I've added the following item to the list:\n\t" + media.toString()
                + "\n\n\tNow you have " + reviewList.inputs.size() + " reviews in the list.";
    }
}
