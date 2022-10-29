package seedu.duke;

public class RemoveCommand extends Commands {
    private final String cannotFind = "Unable to find item for specified type and index";
    private int index;
    private Class type;

    public RemoveCommand(ReviewList reviews, Class type, int index) {
        super(reviews);
        this.type = type;
        this.index = index;
    }

    //@@author matthewphua

    @Override
    public String execute() {
        int current = 0;
        Media deleteMedia;
        for (int i = 0; i < reviewList.inputs.size(); i++) {
            Media currMedia = reviewList.inputs.get(i);
            if (currMedia.getClass().equals(type)) {
                current += 1;
            }
            if (current == index) {
                deleteMedia = reviewList.inputs.get(i);
                reviewList.remove(i);
                return "Noted. I've deleted the following media:" + '\n' + '\t' + deleteMedia.toString()
                        + "\nNow you have " + reviewList.inputs.size() + " reviews in the list.";
            }
        }
        return cannotFind;
    }

}
