package seedu.duke;

public class RemoveCommand extends Commands {
    int index;

    public RemoveCommand(ReviewList reviews, int index) {
        super(reviews);
        this.index = index;
    }

    @Override
    public String execute() {
        Media deleteMedia = reviewList.inputs.get(index);
        reviewList.remove(index);
        return "Noted. I've deleted the following media:" + '\n' + '\t' + deleteMedia.toString()
                + "\nNow you have " + reviewList.inputs.size() + " reviews in the list.";
    }

}
