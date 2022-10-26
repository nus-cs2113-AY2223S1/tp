package seedu.duke;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class ListCommandTest {
    static final String expectedEmpty = "---Here are the reviews in your list---\n"
            + "\nMovies:\n"
            + "\nTV Shows:";
    static final String expectedMovieList = "---Here are the reviews in your list---\n"
            + "\nMovies:\n"
            + "1. [Movie] Harry Potter Rating:5.0 Genre:SciFi Date watched:10-01-2020\n"
            + "\nTV Shows:";
    static final String expectedFullList = "---Here are the reviews in your list---\n"
            + "\nMovies:\n"
            + "1. [Movie] Harry Potter Rating:5.0 Genre:SciFi Date watched:10-01-2020\n"
            + "\nTV Shows:"
            + "\n1. [TV Show] Game of Thrones Rating:5.0 Genre:Fantasy "
            + "Date watched:10-01-2020 Site: HBO";
    ReviewList storedReviews = new ReviewList();

    @Test
    void emptyList() {
        ListCommand command = new ListCommand(storedReviews);
        assertEquals(expectedEmpty, command.execute());
    }

    @Test
    void fullMovieList() {
        ListCommand command = new ListCommand(storedReviews);
        Movie harryPotter = new Movie("Harry Potter",5.0, "SciFi","10-01-2020");
        storedReviews.add(harryPotter);
        assertEquals(expectedMovieList, command.execute());
    }

    @Test
    void fullList() {
        ListCommand command = new ListCommand(storedReviews);
        Movie harryPotter = new Movie("Harry Potter",5.0, "SciFi","10-01-2020");
        storedReviews.add(harryPotter);
        TvShow gameOfThrones = new TvShow("Game of Thrones",5.0, "Fantasy","10-01-2020", "HBO");
        storedReviews.add(gameOfThrones);
        assertEquals(expectedFullList, command.execute());
    }
}