package seedu.duke;

import java.util.ArrayList;

public class FavouriteCommand extends Commands {
    String[] userInput;

    public FavouriteCommand(ReviewList reviews, String[] userInput) {
        super(reviews);
        this.userInput = userInput;
    }

    @Override
    public String execute() {
        String index = userInput[1];
        int favouriteIndex;
        String output = "";
        Media media;

        if (!userInput[1].equals("list")) {
            favouriteIndex = Integer.parseInt(index) - 1;
            media = this.reviewList.inputs.get(favouriteIndex);

            if (media.isFavourite() == false) {
                media.setFavourite(true);
                output = "The following task has been starred:\n" + media.toString();
            } else {
                media.setFavourite(false);
                output = "The following task has been unstarred:\n" + media.toString();
            }
        } else {
            output = "Your favourites are:\n";

            for (int i = 0; i < this.reviewList.inputs.size(); i++) {
                media = this.reviewList.inputs.get(i);
                if (media.isFavourite() == true) {
                    output += media.toString() + '\n';
                }
            }
        }

        return output;
    }
}
