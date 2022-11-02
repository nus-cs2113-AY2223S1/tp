package commands;

import exceptions.DukeException;
import seedu.duke.Media;
import seedu.duke.Movie;
import seedu.duke.ReviewList;
import seedu.duke.TvShow;
import seedu.duke.Ui;

import java.util.logging.Level;

public class FavouriteCommand extends Commands {
    String[] userInput;

    public FavouriteCommand(ReviewList reviews, String[] userInput) {
        super(reviews);
        this.userInput = userInput;
    }

    @Override
    public String execute() {
        String output = "";
        Media media;
        int current = 0;

        if (!userInput[1].equals("list")) {
            try {
                String index = userInput[2];
                int favouriteIndex = Integer.parseInt(index);

                if (favouriteIndex <= 0 || favouriteIndex > reviewList.inputs.size()) {
                    return "Unable to find item for specified type and index";
                }

                String typeString = userInput[1];
                Class type;
                if (typeString.equals("movie")) {
                    type = Movie.class;
                } else if (typeString.equals("tv")) {
                    type = TvShow.class;
                } else {
                    throw new DukeException();
                }

                for (int i = 0; i < reviewList.inputs.size(); i++) {
                    media = reviewList.inputs.get(i);
                    if (media.getClass().equals(type)) {
                        current += 1;
                    }
                    if (current == favouriteIndex) {
                        if (!media.isFavourite()) {
                            media.setFavourite(true);
                            output = "The following review has been starred:\n" + media.toString();
                        } else {
                            media.setFavourite(false);
                            output = "The following review has been unstarred:\n" + media.toString();
                        }
                        return output;
                    }
                }
            } catch (DukeException | NumberFormatException e) {
                Ui.print("Incomplete or wrongly formatted command, try again.");
            }

            return "Unable to find item for specified type and index";
        } else {
            ReviewList favouritesList = new ReviewList();
            int count = 0;

            for (int i = 0; i < reviewList.inputs.size(); i++) {
                media = reviewList.inputs.get(i);

                if (media.isFavourite()) {
                    favouritesList.add(media);
                    count += 1;
                }
            }

            if (count == 1) {
                output = "You have " + count + " favourite in total.\n";
            } else {
                output = "You have " + count + " favourites in total.\n";
            }


            Commands listHelper = new ListCommand(favouritesList);
            output += listHelper.execute();
            return output;
        }
    }
}
