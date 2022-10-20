package seedu.duke;

public class RemoveCommand extends Commands {
    int index;

    public RemoveCommand(ReviewList reviews, int index) {
        super(reviews);
        this.index = index;
    }

    @Override
    public String execute() {
        assert reviewList.inputs.size() != 0 : "Cannot remove from an array of size 0";
        Media deleteMedia = reviewList.inputs.get(index);
        reviewList.remove(index);
        return "Noted. I've deleted the following media:" + '\n' + '\t' + deleteMedia.toString()
                + "\nNow you have " + reviewList.inputs.size() + " reviews in the list.";
    }

}
