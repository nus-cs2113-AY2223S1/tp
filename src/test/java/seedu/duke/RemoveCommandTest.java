package seedu.duke;

import static org.junit.jupiter.api.Assertions.assertEquals;

import commands.RemoveCommand;
import org.junit.jupiter.api.Test;

class RemoveCommandTest {
    static final String CANNOT_FIND = "Unable to find item for specified type and index";
    static final String EXPECTED_MOVIE = "Noted. I've deleted the following media:\n"
            + "\t[Movie] Harry Potter Rating: 5.0  Genre:SciFi  Date watched: 10-01-2020\n"
            + "Now you have 0 reviews in the list.";
    static final String EXPECTED_TV_SHOW = "Noted. I've deleted the following media:\n"
            + "\t[TV Show] Game of Thrones Rating: 5.0  Genre:Fantasy Date watched: 10-01-2020  Site:HBO\n"
            + "Now you have 1 reviews in the list.";
    static final String EXPECTED_MOVIE_MULT = "Noted. I've deleted the following media:\n"
            + "\t[Movie] Star Wars Rating: 5.0  Genre:SciFi  Date watched: 10-02-2020\n"
            + "Now you have 1 reviews in the list.";
    ReviewList storedReviews = new ReviewList();

    @Test
    void removeNotFound() {
        int index = 2;
        Movie harryPotter = new Movie("Harry Potter",5.0, "SciFi","10-01-2020");
        storedReviews.add(harryPotter);
        RemoveCommand command = new RemoveCommand(storedReviews, Movie.class, index);
        assertEquals(CANNOT_FIND, command.execute());
    }

    @Test
    void removeMovie() {
        int index = 1;
        Movie harryPotter = new Movie("Harry Potter",5.0, "SciFi","10-01-2020");
        storedReviews.add(harryPotter);
        RemoveCommand command = new RemoveCommand(storedReviews, Movie.class, index);
        assertEquals(EXPECTED_MOVIE, command.execute());
        assertEquals((new ReviewList()).inputs, storedReviews.inputs);
    }

    @Test
    void removeTvShow() {
        int index = 1;
        Movie harryPotter = new Movie("Harry Potter",5.0, "SciFi","10-01-2020");
        storedReviews.add(harryPotter);
        TvShow gameOfThrones = new TvShow("Game of Thrones",5.0, "Fantasy","10-01-2020", "HBO");
        storedReviews.add(gameOfThrones);
        RemoveCommand command = new RemoveCommand(storedReviews, TvShow.class, index);
        assertEquals(EXPECTED_TV_SHOW, command.execute());
        ReviewList reviewResults = new ReviewList();
        reviewResults.add(harryPotter);
        assertEquals(reviewResults.inputs, storedReviews.inputs);
    }

    @Test
    void removeWithMoreThanTwoReviews() {
        int index = 2;
        Movie harryPotter = new Movie("Harry Potter",5.0, "SciFi","10-01-2020");
        storedReviews.add(harryPotter);
        Movie starWars = new Movie("Star Wars",5.0, "SciFi","10-02-2020");
        storedReviews.add(starWars);
        RemoveCommand command = new RemoveCommand(storedReviews, Movie.class, index);
        assertEquals(EXPECTED_MOVIE_MULT, command.execute());
        ReviewList reviewResults = new ReviewList();
        reviewResults.add(harryPotter);
        assertEquals(reviewResults.inputs, storedReviews.inputs);
    }

}