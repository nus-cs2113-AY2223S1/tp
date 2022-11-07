package seedu.duke;

import java.util.ArrayList;

public class ReviewList {
    public ArrayList<Media> inputs;

    public ReviewList() {
        inputs = new ArrayList<Media>();
    }

    public ReviewList(ArrayList<Media> inputs) {
        this.inputs = inputs;
    }

    /**
     * Adds inputted media into reviews.
     * @param media Object to be added.
     */
    public void add(Media media) {
        inputs.add(media);
    }

    /**
     * Removes the review from the specified index.
     * @param index index to remove review in list.
     */
    public void remove(int index) {
        inputs.remove(index);
    }

    /**
     * Formats a list output of all reviews.
     * @return Outputted string.
     */
    @Override
    public String toString() {
        String output = "";
        for (int i = 0; i < inputs.size(); i++) {
            output += (i + 1) + ". " + inputs.get(i).toString();
        }
        return output;
    }

}
