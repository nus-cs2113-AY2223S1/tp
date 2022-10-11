package seedu.duke;

public class AddCommand extends Commands {
    Media media;

    public AddCommand(ReviewList reviews, Media media) {
        super(reviews);
        this.media = media;
    }

    @Override
    public String execute() {
        this.reviewList.add(this.media);
        return "Noted. I've added the following media:" + '\n' + '\t' + media.toString()
                + "\nNow you have " + reviewList.inputs.size() + " reviews in the list.";
    }

}
