package seedu.duke;


import java.util.ArrayList;

public class Commands {
    protected ReviewList reviewList;

    public Commands(ReviewList reviews) {
        this.reviewList = reviews;
    }

    /**
     * Executes the command and returns the result.
     *
     * @return the output message from the command
     */
    public String execute() {
        throw new UnsupportedOperationException("This method is to be implemented by child classes");
    }

    public void delete(ArrayList<Media> reviewList, String index) {
        int deleteIndex = Integer.parseInt(index) - 1;
        Media deleteMedia = reviewList.get(deleteIndex);

        System.out.println("Noted. I've deleted the following media:" + '\n' + '\t' + deleteMedia.toString());

        reviewList.remove(deleteIndex);
        System.out.println("Now you have " + reviewList.size() + " reviews in the list.");
    }

    public void list(ArrayList<Media> reviewList) {
        System.out.println("Here are the reviews in your list:");
        for (int i = 0; i < reviewList.size(); i++) {
            System.out.println((i + 1) + ". " + reviewList.get(i).toString());
        }
    }

    public void clear(ArrayList<Media> reviewList) {
        reviewList.clear();
        System.out.println("Your list is now cleared.");
    }
}
