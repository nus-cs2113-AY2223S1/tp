package seedu.duke;

import commands.Commands;
import commands.FavouriteCommand;
import commands.ListCommand;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertEquals;


class FavouriteCommandTest {
    ReviewList storedReviews = new ReviewList();

    @Test
    void execute_markFavouriteMovieByExistingIndex_pass() {
        String[] userInput = {"favourite", "movie", "1"};
        String index = "1";
        int favouriteIndex = Integer.parseInt(index) - 1;

        Movie movie1 = new Movie("Harry Potter",99, "SciFi","10-01-2020");
        storedReviews.add(movie1);
        Media assertMovie = storedReviews.inputs.get(favouriteIndex);

        FavouriteCommand favourite = new FavouriteCommand(storedReviews, userInput);
        favourite.execute();
        assertTrue(assertMovie.isFavourite());
    }

    @Test
    void execute_unmarkFavouriteMovieByExistingIndex_pass() {
        String[] userInput = {"favourite", "movie", "1"};
        String index = "1";
        int favouriteIndex = Integer.parseInt(index) - 1;

        Movie movie1 = new Movie("Harry Potter",99, "SciFi","10-01-2020");
        storedReviews.add(movie1);
        Media assertMovie = storedReviews.inputs.get(favouriteIndex);

        FavouriteCommand favourite = new FavouriteCommand(storedReviews, userInput);
        favourite.execute();
        favourite.execute();
        assertFalse(assertMovie.isFavourite());
    }

    @Test
    void execute_listFavouritesCorrectKeyword_pass() {
        Movie movie1 = new Movie("Harry Potter",99, "SciFi","10-01-2020");
        Movie movie2 = new Movie("Harry Potter",50, "SciFi","10-01-2020");
        Movie movie3 = new Movie("Harry Potter",1, "SciFi","10-01-2020");

        storedReviews.add(movie1);
        storedReviews.add(movie2);
        storedReviews.add(movie3);

        Media assertMovie = storedReviews.inputs.get(2);
        assertMovie.setFavourite(true);

        String[] userInput = {"favourite", "list"};
        FavouriteCommand favourite = new FavouriteCommand(storedReviews, userInput);

        String assertOutput = "You have 1 favourite in total.\n" +  "---Here are the reviews---\n\n"
                + "Movies:\n" + "1. [Movie] Harry Potter Rating: 1.0  Genre:SciFi  Date watched: 10-01-2020\n\n"
                + "TV Shows:";

        assertEquals(assertOutput, favourite.execute());
    }
}